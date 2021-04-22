package ooga.model.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import ooga.model.StatsInfo;
import ooga.model.actions.CollisionAction;

public abstract class BaseSystem {

  private Map<String, Consumer<Boolean>> keymaps;
  private Map<String, Consumer<CollisionAction>> actionMaps;
  private Map<String, Supplier<List<StatsInfo>>> statsSuppliers;

  public BaseSystem() {
    keymaps = new HashMap<>();
    actionMaps = new HashMap<>();
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
    statsSuppliers.forEach(statsManager::registerStatsSupplier);
  }

  public abstract void update(double deltaTime);
}
