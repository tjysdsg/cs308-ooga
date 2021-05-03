package ooga.view.util;

import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A class that allows bindings for resources to allow bindings between strings and anything that
 * takes string bindings.
 */
public class ObservableResource {

  private ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();

  private ObjectProperty<ResourceBundle> resourcesProperty() {
    return resources;
  }

  private ResourceBundle getResources() {
    return resourcesProperty().get();
  }

  /**
   * Set the localization to be updated among all bindings.
   *
   * @param resources
   */
  public void setResources(ResourceBundle resources) {
    resourcesProperty().set(resources);
  }

  /** @return The language of the currently active resource */
  public String getLanguage() {
    ResourceBundle res = resourcesProperty().get();
    if (res != null) {
      return res.getString("LANGUAGE");
    }
    return null;
  }

  /**
   * Get an autoupdating binding for whatever localization set.
   *
   * @param key - The string to get localization for
   * @return - The observable string tto bind to text properties.
   */
  public StringBinding getStringBinding(String key) {
    return new StringBinding() {
      {
        bind(resourcesProperty());
      }

      @Override
      protected String computeValue() {
        return getResources().getString(key);
      }
    };
  }
}
