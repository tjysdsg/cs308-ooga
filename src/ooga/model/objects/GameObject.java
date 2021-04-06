package ooga.model.objects;

import java.util.ArrayList;
import java.util.List;
import ooga.model.components.Component;
import ooga.model.observables.ObservableObject;

public class GameObject implements ObservableObject {

  private List<Component> components;
  private final int id;
  private final String name;
  private double x, y;
  private double height, width;

  public GameObject(int id, String name) {
    this.id = id;
    this.name = name;
    this.components = new ArrayList<>();
  }

  boolean isA(String type) {
    return false;
  }

  @Override
  public void setOnUpdate(Runnable callback) {

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
  public String getType() {
    return name;
  }

  public List<Component> getComponents() {
    return components;
  }

  public void addComponent(Component component) {
    this.components.add(component);
  }
}