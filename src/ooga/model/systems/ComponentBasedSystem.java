package ooga.model.systems;

import java.util.List;
import ooga.model.components.Component;

public class ComponentBasedSystem<T extends Component> extends BaseSystem {

  private ComponentManager componentManager;
  private Class componentType;

  public ComponentBasedSystem(ComponentManager componentManager, Class<T> componentType) {
    this.componentManager = componentManager;
    this.componentType = componentType;
  }

  public List<T> getComponents() {
    // NOTE: putting method call here can make sure that newly added components mid-game are included
    return componentManager.getComponents(componentType);
  }

  @Override
  public void update(double deltaTime) {
  }
}
