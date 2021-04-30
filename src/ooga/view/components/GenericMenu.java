package ooga.view.components;

import com.jfoenix.controls.JFXButton;
import java.util.Arrays;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;

/** A boring generic menu */
public class GenericMenu extends StackPane {
  private static final Logger logger = LogManager.getLogger(GenericMenu.class);
  private VBox contents;

  /**
   * Create a simple generic menu.
   * @param title - The binding for the display title.
   */
  public GenericMenu(StringBinding title) {
    super();
    getStyleClass().add("pause-dialog");
    this.contents = new VBox();
    Label pauseLabel = new Label();
    pauseLabel.textProperty().bind(title);
    contents.getChildren().addAll(pauseLabel);
    getChildren().addAll(contents);
  }

  /**
   * 
   * @param text - The title of the option
   * @param action - The callback for when the butten is pressed
   * @param classes - The stylings for the button.
   */
  public void addOption(StringBinding text, Runnable action, String... classes) {
    JFXButton button = new JFXButton();
    button.textProperty().bind(text);
    button.setGraphic(new FontIcon());
    button.setOnAction(e -> action.run());
    logger.info("{} has {} classes", text.get(), Arrays.toString(classes));
    contents.getChildren().addAll(button);
    Arrays.stream(classes).forEach(button.getStyleClass()::add); // sexy :D
  }
}
