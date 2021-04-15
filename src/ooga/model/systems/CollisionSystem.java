package ooga.model.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.model.actions.ActionInfo;
import ooga.model.actions.CollisionInfo;
import ooga.model.objects.GameObject;

public class CollisionSystem extends GameObjectBasedSystem {

  private ActionManager myActionManager;

  public CollisionSystem(ECManager ecmanager, ActionManager actionManager) {
    super(ecmanager);
    myActionManager = actionManager;

  }

  @Override
  public void update(double deltaTime) {
    checkCollisions();
  }

  private void checkCollisions() {
    List<GameObject> objects = getTrackedGameObjects();
    List<GameObject> collidableObjects = new ArrayList<>();

    for (GameObject gameObject : objects) {
      if (gameObject.isCollidable()) {
        collidableObjects.add(gameObject);
      }
    }
    Collections.sort(collidableObjects);
    findCollisions(collidableObjects);
  }

  private void findCollisions(List<GameObject> collidableObjects) {
    for (int k = 0; k < collidableObjects.size() - 1; k++) {
      executeCollisions(k, collidableObjects);
    }
  }

  private void executeCollisions(int index, List<GameObject> collidableObjects) {
    /*
    GameObject collidingObject = collidableObjects.get(index);
    double width = collidingObject.getWidth();
    double height = collidingObject.getHeight();
    double x = collidingObject.getX();
    double y = collidingObject.getY();

    for (int k = index + 1; k < collidableObjects.size(); k++) {
      GameObject collidedObject = collidableObjects.get(k);

      if (x + width < collidedObject.getX()) {
        return;
      } else if (collidedObject.getY() >= y && collidedObject.getY() <= y + height
          || y >= collidedObject.getY() && y <= collidedObject.getY() + collidedObject
          .getHeight()) {
        collide(collidingObject, collidedObject);
      }
    }
    */
    GameObject self = collidableObjects.get(index);
    double w1 = self.getWidth();
    double h1 = self.getHeight();
    double x1 = self.getX() - w1 / 2;
    double y1 = self.getY() - h1 / 2;

    for (int k = index + 1; k < collidableObjects.size(); k++) {
      GameObject other = collidableObjects.get(k);
      double w2 = other.getWidth();
      double h2 = other.getHeight();
      double x2 = other.getX() - w2 / 2;
      double y2 = other.getY() - h2 / 2;

      if (x1 < x2 + w2 && x1 + w1 > x2 &&
          y1 < y2 + h2 && y1 + h1 > y2) {
        collide(self, other);
      }
    }
  }

  public void collide(GameObject self, GameObject other) {
    String selfDirection = detectCollisionDirection(self, other);
    String otherDirection = detectCollisionDirection(other, self);
    CollisionInfo info = new CollisionInfo(self, other, selfDirection);
    myActionManager.handleAction(self, other, info);
    info = new CollisionInfo(other, self, otherDirection);
    myActionManager.handleAction(other, self, info);
  }

  // FIXME: not working correctly
  private String detectCollisionDirection(GameObject collidingObject, GameObject collidedObject) {
    double x = collidedObject.getX();
    double y = collidedObject.getY();
    double width = collidedObject.getWidth();
    double height = collidedObject.getHeight();

    double lComp = 0;
    double rComp = 0;
    double tComp = 0;
    double bComp = 0;

    if (collidingObject.getX() <= x) {
      rComp = Math.max(0,
          Math.min(y + height, collidingObject.getY() + collidingObject.getHeight()) - Math
              .max(y, collidingObject.getY()));
    }
    if (collidingObject.getX() + collidingObject.getWidth() >= x + width) {
      lComp = Math.max(0,
          Math.min(y + height, collidingObject.getY() + collidingObject.getHeight()) - Math
              .max(y, collidingObject.getY()));
    }
    if (collidingObject.getY() >= y) {
      tComp = Math.max(0,
          Math.min(x + width, collidingObject.getX() + collidingObject.getWidth()) - Math
              .max(x, collidingObject.getX()));
    }
    if (collidingObject.getY() + collidingObject.getHeight() <= y + height) {
      bComp = Math.max(0,
          Math.min(x + width, collidingObject.getX() + collidingObject.getWidth()) - Math
              .max(x, collidingObject.getX()));
    }
    if (lComp + rComp + tComp + bComp == 0) {
      if (collidingObject.getX() < x) {
        return "right";
      }
      if (collidingObject.getX() > x) {
        return "left";
      }
    }

    return calculateCollisionDirection(lComp, rComp, tComp, bComp);
  }

  private String calculateCollisionDirection(double lComp, double rComp, double tComp,
      double bComp) {
    if (lComp >= rComp && lComp >= tComp && lComp >= bComp) {
      return "left";
    }
    if (rComp >= lComp && rComp >= tComp && rComp >= bComp) {
      return "right";
    }
    if (tComp >= lComp && tComp >= rComp && tComp >= bComp) {
      return "top";
    }
    if (bComp >= lComp && bComp >= rComp && bComp >= tComp) {
      return "bottom";
    }
    return "";
  }

}
