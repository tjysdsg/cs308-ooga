package ooga.view.util;

import java.util.ResourceBundle;

public class ViewConfiguration {
  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";
  private String language = "English";
  private transient ObservableResource resources = new ObservableResource();

  // Moshi needs this
  public ViewConfiguration(){
  }

  public static ResourceBundle stringToBundle(String language) {
    return ResourceBundle.getBundle(DEFAULT_RESOURCES + language);
  }

  public ObservableResource getResources() {
    return this.resources;
  }

}
