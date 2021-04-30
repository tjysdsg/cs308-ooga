package ooga.view.util;

import com.squareup.moshi.Json;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;

/**
 * Game specific configurations.
 * */
public class GameConfiguration {
  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";

  @Json(name = "key_maps")
  private ObservableMap<KeyCode, String> keyMaps;

  private ObservableList<String> stats;

  // Moshi needs this
  public GameConfiguration() {}

  /**
   * @return - A trackable map of key maps.
   */
  public ObservableMap<KeyCode, String> getKeyMap() {
    return this.keyMaps;
  }

  /**
   * The
   * @return - A trackable list of desired stats to be viewed
   */
  public ObservableList<String> getStats() {
    return this.stats;
  }
}
