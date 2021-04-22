package ooga.model.systems.creature;

import ooga.model.annotations.Track;
import ooga.model.components.MovementSquenceComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

import java.util.List;

@Track(MovementSquenceComponent.class)
public class NPCSystem extends PlayerSystem{
    protected ComponentMapper<MovementSquenceComponent> movementSequenceMapper;

    public NPCSystem(ECManager ecManager) {
        super(ecManager);
        movementSequenceMapper =getComponentMapper(MovementSquenceComponent.class);
        initPlayerType(PlayerComponent.PlayerType.NEUTRAL);
    }

    private List<MovementSquenceComponent> getMovements(){
        return movementSequenceMapper.getComponents();
    }

    @Override
    public void update(double deltaTime){
        for(MovementSquenceComponent m: getMovements()){
            m.update(deltaTime);
        }
    }



}
