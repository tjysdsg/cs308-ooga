package ooga.model.properties.gravities;

import ooga.model.Vector;

public interface Gravity {
  Vector calculate(Vector object);
}
