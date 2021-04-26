package ooga.model.systems;

import java.util.ArrayList;
import java.util.List;
import ooga.model.StatsInfo;
import ooga.model.actions.CollisionAction;
import ooga.model.annotations.Track;
import ooga.model.components.HealthComponent;
import ooga.model.managers.ECManager;

/**
 * Managing the Health System and the destroy detection.
 */
@Track(HealthComponent.class)
public class HealthSystem extends ComponentBasedSystem {

  protected ComponentMapper<HealthComponent> componentMapper;
  private static final String HEALTH_STATS_NAME = "health";

  public HealthSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(HealthComponent.class);
    addStatsSupplier(HEALTH_STATS_NAME, this::healthStatsSupplier);

    addCollisionMapping("change_health", this::changeHealth);
  }

  private List<StatsInfo> healthStatsSupplier() {
    ArrayList<StatsInfo> ret = new ArrayList<>();
    for (HealthComponent healthComponent : componentMapper.getComponents()) {
      ret.add(new StatsInfo(healthComponent.getHealth() + "", healthComponent.getOwner().getId()));
    }
    return ret;
  }

  private void changeHealth(CollisionAction collisionAction) {
    double delta = Double.parseDouble(collisionAction.getPayload());
    changeHealth(collisionAction.getSelf().getId(), delta, delta >= 0);
  }

  /**
   * Change the health of a game object
   *
   * @param entityId ID of the entity whose health is changed
   * @param delta    Health to add, can be negative
   */
  public void changeHealth(int entityId, double delta, boolean increase) {
    componentMapper.get(entityId).healthIncrement(delta, increase);

    triggerStatsUpdate(HEALTH_STATS_NAME);
  }

  protected void destroyObject(int entityId) {
    getECManager().deleteGameObject(entityId);

    triggerStatsUpdate(HEALTH_STATS_NAME);
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
