package ooga.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;


import ooga.model.objects.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CollisionTest {

  private ECManager myECManager;
  private ActionManager myActionManager;
  private CollisionSystem myCollisionSystem;
  private GameObject obj1;
  private GameObject obj2;

  @BeforeEach
  void setUp(){
    myECManager = new ECManager(null, null, null);
    myActionManager = new ActionManager();
    myCollisionSystem = new CollisionSystem(myECManager, myActionManager);

    obj1 = myECManager.createEntity("obj1");
    obj2 = myECManager.createEntity("obj2");

  }

  @Test
  void testNoCollision(){

  }
}
