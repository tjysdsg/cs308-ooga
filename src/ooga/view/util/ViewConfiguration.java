package ooga.view.util;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/** The configurable parameters for the view. */
public class ViewConfiguration {

  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";

  private ObservableResource language = new ObservableResource();
  private static List<String> supportedLanguages;
  private static ResourceBundle systemProperties =
      ResourceBundle.getBundle("ooga.view.util.resources.System");

  public ViewConfiguration() {}

  /** Returns a trackable object from which you can get and set resources globally. */
  public ObservableResource getResources() {
    return this.language;
  }

  /**
   * Set the language of the project by modifying the observable resource.
   *
   * @param lang - The language properties file to set to.
   */
  public void setLanguage(String lang) {
    ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang);
    language.setResources(bundle);
  }

  /** @return - A list of all the languages supported */
  public static List<String> getSupportedLanguages() {
    if (supportedLanguages == null) {
      String langRaw = systemProperties.getString("SupportedLanguages");
      String[] langs = langRaw.split(",");
      supportedLanguages = Arrays.asList(langs);
    }
    return supportedLanguages;
  }
}
