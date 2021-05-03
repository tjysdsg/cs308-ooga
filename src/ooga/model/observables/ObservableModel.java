package ooga.model.observables;

import java.util.function.Consumer;

/**
 * An interface so that the view can observe the model and the game running
 * @author Oliver Rodas
 */
public interface ObservableModel {

  /**
   * Add a callback that calls an that the game ended when notified
   * @param callback
   */
  void setOnGameEnd(Consumer<Boolean> callback);

  /**
   * Add a callback that calls that a level has changed when notified
   * @param callback
   */
  void setOnLevelChange(Consumer<ObservableLevel> callback);

  /**
   * Add a callback that calls that text has been updated when notified
   * @param text callack to use
   */
  void setOnTextUpdate(Consumer<String> text);
}
