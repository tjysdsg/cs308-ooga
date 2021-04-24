package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import ooga.model.actions.CollisionAction;
import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.managers.ECManager;

/**
 * Provides the collision action to change score
 */
@Track(PlayerComponent.class)
public class ScoreSystem extends ComponentBasedSystem {

  private ComponentMapper<PlayerComponent> playerMapper;

  /**
   * Stores the score the player get when hitting each block
   */
  private Map<String, Double> blockScores = new HashMap<>();

  public ScoreSystem(ECManager ecManager) {
    super(ecManager);
    addCollisionMapping("increment_score", this::scoreIncrement);
    playerMapper = getComponentMapper(PlayerComponent.class);

    // FIXME: magic values
    blockScores.put("block", 0.0);
    blockScores.put("qblock", 10.0);
    blockScores.put("goomba", 20.0);
  }

  public void scoreIncrement(CollisionAction event) {
    PlayerComponent p = playerMapper.get(event.getSelf().getId());
    String hitterName = event.getHitter().getName();
    p.changeScore(blockScores.get(hitterName), true);
  }

  @Override
  public void update(double deltaTime) {

  }
}
