package ooga.model.objects;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Map;

public class ObjectFactory {

    private Map<String, GameObject> presetMap;

    public ObjectFactory(Map<String, GameObject> presetMap) {
        this.presetMap = presetMap;
    }

    public GameObject buildObject(ObjectInstance instance) {

        String name = instance.getType();

        GameObject toClone = presetMap.get(name);

        if (toClone == null) {
            throw new RuntimeException(String.format("The type %s was not defined prior to its use.", name));
        }

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<GameObject> objectAdapter = moshi.adapter(GameObject.class);

        String serializedObject = objectAdapter.toJson(toClone);

        GameObject clone;
        try {
            clone = objectAdapter.fromJson(serializedObject);
        } catch (IOException e) {
            throw new RuntimeException(String.format("DEBUG: Serialized Object was Invalid\n%s", serializedObject));
        }

        clone.setX(instance.getX());
        clone.setY(instance.getY());

        return clone;
    }
}
