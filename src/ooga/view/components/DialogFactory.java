package ooga.view.components;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import ooga.view.util.ObservableResource;
import org.kordamp.ikonli.javafx.FontIcon;

/** Generate dialogs. */
public class DialogFactory {

  /**
   * Creates an error dialog
   *
   * @param text - The subtext of the dialog
   * @param resources - Resources needed for localization
   * @return A dialog that can be displayed on stackpanes.
   */
  public static JFXDialog createErrorDialog(String text, ObservableResource resources) {
    Label bodyLabel = new Label(text);
    Label headingLabel = new Label();
    headingLabel.textProperty().bind(resources.getStringBinding("Error"));
    configureLabels(headingLabel, bodyLabel);
    return createDialog(headingLabel, bodyLabel);
  }

  private static void configureLabels(Label headingLabel, Label bodyLabel) {
    headingLabel.getStyleClass().add("medium-font");
    headingLabel.setGraphic(new FontIcon());
    headingLabel.getStyleClass().add("error");
  }

  private static JFXDialog createDialog(Label heading, Label body) {
    JFXDialogLayout layout = new JFXDialogLayout();
    layout.setHeading(heading);
    layout.setBody(body);

    JFXDialog dialog = new JFXDialog();
    dialog.setOverlayClose(true);
    dialog.setContent(layout);
    return dialog;
  }
}
