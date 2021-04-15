package ooga.view.components.game;

import ooga.model.observables.ObservableObject;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GameArea extends AnchorPane {
  private static final double LEFT_EDGE = 20;
  private static final double BOTTOM_EDGE = 0;
  private StackPane objectsPane;

  public GameArea() {
    this.objectsPane = new StackPane();
    getStyleClass().add("game-area");

    // Needed so pane doesn't shrink
    JFXButton placeholder = new JFXButton();
    placeholder.setVisible(false);
    objectsPane.getChildren().add(placeholder);

    objectsPane.setTranslateX(LEFT_EDGE);
    getChildren().add(objectsPane);
    AnchorPane.setBottomAnchor(objectsPane, BOTTOM_EDGE);
    AnchorPane.setLeftAnchor(objectsPane, LEFT_EDGE);
  }

  public void setCameraCenter(ObjectView center) {
    DoubleProperty prop = center.translateXProperty();
    objectsPane.translateXProperty().bind(prop.multiply(-1));
  }

  public void addObject(ObjectView object) {
    objectsPane.getChildren().add(object);
  }

  public void removeObject(ObjectView object) {
    objectsPane.getChildren().remove(object);
  }
}
