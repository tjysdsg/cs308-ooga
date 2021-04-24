package ooga.model.systems;

import java.util.Arrays;
import java.util.List;
import ooga.model.Vector;
import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectInstance;

/**
 * Provide two collision actions:
 *
 * <ol>
 *   <li>
 *     'spawn_object': spawn an object at current position. MUST specify payload with the format
 *     'name,vx,vy'
 *   </li>
 *   <li>'destroy_object': will destroy itself</li>
 * </ol>
 */
public class LifeCircleSystem extends GameObjectBasedSystem {

  public LifeCircleSystem(ECManager entityManager) {
    super(entityManager);
    addCollisionMapping(
        "spawn_object",
        event -> spawnObject(event.getSelf(), event.getPayload())
    );

    addCollisionMapping(
        "destroy_object",
        event -> destroyObject(event.getSelf().getId())
    );
  }

  private void spawnObject(GameObject self, String payload) {
    List<String> tokens = Arrays.asList(payload.split(","));
    String name = tokens.get(0);
    double x = self.getX();
    double y = self.getY();
    Vector v = new Vector(Double.parseDouble(tokens.get(1)), Double.parseDouble(tokens.get(2)));
    System.out.println("Creating object " + name + "at" + x + ", " + y + " with velocity " + v);
    ecManager.addEntity(new ObjectInstance(name, x, y, v));
  }

  private void destroyObject(int entityId) {
    // System.out.println("Deleting object " + entityId);
    ecManager.deleteGameObject(entityId);
  }

  @Override
  public void update(double deltaTime) {
  }
}
