package ooga.view.util;

import com.squareup.moshi.Json;
import java.util.ResourceBundle;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;

public class ViewConfiguration {

  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";
  @Json(name = "key_maps")
  ObservableMap<KeyCode, String> keyMaps;
  private String language = "English";
  private transient ObservableResource resources = new ObservableResource();

  // Moshi needs this
  public ViewConfiguration() {
  }

  public static ResourceBundle stringToBundle(String language) {
    return ResourceBundle.getBundle(DEFAULT_RESOURCES + language);
  }

  public ObservableMap<KeyCode, String> getKeyMap() {
    return this.keyMaps;
  }

  public ObservableResource getResources() {
    return this.resources;
  }
}
