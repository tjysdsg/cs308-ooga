package ooga.model.systems;

import java.util.List;
import ooga.model.annotations.Track;
import ooga.model.components.HealthComponent;
import ooga.model.objects.GameObject;

/**
 * Managing the Health System and the destroy detection.
 */
@Track(HealthComponent.class)
public class HealthSystem extends ComponentBasedSystem {

  ComponentMapper<HealthComponent> componentMapper;

  public HealthSystem(EntityManager entityManager, ComponentManager componentManager) {
    super(entityManager, componentManager);
    componentMapper = getComponentMapper(HealthComponent.class);
  }

  // TODO: How to access the Game Object Health
  public void healthChange(int ID, int health) {
    // gameObjects.setHealth(gameObjects.getHealt()-health);
  }

  protected void checkHealthDeath(GameObject gameObject) {
    // TODO:set the boundary health for each game
    // if(gameObject.getHealth()<=0){
    //// TODO: Where to put the break of the gameobjects?
    // }
  }

  public void destroyDetection() {
    for (HealthComponent healthComponent : componentMapper.getComponents()) {
    }
  }

  @Override
  public void update(double deltaTime) {

  }
}
