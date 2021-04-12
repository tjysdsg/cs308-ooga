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
import ooga.model.systems.TransformSystem;

// TODO: implement methods
class GameLevel implements Level {

  private String name;
  int levelID;

  private transient List<BaseSystem> systems;
  @Json(name = "objects") private EntityManager entityManager;
  private transient ComponentManager componentManager;
  private transient InputManager inputManager;
  private transient ActionManager actionManager;

  // MUST BE HERE!!! MOSHI USES THIS
  public GameLevel() {
  }

  public void init() {
//    entityManager = new EntityManager();
    componentManager = new ComponentManager();
    inputManager = new InputManager();
    actionManager = new ActionManager();
    // TODO: create game objects here
    //gameObjects = entityManager.getEntities();

    // TODO: load configs and create components

    // TODO: create systems here and add them to systems
    systems = new ArrayList<>();
    systems.add(new TransformSystem(entityManager));

    for (var s : systems) {
      s.registerAllInputs(inputManager);
      s.registerAllActions(actionManager);
    }

    // TODO: Move to Moshi adapter
//    componentManager.registerExistingComponents(gameObjects);
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
  public void update(double deltaTime) {
    for (System s : systems) {
      s.update(deltaTime);
    }
  }

  @Override
  public List<GameObject> generateObjects() {
    return entityManager.getEntities();
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
