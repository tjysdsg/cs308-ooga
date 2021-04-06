package ooga.view.components.gameselection;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.MalformedURLException;
import java.util.function.Consumer;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class GameItem extends VBox {
  private Consumer<String> onClick;
  private String directory;
  private String encodedPath;
  private String gameLabel;

  public GameItem(String gamePath) {
    this.directory = gamePath;
    try {
      File gameFile = new File(gamePath);
      this.gameLabel = gameFile.getName();
      this.encodedPath = new File(gamePath).toURI().toURL().toString();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    getStyleClass().addAll("game-item");
    JFXButton game = new JFXButton();
    game.getStyleClass().addAll("game-item-button", "primary");
    game.setGraphic(new FontIcon());
    VBox.setVgrow(game, Priority.ALWAYS);
    Label label = new Label();
    if (!gamePath.isEmpty()) {
      game.setStyle("-fx-background-image: url('" + encodedPath + "thumbnail.jpg');");
      label.setText(gameLabel);
    }
    game.setOnAction(e -> notifyAction());
    label.getStyleClass().add("game-item-label");
    getChildren().addAll(game, label);
    setOnMouseClicked(e -> notifyAction());
  }

  public void setOnAction(Consumer<String> callback) {
    this.onClick = callback;
  }

  private void notifyAction() {
    if (this.onClick != null) {
      onClick.accept(this.directory);
    }
  }

  private String getDirectory() {
    return this.directory;
  }
}
