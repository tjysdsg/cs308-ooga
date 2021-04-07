package ooga.model.systems;

import java.util.List;
import ooga.model.objects.GameObject;

public class GameObjectBasedSystem extends BaseSystem {

  private List<GameObject> gameObjects;

  public GameObjectBasedSystem(EntityManager entityManager) {
    gameObjects = entityManager.getEntities();
  }

  @Override
  public void update(double deltaTime) {
  }
}
