package ooga.model;

public class LevelFactory {

  GameLevel level;

  public LevelFactory(String ObjectsFile) {
    // Load the presets for the levels
  }

  Level buildLevel(String levelFile) {
    // Use the object Factory to create the game objects
    // TODO: FIXME: OLIVER: REMEMBER TO CALL level.init()
    level.init();
    return level;
  }
}
