package ooga.model.systems.creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.actions.Handlers.HandlerFactory;
import ooga.model.actions.Handlers.PlayerActionHandler;
import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.components.PlayerComponent.HorizontalMovementStatus;
import ooga.model.components.PlayerComponent.VerticalMovementStatus;
import ooga.model.objects.GameObject;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.managers.ECManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: add tests for this
@Track(PlayerComponent.class)
public class PlayerSystem extends ComponentBasedSystem {

  private static final Logger logger = LogManager.getLogger(PlayerSystem.class);

  private Map<String, Map<String, PlayerActionHandler>> handlers = new HashMap<>();
  protected ComponentMapper<PlayerComponent> componentMapper;

  // TODO: support active and inactive players

  public PlayerSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(PlayerComponent.class);
    for (PlayerComponent component : componentMapper.getComponents()) {
      registerAction(component);
    }
//    addMapping("right", this::handleRight);
//    addMapping("left", this::handleLeft);
//    addMapping("jump", this::handleJump);

//    addCollisionMapping("jump_self", event -> doJump(event.getSelf()));
    addCollisionMapping(
        "player_blocked_bottom",
        event -> obstacleOnBottom(event.getSelf(), event.getHitter())
    );
    addCollisionMapping(
        "player_blocked_right",
        event -> obstacleOnRight(event.getSelf(), event.getHitter())
    );
    addCollisionMapping(
        "player_blocked_left",
        event -> obstacleOnLeft(event.getSelf(), event.getHitter())
    );
    addCollisionMapping(
        "player_blocked_top",
        event -> obstacleOnTop(event.getSelf(), event.getHitter())
    );
  }

  private void registerAction(PlayerComponent component) {
    for (ActionPair mapping : component.getActionMapping()) {
      String input = mapping.getInput();
      String action = mapping.getAction();

      handlers.putIfAbsent(input, new HashMap<>());

      Map<String, PlayerActionHandler> inputHandlers = handlers.get(input);
      inputHandlers.putIfAbsent(action, HandlerFactory.buildHandler(action));

      PlayerActionHandler handler = inputHandlers.get(action);

      handler.addListener(component);

      addMapping(input, (on) -> {
        inputHandlers.values().forEach( (currHandler) -> currHandler.handleAction(on));
      });
    }
  }

  public List<PlayerComponent> getPlayers() {
    return componentMapper.getComponents();
  }

  /**
   * Callback when the player touches ground
   */
  private void obstacleOnBottom(GameObject go, GameObject other) {
    PlayerComponent p = componentMapper.get(go.getId());
    p.setVerticalStatus(VerticalMovementStatus.GROUNDED);
    p.setObstacle(PlayerComponent.OBSTACLE_KEY_BOTTOM, other);
  }

  private void obstacleOnTop(GameObject go, GameObject other) {
    PlayerComponent p = componentMapper.get(go.getId());
    p.setVerticalStatus(VerticalMovementStatus.FALLING);
    p.setObstacle(PlayerComponent.OBSTACLE_KEY_TOP, other);
  }

  private void obstacleOnLeft(GameObject go, GameObject other) {
    PlayerComponent p = componentMapper.get(go.getId());
    p.setObstacle(PlayerComponent.OBSTACLE_KEY_LEFT, other);
  }

  private void obstacleOnRight(GameObject go, GameObject other) {
    PlayerComponent p = componentMapper.get(go.getId());
    p.setObstacle(PlayerComponent.OBSTACLE_KEY_RIGHT, other);
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
      if (p.getObstacle(PlayerComponent.OBSTACLE_KEY_LEFT) != null
          && go.getVelocity().getX() < 0) {
        go.setVelocityX(0);
      }
      if (p.getObstacle(PlayerComponent.OBSTACLE_KEY_RIGHT) != null
          && go.getVelocity().getX() > 0) {
        go.setVelocityX(0);
      }

      // update vertical movement status
      if (p.getObstacle(PlayerComponent.OBSTACLE_KEY_BOTTOM) == null) {
        if (p.getVerticalStatus() != VerticalMovementStatus.RISING) {
          p.setVerticalStatus(VerticalMovementStatus.FALLING);
        }
      } else {
        p.setVerticalStatus(VerticalMovementStatus.GROUNDED);
      }

      // update vertical velocity according to vertical movement status
      if (p.getVerticalStatus() == VerticalMovementStatus.RISING) {
        if (p.getJumpTimer() >= p.getJumpTime()) {
          p.setVerticalStatus(VerticalMovementStatus.FALLING);
        } else {
          go.setVelocityY(p.getJumpHeight() / p.getJumpTime());
        }
      }
      if (p.getVerticalStatus() == VerticalMovementStatus.FALLING) {
        go.setVelocityY(-p.getJumpHeight() / p.getJumpTime());
      }
      if (p.getVerticalStatus() == VerticalMovementStatus.GROUNDED) {
        go.setVelocityY(0);
      }

      // update the vertical velocity
      p.incrementJumpTimer(deltaTime);

      // reset list of obstacles
      p.setObstacle(PlayerComponent.OBSTACLE_KEY_LEFT, null);
      p.setObstacle(PlayerComponent.OBSTACLE_KEY_RIGHT, null);
      p.setObstacle(PlayerComponent.OBSTACLE_KEY_TOP, null);
      p.setObstacle(PlayerComponent.OBSTACLE_KEY_BOTTOM, null);
    }
  }
}
