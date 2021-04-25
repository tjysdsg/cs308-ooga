package ooga.model.actions.Handlers;

import java.util.List;
import ooga.model.components.PlayerComponent;
import ooga.model.components.PlayerComponent.HorizontalMovementStatus;

public abstract class MovementHandler extends PlayerActionHandler {
  protected void handleHorizontalMovement(boolean run, int direction) {
    List<PlayerComponent> players = getListeners();
    for (PlayerComponent p : players) {
      p.setDirection(direction);
      if (run) {
        p.setHorizontalStatus(HorizontalMovementStatus.RUNNING);
      } else {
        p.setHorizontalStatus(HorizontalMovementStatus.STILL);
      }
    }
  }
}
