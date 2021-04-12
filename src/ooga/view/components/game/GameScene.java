package ooga.view.components.game;

import javafx.scene.paint.Color;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import ooga.view.ModelController;
import ooga.model.observables.ObservableModel;
import ooga.view.util.ObservableResource;

/** A scene in which games are actually tracked and played. */
public class GameScene extends Scene {
  private static int WIDTH = 800;
  private static int HEIGHT = 500;
  private StackPane root;
  private ObservableModel game;
  private ModelController controller;
  private String directory;

  public GameScene(String directory, ObservableResource resources) {
    super(new StackPane(), WIDTH, HEIGHT, Color.BLACK);
    this.root = (StackPane) getRoot();
    root.getStyleClass().add("game-scene");
    GameArea gameArea = new GameArea();
    this.directory = directory;
    //this.controller = game.getController();
    root.getChildren().add(gameArea);
  }

  public void pauseGame() {}

  public void playGame() {}
}
