package ooga.model;

import ooga.model.observables.ObservableObject;

public class GameObject implements ObservableObject {

  boolean isA(String type) {
    return false;
  }

  @Override
  public void setOnUpdate(Runnable callback) {

  }

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public double getY() {
    return 0;
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
}