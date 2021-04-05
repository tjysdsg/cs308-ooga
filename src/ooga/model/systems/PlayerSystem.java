package ooga.model.systems;

import ooga.model.GameObject;
import ooga.model.components.PlayerComponent;

public class PlayerSystem extends BaseSystem {

  public void init() {
    addMapping("right", this::handleRight);
    addMapping("jump", this::handleJump);
  }

  void handleRight(boolean on) {
  }

  void handleJump(boolean on) {
  }
}
