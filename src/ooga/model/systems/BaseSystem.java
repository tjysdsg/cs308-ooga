package ooga.model.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import ooga.model.System;
import ooga.model.components.Component;

public abstract class BaseSystem<T extends Component> implements System {

  private Map<String, Consumer<Boolean>> keymaps;
  private List<T> components;

  public BaseSystem(ComponentManager componentManager, Class<T> componentType) {
    keymaps = new HashMap<>();
    components = componentManager.getComponents(componentType);

    init();
  }

  public List<T> getComponents() {
    return components;
  }

  protected void addMapping(String code, Consumer<Boolean> callback) {
    keymaps.put(code, callback);
  }

  public void registerAllInputs(InputManager inputManager) {
    keymaps.forEach(inputManager::registerInput);
  }

  public abstract void init();

  @Override
  public void update() {

  }
}
