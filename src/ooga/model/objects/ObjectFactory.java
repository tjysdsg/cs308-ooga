package ooga.model.objects;

import com.google.common.base.Preconditions;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Map;
import ooga.model.exceptions.TypeNotFoundException;

public class ObjectFactory {

    private Map<String, GameObject> presetMap;
    private final JsonAdapter<GameObject> objectAdapter;
    public ObjectFactory(Map<String, GameObject> presetMap) {
        this.presetMap = Preconditions.checkNotNull(presetMap, "Preset Map shouldn't be null");
        Moshi moshi = new Moshi.Builder().build();
        this.objectAdapter = moshi.adapter(GameObject.class);
    }

    public GameObject buildObject(ObjectInstance instance) {
        String name = instance.getName();
        GameObject toClone = presetMap.get(name);
        if (toClone == null) {
            throw new TypeNotFoundException(name);
        }
        String serializedObject = objectAdapter.toJson(toClone);

        GameObject clone;
        try {
            clone = objectAdapter.fromJson(serializedObject);
            if (clone == null) {
                throw new RuntimeException(String.format("DEBUG: Serialized Object was Invalid\n%s", serializedObject));
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("DEBUG: Serialized Object was Invalid\n%s", serializedObject));
        }

        clone.setX(instance.getX());
        clone.setY(instance.getY());

        return clone;
    }
}
