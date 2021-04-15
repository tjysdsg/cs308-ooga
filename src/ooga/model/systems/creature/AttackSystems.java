package ooga.model.systems.creature;

import javafx.util.Pair;
import ooga.model.Vector;
import ooga.model.components.Component;
import ooga.model.components.HateComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

import javax.swing.text.html.HTMLEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AttackSystems extends PlayerSystem{
    private ComponentMapper<HateComponent> hateMapper;
    protected Map<Pair<HateComponent,PlayerComponent>, AttackDealer> hateMap = new HashMap<>();

    public AttackSystems(ECManager ecManager) {
        super(ecManager);
        hateMapper = getComponentMapper(HateComponent.class);
    }

    //TODO: put these thing into Hate system when the system design is done
    private boolean detectHate(HateComponent hate0, Component hate){
        Vector difference = hate0.getOwner().getVelocity().difference(hate.getOwner().getVelocity());
        double distance= difference.magnitude();
        return hate0.detectHate(distance);
    }

    /**
     * Now assume that the the enemy will only attack the player.
     */
    public void loopHateMap(){
        List<HateComponent> hateAll= hateMapper.getComponents();
        List<PlayerComponent> playerComponents = getPlayers();
        for (HateComponent hateComponent : hateAll) {
            for (int j = 0; j < playerComponents.size(); j++) {
                Pair<HateComponent,PlayerComponent> tmpPair= new Pair<>(hateComponent,playerComponents.get(j ));
                if(!hateMap.containsKey(tmpPair)){
                    AttackDealer tmpAttackDealer= new AttackDealer();
                    hateMap.put(tmpPair,tmpAttackDealer);
                }
                AttackDealer tmpDealer= hateMap.get(tmpPair);
                tmpDealer.setHate(detectHate(hateComponent,playerComponents.get(j)));
                tmpDealer.determineAttack(hateComponent.getFrequency());
            }
        }
    }

    protected static class AttackDealer {
        private boolean hate=false;
        private int counter=0;
        private boolean attack=false;
        public void determineAttack(int frequency){
            if(hate){
                if(counter==0){
                    attack=true;
                }
            }
            attack=false;
            update(frequency);
        }
        private void update(int frequency){
            counter++;
            counter%=frequency;
        }
        public void setHate(boolean hate){
            this.hate=hate;
        }
        public boolean attackOrNot(){
            return attack;
        }
    }


}
