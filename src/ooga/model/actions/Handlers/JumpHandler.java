package ooga.model.actions.Handlers;

import java.util.List;
import ooga.model.components.MovementComponent;
import ooga.model.components.MovementComponent.VerticalMovementStatus;
import ooga.model.objects.GameObject;

public class JumpHandler extends MovementActionHandler {

  @Override
  public void handleAction(boolean on) {
    List<MovementComponent> players = getListeners();
    if (on) {
      for (MovementComponent p : players) {
        GameObject go = p.getOwner();
        if (p.getVerticalStatus() == VerticalMovementStatus.GROUNDED) {
          go.setVelocityY(p.getJumpHeight() / p.getJumpTime());
          go.setY(go.getY() + 3.0);
          p.setVerticalStatus(VerticalMovementStatus.RISING);
          p.resetJumpTimer();
          p.setObstacle(MovementComponent.OBSTACLE_KEY_BOTTOM, null);
        }
      }
    } else {
      for (MovementComponent p : players) {
        p.setVerticalStatus(VerticalMovementStatus.FALLING);
      }
    }
  }
}
