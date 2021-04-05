package ooga.model.systems;

import java.lang.reflect.InvocationTargetException;
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

  public <T extends Component> T createComponent(
      GameObject owner, Class<T> componentClass
  ) {
    // TODO: log errors
    T ret = null;
    try {
      var constructor = componentClass.getConstructor(int.class, GameObject.class);
      ret = constructor.newInstance(idManager.getNewId(), owner);
    } catch (NoSuchMethodException e) {
      System.out.println(
          "Cannot find a valid constructor in component class: " + componentClass.getName()
      );
    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
      System.out.println(
          "Failed to instantiate component class: " + componentClass.getName()
      );
    }
    return ret;
  }
}
