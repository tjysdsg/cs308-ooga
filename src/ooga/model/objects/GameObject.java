package ooga.model.objects;

import java.util.ArrayList;
import java.util.List;
import ooga.model.Vector;
import ooga.model.actions.ActionInfo;
import ooga.model.components.Component;
import ooga.model.observables.ObservableObject;

public class GameObject implements ObservableObject, Comparable<GameObject> {

  private List<Component> components;
  private final int id;
  private final String name;
  private double x, y;
  private Vector velocity;
  private boolean collideable = true;
  private double height, width;
  List<ActionInfo> onCollide;

  public GameObject(int id, String name) {
    this.id = id;
    this.name = name;
    this.components = new ArrayList<>();
  }
  public List<ActionInfo> getActions() {
    return this.onCollide;
  }
  public boolean isA(String type) {
    return false;
  }

  // FIXME: since we use systems to update data, do we need this?
  @Override
  public void setOnUpdate(Runnable callback) {
  }

  public boolean isCollideable() {
    return collideable;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public double getHeight() {
    return 0;
  }

  @Override
  public double getWidth() {
    return 0;
  }

  @Override
  public int getMode() {
    return 0;
  }

  @Override
  public boolean isVisible() {
    return false;
  }

  @Override
  public String objectId() {
    return null;
  }

  @Override
  public String getName() {
    return name;
  }

  public List<Component> getComponents() {
    return components;
  }

  public void addComponent(Component component) {
    this.components.add(component);
  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public void setVelocityX(double velocityX) {
    this.velocity.setX(velocityX);
  }

  public void setVelocityY(double velocityY) {
    this.velocity.setY(velocityY);
  }

  @Override
  public int compareTo(GameObject o) {
    double diff = this.getX() - o.getX();
    return (int) Math.signum(diff);
  }
}