package ooga.model.actions;

import java.util.Map;
import ooga.model.objects.GameObject;

public class CollisionAction {

  private GameObject self;
  private GameObject hitter;
  private Map<String, String> payload;

  public CollisionAction(GameObject self, GameObject hitter, Map<String, String> payload) {
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

  public Map<String, String> getPayload() {
    return payload;
  }
}
