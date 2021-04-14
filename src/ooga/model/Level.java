package ooga.model;

import java.util.List;

import ooga.model.objects.GameObject;
import ooga.model.systems.ECManager;

public interface Level {

  String getName();

  int getID();

  void update(double deltaTime);

  List<GameObject> generateObjects();

  ECManager getECManager();
}
