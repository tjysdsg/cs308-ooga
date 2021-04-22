package ooga.model.components.equipment;

import ooga.model.objects.GameObject;

public class WeaponComponent extends EquipmentComponent{
  private double attackRange =1;


  public WeaponComponent(int id, GameObject owner) {
    super(id, owner);
  }

  public double getAttackRange(){
    return attackRange;
  }
  public void setAttackRange(double attackRange ){
    this.attackRange=attackRange;

  }

  public double getAttack(){
    healthIncrement(-1, false);
    return getPayLoad();
  }

}
