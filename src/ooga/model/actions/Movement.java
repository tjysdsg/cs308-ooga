package ooga.model.actions;

import ooga.model.components.MovementComponent;
import ooga.model.components.MovementComponent.HorizontalMovementStatus;

public abstract class Movement extends PlayerAction {

  protected void handleHorizontalMovement(boolean run, int direction) {
    MovementComponent p = getOwner();
    p.setDirection(direction);
    if (run) {
      p.setHorizontalStatus(HorizontalMovementStatus.RUNNING);
    } else {
      p.setHorizontalStatus(HorizontalMovementStatus.STILL);
    }
  }
}
