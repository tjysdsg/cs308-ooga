package ooga.view.components.gameselection;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ooga.view.util.MetaGame;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;

class GSelectionView extends VBox {
  private static final Logger logger = LogManager.getLogger(GSelectionView.class);
  private String directory = "/home/joshu/Pictures/";
  private ImageView thumbnail;
  private static double IMAGE_DIM = 200;
  private Label gameTitleLabel;
  private ObservableResource resources;
  private Button playGame;
  private MetaGame metadata;

  public GSelectionView(ObservableResource resources) {
    this.thumbnail = new ImageView();
    this.gameTitleLabel = new Label();
    this.resources = resources;

    thumbnail.getStyleClass().addAll("game-selection-thumbnail");
    getStyleClass().addAll("game-selection-view");

    this.playGame = new JFXButton();
    playGame.getStyleClass().addAll("game-item-button", "secondary", "special");
    playGame.setGraphic(new FontIcon());

    VBox.setVgrow(playGame, Priority.ALWAYS);

    gameTitleLabel.getStyleClass().add("game-selection-title");

    getChildren().addAll(gameTitleLabel, thumbnail, playGame);
  }

  private void readMetaData(File directory) {
    // TODO: Set Meta data of game on view
    this.metadata = MetaGame.createMetaDataFromDirectory(directory);
  }

  public void setDirectory(String directory) {
    this.directory = directory;
    File gameDirectory = new File(directory);
    File fileImage = new File(directory + "thumbnail.jpg");
    try {
      Image image =
          new Image(fileImage.toURI().toURL().toExternalForm(), IMAGE_DIM, IMAGE_DIM, false, true);
      this.thumbnail.setImage(image);
      String gameName = fileImage.getParentFile().getName();
      gameTitleLabel.setText(gameName);
      logger.debug("GSelection View set to: {}", directory);
      readMetaData(gameDirectory);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
