package ooga.model.components.enemy;

import ooga.model.components.Component;
import ooga.model.objects.GameObject;

public class HateComponent extends Component {

  private double range;
  private boolean hate;
  private int party; // different parties will have different hate relationship
  private int frequency = 240;
  private double damage=10;

  public HateComponent(){};

  public HateComponent(int id, GameObject owner) {
    super(id, owner);
  }

  @Override
  public String typeUnerasure() {
    return HateComponent.class.getName();
  }

  public void setRange(double range) {
    this.range = range;
  }

  public boolean detectHate(double distance) {
    return distance < this.range;
  }

  public int getFrequency() {
    return frequency;
  }

  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  public boolean getHate() {
    return hate;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }
}
