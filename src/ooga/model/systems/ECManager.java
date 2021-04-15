package ooga.model.systems;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import ooga.model.Configuration;
import ooga.model.components.Component;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectFactory;
import ooga.model.objects.ObjectInstance;
import ooga.model.observables.ObservableObject;

/**
 * System for creating, accessing, updating, and deleting entities/components.
 */
public class ECManager {

  private IDManager idManager;
  private Map<Integer, GameObject> entities;
  private Map<String, Map<Integer, Component>> existingComponents;
  private ObjectFactory factory;
  private Consumer<ObservableObject> newObjectCallback;

  public ECManager(ObjectFactory factory, Consumer<ObservableObject> newObjectCallback) {
    this.factory = factory;
    idManager = new IDManager();
    entities = new HashMap<>();
    existingComponents = new HashMap<>();
    this.newObjectCallback = newObjectCallback;
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
    notifyNewObject(newObject);
  }

  private void notifyNewObject(GameObject newObject) {
    if (newObjectCallback != null) {
      newObjectCallback.accept(newObject);
    }
  }

  public <T> List<T> getComponents(Class<T> componentClass) {
    Map<Integer, Component> components = existingComponents.get(componentClass.getName());
    if (components == null) {
      return new ArrayList<T>();
    }
    return (List<T>) new ArrayList<>(components.values());
  }

  public <T extends Component> void registerExistingComponent(GameObject owner, T component) {
    component.setOwner(owner);
    component.setId(idManager.getNewId());
    addComponentToMap(component);
  }

  /**
   * @apiNote This doesn't remove the component from its parent's component list
   */
  public <T> void removeComponent(Class<T> componentType, int id) {
    Map<Integer, Component> idCompMap = existingComponents.get(componentType.getName());
    if (idCompMap != null) {
      idCompMap.remove(id);
    } else {
      // TODO: log warning
    }
  }

  private <T extends Component> void addComponentToMap(T component) {
    int id = component.getId();
    Map<Integer, Component> idCompMap = existingComponents.get(component.typeUnerasure());
    if (idCompMap != null) {
      idCompMap.put(id, component);
    } else {
      idCompMap = new HashMap<>();
      idCompMap.put(id, component);
      existingComponents.put(component.typeUnerasure(), idCompMap);
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
