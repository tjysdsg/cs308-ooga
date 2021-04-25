package ooga.view.components;

import com.jfoenix.controls.JFXComboBox;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ooga.view.util.ObservableResource;

public class SettingsModule extends StackPane {
  private FlowPane area;
  private StringBinding moduleName;

  public SettingsModule(StringBinding moduleName) {
    getStyleClass().addAll("settings-module");
    this.moduleName = moduleName;
    area = new FlowPane();
    area.getStyleClass().addAll("settings-area");
    getChildren().addAll(area);
  }

  public StringBinding moduleBinding() {
    return this.moduleName;
  }

  /**
   * Add a setting to the settings pane and return a binding
   * to track its updates.
   *
   * @param label - The label lol
   */
  public StringBinding addFieldSettings(StringBinding text) {
    Label label = new Label();
    label.textProperty().bind(text);
    return null;
  }

  public ReadOnlyObjectProperty<String> addListSetting(StringBinding text, ObservableList<String> list) {
    JFXComboBox<String> comboBox = new JFXComboBox<>(list);
    Label label = new Label();
    label.textProperty().bind(text);
    createPair(label, comboBox);
    return comboBox.getSelectionModel().selectedItemProperty();
  }

  private void createPair(Label label, Node value) {
    HBox box = new HBox();
    box.getStyleClass().addAll("settings-pair");
    box.getChildren().addAll(label, value);
    area.getChildren().addAll(box);
  }

}
