package ooga.model.systems.creature;

import com.google.common.collect.ListMultimap;
import javafx.util.Pair;
import ooga.model.Vector;
import ooga.model.components.HateComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.objects.GameObject;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackSystems extends PlayerSystem{
    private ComponentMapper<HateComponent> hateMapper;
    protected Map<Pair<HateComponent,PlayerComponent>,Boolean> hateMap;

    public AttackSystems(ECManager ecManager) {
        super(ecManager);
        hateMapper = getComponentMapper(HateComponent.class);
        hateMap=new HashMap<Pair<HateComponent, PlayerComponent>, Boolean>();
    }

    //TODO: put these thing into Hate system when the system design is done
    private boolean detectHate(HateComponent hate0, HateComponent hate){
        Vector difference = hate0.getOwner().getVelocity().difference(hate.getOwner().getVelocity());
        double distance= difference.magnitude();
        return hate.detectHate(distance);
    }

    /**
     * Now assume that the the enemy will only attack the player.
     */
    public void loopHate(){
        List<HateComponent> hateAll= hateMapper.getComponents();
        List<PlayerComponent> playerComponents = getPlayers();
        for(int i=0;i<hateAll.size();i++){
            for(int j=0;j<playerComponents.size();j++){
                hateMap.put(new Pair<>(hateAll.get(i),playerComponents.get(j)),detectHate(hateAll.get(i),hateAll.get(j)));
            }
        }
    }

}
