package ooga.model.actions;

import ooga.model.components.PlayerComponent;

public class MoveRightHandler extends MovementHandler {
  @Override
  public void handleAction(boolean on) {
    handleHorizontalMovement(on, PlayerComponent.RIGHT_DIRECTION);
  }
}
