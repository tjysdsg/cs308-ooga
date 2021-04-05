package ooga.model.systems;

import ooga.model.GameObject;

/**
 * System for creating, accessing, updating, and deleting entities
 */
public class EntityManager extends BaseSystem {

  private IDManager idManager;

  @Override
  public void init() {
    idManager = new IDManager();
  }

  @Override
  public void update() {
  }

  public GameObject createEntity(String name) {
    return new GameObject(idManager.getNewId(), name);
  }
}
