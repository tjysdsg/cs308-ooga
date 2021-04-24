package ooga.model.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import ooga.model.StatsInfo;
import ooga.model.actions.CollisionAction;
import ooga.model.managers.ActionManager;
import ooga.model.managers.InputManager;
import ooga.model.managers.StatsManager;
import ooga.model.managers.SystemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseSystem {

  private static final Logger logger = LogManager.getLogger(BaseSystem.class);

  private Map<String, Consumer<Boolean>> keymaps;
  private Map<String, Consumer<CollisionAction>> actionMaps;
  private Map<String, Supplier<List<StatsInfo>>> statsSuppliers;

  /**
   * Used to trigger the update of a stats.
   * <p>
   * Optional since some systems don't need to track stats
   */
  private StatsManager statsManager;

  private SystemManager systemManager;

  public BaseSystem() {
    keymaps = new HashMap<>();
    actionMaps = new HashMap<>();
    statsSuppliers = new HashMap<>();
  }

  public void setSystemManager(SystemManager systemManager) {
    this.systemManager = systemManager;
  }

  protected <T extends BaseSystem> T getSystem(Class<? extends BaseSystem> systemClass) {
    if (systemManager == null) {
      logger.error(
          "Cannot get system with type {} because this.systemManager is null", systemClass
      );
      return null;
    }
    return (T) systemManager.getSystem(systemClass);
  }

  protected void addMapping(String code, Consumer<Boolean> callback) {
    keymaps.put(code, callback);
  }

  protected void addCollisionMapping(String code, Consumer<CollisionAction> callback) {
    actionMaps.put(code, callback);
  }

  protected void addStatsSupplier(String stats, Supplier<List<StatsInfo>> supplier) {
    statsSuppliers.put(stats, supplier);
  }

  public void registerAllInputs(InputManager inputManager) {
    keymaps.forEach(inputManager::registerInput);
  }

  public void registerAllActions(ActionManager actionManager) {
    actionMaps.forEach(actionManager::registerAction);
  }

  public void registerAllStats(StatsManager statsManager) {
    this.statsManager = statsManager;
    statsSuppliers.forEach(statsManager::registerStatsSupplier);
  }

  /**
   * Trigger the update of a stats, intended to be called in subclasses
   *
   * @param stats Stats name
   */
  protected void triggerStatsUpdate(String stats) {
    if (statsManager != null) {
      statsManager.updateStats(stats);
    } else {
      // TODO: warn if statsManager is null (registerAllStats is not called)
    }
  }

  public abstract void update(double deltaTime);
}
