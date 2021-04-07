package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import ooga.model.System;

public abstract class BaseSystem implements System {

  private Map<String, Consumer<Boolean>> keymaps;

  public BaseSystem() {
    keymaps = new HashMap<>();
  }

  protected void addMapping(String code, Consumer<Boolean> callback) {
    keymaps.put(code, callback);
  }

  public void registerAllInputs(InputManager inputManager) {
    keymaps.forEach(inputManager::registerInput);
  }

  public abstract void update(double deltaTime);
}
