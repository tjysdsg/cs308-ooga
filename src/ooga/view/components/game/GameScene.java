package ooga.view.components.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
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
import ooga.model.observables.ObservableLevel;
import ooga.view.Controller;
import ooga.view.ModelController;
import ooga.view.components.SettingsModule;
import ooga.view.util.ConfigurationFactory;
import ooga.view.util.GameConfiguration;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** A scene in which games are actually tracked and played. */
public class GameScene extends Scene {

  private static final Logger logger = LogManager.getLogger(GameScene.class);
  private static int WIDTH = 800;
  private static int HEIGHT = 500;
  private StackPane root;
  private ModelController controller;
  private Model model;
  private GameArea gameArea;
  private Consumer<StackPane> onEscape;
  private GameLoop loop;
  private ImageConfiguration images;
  private ObservableLevel currentLevel;
  private StatsView statsView;
  private GameConfiguration gameConfiguration;
  private ObservableResource resources;
  private SettingsModule settings;
  private Consumer<Boolean> ionly;

  public GameScene(String directory, ObservableResource resources) {
    super(new StackPane(), WIDTH, HEIGHT, Color.BLACK);
    logger.debug("Game scene constructing for {}", directory);
    this.resources = resources;
    this.root = (StackPane) getRoot();
    this.model = new Model();
    this.controller = new Controller(model);
    String defaultConfig = "";
    defaultConfig = Paths.get(directory + "view.json").toString();
    this.gameConfiguration = ConfigurationFactory.createConfiguration(defaultConfig);

    controller.setKeyMap(gameConfiguration.getKeyMap());
    this.statsView = new StatsView(resources);
    this.loop = new GameLoop();
    this.images = new ImageConfiguration(directory);
    // Temporary

    statsView.addStatistics(gameConfiguration.getStats());
    //    statsView.updateStat("Health", "30");
    //    statsView.updateStat("Points", "50");
    // End temporary
    File gameDirectory = new File(directory);

    model.setOnLevelChange(this::updateScene);
    model.setOnGameEnd(this::notifyEnd);

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
    setOnKeyPressed(e -> handlePress(e.getCode()));
    setOnKeyReleased(e -> handleRelease(e.getCode()));
    setupSettings();
    loop.setOnUpdate(controller::step);
    loop.start();
  }

  private void updateScene(ObservableLevel observableLevel) {
    this.currentLevel = observableLevel;
    if (this.gameArea != null) {
      root.getChildren().remove(gameArea);
    }
    this.gameArea = new GameArea(observableLevel, statsView);
    root.getChildren().add(gameArea);
    observableLevel
        .getAvailableGameObjects()
        .forEach(
            obj -> {
              gameArea.addObject(new ObjectView(obj, images));
            });
    observableLevel.setOnNewObject(
        e -> {
          ObjectView obj = new ObjectView(e, images);
          gameArea.addObject(obj);
        });
    observableLevel.setOnObjectDestroy(
        e -> {
          gameArea.removeObject(e);
        });
    logger.info("Available stats {}", observableLevel.getAvailableStats());
    setBackground(observableLevel.getBackgroundID());
  }

  private void setBackground(String newBackground) {
    BackgroundImage bg;
    Image bgImage = images.getImage(newBackground, WIDTH, HEIGHT);

    try {

      logger.info("Openning {} for background", bgImage.getUrl());
      bg =
          new BackgroundImage(
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

  private void setupSettings() {
    this.settings = new SettingsModule(resources.getStringBinding("GameSettings"));
    settings.addKeysOption(gameConfiguration.getKeyMap(), currentLevel.getAvailableInputs());
  }

  /** @return The settings this game contains. */
  public SettingsModule getSettings() {
    return this.settings;
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

  private void handleRelease(KeyCode code) {
    controller.handleKeyRelease(code);
  }

  private void notifyEscape() {
    if (this.onEscape != null) {
      onEscape.accept(this.root);
    }
  }

  /**
   * Set a listener for when the game ends.
   *
   * <p>The boolean specifies whether the game was won or lost
   *
   * @param callback - The function that will be run when the game ends
   */
  public void setOnEnd(Consumer<Boolean> callback) {
    this.ionly = callback;
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

  /** Pause the game */
  public void pauseGame() {
    loop.pause();
  }

  // b ruh
  private void notifyEnd(boolean victory) {
    if (this.ionly != null) {
      ionly.accept(victory); // 😤
    }
  }

  /** @return The root stackpane */
  public StackPane getRootCover() {
    return this.root;
  }

  /** Unpause the game. */
  public void playGame() {
    loop.play();
  }
}
