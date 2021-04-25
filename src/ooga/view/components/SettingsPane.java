package ooga.view.components;

import com.jfoenix.controls.JFXTabPane;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.StackPane;
import ooga.view.util.ObservableResource;

public class SettingsPane extends StackPane {
  private TabPane modules;

  public SettingsPane(ObservableResource resources) {
    this.modules = new TabPane();
    modules.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    getStyleClass().add("settings-dialog");
    getChildren().addAll(modules);
  }

  public void addModule(SettingsModule module) {
    Tab tab = new Tab("System", module);
    //tab.textProperty().bind(module.moduleBinding());
    //tab.setText("OKOKO");
    this.modules.getTabs().add(tab);
  }
}
