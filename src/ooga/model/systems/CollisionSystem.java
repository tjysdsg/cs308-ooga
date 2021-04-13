package ooga.model.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.model.objects.GameObject;

public class CollisionSystem extends GameObjectBasedSystem{

  private ActionManager myActionManager;

  public CollisionSystem(EntityManager entityManager, ActionManager actionManager) {
    super(entityManager);
    myActionManager = actionManager;

  }

  @Override
  public void update(double deltaTime) {
    checkCollisions();
  }

  private void checkCollisions() {
    List<GameObject> objects = getTrackedGameObjects();
    List<GameObject> collidableObjects = new ArrayList<>();

    for(GameObject gameObject: objects){
      if(gameObject.isCollidable()){
        collidableObjects.add(gameObject);
      }
    }
    Collections.sort(collidableObjects);
    findCollisions(collidableObjects);
  }

  private void findCollisions(List<GameObject> collidableObjects) {
    for(int k =0; k<collidableObjects.size()-1; k++){
      executeCollisions(k, collidableObjects);
    }
  }

  private void executeCollisions(int index, List<GameObject> collidableObjects) {
    GameObject collidingObject = collidableObjects.get(index);
    double width = collidingObject.getWidth();
    double height = collidingObject.getHeight();
    double x = collidingObject.getX();
    double y = collidingObject.getY();

    for(int k = index+1; k<collidableObjects.size(); k++){
      GameObject collidedObject = collidableObjects.get(k);

      if(x + width < collidedObject.getX()){
        return;
      }
      else if(collidedObject.getY() >= y && collidedObject.getY() <= y+height || y>=collidedObject.getY() && y<= collidedObject.getY()+collidedObject.getHeight()){
        collide(collidingObject, collidedObject);
      }

    }
  }

  public void collide(GameObject collidingObject, GameObject collidedObject) {
    String collidingObjectDirection = detectCollisionDirection(collidingObject, collidedObject);
    String collidedObjectDirection = detectCollisionDirection(collidedObject, collidingObject);
  }

  private String detectCollisionDirection(GameObject collidingObject, GameObject collidedObject) {
    double x = collidedObject.getX();
    double y = collidedObject.getY();
    double width = collidedObject.getWidth();
    double height = collidedObject.getHeight();

    double lComp = 0;
    double rComp = 0;
    double tComp = 0;
    double bComp = 0;

    if(collidingObject.getX() <= x){
      rComp = Math.max(0, Math.min(y+height,collidingObject.getY()+collidingObject.getHeight()) - Math.max(y, collidingObject.getY()));
    }
    if(collidingObject.getX()+collidingObject.getWidth() >= x + width){
      lComp = Math.max(0, Math.min(y+height,collidingObject.getY()+collidingObject.getHeight()) - Math.max(y, collidingObject.getY()));
    }
    if(collidingObject.getY() >= y){
      tComp= Math.max(0, Math.min(x+width,collidingObject.getX()+collidingObject.getWidth()) - Math.max(x, collidingObject.getX()));
    }
    if(collidingObject.getY()+collidingObject.getHeight() <= y+height){
      bComp =  Math.max(0, Math.min(x+width,collidingObject.getX()+collidingObject.getWidth()) - Math.max(x, collidingObject.getX()));
    }

    return calculateCollisionDirection(lComp,rComp,tComp,bComp);
  }

  private String calculateCollisionDirection(double lComp, double rComp, double tComp, double bComp) {
    if(lComp >= rComp && lComp>= tComp && lComp >=bComp){
      return "left";
    }
    if(rComp >= lComp && rComp >= tComp && rComp >= bComp){
      return "right";
    }
    if(tComp >= lComp && tComp >= rComp && tComp >= bComp){
      return "top";
    }
    if(bComp >= lComp && bComp >= rComp && bComp >=tComp){
      return "bottom";
    }
    return "";
  }

}
