package ooga.view;

import fr.brouillard.oss.cssfx.CSSFX;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.*;
import ooga.model.Model;
import ooga.view.components.SplashScreen;
import ooga.view.components.GSelectionScene;
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
  private Scene currentScene;
  private FadeTransition fadeOutTransition;
  private FadeTransition fadeInTransition;
  private Stage stage;

  public View(Stage stage) {
    CSSFX.start();
    this.stage = stage;
    this.resources = new ObservableResource();
    resources.setResources(ResourceBundle.getBundle(DEFAULT_RESOURCES + "English"));
    // this.modelController = model.getController();

    StackPane pane = new StackPane();
    SplashScreen splashScreen = new SplashScreen(HEIGHT, WIDTH, resources);
    GSelectionScene gameSelection = new GSelectionScene(HEIGHT, WIDTH, resources);
    splashScreen
        .getStylesheets()
        .add(getClass().getResource(RESOURCES + "main.css").toExternalForm());
    createAnimations();
    setScene(splashScreen);

    exitApplication =
        () -> {
          fadeOutTransition.setNode(currentScene.getRoot());
          fadeOutTransition.play();
          fadeOutTransition.setOnFinished(
              e -> {
                stage.close();
                logger.info("Exited Application");
              });
        };
    splashScreen.setOnExit(exitApplication);
    splashScreen.setOnPlay( () -> setScene(gameSelection));
    gameSelection
        .getStylesheets()
        .add(getClass().getResource(RESOURCES + "main.css").toExternalForm());
    logger.info("Displaying Splash Screen");
    stage.show();
  }

  private void createAnimations() {
    fadeOutTransition = new FadeTransition(Duration.millis(1000));
    fadeOutTransition.setFromValue(1.0);
    fadeOutTransition.setToValue(0.05);

    fadeInTransition = new FadeTransition(Duration.millis(800));
    fadeInTransition.setFromValue(0.05);
    fadeInTransition.setToValue(1.0);
  }

  private void setScene(Scene scene) {
    if (this.currentScene != null) {
      fadeOutTransition.setNode(currentScene.getRoot());
      fadeOutTransition.play();
      fadeOutTransition.setOnFinished( e -> {
        stage.setScene(null);
        stage.setScene(scene);
        fadeInTransition.setNode(scene.getRoot());
        fadeInTransition.play();
      });
    } else {
      stage.setScene(scene);
      fadeInTransition.setNode(scene.getRoot());
      fadeInTransition.play();
    }
    this.currentScene = scene;
  }
}
