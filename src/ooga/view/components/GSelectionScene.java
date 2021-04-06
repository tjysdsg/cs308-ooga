package ooga.view.components;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.prefs.Preferences;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;

public class GSelectionScene extends Scene {
  private static final Logger logger = LogManager.getLogger(GSelectionScene.class);
  private StackPane root;
  private FlowPane gamesList;
  private GSelectionView gameView;
  private ObservableResource resources;
  private Preferences prefs;
  private Set<String> presentDirectories;

  public GSelectionScene(int width, int height, ObservableResource resources) {
    super(new StackPane(), width, height, Color.BLACK);
    this.root = (StackPane) getRoot();
    this.resources = resources;
    this.prefs = Preferences.userNodeForPackage(GSelectionScene.class);
    this.presentDirectories = new HashSet<>();
    VBox gameSelectionCon = new VBox();

    this.gamesList = new FlowPane();
    gamesList.getStyleClass().add("game-selection");

    // TODO: Retrieve from prefs.
    JFXButton addGame = new JFXButton();
    initSelection(addGame);
    this.gameView = new GSelectionView(resources);

    VBox addGameItem = new VBox();
    addGameItem.getStyleClass().addAll("game-item");
    addGame.setGraphic(new FontIcon());
    addGame.getStyleClass().addAll("add-game", "primary");

    Label addGameLabel = new Label("Add Game");
    addGameLabel.getStyleClass().addAll("game-item-label");
    addGameItem.getChildren().addAll(addGame, addGameLabel);

    Label gameSelectionTitle = new Label("Game Selection Is Dope");
    gameSelectionTitle.getStyleClass().add("title-heading");

    ScrollPane gameListScroll = new ScrollPane();
    gameListScroll.setContent(gamesList);
    addGameItem.getStyleClass().addAll("add-game");

    gamesList.getChildren().add(addGameItem);
    HBox.setHgrow(gamesList, Priority.ALWAYS);

    HBox gameBrowser = new HBox();
    VBox.setVgrow(gameBrowser, Priority.ALWAYS);
    HBox.setHgrow(gameListScroll, Priority.ALWAYS);
    gameBrowser.getChildren().addAll(gameListScroll, gameView);

    // TODO: gamesList is going to be in an hbox with the drawer menu thing
    gameSelectionCon.getChildren().addAll(gameSelectionTitle, gameBrowser);

    this.root.getChildren().add(gameSelectionCon);
     createItem("/home/joshu/schoolStuff/308/ooga_team08/data/Jumping Baloons");
    // createItem("/home/joshu/schoolStuff/308/ooga_team08/data/Ultimate Game");
  }

  private void createItem(String directory) {
    //TODO: Add check for directories without games.
    if (presentDirectories.contains(directory)){
      return;
    }
    GameItem newGame = new GameItem(directory);
    gamesList.getChildren().add(0, newGame);
    newGame.setOnAction(gameView::setDirectory);
    presentDirectories.add(directory);
    logger.debug("Adding Game: " + directory);
  }

  private void initSelection(Button addGame) {
    DirectoryChooser dirChooser = new DirectoryChooser();
    addGame.setOnAction(
        e -> {
          String dirPreset = prefs.get("last_selection_dir", System.getProperty("user.home"));
          dirChooser.setInitialDirectory(new File(dirPreset));
          File selectedDir = dirChooser.showDialog(getWindow());
          if (selectedDir != null) {
            String newDir = selectedDir.getPath() + "/";
            prefs.put("last_selection_dir", newDir);
            createItem(newDir);
          }
        });
  }
}
