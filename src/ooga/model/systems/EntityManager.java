package ooga.model.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectFactory;
import ooga.model.objects.ObjectInstance;

/**
 * System for creating, accessing, updating, and deleting entities
 */
public class EntityManager {

  private IDManager idManager;
  private Map<Integer, GameObject> entities;
  private ObjectFactory factory;

  public EntityManager(ObjectFactory factory) {
    this.factory = factory;
    idManager = new IDManager();
    entities = new HashMap<>();
  }

  public List<GameObject> getEntities() {
    return new ArrayList<GameObject>(entities.values());
  }

  public GameObject createEntity(String name) {
    int id = idManager.getNewId();
   GameObject ret = new GameObject(id, name);

    entities.put(id, ret);
    return ret;
  }
  public GameObject getEntity(int ID){
    return entities.get(ID);
  }
  public void deleteGameObject(int ID){
    entities.remove(ID);
  }

  public void addEntity(ObjectInstance instance) {
    GameObject newObject = factory.buildObject(instance);
    entities.put(newObject.getId(), newObject);
  }
}
