package ooga.model.systems.creature;

import javafx.util.Pair;
import ooga.model.components.HateComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.systems.ECManager;

import java.util.Map;

public class SampleAttackSystem extends AttackSystems{
    public SampleAttackSystem(ECManager ecManager) {
        super(ecManager);
    }

    @Override
    public void update(double deltaTime){
        loopHateMap();
        for(Pair<HateComponent, PlayerComponent> p: hateMap.keySet()){
            AttackDealer tmpDealer= hateMap.get(p);
            if(tmpDealer.attackOrNot()){
                //TODO: How to link to the health component
            }
        }
    }
}
