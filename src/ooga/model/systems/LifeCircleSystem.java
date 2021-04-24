package ooga.model.systems;

public class LifeCircleSystem extends GameObjectBasedSystem {

  public LifeCircleSystem(ECManager entityManager) {
    super(entityManager);
    addCollisionMapping(
        "spawn_object",
        event -> spawnObject(event.getPayload())
    );

    addCollisionMapping(
        "destroy_object",
        event -> destroyObject(event.getSelf().getId())
    );
  }

  private void spawnObject(String type) {
    ecManager.createEntity(type);
  }

  private void destroyObject(int entityId) {
    System.out.println("Deleting object " + entityId);
    ecManager.deleteGameObject(entityId);
  }

  @Override
  public void update(double deltaTime) {
  }
}
