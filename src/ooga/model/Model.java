package ooga.model;

import ooga.model.observables.ObservableModel;

public interface Model extends ObservableModel {
  void checkCollisions();
}
