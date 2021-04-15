package ooga.view.components.game;

import java.io.File;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ooga.model.ModelFactory;
import ooga.model.observables.ObservableModel;
import ooga.view.ModelController;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.Logger;

/** A scene in which games are actually tracked and played. */
public class GameScene extends Scene {
  private static final Logger logger = LogManager.getLogger(GameScene.class);
  private static int WIDTH = 800;
  private static int HEIGHT = 500;
  private StackPane root;
  private ObservableModel game;
  private ModelController controller;
  private String directory;
  private Consumer<StackPane> onEscape;

  public GameScene(String directory, ObservableResource resources) {
    super(new StackPane(), WIDTH, HEIGHT, Color.BLACK);
    this.root = (StackPane) getRoot();
    root.getStyleClass().add("game-scene");
    GameArea gameArea = new GameArea();
    this.directory = directory;
    File gameDirectory = new File(directory);
    if (!ModelFactory.verifyGameDirectory(gameDirectory)) {
      handleInvalidGame();
    } 
    // this.controller = game.getController();
    root.getChildren().add(gameArea);
    gameArea.requestFocus();
    setOnKeyPressed(e -> handlePress(e.getCode()));
    setOnKeyReleased(e -> handleRelease(e.getCode()));
  }

  private void handleInvalidGame() {

  }

  private void handlePress(KeyCode code) {
    if (code == KeyCode.ESCAPE) {
      notifyEscape();
      return;
    }
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
