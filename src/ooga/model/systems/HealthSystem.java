package ooga.model.systems;

import java.util.List;
import ooga.model.components.HealthComponent;
import ooga.model.objects.GameObject;

/**
 * Managing the Health System and the destroy detection.
 */
public class HealthSystem extends ComponentBasedSystem<HealthComponent> {

  public HealthSystem(
      ComponentManager componentManager, Class<HealthComponent> componentType
  ) {
    super(componentManager, componentType);
  }

  // TODO: How to access the Game Object Health
  public void healthChange(int ID, int health) {
    // gameObjects.setHealth(gameObjects.getHealt()-health);
  }

  protected void singleObjectDestroy(GameObject gameObject) {
    // TODO:set the boundary health for each game
    // if(gameObject.getHealth()<=0){
    //// TODO: Where to put the break of the gameobjects?
    // }
  }

  public void destroyDetection() {
    for (GameObject gameObject : getTrackedGameObjects()) {
      singleObjectDestroy(gameObject);
    }
  }
}
