package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import ooga.model.System;

public abstract class BaseSystem implements System {

  private Map<String, Consumer<Boolean>> keymaps;

  public BaseSystem() {
    keymaps = new HashMap<>();

    init();
  }

  protected void addMapping(String code, Consumer<Boolean> callback) {
    keymaps.put(code, callback);
  }

  public void registerAllInputs(InputSystem inputSystem) {
    keymaps.forEach(inputSystem::registerInput);
  }

  public abstract void init();

  @Override
  public void update() {

  }
}
