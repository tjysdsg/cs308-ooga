package ooga.model.systems.creature;

import ooga.model.Vector;
import ooga.model.annotations.Track;
import ooga.model.components.Component;
import ooga.model.components.HealthComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.components.enemy.HateComponent;
import ooga.model.components.equipment.WeaponComponent;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;
import ooga.model.systems.HealthSystem;

@Track({WeaponComponent.class, PlayerComponent.class, HateComponent.class, HealthComponent.class})
public class AttackSystem extends HealthSystem {
  private ComponentMapper<WeaponComponent> weaponMapper;
  private ComponentMapper<PlayerComponent> playerMapper;
  private ComponentMapper<HateComponent> enemyMapper;

  public AttackSystem(ECManager ecManager) {
    super(ecManager);
    weaponMapper=getComponentMapper(WeaponComponent.class);
    playerMapper=getComponentMapper(PlayerComponent.class);
    enemyMapper=getComponentMapper(HateComponent.class);

    addMapping("attack",this::attack);
  }

  public void  attack(boolean  on){
    for(WeaponComponent w:weaponMapper.getComponents()){
      for(HateComponent h:enemyMapper.getComponents()){
        int enemyID=h.getId();
        if(withinRange(w,h)&&faceToEnemy(w,h)){
          componentMapper.get(enemyID).healthIncrement(w.getAttack(),false);
        }
      }
    }
  }

  private boolean withinRange(WeaponComponent w, HateComponent h){
    return (Vector.difference(w.getOwner().getLocation(),h.getOwner().getLocation())<w.getAttackRange());
  }

  private boolean faceToEnemy(WeaponComponent w, HateComponent h){
    Vector delta= h.getOwner().getLocation().difference(w.getOwner().getLocation());
    double deltaX=delta.getX();
    return  deltaX*playerMapper.get(w.getId()).getDirection()>0;
  }

}
