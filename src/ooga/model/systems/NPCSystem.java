package ooga.model.systems;

import ooga.model.components.MovementSquenceComponent;
import ooga.model.components.PlayerComponent;

public class NPCSystem extends PlayerSystem{
    protected ComponentMapper<MovementSquenceComponent> movementSequenceMapper;

    public NPCSystem(ECManager ecManager) {
        super(ecManager);
        movementSequenceMapper =getComponentMapper(MovementSquenceComponent.class);
    }

    @Override
    public void init(){
        initPlayerType(PlayerComponent.PlayerType.NEUTRAL);
    }

    private void moveRight()


}
