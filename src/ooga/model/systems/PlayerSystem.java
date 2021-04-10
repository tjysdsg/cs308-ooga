package ooga.model.systems;

import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.objects.GameObject;

@Track(PlayerComponent.class)
public class PlayerSystem extends ComponentBasedSystem {

  public PlayerSystem(EntityManager entityManager, ComponentManager componentManager) {
    super(entityManager, componentManager);
  }

  public void init() {
    addMapping("right", this::handleRight);
    addMapping("jump", this::handleJump);
    addCollisionMapping("jump_self", event -> handleJump(event.getSelf()));
  }

  public void handleJump(GameObject obj) {
  }

  void handleRight(boolean on) {
  }


  void handleJump(boolean on) {
  }

  @Override
  public void update(double deltaTime) {
  }
}