package ooga.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Level {
  String getName();
  int getID();
  List<GameObject> generateObjects();
}
