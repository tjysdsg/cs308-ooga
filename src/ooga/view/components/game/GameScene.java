package ooga.view.components.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.collections.ObservableMap;
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
import ooga.model.observables.ObservableLevel;
import ooga.model.observables.ObservableModel;
import ooga.view.Controller;
import ooga.view.ModelController;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A scene in which games are actually tracked and played.
 */
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
  private BiConsumer<Double, Double> resizeCallback;
  private ObservableLevel currentLevel;

  public GameScene(String directory, ObservableResource resources,
      ObservableMap<KeyCode, String> keymaps) {
    super(new StackPane(), WIDTH, HEIGHT, Color.BLACK);
    this.root = (StackPane) getRoot();
    this.model = new Model();
    this.controller = new Controller(model);
    controller.setKeyMap(keymaps);
    this.directory = directory;
    this.gameArea = new GameArea();
    this.loop = new GameLoop();
    this.images = new ImageConfiguration(directory);

    File gameDirectory = new File(directory);
    model.setOnNewObject(e -> {
      ObjectView obj = new ObjectView(e, images);
      gameArea.addObject(obj);
    });

    model.setOnLevelChange(this::updateScene);
    model.setOnObjectDestroy(e -> {
      gameArea.removeObject(e);
    });

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

  private void updateScene(ObservableLevel observableLevel) {
    currentLevel = observableLevel;
    setBackground(observableLevel.getBackgroundID());
    notifyResize();
  }

  private void setBackground(String newBackground) {
    BackgroundImage bg;
    Image bgImage = images.getImage(newBackground, WIDTH, HEIGHT);

    try {
      logger.info("Openning {} for background", bgImage.getUrl());
      bg = new BackgroundImage(
          bgImage,
          BackgroundRepeat.REPEAT,
          BackgroundRepeat.REPEAT,
          BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);

    } catch (Exception e) {
      throw new InvalidDataFileException(bgImage.getUrl());
    }

    gameArea.setBackground(new Background(bg));
  }

  private void handleInvalidGame() {
  }

  private void handlePress(KeyCode code) {
    if (code == KeyCode.ESCAPE) {
      logger.info("Escaping game");
      notifyEscape();
      return;
    }
    controller.handleKeyPress(code);
    System.out.println("THI SIS: " + KeyCode.getKeyCode(" "));
  }

  private void handleRelease(KeyCode code) {
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

  public void pauseGame() {
    loop.stop();
  }

  public void setOnResize(BiConsumer<Double, Double> resizeCallback) {
    this.resizeCallback = resizeCallback;
    notifyResize();
  }

  public void notifyResize() {
    if (resizeCallback != null) {
      resizeCallback.accept((double) currentLevel.getHeight(), (double) currentLevel.getWidth());
    }
  }

  public void playGame() {
    loop.start();
  }
}
