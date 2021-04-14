package ooga.model.systems.creature;

import java.util.List;
import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.components.PlayerComponent.HorizontalMovementStatus;
import ooga.model.objects.GameObject;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

// TODO: add tests for this
@Track(PlayerComponent.class)
public class PlayerSystem extends ComponentBasedSystem {

  protected ComponentMapper<PlayerComponent> componentMapper;
  // TODO: support active and inactive players

  public PlayerSystem(ECManager ecManager) {
    super(ecManager);
    componentMapper = getComponentMapper(PlayerComponent.class);
  }

  public void init() {
    addMapping("right", this::handleRight);
    addMapping("left", this::handleLeft);
    addMapping("jump", this::handleJump);
    addCollisionMapping("jump_self", event -> handleJump(event.getSelf()));
    initPlayerType(PlayerComponent.PlayerType.PLAYER);
  }

  public List<PlayerComponent> getPlayers() {
    return componentMapper.getComponents();
  }

  public void handleJump(GameObject obj) {
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

  void handleJump(boolean on) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      // TODO: implement jumping mechanism
    }
  }

  public void initPlayerType(PlayerComponent.PlayerType playerType){
    List<PlayerComponent> players= getPlayers();
    for(PlayerComponent p: players){
      p.setPlayerType(playerType);
    }
  }

  @Override
  public void update(double deltaTime) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      GameObject go = p.getOwner();
      go.setVelocityX(
          p.getHorizontalStatus() == HorizontalMovementStatus.RUNNING
              ? p.getDirection() * p.getMaxSpeed() : 0
      );
      // TODO: set vertical velocity accordingly go.setVelocityY();
    }
  }
}