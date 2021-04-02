package ooga.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.view.util.ObservableResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;

public class SplashScreen extends Scene {
  private static final Logger logger = LogManager.getLogger(SplashScreen.class);
  private StackPane root;

  public SplashScreen(int width, int height, ObservableResource resources) {
    super(new StackPane(), width, height, Color.BLACK);
    this.root = (StackPane) getRoot();
    VBox vbox = new VBox();

    Label title = new Label("Ting & Friends");
    title.getStyleClass().add("splash_title");
    vbox.getStyleClass().add("splashscreen");

    JFXButton resume = new JFXButton();
    resume.textProperty().bind(resources.getStringBinding("LoadGame"));
    resume.getStyleClass().addAll("special", "primary", "load_library");
    resume.setGraphic(new FontIcon());

    JFXButton settings = new JFXButton();
    settings.textProperty().bind(resources.getStringBinding("Settings"));
    settings.getStyleClass().addAll("primary", "settings_button");
    settings.setGraphic(new FontIcon());

    JFXButton exitGame = new JFXButton();
    exitGame.textProperty().bind(resources.getStringBinding("Exit"));
    exitGame.getStyleClass().addAll("secondary", "exit");
    exitGame.setGraphic(new FontIcon());

    VBox.setVgrow(resume, Priority.ALWAYS);
    VBox.setVgrow(exitGame, Priority.ALWAYS);
    vbox.getChildren().addAll(title, resume, settings, exitGame);

    root.getChildren().add(vbox);
  }
}
