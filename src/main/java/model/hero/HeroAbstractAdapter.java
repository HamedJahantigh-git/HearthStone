package model.hero;

import com.google.gson.*;

import javax.swing.text.AbstractDocument;
import java.lang.reflect.Type;

public class HeroAbstractAdapter implements JsonSerializer<Hero>, JsonDeserializer<Hero> {

    @Override
    public Hero deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        String className = jsonObj.get(Hero.class.getName()).getAsString();
        try {
            Class<?> clz = Class.forName(className);
            return jsonDeserializationContext.deserialize(jsonElement, clz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }


    @Override
    public JsonElement serialize(Hero object, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonEle = jsonSerializationContext.serialize(object, object.getClass());
        jsonEle.getAsJsonObject().addProperty(Hero.class.getName(),
                object.getClass().getCanonicalName());
        return jsonEle;
    }
}
