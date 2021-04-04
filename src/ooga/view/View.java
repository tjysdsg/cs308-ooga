package ooga.view;

import fr.brouillard.oss.cssfx.CSSFX;
import java.util.ResourceBundle;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ooga.model.Model;
import ooga.model.ModelFactory;
import ooga.model.observables.ObservableModel;
import ooga.view.components.SplashScreen;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View {
  public static final int HEIGHT = 700;
  public static final int WIDTH = 700;
  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";
  private static final Logger logger = LogManager.getLogger(View.class);

  private Model model;
  private Runnable exitApplication;
  private ModelController modelController;
  private ObservableResource resources;

  public View(Stage stage) {
    CSSFX.start();
    this.resources = new ObservableResource();
    resources.setResources(ResourceBundle.getBundle(DEFAULT_RESOURCES + "English"));
    // this.modelController = model.getController();

    StackPane pane = new StackPane();
    SplashScreen splashScreen = new SplashScreen(HEIGHT, WIDTH, resources);
    splashScreen
        .getStylesheets()
        .add(getClass().getResource(RESOURCES + "main.css").toExternalForm());
    exitApplication =
        () -> {
          stage.close();
          logger.info("Exited Application");
        };
    splashScreen.setOnExit(exitApplication);

    logger.info("Displaying Splash Screen");
    stage.setScene(splashScreen);
    stage.show();
  }
}
