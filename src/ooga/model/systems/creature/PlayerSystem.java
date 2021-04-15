package ooga.model.systems.creature;

import java.util.List;
import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.components.PlayerComponent.HorizontalMovementStatus;
import ooga.model.components.PlayerComponent.VerticalMovementStatus;
import ooga.model.objects.GameObject;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: add tests for this
@Track(PlayerComponent.class)
public class PlayerSystem extends ComponentBasedSystem {

  private static final Logger logger = LogManager.getLogger(PlayerSystem.class);

  protected ComponentMapper<PlayerComponent> componentMapper;
  // TODO: support customizing gravitational acceleration
  private double gravitationalAcceleration = 100;
  // TODO: support active and inactive players

  public PlayerSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(PlayerComponent.class);
    addMapping("right", this::handleRight);
    addMapping("left", this::handleLeft);
    addMapping("jump", this::handleJump);

    addCollisionMapping("jump_self", event -> doJump(event.getSelf()));
    addCollisionMapping("player_blocked_bottom", event -> obstacleOnBottom(event.getSelf()));
    addCollisionMapping("player_blocked_right", event -> obstacleOnRight(event.getSelf()));
    addCollisionMapping("player_blocked_left", event -> obstacleOnLeft(event.getSelf()));
    addCollisionMapping("player_blocked_top", event -> obstacleOnTop(event.getSelf()));
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

  /**
   * Callback when the player touches ground
   */
  private void obstacleOnBottom(GameObject go) {
    PlayerComponent p = componentMapper.get(go.getId());
    if (go.getVelocity().getY() > 0) {
      go.setVelocityY(0);
    }
    p.setVerticalStatus(VerticalMovementStatus.GROUNDED);
  }

  private void obstacleOnTop(GameObject go) {
    PlayerComponent p = componentMapper.get(go.getId());
    if (go.getVelocity().getY() < 0) {
      go.setVelocityY(0);
    }
  }

  private void obstacleOnLeft(GameObject go) {
    if (go.getVelocity().getX() < 0) {
      go.setVelocityX(0);
    }
  }

  private void obstacleOnRight(GameObject go) {
    if (go.getVelocity().getX() > 0) {
      go.setVelocityX(0);
    }
  }

  private void handleRight(boolean on) {
    handleHorizontalMovement(on, PlayerComponent.RIGHT_DIRECTION);
  }

  private void handleLeft(boolean on) {
    logger.info("Left being handled");
    handleHorizontalMovement(on, PlayerComponent.LEFT_DIRECTION);
  }

  public void doJump(GameObject go, PlayerComponent p) {
    // adding a little bit of vertical offset to make the player "break free" the collision
    go.setY(go.getY() + 3.0);
    go.setVelocityY(p.getJumpImpulse());
    p.setVerticalStatus(VerticalMovementStatus.AIRBORNE);
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

  public void initPlayerType(PlayerComponent.PlayerType playerType) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      p.setPlayerType(playerType);
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
              ? p.getDirection() * p.getMaxSpeed()
              : 0);

      // logger.info(go.getVelocity().getX());

      // change the vertical velocity according to gravity if in air
      if (p.getVerticalStatus() == VerticalMovementStatus.AIRBORNE) {
        double vy = go.getVelocity().getY();
        go.setVelocityY(vy - gravitationalAcceleration * deltaTime);
      } else {
        go.setVelocityY(0);
      }
    }
  }
}
