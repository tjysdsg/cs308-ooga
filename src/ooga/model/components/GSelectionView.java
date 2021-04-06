package ooga.view.components;

import ooga.view.util.ObservableResource;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;

class GSelectionView extends VBox {
  private static final Logger logger = LogManager.getLogger(SplashScreen.class);
  private String directory = "/home/joshu/Pictures/";
  private ImageView thumbnail;
  private double IMAGE_DIM = 200;
  private Label gameTitleLabel;
  private ObservableResource resources;

  public GSelectionView(ObservableResource resources) {
    this.thumbnail = new ImageView();
    gameTitleLabel = new Label();
    this.resources = resources;

    thumbnail.getStyleClass().addAll("game-selection-thumbnail");
    getStyleClass().addAll("game-selection-view");
    setDirectory(directory);

    JFXButton playGame = new JFXButton();
    playGame.getStyleClass().addAll("game-item-button", "secondary", "special");
    playGame.setGraphic(new FontIcon());

    VBox.setVgrow(playGame, Priority.ALWAYS);
    // playGame.setStyle("-fx-background-image: url('file:" + gamePath + "thumbnail.jpg');");

    gameTitleLabel.getStyleClass().add("game-selection-title");

    getChildren().addAll(gameTitleLabel, thumbnail, playGame);
  }

  public void setDirectory(String directory) {
    this.directory = directory;

    File fileImage = new File(directory + "thumbnail.jpg");
    try {
      Image image =
          new Image(fileImage.toURI().toURL().toExternalForm(), IMAGE_DIM, IMAGE_DIM, false, true);
      this.thumbnail.setImage(image);
      String gameName = fileImage.getParentFile().getName();
      gameTitleLabel.setText(gameName);
      logger.info("GSelection View set to: " + directory);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
