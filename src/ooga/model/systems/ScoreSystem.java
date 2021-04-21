package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import ooga.model.actions.CollisionAction;
import ooga.model.objects.GameObject;

public class ScoreSystem extends ComponentBasedSystem{
  Map<String, Double>scoreChange = new HashMap<>();

  public ScoreSystem(ECManager ecManager) {
    super(ecManager);
    addCollisionMapping("increment_score",e->scoreIncrement(e));
    scoreChange.put("block",0.0);

  }

  public void scoreIncrement(CollisionAction event){

  }

  @Override
  public void update(double deltaTime) {

  }
}
