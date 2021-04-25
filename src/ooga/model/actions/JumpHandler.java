package ooga.model.actions;

import java.util.List;
import ooga.model.components.PlayerComponent;
import ooga.model.components.PlayerComponent.VerticalMovementStatus;
import ooga.model.objects.GameObject;

public class JumpHandler extends PlayerActionHandler {

  @Override
  public void handleAction(boolean on) {
    List<PlayerComponent> players = getListeners();
    if (on) {
      for (PlayerComponent p : players) {
        GameObject go = p.getOwner();
        if (p.getVerticalStatus() == VerticalMovementStatus.GROUNDED) {
          go.setVelocityY(p.getJumpHeight() / p.getJumpTime());
          go.setY(go.getY() + 3.0);
          p.setVerticalStatus(VerticalMovementStatus.RISING);
          p.resetJumpTimer();
          p.setObstacle(PlayerComponent.OBSTACLE_KEY_BOTTOM, null);
        }
      }
    } else {
      for (PlayerComponent p : players) {
        p.setVerticalStatus(VerticalMovementStatus.FALLING);
      }
    }
  }
}
