package ooga.view;

import fr.brouillard.oss.cssfx.CSSFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.*;
import ooga.view.components.SplashScreen;
import ooga.view.components.game.GameScene;
import ooga.view.components.gameselection.GSelectionScene;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View {
  private static final double FADE_OPACITY = 0.1;
  private static final int TRANSITION_SPEED = 700; // milliseconds
  public static final int HEIGHT = 700;
  public static final int WIDTH = 700;
  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";
  private static final Logger logger = LogManager.getLogger(View.class);

  private Runnable exitApplication;
  private ObservableResource resources;
  private Scene currentScene;
  private FadeTransition fadeOutTransition;
  private FadeTransition fadeInTransition;
  private Stage stage;
  private String cssFile;

  public View(Stage stage) {
    CSSFX.start();
    this.stage = stage;
    this.resources = new ObservableResource();
    resources.setResources(ResourceBundle.getBundle(DEFAULT_RESOURCES + "English"));
    SplashScreen splashScreen = new SplashScreen(HEIGHT, WIDTH, resources);
    GSelectionScene gameSelection = new GSelectionScene(HEIGHT, WIDTH, resources);
    // this.modelController = model.getController();
    final URL cssFileURL = getClass().getResource(RESOURCES + "main.css");
    if (cssFileURL != null) {
      this.cssFile = cssFileURL.toExternalForm();
      gameSelection.getStylesheets().add(cssFile);
      splashScreen.getStylesheets().add(cssFile);
    } else {
      logger.warn("Css file could not be loaded");
    }
    createAnimations();
    // setScene(splashScreen);
    startGame("data/example/");
    gameSelection.setOnGameSelected(this::startGame);
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
    splashScreen.setOnPlay(() -> setScene(gameSelection));

    logger.info("Displaying Splash Screen");
    stage.show();
  }

  private void startGame(String directory) {
    logger.info("Game Selected {}", directory);
    // TODO: Have a check if a game is currently playing and ask
    // if want to quit
    GameScene newGame = new GameScene(directory, resources);
    if (!cssFile.isBlank()) {
      newGame.getStylesheets().add(cssFile);
    }
    setScene(newGame);
  }

  private void createAnimations() {
    fadeOutTransition = new FadeTransition(Duration.millis(TRANSITION_SPEED));
    fadeOutTransition.setFromValue(1.0);
    fadeOutTransition.setToValue(FADE_OPACITY);

    fadeInTransition = new FadeTransition(Duration.millis(TRANSITION_SPEED));
    fadeInTransition.setFromValue(FADE_OPACITY);
    fadeInTransition.setToValue(1.0);
  }

  private void setScene(Scene scene) {
    if (this.currentScene != null) {
      fadeOutTransition.setNode(currentScene.getRoot());
      fadeOutTransition.play();
      fadeOutTransition.setOnFinished(
          e -> {
            scene.getRoot().setStyle("-fx-opacity: " + FADE_OPACITY);
            fadeInTransition.setNode(scene.getRoot());
            stage.setScene(scene);
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
