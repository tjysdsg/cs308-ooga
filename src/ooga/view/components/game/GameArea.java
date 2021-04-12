package ooga.view.components.game;

import ooga.model.observables.ObservableObject;
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

    objectsPane.setTranslateX(LEFT_EDGE);
    getChildren().add(objectsPane);
    AnchorPane.setBottomAnchor(objectsPane, BOTTOM_EDGE);
    AnchorPane.setLeftAnchor(objectsPane, LEFT_EDGE);
  }

  public void setCameraCenter(ObjectView center) {
    DoubleProperty prop = center.translateXProperty();
    objectsPane.translateXProperty().bind(prop.multiply(-1));
  }

  public void addObject(ObservableObject object) {
    objectsPane.getChildren().add(new ObjectView(object));
  }

  public void removeObject(ObservableObject object) {
    objectsPane.getChildren().remove(object);
  }
}
