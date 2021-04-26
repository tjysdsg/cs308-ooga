package ooga.model.components;

import ooga.model.objects.GameObject;

public class CriticalHitMultiplier extends Component {

  private double multiplier = 2;
  private double chance  = 0.5;

  public CriticalHitMultiplier(int id, GameObject owner) {
    super(id, owner);
  }

  @Override
  public String typeUnerasure() {
    return CriticalHitMultiplier.class.getName();
  }

  public double getMultiplier() {
    if(Math.random()<chance){
      return multiplier;
    }
    else return 1;
  }

  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  public void setChance(double chance){
    this.chance=chance;
  }
}
