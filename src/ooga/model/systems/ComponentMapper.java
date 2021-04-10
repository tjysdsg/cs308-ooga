package ooga.model.systems;

import java.util.List;
import ooga.model.components.Component;

// TODO: implement methods
public class ComponentMapper<T extends Component> {

  private EntityManager entityManager;
  private ComponentManager componentManager;
  private Class<T> componentClass;

  public ComponentMapper(
      EntityManager entityManager, ComponentManager componentManager, Class<T> componentClass
  ) {
    this.entityManager = entityManager;
    this.componentManager = componentManager;
    this.componentClass = componentClass;
  }

  /**
   * Get the component of type T from this entity
   *
   * @param entityId ID of the entity that possess the component
   */
  public T get(int entityId) {
    return null;
  }

  /**
   * Create a Component with type T for this entity
   *
   * @param entityId ID of the entity where component is created
   * @return the instance of the component
   */
  public T create(int entityId) {
    return null;
  }

  /**
   * Remove component of type T from this entity
   *
   * @param entityId ID of the entity that possess the component
   */
  public void remove(int entityId) {
  }

  public List<T> getComponents() {
    // NOTE: putting method call here can make sure that newly added components mid-game are included
    return componentManager.getComponents(componentClass);
  }
}
