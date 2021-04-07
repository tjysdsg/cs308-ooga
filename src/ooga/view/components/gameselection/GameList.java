package ooga.view.components.gameselection;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.prefs.Preferences;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;

public class GameList extends FlowPane {

  private Set<String> presentDirectories;
  private static final Logger logger = LogManager.getLogger(GameList.class);
  private Consumer<String> selectionCallback;
  private Preferences prefs;
  private StringBinding directoryTitle;

  public GameList(ObservableResource resources) {
    presentDirectories = new HashSet<>();
    getStyleClass().add("game-selection");
    this.directoryTitle = resources.getStringBinding("SelectGameDirectory");
    this.prefs = Preferences.userNodeForPackage(GameList.class);
    VBox addGameItem = new VBox();
    JFXButton addGame = new JFXButton();
    addGame.setGraphic(new FontIcon());
    addGame.getStyleClass().addAll("add-game", "primary");
    initSelection(addGame);

    Label addGameLabel = new Label();
    addGameLabel.textProperty().bind(resources.getStringBinding("AddGame"));
    addGameLabel.getStyleClass().addAll("game-item-label");
    addGameItem.getStyleClass().addAll("add-game", "game-item");

    addGameItem.getChildren().addAll(addGame, addGameLabel);
    getChildren().add(addGameItem);
  }

  private void initSelection(Button addGame) {
    DirectoryChooser dirChooser = new DirectoryChooser();
    addGame.setOnAction(
        e -> {
          String dirPreset = prefs.get("last_selection_dir", System.getProperty("user.home"));
          dirChooser.setInitialDirectory(new File(dirPreset));
          dirChooser.setTitle(directoryTitle.getValue());
          File selectedDir = dirChooser.showDialog(getScene().getWindow());
          if (selectedDir != null) {
            String newDir = selectedDir.getPath() + "/";
            prefs.put("last_selection_dir", newDir);
            createItem(newDir);
          }
        });
  }

  public void createItem(String directory) {
    //TODO: Add check for directories without games.
    if (presentDirectories.contains(directory)) {
      return;
    }
    GameItem newGame = new GameItem(directory);
    getChildren().add(0, newGame);
    newGame.setOnAction(this::notifySelection);
    presentDirectories.add(directory);
    notifySelection(directory);
    logger.debug("Adding Game: {}", directory);
  }

  private void notifySelection(String path) {
    if(selectionCallback != null) {
      selectionCallback.accept(path);
    } else {
      logger.warn("Callback not set on GameList selection notification");
    }
  }

  public void setOnSelection(Consumer<String> selectionCallback) {
    this.selectionCallback = selectionCallback;
  }
}
