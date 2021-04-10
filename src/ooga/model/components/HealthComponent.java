package ooga.model.components;

import ooga.model.objects.GameObject;

public class HealthComponent extends Component {

  private double health = 0;

  public HealthComponent(int id, GameObject owner) {
    super(id, owner);
  }

  public double increment(double delta) {
    health += delta;
    return health;
  }

  public double getHealth() {
    return health;
  }

  public void setHealth(double health) {
    this.health = health;
  }
}
