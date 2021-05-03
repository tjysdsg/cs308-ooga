package ooga.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;
import ooga.view.util.ObservableResource;

/**
 * A pane to organize settings modules.
 * */
public class SettingsPane extends AnchorPane {
  private TabPane modules;
  private Runnable closeCallback;

  public SettingsPane(ObservableResource resources) {
    this.modules = new TabPane();
    AnchorPane.setLeftAnchor(modules, 0.0);
    AnchorPane.setRightAnchor(modules, 0.0);
    AnchorPane.setBottomAnchor(modules, 0.0);
    AnchorPane.setTopAnchor(modules, 0.0);
    JFXButton closeButton = new JFXButton();
    closeButton.getStyleClass().addAll("exit", "secondary");
    closeButton.textProperty().bind(resources.getStringBinding("Close"));
    AnchorPane.setRightAnchor(closeButton, 20.0);
    AnchorPane.setBottomAnchor(closeButton, 20.0);

    closeButton.setOnAction(e -> notifyClose());

    modules.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    getStyleClass().add("settings-dialog");
    getChildren().addAll(modules, closeButton);
  }

  private void notifyClose() {
    if (this.closeCallback != null) {
      closeCallback.run();
    }
  }

  /**
   * Add a module tab.
   * @param module - The module to add
   */
  public void addModule(SettingsModule module) {
    Tab tab = new Tab("System", module);
    this.modules.getTabs().add(tab);
  }

  /**
   * Define an action to be run when the pane closes.
   * @param run
   */
  public void setOnClose(Runnable run) {
    this.closeCallback = run;
  }
}
