package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import ooga.model.actions.CollisionAction;
import ooga.model.annotations.Track;
import ooga.model.components.PlayerComponent;
import ooga.model.objects.GameObject;

@Track(PlayerComponent.class)
public class ScoreSystem extends ComponentBasedSystem{
  private ComponentMapper<PlayerComponent> playerMapper;
  Map<String, Double>scoreChange = new HashMap<>();

  public ScoreSystem(ECManager ecManager) {
    super(ecManager);
    addCollisionMapping("increment_score",e->scoreIncrement(e));
    playerMapper=getComponentMapper(PlayerComponent.class);
    scoreChange.put("block",0.0);
    scoreChange.put("qblock",10.0);
    scoreChange.put("goomba",20.0);

  }

  public void scoreIncrement(CollisionAction event){
    PlayerComponent p = playerMapper.get(event.getSelf().getId());
    String hitterName=event.getHitter().getName();
    p.changeScore(scoreChange.get(hitterName),true);
  }

  @Override
  public void update(double deltaTime) {

  }
}
