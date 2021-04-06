package ooga.model;

import java.util.List;

import ooga.model.objects.GameObject;
import ooga.model.systems.ComponentManager;
import ooga.model.systems.EntityManager;

public interface Level {
  String getName();
  int getID();
  List<GameObject> generateObjects();

  EntityManager getEntityManager();
  ComponentManager getComponentManager();
}
