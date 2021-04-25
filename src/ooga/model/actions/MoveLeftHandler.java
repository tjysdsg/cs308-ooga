package ooga.model.actions;

import ooga.model.components.PlayerComponent;

public class MoveLeftHandler extends MovementHandler {

  @Override
  public void handleAction(boolean on) {
    handleHorizontalMovement(on, PlayerComponent.LEFT_DIRECTION);
  }
}
