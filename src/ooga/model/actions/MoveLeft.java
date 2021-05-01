package ooga.model.actions;

import ooga.model.components.MovementComponent;

public class MoveLeft extends Movement {
  @Override
  public void handleAction(boolean on) {
    handleHorizontalMovement(on, MovementComponent.LEFT_DIRECTION);
  }
}
