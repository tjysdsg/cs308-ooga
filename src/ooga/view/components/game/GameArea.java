package ooga.view.components.game;

import ooga.model.observables.ObservableObject;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GameArea extends AnchorPane {
  private static final Logger logger = LogManager.getLogger(GameArea.class);
  private static final double LEFT_EDGE = 20;
  private static final double BOTTOM_EDGE = 0;
  private List<ObjectView> objects;
  private StackPane objectsPane;

  public GameArea() {
    this.objectsPane = new StackPane();
    getStyleClass().add("game-area");
    this.objects = new ArrayList<>();
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
    center.translateXProperty().addListener((xProp, oldVal, newVal) -> offsetX(newVal));
    center.translateYProperty().addListener((yProp, oldVal, newVal) -> offsetY(newVal));
  }

  private void offsetX(Number objectValue) {
    double position = objectValue.doubleValue() * -1;
    if (position < -200) {
      objectsPane.translateXProperty().set(position + 200);
    }
  }

  private void offsetY(Number objectValue) {
    double position = objectValue.doubleValue() * -1;
    logger.info("obj position: {}", position);
    if (position > 250) {
      objectsPane.translateYProperty().set(position - 200 );
    }
  }

  public void addObject(ObjectView object) {
    if(objectsPane.getChildren().size() == 1) {
      setCameraCenter(object);
    }
    objects.add(object);
    objectsPane.getChildren().add(object);
  }

  public void removeObject(ObservableObject object) {
    ObjectView viewToRemove = null;
    for(ObjectView o : objects) {
      if (o.isObject(object)) {
        objectsPane.getChildren().remove(o);
      }
    }
    objects.remove(viewToRemove);
  }
}