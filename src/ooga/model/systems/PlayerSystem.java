package ooga.model.systems;

import java.util.function.Consumer;
import ooga.model.components.PlayerComponent;

public class PlayerSystem extends ComponentBasedSystem {

  public PlayerSystem(ComponentManager componentManager) {
    super(componentManager, PlayerComponent.class);
  }

  public void init() {
    addMapping("right", (Consumer<Boolean>) this::handleRight);
    addMapping("jump", (Consumer<Boolean>) this::handleJump);
  }

  void handleRight(boolean on) {
  }

  void handleJump(boolean on) {
  }
}
