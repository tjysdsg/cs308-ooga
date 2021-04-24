package ooga.model.systems;

import java.util.ArrayList;
import java.util.List;
import ooga.model.StatsInfo;
import ooga.model.actions.CollisionAction;
import ooga.model.annotations.Track;
import ooga.model.components.ScoreComponent;
import ooga.model.managers.ECManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides the collision action to change score
 * <p>
 * User must specified the score value to change in the config, such as '-10.0'
 */
@Track(ScoreComponent.class)
public class ScoreSystem extends ComponentBasedSystem {

  private static final Logger logger = LogManager.getLogger(ScoreSystem.class);
  private static final String SCORE_STATS_NAME = "score";

  private ComponentMapper<ScoreComponent> scoreMapper;

  public ScoreSystem(ECManager ecManager) {
    super(ecManager);
    addCollisionMapping("change_score", this::scoreIncrement);
    scoreMapper = getComponentMapper(ScoreComponent.class);

    addStatsSupplier(SCORE_STATS_NAME, this::scoreStatsSupplier);
  }

  private List<StatsInfo> scoreStatsSupplier() {
    ArrayList<StatsInfo> ret = new ArrayList<>();
    for (ScoreComponent comp : scoreMapper.getComponents()) {
      ret.add(new StatsInfo(comp.getScore() + "", comp.getOwner().getId()));
    }
    return ret;
  }

  public void scoreIncrement(CollisionAction event) {
    ScoreComponent comp = scoreMapper.get(event.getSelf().getId());

    double delta = 0;
    try {
      delta = Double.parseDouble(event.getPayload());
    } catch (NullPointerException | NumberFormatException e) {
      logger.error(
          "Cannot parse payload of 'change_score' action as a double: {}",
          event.getPayload()
      );
    }

    comp.changeScore(delta, true);
  }

  @Override
  public void update(double deltaTime) {
  }
}
