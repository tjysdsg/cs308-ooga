package ooga.model.systems;

import java.util.List;
import ooga.model.components.Component;

public class ComponentBasedSystem<T extends Component> extends BaseSystem {

  private List<T> components;

  public ComponentBasedSystem(ComponentManager componentManager, Class<T> componentType) {
    components = componentManager.getComponents(componentType);
  }

  public List<T> getComponents() {
    return components;
  }

  @Override
  public void update(double deltaTime) {
  }
}
