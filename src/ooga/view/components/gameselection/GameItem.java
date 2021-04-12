package ooga.view.components.gameselection;

import com.jfoenix.controls.JFXButton;

import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.function.Consumer;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class GameItem extends VBox {
  private Consumer<String> onLeftClick;
  private String directory;
  private String encodedPath;
  private String gameLabel;
  private Consumer<GameItem> onRightClick;

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
    game.setOnMouseClicked(e -> notifyAction(e));
    label.getStyleClass().add("game-item-label");
    getChildren().addAll(game, label);
//    setOnMouseClicked(e -> game.cl);
  }

  public void setOnLeftClick(Consumer<String> callback) {
    this.onLeftClick = callback;
  }
  public void setOnRightClick(Consumer<GameItem> callback) {
    onRightClick = callback;
  }

  private void notifyAction(MouseEvent e) {
    if (e.getButton() == MouseButton.PRIMARY) {
      if (this.onLeftClick != null) {
        onLeftClick.accept(this.directory);
      }
    } else if(e.getButton() == MouseButton.SECONDARY) {
      if (this.onRightClick != null) {
        onRightClick.accept(this);
      }
    }
  }

  private String getDirectory() {
    return this.directory;
  }
}
