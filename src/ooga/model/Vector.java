package ooga.model;

import java.lang.Math;

public class Vector {
  private double x;
  private double y;

  public Vector(double x, double y) {
    this.x = x; this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Vector difference(Vector other) {
    return new Vector(x - other.getX(),
        y - other.getY());
  }

  public Vector unitVector(){
    Vector unit = new Vector(this.x,this.y);
    double magit=magnitude();
    unit.setX(this.x/magit);
    unit.setY(this.y/magit);
    return unit;
  }

  public double magnitude() {
    return Math.hypot(x, y);
  }

  public double sin() {
    return y / magnitude();
  }

  public double cos() {
    return x / magnitude();
  }
}
