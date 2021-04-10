package ooga.model.systems;

import ooga.model.annotations.Track;
import ooga.model.components.HealthComponent;

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

  /**
   * Change the health of a game object
   *
   * @param entityId ID of the entity whose health is changed
   * @param delta    Health to add, can be negative
   */
  public void changeHealth(int entityId, double delta) {
    componentMapper.get(entityId).increment(delta);
  }

  protected void destroyObject(int entityId) {
    getEntityManager().deleteGameObject(entityId);
  }

  public void destroyDetection() {
    for (HealthComponent healthComponent : componentMapper.getComponents()) {
      if (healthComponent.getHealth() <= 0) {
        destroyObject(healthComponent.getOwner().getId());
      }
    }
  }

  @Override
  public void update(double deltaTime) {
    destroyDetection();
  }
}
