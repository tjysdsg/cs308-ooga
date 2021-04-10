package ooga.model;

import java.util.ArrayList;
import java.util.List;

import com.squareup.moshi.Json;
import ooga.model.objects.GameObject;
import ooga.model.systems.ActionManager;
import ooga.model.systems.BaseSystem;
import ooga.model.systems.ComponentManager;
import ooga.model.systems.EntityManager;
import ooga.model.systems.InputManager;
import ooga.model.systems.PlayerSystem;
import ooga.model.systems.TransformSystem;

// TODO: implement methods
class GameLevel implements Level {

  private String name;
  int levelID;
  @Json(name = "objects")
  List<GameObject> gameObjects;

  private transient List<BaseSystem> systems;
  private transient EntityManager entityManager;
  private transient ComponentManager componentManager;
  private transient InputManager inputManager;
  private transient ActionManager actionManager;

  // MUST BE HERE!!! MOSHI USES THIS
  public GameLevel() {
  }

  public void init() {
    // NOTE: Before calling this method, make sure the followings:
    // - game objects are all created
    // - components are all created

    entityManager = new EntityManager();
    componentManager = new ComponentManager();
    inputManager = new InputManager();
    actionManager = new ActionManager();

    systems = new ArrayList<>();
    systems.add(new TransformSystem(entityManager));
    systems.add(new PlayerSystem(entityManager, componentManager));
    // TODO: create other systems and add them to the list

    for (var s : systems) {
      s.registerAllInputs(inputManager);
      s.registerAllActions(actionManager);
    }

    // TODO: Move to Moshi adapter
    componentManager.registerExistingComponents(gameObjects);
  }

  public void handleCode(String k, boolean on) {
    inputManager.handleCode(k, on);
  }

  @Override
  public String getName() {
    return name;
  }

  //TODO: Probably won't need this. And can remove
  @Override
  public int getID() {
    return levelID;
  }

  @Override
  public void update(double deltaTime) {
    for (BaseSystem s : systems) {
      s.update(deltaTime);
    }
  }

  @Override
  public List<GameObject> generateObjects() {
    return gameObjects;
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
