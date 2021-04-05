package ooga.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

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
