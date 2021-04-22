package ooga.model.systems.creature;

import javafx.util.Pair;
import ooga.model.annotations.Track;
import ooga.model.components.HateComponent;
import ooga.model.components.HealthComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

import java.util.Map;

@Track(HealthComponent.class)
public class SampleAttackSystem extends AttackSystems{
    private ComponentMapper<HealthComponent> healthMapper;
    private final int sampleDecrease=10;


    public SampleAttackSystem(ECManager ecManager) {
        super(ecManager);
        healthMapper= getComponentMapper(HealthComponent.class);
    }

    @Override
    public void update(double deltaTime){
        loopHateMap();
        for(Pair<HateComponent, PlayerComponent> p: hateMap.keySet()){
            AttackDealer tmpDealer= hateMap.get(p);
            if(tmpDealer.attackOrNot()){
              HealthComponent health= healthMapper.get(p.getValue().getId());
              health.healthIncrement(sampleDecrease,false);
            }
        }
    }
}
