package ooga.model;

import java.util.List;

public interface Level {
  String getName();
  int getID();
  List<GameObject> generateObjects();

  EntityManager getEntityManager();
  ComponentManager getComponentManager();
}
