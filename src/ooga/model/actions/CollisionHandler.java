package ooga.model.actions;

import ooga.model.objects.GameObject;

public interface CollisionHandler {
  void handleCollision(GameObject obj);
}