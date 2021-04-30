package ooga.view.components.gameselection;

import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.view.util.ObservableResource;

public class GSelectionScene extends Scene {

  private StackPane root;
  private GameList gamesList;
  private GSelectionView gameView;

  public GSelectionScene(int width, int height, ObservableResource resources) {
    super(new StackPane(), width, height, Color.BLACK);
    this.root = (StackPane) getRoot();

    gamesList = new GameList(resources, root);
    this.gameView = new GSelectionView(resources);
    gamesList.setOnSelection(gameView::setDirectory);
    VBox gameSelectionCon = new VBox();
    root.setId("selection-scene");
    Label gameSelectionTitle = new Label();
    gameSelectionTitle.textProperty().bind(resources.getStringBinding("GameSelection"));
    gameSelectionTitle.getStyleClass().add("title-heading");

    ScrollPane gameListScroll = new ScrollPane();
    gameListScroll.setContent(gamesList);
    HBox.setHgrow(gamesList, Priority.ALWAYS);

    HBox gameBrowser = new HBox();
    VBox.setVgrow(gameBrowser, Priority.ALWAYS);
    HBox.setHgrow(gameListScroll, Priority.ALWAYS);
    gameBrowser.getChildren().addAll(gameListScroll, gameView);

    gameSelectionCon.getChildren().addAll(gameSelectionTitle, gameBrowser);

    this.root.getChildren().add(gameSelectionCon);
  }

  /**
   * Set the callback for when a game is target to be played.
   *
   * @param callback - A function that is passed a string of the gamedirectory.
   */
  public void setOnGameSelected(Consumer<String> callback) {
    this.gameView.setOnPlayRequested(callback);
    this.gamesList.setOnRun(callback);
  }
}
