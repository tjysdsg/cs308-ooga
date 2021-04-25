package ooga.view.util;

import com.squareup.moshi.Json;
import java.util.ResourceBundle;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;

public class ViewConfiguration {
  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";
  private String language = "English";

  @Json(name = "key_maps")
  ObservableMap<KeyCode, String> keyMaps;

  private transient ObservableResource resources = new ObservableResource();

  // Moshi needs this
  public ViewConfiguration() {}

  public static ResourceBundle stringToBundle(String language) {
    return ResourceBundle.getBundle(DEFAULT_RESOURCES + language);
  }

  public ObservableResource getResources() {
    return this.resources;
  }
}
