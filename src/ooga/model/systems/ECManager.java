package ooga.model.systems;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.components.Component;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectFactory;
import ooga.model.objects.ObjectInstance;

/**
 * System for creating, accessing, updating, and deleting entities/components.
 */
public class ECManager {

  private IDManager idManager;
  private Map<Integer, GameObject> entities;
  private Map<Class, Map<Integer, Component>> existingComponents;
  private ObjectFactory factory;

  public ECManager(ObjectFactory factory) {
    this.factory = factory;
    idManager = new IDManager();
    entities = new HashMap<>();
    existingComponents = new HashMap<>();
  }

  public List<GameObject> getEntities() {
    return new ArrayList<>(entities.values());
  }

  public GameObject createEntity(String name) {
    int id = idManager.getNewId();
    GameObject ret = new GameObject(id, name);
    entities.put(id, ret);
    return ret;
  }

  public GameObject getEntity(int ID) {
    return entities.get(ID);
  }

  /**
   * Delete entity from EntityManager, and remove the components it has from ComponentManager
   */
  public void deleteGameObject(int ID) {
    // FIXME: how to invalidate the references to the removed GameObject and Components?
    GameObject entity = entities.get(ID);

    // remove all of its components
    for (Component component : entity.getComponents()) {
      removeComponent(component.getClass(), component.getId());
    }

    entities.remove(ID);
  }

  public void addEntity(ObjectInstance instance) {
    GameObject newObject = factory.buildObject(instance);
    entities.put(newObject.getId(), newObject);
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
