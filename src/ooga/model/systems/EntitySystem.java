package ooga.model.systems;

public abstract  class EntitySystem  extends GameObjectBasedSystem{
  protected EntityManager entityManager;

  public EntitySystem(EntityManager entityManager) {
    super(entityManager);
    this.entityManager=entityManager;
  }
}
