package ooga.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXScrollPane;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import ooga.view.util.ObservableResource;
import org.kordamp.ikonli.javafx.FontIcon;

public class GSelectionScene extends Scene {
  private StackPane root;
  private FlowPane gamesList;

  public GSelectionScene(int width, int height, ObservableResource resources) {
    super(new StackPane(), width, height, Color.BLACK);
    this.root = (StackPane) getRoot();
    VBox gameSelectionCon = new VBox();

    this.gamesList = new FlowPane();
    gamesList.getStyleClass().add("game-selection");

    GameItem currentGame = new GameItem("/home/joshu/Pictures/");
    JFXButton addGame = new JFXButton();
    GameItem otherGame = new GameItem("/home/joshu/Pictures/");

    VBox addGameItem = new VBox();
    addGameItem.getStyleClass().addAll("game-item");
    addGame.setGraphic(new FontIcon());
    addGame.getStyleClass().addAll("add-game", "primary");

    Label addGameLabel = new Label("Add Game");
    addGameLabel.getStyleClass().addAll("game-item-label");
    addGameItem.getChildren().addAll(addGame, addGameLabel);

    Label gameSelectionTitle = new Label("Game Selection Is Dope");
    gameSelectionTitle.getStyleClass().add("title-heading");

    addGameItem.getStyleClass().addAll("add-game");
    gamesList.getChildren().addAll(currentGame, otherGame);
    gamesList.getChildren().add(addGameItem);
    HBox.setHgrow(gamesList, Priority.ALWAYS);

    //TODO: gamesList is going to be in an hbox with the drawer menu thing
    gameSelectionCon.getChildren().addAll(gameSelectionTitle, gamesList);

    this.root.getChildren().add(gameSelectionCon);
  }
}


class GameItem extends VBox {
  public GameItem(String gamePath) {
    getStyleClass().addAll("game-item");
    JFXButton game = new JFXButton();
    game.getStyleClass().addAll("game-item-button", "primary");
    game.setGraphic(new FontIcon());
    VBox.setVgrow(game, Priority.ALWAYS);
    Label label = new Label();
    if (!gamePath.isEmpty()) {
      game.setStyle("-fx-background-image: url('file:" + gamePath + "thumbnail.jpg');");
      label.setText("VERY LONG LABEL FOR THIS THING");
    } else {
      label.setText("Add Game");
    }
    label.getStyleClass().add("game-item-label");
    getChildren().addAll(game, label);
  }
}
