package ooga.model.systems;

import java.util.ArrayList;
import java.util.List;
import ooga.model.StatsInfo;
import ooga.model.annotations.Track;
import ooga.model.components.HealthComponent;
import ooga.model.managers.ECManager;

/**
 * Managing the Health System and the destroy detection.
 */
@Track(HealthComponent.class)
public class HealthSystem extends ComponentBasedSystem {

  private static final String HEALTH_STATS_NAME = "health";
  protected ComponentMapper<HealthComponent> componentMapper;

  public HealthSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(HealthComponent.class);
    addStatsSupplier(HEALTH_STATS_NAME, this::healthStatsSupplier);
  }

  private List<StatsInfo> healthStatsSupplier() {
    ArrayList<StatsInfo> ret = new ArrayList<>();
    for (HealthComponent healthComponent : componentMapper.getComponents()) {
      ret.add(new StatsInfo(healthComponent.getHealth() + "", healthComponent.getOwner().getId()));
    }
    return ret;
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
