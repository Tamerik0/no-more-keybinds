package tamerlan.fabric.gui.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tamerlan.fabric.gui.inputevents.InputHandler;

import java.util.HashMap;

public abstract class GUILoader<T extends IRenderable> {
    static HashMap<String, GUILoader> loaders;

    protected ChildLoader getChildLoader(T obj) {
        if (obj instanceof ContainerBasedRenderable || obj instanceof InputHandlerBased) {
            return new DefaultChildLoader(obj);
        }
        return null;
    }

    public abstract static class ChildLoader {
        public abstract void load(JsonElement json);
    }

    static class DefaultChildLoader extends ChildLoader {
        GUIContainer container;
        InputHandler inputHandler;
        public DefaultChildLoader(Object obj) {
            if(obj instanceof ContainerBasedRenderable castedObj)
                container = castedObj.getContainer();
            if(obj instanceof InputHandlerBased castedObj)
                inputHandler = castedObj.getInputHandler();
        }

        @Override
        public void load(JsonElement json) {
            var obj = json.getAsJsonObject();
            if(container!=null)
                container.addRenderable(GUILoader.staticLoad(json.getAsJsonObject()));
            if(inputHandler != null){
                inputHandler.addListener(obj);
            }
        }
    }

    public abstract T loadBase(JsonElement json);

    public T load(JsonElement json) {
        var obj = loadBase(json);
        var childLoader = getChildLoader(obj);
        if (childLoader != null) {
            for (var i : json.getAsJsonObject().get("children").getAsJsonArray()) {
                childLoader.load(i);
            }
        }
        return obj;
    }

    public static IRenderable staticLoad(JsonObject json) {
        return loaders.get(json.get("type").getAsString()).load(json.get("obj"));
    }
}
