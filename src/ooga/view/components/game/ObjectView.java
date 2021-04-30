package ooga.view.components.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import ooga.model.observables.ObservableObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A view component which can track with a given observable object.
 *
 * <p>This class can track and update itself whenever its observable updates
 */
public class ObjectView extends StackPane {

  private static final Logger logger = LogManager.getLogger(ObjectView.class);
  private ObservableObject gameObject;
  private ImageView playerImage;

  public ObjectView(ObservableObject obj, ImageConfiguration images) {
    this.gameObject = obj;
    getStyleClass().add("object-view");
    this.playerImage = new ImageView();

    Image image = images.getImage(obj.getImageID(), obj.getWidth(), obj.getHeight());
    if (image != null) {
      playerImage.setImage(image);
    }
    setHeight(gameObject.getHeight());
    setWidth(gameObject.getWidth());
    System.out.println(gameObject.getWidth());
    obj.setOnPositionUpdate(this::refreshPosition);
    refreshPosition();
    getChildren().add(playerImage);
    logger.debug("New object created at {} {}", getTranslateX(), getTranslateY());
  }

  private void refreshPosition() {
    setTranslateX(gameObject.getX());
    setTranslateY(-gameObject.getY());
  }

  /**
   * Determine if this view is tracking this observable object.
   *
   * @param o - The object to request comparison to
   */
  protected boolean isObject(ObservableObject o) {
    return o == this.gameObject;
  }

  /**
   * Determine if this view is tracking this observable object.
   *
   * @param objectID - The id of the object to verify.
   */
  protected boolean isObject(int objectID) {
    return gameObject.getID() == objectID;
  }
}
