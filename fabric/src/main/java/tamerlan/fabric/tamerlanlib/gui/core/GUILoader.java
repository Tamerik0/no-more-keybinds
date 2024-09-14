package tamerlan.fabric.tamerlanlib.gui.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tamerlan.fabric.tamerlanlib.events.InputHandler;

import java.util.HashMap;

public abstract class GUILoader<T> {
    static HashMap<String, GUILoader> loaders;

    protected ChildLoader getChildLoader(T obj) {
        if (obj instanceof GUIContainerProvider || obj instanceof InputHandlerProvider) {
            return new DefaultChildLoader(obj);
        }
        return null;
    }

    public interface ChildLoader {
        void load(JsonElement json);
    }

    static class DefaultChildLoader implements ChildLoader {
        GUIContainer container;
        InputHandler inputHandler;
        public DefaultChildLoader(Object obj) {
            if(obj instanceof GUIContainerProvider castedObj)
                container = castedObj.getContainer();
            if(obj instanceof InputHandlerProvider castedObj)
                inputHandler = castedObj.getInputHandler();
        }

        @Override
        public void load(JsonElement json) {
            var obj = GUILoader.staticLoad(json.getAsJsonObject());
            if(container!=null) {
                if(obj instanceof IRenderable castedObj)
                    container.addRenderable(castedObj);
            }
            if(inputHandler != null){
                if(obj instanceof InputHandler castedObj)
                    inputHandler.addListener(castedObj);
                if(obj instanceof InputHandlerProvider castedObj)
                    inputHandler.addListener(castedObj);
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

    public static Object staticLoad(JsonObject json) {
        return loaders.get(json.get("type").getAsString()).load(json.get("obj"));
    }
}
