package ooga.model.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.actions.Handlers.HandlerFactory;
import ooga.model.actions.Handlers.MovementActionHandler;
import ooga.model.annotations.Track;
import ooga.model.components.MovementComponent;
import ooga.model.components.MovementComponent.HorizontalMovementStatus;
import ooga.model.components.MovementComponent.VerticalMovementStatus;
import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;
import ooga.model.systems.creature.ActionPair;

@Track(MovementComponent.class)
public class MovementSystem extends ComponentBasedSystem {

  private ComponentMapper<MovementComponent> movementMapper;
  private Map<String, Map<String, MovementActionHandler>> handlers = new HashMap<>();

  public MovementSystem(ECManager ecManager) {
    super(ecManager);
    movementMapper = getComponentMapper(MovementComponent.class);

    addCollisionMapping(
        "blocked_bottom",
        event -> obstacleOnBottom(event.getSelf(), event.getHitter())
    );
    addCollisionMapping(
        "blocked_right",
        event -> obstacleOnRight(event.getSelf(), event.getHitter())
    );
    addCollisionMapping(
        "blocked_left",
        event -> obstacleOnLeft(event.getSelf(), event.getHitter())
    );
    addCollisionMapping(
        "blocked_top",
        event -> obstacleOnTop(event.getSelf(), event.getHitter())
    );

    for (MovementComponent component : movementMapper.getComponents()) {
      registerAction(component);
    }
  }

  private void registerAction(MovementComponent component) {
    for (ActionPair mapping : component.getActionMapping()) {
      String input = mapping.getInput();
      String action = mapping.getAction();

      handlers.putIfAbsent(input, new HashMap<>());

      Map<String, MovementActionHandler> inputHandlers = handlers.get(input);
      inputHandlers.putIfAbsent(action, HandlerFactory.buildHandler(action));

      MovementActionHandler handler = inputHandlers.get(action);

      handler.addListener(component);

      addMapping(input, (on) -> {
        inputHandlers.values().forEach((currHandler) -> currHandler.handleAction(on));
      });
    }
  }

  /**
   * Callback when the player touches ground
   */
  private void obstacleOnBottom(GameObject go, GameObject other) {
    MovementComponent p = movementMapper.get(go.getId());
    p.setVerticalStatus(VerticalMovementStatus.GROUNDED);
    p.setObstacle(MovementComponent.OBSTACLE_KEY_BOTTOM, other);
  }

  private void obstacleOnTop(GameObject go, GameObject other) {
    MovementComponent p = movementMapper.get(go.getId());
    p.setVerticalStatus(VerticalMovementStatus.FALLING);
    p.setObstacle(MovementComponent.OBSTACLE_KEY_TOP, other);
  }

  private void obstacleOnLeft(GameObject go, GameObject other) {
    MovementComponent p = movementMapper.get(go.getId());
    p.setObstacle(MovementComponent.OBSTACLE_KEY_LEFT, other);
  }

  private void obstacleOnRight(GameObject go, GameObject other) {
    MovementComponent p = movementMapper.get(go.getId());
    p.setObstacle(MovementComponent.OBSTACLE_KEY_RIGHT, other);
  }

  @Override
  public void update(double deltaTime) {
    List<MovementComponent> comps = movementMapper.getComponents();
    for (MovementComponent p : comps) {
      GameObject go = p.getOwner();

      // set horizontal velocity of player if it's moving
      go.setVelocityX(
          p.getHorizontalStatus() == HorizontalMovementStatus.RUNNING
              ? p.getDirection() * p.getMaxSpeed()
              : 0);
      if (p.getObstacle(MovementComponent.OBSTACLE_KEY_LEFT) != null
          && go.getVelocity().getX() < 0) {
        go.setVelocityX(0);
      }
      if (p.getObstacle(MovementComponent.OBSTACLE_KEY_RIGHT) != null
          && go.getVelocity().getX() > 0) {
        go.setVelocityX(0);
      }

      // update vertical movement status
      if (p.getObstacle(MovementComponent.OBSTACLE_KEY_BOTTOM) == null) {
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
      p.setObstacle(MovementComponent.OBSTACLE_KEY_LEFT, null);
      p.setObstacle(MovementComponent.OBSTACLE_KEY_RIGHT, null);
      p.setObstacle(MovementComponent.OBSTACLE_KEY_TOP, null);
      p.setObstacle(MovementComponent.OBSTACLE_KEY_BOTTOM, null);
    }
  }
}
