package ooga.view;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.model.Model;
import ooga.model.ModelController;
import ooga.model.ModelFactory;
import ooga.model.observables.ObservableModel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class View {
  public static final String RESOURCES = "resources/";
  private static final Logger logger = LogManager.getLogger(View.class);
  Model model;
  ModelController modelController;

  public View(Stage stage) {
    ObservableModel model = ModelFactory.createObservableModel();
    // this.modelController = model.getController();
    StackPane pane = new StackPane();
    Scene scene = new Scene(pane, 500, 300, Color.BLACK);
    scene.getStylesheets().add(getClass().getResource(RESOURCES + "main.css").toExternalForm());
    stage.setScene(scene);
    HBox hbox = new HBox();

    hbox.getStyleClass().add("buttons-pane");

    JFXButton defaultb = new JFXButton("Default");
    JFXButton primary = new JFXButton("Primary");
    primary.getStyleClass().add("primary");
    JFXButton secondary = new JFXButton("Secondary");
    secondary.getStyleClass().add("secondary");

    hbox.getChildren().addAll(defaultb, primary, secondary);
    pane.getChildren().add(hbox);
    logger.info("Displaying Stage");
    stage.show();
  }
}
