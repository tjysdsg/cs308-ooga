package ooga.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import javafx.scene.input.KeyCode;

/**
 * An interface used to make modifications to a model.
 *
 * <p>The view communicates through this interface rather than touching the model directly.
 */
public interface ModelController {

  /**
   * Manually set the level to be displayed within the game.
   *
   * @param levelName - The name of the level to be set.
   * @throws FileNotFoundException - If the level file is not found.
   */
  void setCurrentLevel(String levelName) throws FileNotFoundException;

  /**
   * Translate an input into something which the model can read
   *
   * @param code - The keycode of what was pressed.
   */
  void handleKeyPress(KeyCode code);

  /**
   * Translate an input into something which the model can read
   *
   * @param code - The keycode of what was released.
   */
  void handleKeyRelease(KeyCode code);

  /**
   * Give the map needed to translate keyboard actions into model readable commands.
   *
   * @param map - The mapping of keycode to model command
   */
  void setKeyMap(Map<KeyCode, String> map);

  /**
   * Returns a list of the strings which can trigger certain actions within the model.
   *
   * @return The strokes that can be used to trigger actions.
   */
  List<String> getAvailableActions();

  /**
   * Set the stroke mapping for a certain action
   *
   * @param stroke - The key that will be pressed
   * @param actions - The action that will result from the pressed key
   */
  void setStrokeMapping(KeyCode code, String action);

  /**
   * Get a list of the levels available in the game.
   *
   * @return The list of levels within the game
   */
  List<String> getLevels();

  /**
   * Move the game forward a specified amount of time
   *
   * @param delta - The amount of time to move forward
   */
  void step(double delta);

  /**
   * Start a game in the specified directory
   *
   * @param gameDirectory - The location to begin a game
   * @throws FileNotFoundException - Thrown if the directory does not exist
   */
  void setGame(File gameDirectory) throws FileNotFoundException;
}
