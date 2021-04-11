package ooga.view;

import ooga.model.observables.ObservableObject;

/** A view component which can track with a given gameobject. */
public class ObjectView {
  ObservableObject gameObject;

  public ObjectView(ObservableObject obj) {
    this.gameObject = obj;
  }
}
