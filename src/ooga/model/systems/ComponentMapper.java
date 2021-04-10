package ooga.model.systems;

import java.util.List;
import ooga.model.components.Component;
import ooga.model.objects.GameObject;

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
    GameObject entity = entityManager.getEntity(entityId);
    if (entity == null) {
      return null;
    }

    for (Component component : entity.getComponents()) {
      if (component.getClass() == componentClass) {
        return (T) component;
      }
    }
    return null;
  }

  /**
   * Create a Component with type T for this entity
   *
   * @param entityId ID of the entity where component is created
   * @return the instance of the component
   */
  public T create(int entityId) {
    return componentManager.createComponent(entityManager.getEntity(entityId), componentClass);
  }

  /**
   * Remove component of type T from this entity
   *
   * @param entityId ID of the entity that possess the component
   */
  public void remove(int entityId) {
    GameObject entity = entityManager.getEntity(entityId);
    T comp = get(entityId);
    if (comp != null) {
      componentManager.removeComponent(componentClass, comp.getId());
      entity.removeComponent(comp.getId());
    }
  }

  public List<T> getComponents() {
    // NOTE: putting method call here can make sure that newly added components mid-game are included
    return componentManager.getComponents(componentClass);
  }
}
