package ooga.model;

import java.util.List;

class GameLevel implements Level {
  Configuration gameConfiguration;
  private String name;
  int levelID;

  @Override
  public String getName() {
    return null;
  }

  //TODO: Probably won't need this. And can remove
  @Override
  public int getID() {
    return 0;
  }

  @Override
  public List<GameObject> generateObjects() {
    return null;
  }
}
