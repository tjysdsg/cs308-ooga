package ooga.view.components.game;

import javafx.scene.layout.StackPane;
import ooga.model.observables.ObservableObject;

/** A view component which can track with a given gameobject. */
public class ObjectView extends StackPane {
  ObservableObject gameObject;

  public ObjectView(ObservableObject obj) {
    this.gameObject = obj;
    setStyle("-fx-background-color: yellow"); // graphic design is my passion
    setHeight(gameObject.getHeight());
    setHeight(gameObject.getWidth());

    obj.setOnUpdate(this::refreshPosition);
  }

  private void refreshPosition() {
    setTranslateX(gameObject.getX());
    setTranslateY(gameObject.getY());
  }
}
