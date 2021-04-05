package ooga.model.components;

import ooga.model.GameObject;

public class Component {

  private GameObject owner;
  private int id;

  public Component(int id, GameObject owner) {
    this.owner = owner;
  }
}
