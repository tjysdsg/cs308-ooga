package ooga.model.systems;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.components.Component;
import ooga.model.objects.GameObject;

/**
 * System for creating, accessing, updating, and deleting components
 */
public class ComponentManager {

  private IDManager idManager;
  private Map<Class, Map<Integer, Component>> existingComponents;

  public ComponentManager() {
    idManager = new IDManager();
    existingComponents = new HashMap<>();
  }

  public <T> List<T> getComponents(Class<T> componentClass) {
    Map<Integer, Component> components = existingComponents.get(componentClass);
    if (components == null) {
      return new ArrayList<T>();
    }
    return (List<T>) new ArrayList<>(components.values());
  }

  public void registerExistingComponent(GameObject owner, Component component) {
    component.setOwner(owner);
    component.setId(idManager.getNewId());
    addComponentToMap(component);
  }

  /**
   * @apiNote This doesn't remove the component from its parent's component list
   */
  public <T> void removeComponent(Class<T> componentType, int id) {
    Map<Integer, Component> idCompMap = existingComponents.get(componentType);
    if (idCompMap != null) {
      idCompMap.remove(id);
    } else {
      // TODO: log warning
    }
  }

  private void addComponentToMap(Component component) {
    int id = component.getId();
    Map<Integer, Component> idCompMap = existingComponents.get(component.getClass());
    if (idCompMap != null) {
      idCompMap.put(id, component);
    } else {
      idCompMap = new HashMap<>();
      idCompMap.put(id, component);
      existingComponents.put(component.getClass(), idCompMap);
    }
  }

  public void registerExistingComponents(List<GameObject> gameObjects) {
    for (GameObject go : gameObjects) {
      for (Component comp : go.getComponents()) {
        registerExistingComponent(go, comp);
      }
    }
  }

  public <T extends Component> T createComponent(
      GameObject owner, Class<T> componentClass
  ) {
    // TODO: log errors
    T ret = null;
    try {
      var constructor = componentClass.getConstructor(int.class, GameObject.class);
      ret = constructor.newInstance(idManager.getNewId(), owner);
      owner.addComponent(ret);
      addComponentToMap(ret);
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
