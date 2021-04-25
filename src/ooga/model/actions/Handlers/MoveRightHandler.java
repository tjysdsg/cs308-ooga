package ooga.model.actions.Handlers;

import ooga.model.components.MovementComponent;

public class MoveRightHandler extends MovementHandler {

  @Override
  public void handleAction(boolean on) {
    handleHorizontalMovement(on, MovementComponent.RIGHT_DIRECTION);
  }
}
