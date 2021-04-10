package ooga.model.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.components.Component;
import ooga.model.objects.GameObject;

/**
 * System for creating, accessing, updating, and deleting entities
 */
public class EntityManager {

  private IDManager idManager;
  private Map<Integer, GameObject> entities;
  private ComponentManager componentManager;

  public EntityManager(ComponentManager componentManager) {
    idManager = new IDManager();
    entities = new HashMap<>();
    this.componentManager = componentManager;
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

  public GameObject getEntity(int ID) {
    return entities.get(ID);
  }

  /**
   * Delete entity from EntityManager, and remove the components it has from ComponentManager
   */
  public void deleteGameObject(int ID) {
    // FIXME: how to invalidate the references to the removed GameObject and Components?
    GameObject entity = entities.get(ID);

    // remove all of its components
    for (Component component : entity.getComponents()) {
      componentManager.removeComponent(component.getClass(), component.getId());
    }

    entities.remove(ID);
  }

}
