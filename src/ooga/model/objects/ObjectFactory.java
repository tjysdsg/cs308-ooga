package ooga.model.objects;

import com.google.common.base.Preconditions;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import java.io.IOException;
import java.util.Map;
import ooga.model.LevelFactory;
import ooga.model.Vector;
import ooga.model.components.Component;
import ooga.model.exceptions.TypeNotFoundException;
import ooga.model.managers.IDManager;

public class ObjectFactory {

  private final JsonAdapter<GameObject> objectAdapter;
  private final String ID_SETTER = "(?<=\"id\":).d?";
  private IDManager idManager = new IDManager();
  private Map<String, GameObject> presetMap;

  public ObjectFactory(Map<String, GameObject> presetMap) {
    this.presetMap = Preconditions.checkNotNull(presetMap, "Preset Map shouldn't be null");
    PolymorphicJsonAdapterFactory<Component> componentAdapter = LevelFactory
        .createComponentAdapter();
    Moshi moshi = new Moshi.Builder().add(componentAdapter).build();
    this.objectAdapter = moshi.adapter(GameObject.class);
  }

  public GameObject buildObject(ObjectInstance instance) {
    String name = instance.getName();
    GameObject toClone = presetMap.get(name);
    if (toClone == null) {
      throw new TypeNotFoundException(name);
    }

    String serializedObject = objectAdapter.toJson(toClone);
    serializedObject = serializedObject.replaceAll(ID_SETTER, String.valueOf(idManager.getNewId()));

    GameObject clone;
    try {
      clone = objectAdapter.fromJson(serializedObject);
      if (clone == null) {
        throw new RuntimeException(
            String.format("DEBUG: Serialized Object was Invalid\n%s", serializedObject));
      }
    } catch (IOException e) {
      throw new RuntimeException(
          String.format("DEBUG: Serialized Object was Invalid\n%s", serializedObject));
    }

    clone.setX(instance.getX());
    clone.setY(instance.getY());

    Vector velocityTest = instance.getVelocity();
    if (velocityTest != null) {
      clone.setVelocity(velocityTest);
    }

    return clone;
  }
}
