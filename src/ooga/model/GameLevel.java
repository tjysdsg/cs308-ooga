package ooga.model;

import java.util.List;
import ooga.model.systems.ComponentManager;
import ooga.model.systems.EntityManager;

// TODO: implement methods
class GameLevel implements Level {
  Configuration gameConfiguration;
  private String name;
  int levelID;

  EntityManager entityManager;
  ComponentManager componentManager;

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
