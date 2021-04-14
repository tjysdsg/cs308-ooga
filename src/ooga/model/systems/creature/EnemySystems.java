package ooga.model.systems.creature;

import ooga.model.Vector;
import ooga.model.components.HateComponent;
import ooga.model.objects.GameObject;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

import java.util.List;

public class EnemySystems extends PlayerSystem{
    private ComponentMapper<HateComponent> hateMapper;
    public EnemySystems(ECManager ecManager) {
        super(ecManager);
    }

    //TODO: put these thing into Hate system when the system design is done
    private void detectHate(HateComponent hate0, HateComponent hate){
        Vector difference = hate0.getOwner().getVelocity().difference(hate.getOwner().getVelocity());
        double distance= difference.magnitude();
        hate.detectHate(distance);
    }

    /**
     * Now assume that the the enemy will only attack the player.
     */
    public void loopHate(){
        List<HateComponent> hateAll= hateMapper.getComponents();
        for(int i=0;i<hateAll.size();i++){
            for(int j=i+1;j<hateAll.size();j++){
                if(i==j ){
                    continue;
                }
                detectHate(hateAll.get(i),hateAll.get(j));
            }
        }
    }
}
