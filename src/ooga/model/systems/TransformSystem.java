package ooga.model.systems;

import java.util.List;
import ooga.model.Vector;
import ooga.model.objects.GameObject;

/**
 * Update GameObject's position and rotation according to its velocity and angular velocity
 */
public class TransformSystem extends GameObjectBasedSystem {

  public TransformSystem(ECManager ecManager) {
    super(ecManager);
  }

  @Override
  public void update(double deltaTime) {
    List<GameObject> gameObjects = getTrackedGameObjects();

    for (GameObject go : gameObjects) {
      double x = go.getX();
      double y = go.getY();
      Vector v = go.getVelocity();

      go.setX(x + deltaTime * v.getX());
      go.setY(y - deltaTime * v.getY());
    }
  }
}
