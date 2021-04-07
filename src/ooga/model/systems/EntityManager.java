package ooga.model.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.objects.GameObject;

/**
 * System for creating, accessing, updating, and deleting entities
 */
public class EntityManager {

  private IDManager idManager;
  private Map<Integer, GameObject> entities;

  public EntityManager() {
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
}
