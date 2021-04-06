package ooga.view.components.gameselection;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.function.Consumer;
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
  private GameList gamesList;
  private GSelectionView gameView;
  private ObservableResource resources;
  private Preferences prefs;
  private Consumer<String> gameSelectionCallback;

  public GSelectionScene(int width, int height, ObservableResource resources) {
    super(new StackPane(), width, height, Color.BLACK);
    this.root = (StackPane) getRoot();
    this.resources = resources;
    gamesList = new GameList(resources);
    this.gameView = new GSelectionView(resources);
    gamesList.setOnSelection(gameView::setDirectory);
    VBox gameSelectionCon = new VBox();

    Label gameSelectionTitle = new Label("Game Selection Is Dope");
    gameSelectionTitle.getStyleClass().add("title-heading");

    ScrollPane gameListScroll = new ScrollPane();
    gameListScroll.setContent(gamesList);
    HBox.setHgrow(gamesList, Priority.ALWAYS);

    HBox gameBrowser = new HBox();
    VBox.setVgrow(gameBrowser, Priority.ALWAYS);
    HBox.setHgrow(gameListScroll, Priority.ALWAYS);
    gameBrowser.getChildren().addAll(gameListScroll, gameView);

    gameSelectionCon.getChildren().addAll(gameSelectionTitle, gameBrowser);

    this.root.getChildren().add(gameSelectionCon);
     gamesList.createItem("/home/joshu/schoolStuff/308/ooga_team08/data/Jumping Baloons/");
    // createItem("/home/joshu/schoolStuff/308/ooga_team08/data/Ultimate Game");
  }

  public void setOnGameSelected(Consumer<String> callBack) {
    this.gameSelectionCallback = callBack;
  }
}
