package ooga.model;

import java.util.List;
import java.util.function.Consumer;
import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;
import ooga.model.observables.ObservableLevel;

/**
 * This interface allows the model to access a game level without having to worry about its implementation
 */
public interface Level {

  /**
   * @return the name of the object
   */
  String getName();


  /**
   * @return the ID of the object
   */
  int getID();

  /**
   * Update the time for given time
   * @param deltaTime
   */
  void update(double deltaTime);

  /**
   * @return The game objects for a level
   */
  List<GameObject> generateObjects();

  /**
   * @return The ECManager of the level
   */
  ECManager getECManager();

  /**
   * Handle a code from the controller as an input
   * @param k the input
   * @param on if the input was on or off
   */
  void handleCode(String k, boolean on);

  String getBackgroundID();

  /**
   * The level must be able to be made as an observable
   * @return the level as an observable
   */
  ObservableLevel asObservable();

  /**
   * Add a callback to set an update when the level ends
   * @param update if the level ended
   */
  void setOnLevelEnd(Consumer<Boolean> update);

  /**
   * @return the number of the level
   */
  int getLevelNumber();
}
