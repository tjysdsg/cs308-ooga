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

  // TODO: support active and inactive players

  public PlayerSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(PlayerComponent.class);
    addMapping("right", this::handleRight);
    addMapping("left", this::handleLeft);
    addMapping("jump", this::handleJump);

    addCollisionMapping("jump_self", event -> doJump(event.getSelf()));
    addCollisionMapping(
        "player_blocked_bottom",
        event -> obstacleOnBottom(event.getSelf(), event.getHitter())
    );
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
  private void obstacleOnBottom(GameObject go, GameObject other) {
    PlayerComponent p = componentMapper.get(go.getId());
    if (go.getVelocity().getY() > 0) {
      go.setVelocityY(0);
    }
    p.setVerticalStatus(VerticalMovementStatus.GROUNDED);
    p.getBlocksBelow().add(other);
  }

  private void obstacleOnTop(GameObject go) {
    PlayerComponent p = componentMapper.get(go.getId());
    p.setVerticalStatus(VerticalMovementStatus.FALLING);
    go.setVelocityY(0);
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
    if (p.getVerticalStatus() == VerticalMovementStatus.GROUNDED) {
      go.setVelocityY(p.getJumpHeight() / p.getJumpTime());
      go.setY(go.getY() + 3.0);
      p.setVerticalStatus(VerticalMovementStatus.RISING);
      p.resetJumpTimer();
      p.resetBlocksBelow();
    }
  }

  public void doJump(GameObject go) {
    doJump(go, componentMapper.get(go.getId()));
  }

  private void handleJump(boolean on) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      doJump(p.getOwner(), p);
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

      logger.debug("Vertical movement status {}", p.getVerticalStatus());

      // update vertical movement status
      if (p.getBlocksBelow().size() == 0) {
        if (p.getVerticalStatus() != VerticalMovementStatus.RISING) {
          p.setVerticalStatus(VerticalMovementStatus.FALLING);
        }
      } else {
        p.setVerticalStatus(VerticalMovementStatus.GROUNDED);
      }

      // update vertical velocity according to vertical movement status
      if (p.getVerticalStatus() == VerticalMovementStatus.RISING) {
        if (p.getJumpTimer() >= p.getJumpTime()) {
          go.setVelocityY(0);
          p.setVerticalStatus(VerticalMovementStatus.FALLING);
        } else {
          go.setVelocityY(p.getJumpHeight() / p.getJumpTime());
        }
      } else if (p.getVerticalStatus() == VerticalMovementStatus.FALLING) {
        go.setVelocityY(-p.getJumpHeight() / p.getJumpTime());
      } else {
        go.setVelocityY(0);
      }

      // update the vertical velocity
      p.incrementJumpTimer(deltaTime);
      // reset list of blocks below
      p.resetBlocksBelow();
    }
  }
}
