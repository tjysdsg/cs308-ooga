package ooga.model.systems;

import ooga.model.components.PlayerComponent;
import ooga.model.objects.GameObject;

public class PlayerSystem extends ComponentBasedSystem<PlayerComponent> {

  public PlayerSystem(ComponentManager componentManager) {
    super(componentManager, PlayerComponent.class);
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
}