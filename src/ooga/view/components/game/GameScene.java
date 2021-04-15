package ooga.view.components.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ooga.model.Model;
import ooga.model.ModelFactory;
import ooga.model.exceptions.InvalidDataFileException;
import ooga.model.observables.ObservableModel;
import ooga.view.Controller;
import ooga.view.ModelController;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** A scene in which games are actually tracked and played. */
public class GameScene extends Scene {
  private static final Logger logger = LogManager.getLogger(GameScene.class);
  private static int WIDTH = 800;
  private static int HEIGHT = 500;
  private StackPane root;
  private ObservableModel game;
  private ModelController controller;
  private Model model;
  private String directory;
  private GameArea gameArea;
  private Consumer<StackPane> onEscape;
  private GameLoop loop;
  private ImageConfiguration images;

  public GameScene(String directory, ObservableResource resources) {
    super(new StackPane(), WIDTH, HEIGHT, Color.BLACK);
    this.root = (StackPane) getRoot();
    this.model = new Model();
    this.controller = new Controller(model);
    this.directory = directory;
    this.gameArea = new GameArea();
    this.loop = new GameLoop();
    this.images = new ImageConfiguration(directory);

    File gameDirectory = new File(directory);
    model.setOnNewObject(e -> {
      ObjectView obj = new ObjectView(e, images);
      gameArea.addObject(obj);
    });

    BackgroundImage bg;
    try {
      logger.info("%n%n%nOpenning {} for background%n%n%n", directory + "images/sunny_day.png");
       bg = new BackgroundImage(
          new Image(
            new FileInputStream(directory + "images/sunny_day.png")),
          BackgroundRepeat.REPEAT,
          BackgroundRepeat.REPEAT,
          BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);

    } catch (Exception e) {
      throw new InvalidDataFileException("thing");
    }

    gameArea.setBackground(new Background(bg));

    if (!ModelFactory.verifyGameDirectory(gameDirectory)) {
      handleInvalidGame();
    }
    try {
      model.setGame(gameDirectory);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (InvalidDataFileException e) {
      e.printStackTrace();
    }

    root.getStyleClass().add("game-scene");
    // this.controller = game.getController();
    root.getChildren().add(gameArea);
    gameArea.requestFocus();
    setOnKeyPressed(e -> handlePress(e.getCode()));
    setOnKeyReleased(e -> handleRelease(e.getCode()));
    loop.setOnUpdate(controller::step);
    loop.start();
  }

  private void handleInvalidGame() {}

  private void handlePress(KeyCode code) {
     if (code == KeyCode.ESCAPE) {
       logger.info("Escaping game");
       notifyEscape();
       return;
     }
    controller.handleKeyPress(code);
  }

  private void handleRelease(String code) {
    controller.handleKeyRelease(code);
  }

  private void notifyEscape() {
    if (this.onEscape != null) {
      onEscape.accept(this.root);
    }
  }

  /**
   * Notify that the user has tried to escape the game.
   *
   * <p>Passes a reference of the root to allow the parent to show a pause menu or anything else
   *
   * @param callback - The callback that will be called.
   */
  public void setOnEscape(Consumer<StackPane> callback) {
    this.onEscape = callback;
  }

  private void handleRelease(KeyCode code) {}

  public void pauseGame() {}

  public void playGame() {}
}
