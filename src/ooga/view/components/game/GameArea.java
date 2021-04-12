package ooga.view.components.game;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GameArea extends AnchorPane {
  private static final double LEFT_EDGE = 20;
  private static final double BOTTOM_EDGE = 100;
  private StackPane objectsPane;

  public GameArea() {
    this.objectsPane = new StackPane();
    getStyleClass().add("game-area");
    objectsPane.setStyle("-fx-background-color: green");
    JFXButton addGame = new JFXButton();
    JFXButton removeGame = new JFXButton();
    removeGame.setTranslateX(100);
    addGame.setOnAction(
        e -> {
          double x = addGame.getTranslateX();
          addGame.setTranslateX(x + 10);
        });
    objectsPane.setTranslateX(LEFT_EDGE);
    objectsPane.getChildren().addAll(addGame, removeGame);
    getChildren().add(objectsPane);
    DoubleProperty prop = addGame.translateXProperty();
    objectsPane.translateXProperty().bind(prop.multiply(-1));
    AnchorPane.setBottomAnchor(objectsPane, BOTTOM_EDGE);
    AnchorPane.setLeftAnchor(objectsPane, LEFT_EDGE);
  }

  public void setCameraCenter(ObjectView center) {

  }
}
