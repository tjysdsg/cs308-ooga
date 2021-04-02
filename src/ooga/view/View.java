package ooga.view;

import fr.brouillard.oss.cssfx.CSSFX;
import java.util.ResourceBundle;
import ooga.view.util.ObservableResource;
import ooga.view.components.SplashScreen;
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
  public static final int HEIGHT = 700;
  public static final int WIDTH = 700;
  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCES = "ooga.view.resources.languages.";
  private static final Logger logger = LogManager.getLogger(View.class);

  private Model model;
  private Runnable exitApplication;
  private ModelController modelController;
  private ObservableResource resources;

  public View(Stage stage) {
    CSSFX.start();
    ObservableModel model = ModelFactory.createObservableModel();
    this.resources = new ObservableResource();
    resources.setResources(ResourceBundle.getBundle(DEFAULT_RESOURCES + "English"));
    // this.modelController = model.getController();

    StackPane pane = new StackPane();
    SplashScreen splashScreen = new SplashScreen(HEIGHT, WIDTH, resources);
    splashScreen.getStylesheets().add(getClass().getResource(RESOURCES + "main.css").toExternalForm());
    exitApplication = () -> {
      stage.close();
      logger.info("Exited Application");
    };
    splashScreen.setOnExit(exitApplication);

    logger.info("Displaying Splash Screen");
    stage.setScene(splashScreen);
    stage.show();
  }
}
