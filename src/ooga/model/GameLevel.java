package ooga.model;

import java.util.ArrayList;
import java.util.List;

import ooga.model.objects.GameObject;
import ooga.model.systems.BaseSystem;
import ooga.model.systems.ComponentManager;
import ooga.model.systems.EntityManager;
import ooga.model.systems.InputManager;

// TODO: implement methods
class GameLevel implements Level {

  transient Configuration gameConfiguration;
  private String name;
  int levelID;
  List<GameObject> gameObjects;

  private transient List<BaseSystem> systems;
  private transient EntityManager entityManager;
  private transient ComponentManager componentManager;
  private transient InputManager inputManager;

  // MUST BE HERE!!! MOSHI USES THIS
  public GameLevel() {
  }

  public void init() {
    entityManager = new EntityManager();
    componentManager = new ComponentManager();
    inputManager = new InputManager();

    systems = new ArrayList<>();
    // TODO: add systems here systems.add();

    // TODO: load configs and create components

    for (var s : systems) {
      s.registerAllInputs(inputManager);
    }

    componentManager.registerExistingComponents(gameObjects);
  }

  public void handleCode(String k, boolean on) {
    inputManager.handleCode(k, on);
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
}
