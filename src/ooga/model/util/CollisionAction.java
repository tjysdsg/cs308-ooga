package ooga.model.util;

import ooga.model.objects.GameObject;

public class ActionEvent {

  private GameObject self;
  private GameObject hitter;

  public ActionEvent(GameObject self, GameObject hitter) {

    this.self = self;
    this.hitter = hitter;
  }
}
