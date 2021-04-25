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
    for (MovementSquenceComponent m : getMovements()) {
      m.update(deltaTime);
    }
  }

  public void unitUpdate(MovementComponent m, MovementSquenceComponent ms){

  }


}
