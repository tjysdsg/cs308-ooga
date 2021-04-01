package ooga.view;

import ooga.model.ObservableObject;

/** A view component which can track with a given gameobject. */
public class ObjectView {
  ObservableObject gameObject;

  public ObjectView(ObservableObject obj) {
    this.gameObject = obj;
  }
}
