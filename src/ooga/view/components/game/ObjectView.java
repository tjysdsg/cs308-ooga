package ooga.view.components.game;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import ooga.model.observables.ObservableObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** A view component which can track with a given gameobject. */
public class ObjectView extends StackPane {
  private static final Logger logger = LogManager.getLogger(ObjectView.class);
  private ObservableObject gameObject;
  private ImageView playerImage;

  public ObjectView(ObservableObject obj) {
    this.gameObject = obj;
    this.playerImage = new ImageView();
    File fileImage =
        new File(
            "src/ooga/view/resources/images/mario.png");
    Image image = null;
    try {
    image =
        new Image(
            fileImage.toURI().toURL().toExternalForm(),
            gameObject.getWidth(),
            gameObject.getHeight(),
            false,
            true);
    } catch(MalformedURLException e) {
      logger.warn("Could not load image {}", "image directory");
    }
    if (image != null) {
      playerImage.setImage(image);
    }
    setHeight(gameObject.getHeight());
    setWidth(gameObject.getWidth());
    System.out.println(gameObject.getWidth());
    obj.setOnPositionUpdate(this::refreshPosition);
    refreshPosition();
    getChildren().add(playerImage);
    logger.info("New object created at {} {}", getTranslateX(), getTranslateY());
  }

  private void refreshPosition() {
    setTranslateX(gameObject.getX());
    setTranslateY(gameObject.getY());
  }
}
