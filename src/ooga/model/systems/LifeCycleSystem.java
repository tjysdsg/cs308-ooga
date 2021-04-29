package ooga.model.systems;

import java.util.Map;
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
public class LifeCycleSystem extends GameObjectBasedSystem {

  public LifeCycleSystem(ECManager entityManager) {
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

  private void spawnObject(GameObject self, Map<String, String> payload) {
    String which = payload.get("which");
    double x = self.getX() + Double.parseDouble(payload.getOrDefault("offsetX", "0"));
    double y = self.getY() + Double.parseDouble(payload.getOrDefault("offsetY", "0"));
    double vx = Double.parseDouble(payload.getOrDefault("velocityX", "0"));
    double vy = Double.parseDouble(payload.getOrDefault("velocityY", "0"));
    Vector v = new Vector(vx, vy);
    System.out.println("Creating object " + which + "at" + x + ", " + y + " with velocity " + v);
    ecManager.addEntity(new ObjectInstance(which, x, y, v));
  }

  private void destroyObject(int entityId) {
    // System.out.println("Deleting object " + entityId);
    ecManager.deleteGameObject(entityId);
  }

  @Override
  public void update(double deltaTime) {
  }
}
