package ooga.view.components;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.view.util.ObservableResource;

public class PauseMenu extends JFXDialogLayout {
  private ObservableResource resources;
  private VBox buttons;

  public PauseMenu(ObservableResource resources) {
    super();
    this.resources = resources;
    this.buttons = new VBox();
    Label pauseLabel = new Label();
    pauseLabel.textProperty().bind(resources.getStringBinding("Paused"));
    setHeading(pauseLabel);
    setBody(buttons);
  }

  public void addOption(StringBinding text, Runnable action) {
    JFXButton button = new JFXButton();
    button.textProperty().bind(text);
    button.setOnAction(e -> action.run());
  }

}
