package model.infoPassive;

import com.google.gson.*;

import java.lang.reflect.Type;

public class InfoPassiveAbstractAdapter implements JsonSerializer<InfoPassive>, JsonDeserializer<InfoPassive> {

    @Override
    public InfoPassive deserialize(JsonElement jsonElement, Type type,
                            JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        String className = jsonObj.get(InfoPassive.class.getName()).getAsString();
        try {
            Class<?> clz = Class.forName(className);
            return jsonDeserializationContext.deserialize(jsonElement, clz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }


    @Override
    public JsonElement serialize(InfoPassive object, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonEle = jsonSerializationContext.serialize(object, object.getClass());
        jsonEle.getAsJsonObject().addProperty(InfoPassive.class.getName(),
                object.getClass().getCanonicalName());
        return jsonEle;
    }
}
