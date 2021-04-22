package ooga.model.systems.creature;

import ooga.model.annotations.Track;
import ooga.model.components.Component;
import ooga.model.components.PlayerComponent;
import ooga.model.components.enemy.HateComponent;
import ooga.model.components.equipment.WeaponComponent;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

@Track({WeaponComponent.class, PlayerComponent.class, HateComponent.class})
public class AttackSystem extends ComponentBasedSystem {
  private ComponentMapper<WeaponComponent> weaponMapper;
  private ComponentMapper<PlayerComponent> playerMapper;
  private ComponentMapper<HateComponent> enemyMapper;

  public AttackSystem(ECManager ecManager) {
    super(ecManager);
    weaponMapper=getComponentMapper(WeaponComponent.class);
    playerMapper=getComponentMapper(PlayerComponent.class);
    enemyMapper=getComponentMapper(HateComponent.class);
    addMapping("Attack",);
  }


  @Override
  public void update(double deltaTime) {

  }
}
