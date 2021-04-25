package ooga.model.observables;

import java.util.List;
import java.util.function.Consumer;
import ooga.model.StatsInfo;

public interface ObservableLevel {

  String getBackgroundID();

  int getHeight();

  int getWidth();

  /**
   * Set the callback that is called, when the game object that's being focused on by main camera is
   * changed
   *
   * @param callback The integer represents the object id
   */
  void setOnFocusUpdate(Consumer<Integer> callback);

  /**
   * Set the callback that is called, when a certain statistics is updated, such as "score", or
   * "position"
   */
  void setOnStatsUpdate(String statsName, Consumer<List<StatsInfo>> callback);

  /**
   * Retrieve the names of all statistics that's available in this game
   * <p>
   * The returned strings can be used for the first parameter in {@link
   * ObservableLevel#setOnStatsUpdate(String, Consumer)}
   */
  List<String> getAvailableStats();
}
