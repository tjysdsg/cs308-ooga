package ooga.model.systems;

public class CollisionSystem extends GameObjectBasedSystem{

  private ActionManager myActionManager;

  public CollisionSystem(EntityManager entityManager, ActionManager actionManager) {
    super(entityManager);
    myActionManager = actionManager;

  }

  @Override
  public void update(double deltaTime) {

  }
}
