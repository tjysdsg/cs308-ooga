package ooga.model.components;

import ooga.model.objects.GameObject;

public class Component {

  private transient GameObject owner;
  private int id;

  public Component(int id, GameObject owner) {
    this.id = id;
    this.owner = owner;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public GameObject getOwner() {
    return owner;
  }

  public void setOwner(GameObject owner) {
    this.owner = owner;
  }
}
