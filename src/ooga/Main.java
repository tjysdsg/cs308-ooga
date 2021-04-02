package ooga;

import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.View;

public class Main extends Application {
  /** A method to test (and a joke :). */
  public double getVersion() {
    return 0.001;
  }

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    CSSFX.start();
    View view = new View(stage);
  }
}
