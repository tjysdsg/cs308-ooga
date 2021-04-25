package ooga.model.actions.Handlers;

import ooga.model.components.MovementComponent;

public class MoveLeftHandler extends MovementHandler {

  @Override
  public void handleAction(boolean on) {
    handleHorizontalMovement(on, MovementComponent.LEFT_DIRECTION);
  }
}
