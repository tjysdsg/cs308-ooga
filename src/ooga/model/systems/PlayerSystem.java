package ooga.model.systems;

import java.util.List;
import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.components.PlayerComponent.HorizontalMovementStatus;
import ooga.model.components.PlayerComponent.VerticalMovementStatus;
import ooga.model.objects.GameObject;

// TODO: add tests for this
@Track(PlayerComponent.class)
public class PlayerSystem extends ComponentBasedSystem {

  private ComponentMapper<PlayerComponent> componentMapper;
  // TODO: support customizing gravitational acceleration
  private double gravitationalAcceleration = 9.8;
  // TODO: support active and inactive players

  public PlayerSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(PlayerComponent.class);
  }

  public void init() {
    addMapping("right", this::handleRight);
    addMapping("left", this::handleLeft);
    addMapping("jump", this::handleJump);
    addCollisionMapping("jump_self", event -> doJump(event.getSelf()));
  }

  public List<PlayerComponent> getPlayers() {
    return componentMapper.getComponents();
  }

  private void handleHorizontalMovement(boolean run, int direction) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      p.setDirection(direction);
      if (run) {
        p.setHorizontalStatus(HorizontalMovementStatus.RUNNING);
      } else {
        p.setHorizontalStatus(HorizontalMovementStatus.STILL);
      }
    }
  }

  private void handleRight(boolean on) {
    handleHorizontalMovement(on, PlayerComponent.RIGHT_DIRECTION);
  }

  private void handleLeft(boolean on) {
    handleHorizontalMovement(on, PlayerComponent.LEFT_DIRECTION);
  }

  private double calculateJumpVerticalSpeed(double maxJumpHeight, double timeToApex) {
    // h = vt - 0.5 gt^2
    // v = (h + 0.5 gt^2) / t
    return (maxJumpHeight + 0.5 * gravitationalAcceleration * timeToApex * timeToApex) / timeToApex;
  }

  public void doJump(GameObject go, PlayerComponent p) {
    go.setVelocityY(calculateJumpVerticalSpeed(p.getMaxJumpHeight(), p.getTimeToJumpApex()));
  }

  public void doJump(GameObject go) {
    doJump(go, componentMapper.get(go.getId()));
  }

  private void handleJump(boolean on) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      if (p.getVerticalStatus() == VerticalMovementStatus.GROUNDED) {
        doJump(p.getOwner(), p);
      }
    }
  }

  @Override
  public void update(double deltaTime) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      GameObject go = p.getOwner();

      // set horizontal velocity of player if it's moving
      go.setVelocityX(
          p.getHorizontalStatus() == HorizontalMovementStatus.RUNNING
              ? p.getDirection() * p.getMaxSpeed() : 0
      );

      // change the vertical velocity according to gravity if in air
      if (p.getVerticalStatus() == VerticalMovementStatus.AIRBORNE) {
        double vy = go.getVelocity().getY();
        go.setVelocityY(vy - gravitationalAcceleration * deltaTime);
      }
    }
  }
}