package ooga.model.actions.Handlers;

import java.util.List;
import ooga.model.components.MovementComponent;
import ooga.model.components.MovementComponent.HorizontalMovementStatus;

public abstract class MovementHandler extends MovementActionHandler {

  protected void handleHorizontalMovement(boolean run, int direction) {
    List<MovementComponent> players = getListeners();
    for (MovementComponent p : players) {
      p.setDirection(direction);
      if (run) {
        p.setHorizontalStatus(HorizontalMovementStatus.RUNNING);
      } else {
        p.setHorizontalStatus(HorizontalMovementStatus.STILL);
      }
    }
  }
}
