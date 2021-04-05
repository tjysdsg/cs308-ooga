package ooga.model;

import com.google.common.collect.Multimap;
import java.util.List;
import ooga.model.systems.BaseSystem;
import ooga.model.systems.ComponentManager;
import ooga.model.systems.EntityManager;
import ooga.model.systems.InputSystem;

// TODO: implement methods
class GameLevel implements Level {

  Configuration gameConfiguration;
  private String name;
  int levelID;
  List<BaseSystem> systems;

  EntityManager entityManager;
  ComponentManager componentManager;
  InputSystem inputSystem;

  public GameLevel() {
    entityManager = new EntityManager();
    componentManager = new ComponentManager();
    inputSystem = new InputSystem();

    // TODO: load configs and create components

    for (var s : systems) {
      s.registerAllInputs(inputSystem);
    }
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
}
