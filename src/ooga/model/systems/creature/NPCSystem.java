package ooga.model.systems.creature;

import java.util.List;
import ooga.model.annotations.Track;
import ooga.model.components.MovementComponent;
import ooga.model.components.MovementSquenceComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.managers.ECManager;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.MovementSystem;

@Track({MovementSquenceComponent.class, PlayerComponent.class, MovementSquenceComponent.class})
public class NPCSystem extends PlayerSystem {

  protected ComponentMapper<MovementSquenceComponent> movementSequenceMapper;

  public NPCSystem(ECManager ecManager) {
    super(ecManager);
    movementSequenceMapper = getComponentMapper(MovementSquenceComponent.class);
    initPlayerType(PlayerComponent.PlayerType.NEUTRAL);
  }

  private List<MovementSquenceComponent> getMovements() {
    return movementSequenceMapper.getComponents();
  }

  @Override
  public void update(double deltaTime) {
    for (MovementSquenceComponent ms : getMovements()) {
      unitUpdate(deltaTime,movementMapper.get(ms.getOwner().getId()),ms);
    }
  }

  public void unitUpdate(double deltaTime,MovementComponent m, MovementSquenceComponent ms){
    double cumTime= ms.getCumTime();
    List<String> actionSequence=ms.getActionSequence();
    List<Double> actionTime=ms.getActionTime();
    int actionIndex = ms.getActionIndex();
    if (cumTime < actionTime.get(actionIndex)) {
      cumTime += deltaTime;
      ms.setCumTime(cumTime);
    } else {
      ms.setCumTime(0);
      ms.setActionIndex(ms.getActionIndex()+1);
    }
    if (actionIndex >= actionSequence.size()) {
      ms.setActionIndex(0);
    }
    execAction(actionSequence.get(actionIndex), deltaTime);
  }





}
