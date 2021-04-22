package ooga.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;


import ooga.model.objects.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CollisionTest {

  private ECManager myECManager;
  private ActionManager myActionManager;
  private CollisionSystem myCollisionSystem;

  @BeforeEach
  void setUp(){
    myECManager = new ECManager(null, null, null);
    myActionManager = new ActionManager();
    myCollisionSystem = new CollisionSystem(myECManager, myActionManager);


  }

  @Test
  void testNoCollision(){
    GameObject obj1 = myECManager.createEntity("obj1");

  }
}
