package ooga.model.systems;

import java.util.List;
import ooga.model.objects.GameObject;

public abstract class GameObjectBasedSystem extends BaseSystem {

  private List<GameObject> gameObjects;

  public GameObjectBasedSystem(EntityManager entityManager) {
    gameObjects = entityManager.getEntities();
  }

  protected List<GameObject> getTrackedGameObjects() {
    return gameObjects;
  }
}
