package ooga.model.systems.creature;

import java.util.List;
import java.util.Random;
import ooga.model.annotations.Track;
import ooga.model.components.MovementComponent;
import ooga.model.components.MovementComponent.HorizontalMovementStatus;
import ooga.model.components.MovementSquenceComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.managers.ECManager;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;

@Track({MovementSquenceComponent.class, PlayerComponent.class, MovementComponent.class})
public class NPCSystem extends ComponentBasedSystem {

  private ComponentMapper<MovementSquenceComponent> movementSequenceMapper;
  private ComponentMapper<MovementComponent> movementMapper;

  public NPCSystem(ECManager ecManager) {
    super(ecManager);
    movementSequenceMapper = getComponentMapper(MovementSquenceComponent.class);
    movementMapper= getComponentMapper(MovementComponent.class);
  }

  private List<MovementSquenceComponent> getMovementSequence() {
    return movementSequenceMapper.getComponents();
  }

  @Override
  public void update(double deltaTime) {
    for (MovementSquenceComponent ms : getMovementSequence()) {
      unitUpdate(deltaTime,movementMapper.get(ms.getOwner().getId()),ms);
    }
  }

  private void execAction(String actionCode, double deltaTime, MovementComponent m, MovementSquenceComponent ms){
    switch (actionCode){
      case "move_left":
        moveLeft(deltaTime,m);
        break;
      case "move_right":
        moveRight(deltaTime,m);
        break;
      case "stand_still":
        standStill(m);
        break;
      default:
        break;
    }
  }

  private void moveRight(double deltaTime, MovementComponent m){
    m.setHorizontalStatus(HorizontalMovementStatus.RUNNING);
    m.setDirection(1);
  }

  private void standStill(MovementComponent m){
    m.setHorizontalStatus(HorizontalMovementStatus.STILL);
  }

  private void moveLeft(double deltaTime, MovementComponent m){
    m.setHorizontalStatus(HorizontalMovementStatus.RUNNING);
    m.setDirection(-1);
  }

  private void unitUpdate(double deltaTime,MovementComponent m, MovementSquenceComponent ms){
    double cumTime= ms.getCumTime();
    List<String> actionSequence=ms.getActionSequence();
    List<Double> actionTime=ms.getActionTime();
    int actionIndex = ms.getActionIndex();
    if (cumTime < actionTime.get(actionIndex)) {
      cumTime += deltaTime;
      ms.setCumTime(cumTime);
    } else {
      ms.setCumTime(0);
      actionIndex++;
      ms.setActionIndex(actionIndex);
    }
    if (actionIndex >= actionSequence.size()) {
      ms.setActionIndex(0);
    }
    execAction(actionSequence.get(actionIndex), deltaTime, m, ms);
  }

}
