package ooga.model.systems;

import ooga.model.GameObject;
import ooga.model.components.Component;

/**
 * System for creating, accessing, updating, and deleting components
 */
public class ComponentManager extends BaseSystem {

  private IDManager idManager;

  @Override
  public void init() {
    idManager = new IDManager();
  }

  @Override
  public void update() {
  }

  public <T extends Component> T createComponent(GameObject owner, Class<T> componentClass) {
    return null;
  }
}
