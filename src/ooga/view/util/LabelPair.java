package ooga.view.util;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/** A component that pairs labels together. */
public class LabelPair extends HBox {

  private Label value;

  public LabelPair(StringBinding labelTitle) {
    Label labelName = new Label();
    this.value = new Label();
    labelName.setLabelFor(value);
    labelName.textProperty().bind(labelTitle);
    value.getStyleClass().add("value-label");
    value.setWrapText(true);
    HBox.setHgrow(value, Priority.NEVER);
    HBox.setHgrow(labelName, Priority.ALWAYS);
    getChildren().addAll(labelName, value);
  }

  /**
   * Set the value of the second label in the label pair.
   *
   * @param labelValue - The new value for the second label.
   */
  public void setValue(String labelValue) {
    value.setText(labelValue);
  }
}
