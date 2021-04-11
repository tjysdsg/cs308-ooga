package ooga.model.components;

import ooga.model.objects.GameObject;

public class HealthComponent extends Component {

  protected double health = 0;

  public HealthComponent(int id, GameObject owner) {
    super(id, owner);
  }

  public void healthIncrement(double delta) {
    health += delta;
  }

  public double getHealth() {
    return health;
  }

  public void setHealth(double health) {
    this.health = health;
  }
}
