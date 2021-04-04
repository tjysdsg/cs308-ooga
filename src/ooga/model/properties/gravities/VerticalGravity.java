package ooga.model.properties.gravities;

import ooga.model.Vector;

public class VerticalGravity implements Gravity {
  private double amount;

  public VerticalGravity(double amount) {
    this.amount = amount;
  }

  @Override
  public Vector calculate(Vector object) {
    return new Vector(0.0, amount);
  }
}
