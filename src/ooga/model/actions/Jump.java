package ooga.model.actions;

import ooga.model.components.MovementComponent;
import ooga.model.components.MovementComponent.VerticalMovementStatus;
import ooga.model.objects.GameObject;

public class Jump extends PlayerAction {

  @Override
  public void handleAction(boolean on) {
    MovementComponent p = getOwner();
    if (on) {
      GameObject go = p.getOwner();
      if (p.getVerticalStatus() == VerticalMovementStatus.GROUNDED) {
        go.setVelocityY(p.getJumpHeight() / p.getJumpTime());
        go.setY(go.getY() + 3.0);
        p.setVerticalStatus(VerticalMovementStatus.RISING);
        p.resetJumpTimer();
        p.setObstacle(MovementComponent.OBSTACLE_KEY_BOTTOM, null);
      }
    } else {
      p.setVerticalStatus(VerticalMovementStatus.FALLING);
    }
  }
}
