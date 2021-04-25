package ooga.model.systems;

import java.util.List;
import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;

public abstract class GameObjectBasedSystem extends BaseSystem {

  ECManager ecManager;

  public GameObjectBasedSystem(ECManager entityManager) {
    this.ecManager = entityManager;
  }

  protected List<GameObject> getTrackedGameObjects() {
    return ecManager.getEntities();
  }
}
