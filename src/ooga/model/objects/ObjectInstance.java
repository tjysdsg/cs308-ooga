package ooga.model.objects;

import ooga.model.Vector;

public class ObjectInstance {

  private String name;
  private double x, y;
  private Vector velocity;

  public ObjectInstance(String name, double x, double y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }

  public ObjectInstance(String name, double x, double y, Vector velocity) {
    this(name, x, y);
    this.velocity = velocity;
  }

  public ObjectInstance(GameObject object) {
    this.name = object.getName();
    this.x = object.getX();
    this.y = object.getY();
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public String getName() {
    return name;
  }

  public Vector getVelocity() {
    return velocity;
  }
}
