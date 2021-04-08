package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import ooga.model.util.CollisionAction;

public abstract class BaseSystem {

  private Map<String, Consumer<Boolean>> keymaps;

  private Map<String, Consumer<CollisionAction>> actionMaps;

  public BaseSystem() {
    keymaps = new HashMap<>();
    actionMaps = new HashMap<>();
  }

  protected void addMapping(String code, Consumer<Boolean> callback) {
    keymaps.put(code, callback);
  }

  //TODO: Change BiConsumer to take in single CollisionEvent object.
  protected void addCollisionMapping(String code, Consumer<CollisionAction> callback) {
    actionMaps.put(code, callback);
  }

  public void registerAllInputs(InputManager inputManager) {
    keymaps.forEach(inputManager::registerInput);
  }

  public void registerAllActions(ActionManager actionManager) {
    actionMaps.forEach(actionManager::registerAction);
  }

  public abstract void update(double deltaTime);
}
