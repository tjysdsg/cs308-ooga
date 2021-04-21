package ooga.view.components;

import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;
import com.jfoenix.controls.JFXDialog;
import ooga.view.util.ObservableResource;
import com.jfoenix.controls.JFXDialogLayout;




public class DialogFactory {
  public static JFXDialog createErrorDialog(String text, ObservableResource resources) {
    Label bodyLabel = new Label(text);
    Label headingLabel = new Label();
    headingLabel.textProperty().bind(resources.getStringBinding("Error"));
    configureLabels(headingLabel, bodyLabel);
    return createDialog(headingLabel, bodyLabel);
  }

  public static JFXDialog showException(String message) {
    Label headingLabel = new Label("Error");
    Label bodyLabel = new Label(message);
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
