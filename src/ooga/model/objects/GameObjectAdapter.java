package ooga.model.objects;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class GameObjectAdapter {

    private ObjectFactory objectFactory;

    public GameObjectAdapter(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @FromJson
    GameObject objectFromJson(ObjectInstance instance) {
        GameObject object = objectFactory.buildObject(instance);
        return object;
    }

    @ToJson
    ObjectInstance objectToJson(GameObject object) {
        ObjectInstance json = new ObjectInstance(object.getName(),
                object.getX(), object.getY());
        return json;
    }
}
