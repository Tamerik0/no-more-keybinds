package tamerlan.fabric.data;

import com.google.gson.*;
import tamerlan.fabric.NMKClient;

import java.lang.reflect.Type;

public class GsonAdapter implements JsonSerializer<GsonSerializable>, JsonDeserializer<GsonSerializable>, InstanceCreator<GsonSerializable> {

    @Override
    public GsonSerializable createInstance(Type type) {
        return new GsonSerializable();
    }

    @Override
    public GsonSerializable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var obj = new GsonSerializable<>();
        try {
            obj.obj = new Gson().fromJson(json.getAsJsonObject().get("object"), Class.forName(json.getAsJsonObject().get("type").getAsString()));
        } catch (Exception e) {
            NMKClient.LOGGER.error(e.getMessage());
        }
        return obj;
    }

    @Override
    public JsonElement serialize(GsonSerializable src, Type typeOfSrc, JsonSerializationContext context) {
        var json = new JsonObject();
        json.addProperty("type", src.obj.getClass().getName());
        json.add("object", new Gson().toJsonTree(src.obj, src.getClass()));
        return json;
    }

    static {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(GsonSerializable.class, new GsonAdapter());
    }
}
