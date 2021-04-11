package ooga.model.systems;

import java.util.List;
import ooga.model.objects.GameObject;

public abstract class GameObjectBasedSystem extends BaseSystem {

  EntityManager entityManager;

  public GameObjectBasedSystem(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  protected List<GameObject> getTrackedGameObjects() {
    return entityManager.getEntities();
  }
}
