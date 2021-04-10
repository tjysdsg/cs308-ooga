package ooga.model.actions;

import ooga.model.objects.GameObject;

public class CollisionAction {

  private GameObject self;
  private GameObject hitter;
  private String payload;

  public CollisionAction(GameObject self, GameObject hitter, String payload) {
    this.self = self;
    this.hitter = hitter;
    this.payload = payload;
  }

  public GameObject getSelf() {
    return self;
  }

  public GameObject getHitter() {
    return hitter;
  }

  public String getPayload() {
    return payload;
  }
}
