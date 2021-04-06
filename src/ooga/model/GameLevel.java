package ooga.model;

import java.util.List;
import java.util.Map;

import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectFactory;
import ooga.model.objects.ObjectInstance;
import ooga.model.systems.BaseSystem;
import ooga.model.systems.ComponentManager;
import ooga.model.systems.EntityManager;
import ooga.model.systems.InputSystem;

// TODO: implement methods
class GameLevel implements Level {

  transient Configuration gameConfiguration;
  private String name;
  int levelID;
  List<GameObject> gameObjects;
  List<ObjectInstance> objectInstances;

  private transient List<BaseSystem> systems;
  private transient EntityManager entityManager;
  private transient ComponentManager componentManager;
  private transient InputSystem inputSystem;

  // MUST BE HERE!!! MOSHI USES THIS
  public GameLevel() {
  }

  public void init() {
    entityManager = new EntityManager();
    componentManager = new ComponentManager();
    inputSystem = new InputSystem();

    // TODO: load configs and create components

    for (var s : systems) {
      s.registerAllInputs(inputSystem);
    }

    componentManager.registerExistingComponents(gameObjects);
  }

  public void handleCode(String k, boolean on) {
    inputSystem.handleCode(k, on);
  }

  @Override
  public String getName() {
    return null;
  }

  //TODO: Probably won't need this. And can remove
  @Override
  public int getID() {
    return 0;
  }

  @Override
  public List<GameObject> generateObjects() {
    return null;
  }

  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }

  @Override
  public ComponentManager getComponentManager() {
    return componentManager;
  }

  public void applyPresets(ObjectFactory factory) {
    for (ObjectInstance instance : objectInstances) {
      factory.buildObject(instance);
    }
  }
}
