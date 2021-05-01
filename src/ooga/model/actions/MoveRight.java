package ooga.model.actions;

import ooga.model.components.MovementComponent;

public class MoveRight extends Movement {

  @Override
  public void handleAction(boolean on) {
    handleHorizontalMovement(on, MovementComponent.RIGHT_DIRECTION);
  }
}
