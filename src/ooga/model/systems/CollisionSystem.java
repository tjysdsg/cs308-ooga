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
      else if(collidedObject.getY() >= y && collidedObject.getY() <= y+height){
        collide(collidingObject, collidedObject);
      }

    }
  }

  public void collide(GameObject collidingObject, GameObject collidedObject) {
  }

}
