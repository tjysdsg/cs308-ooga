package ooga.model.systems;

import ooga.model.GameObject;

/**
 * System for creating, accessing, updating, and deleting entities
 */
public class EntityManager {

  private IDManager idManager;

  public EntityManager() {
    idManager = new IDManager();
  }

  public GameObject createEntity(String name) {
    return new GameObject(idManager.getNewId(), name);
  }
}
