package ooga.model;

import javafx.scene.input.KeyEvent;

public interface ModelController {
  void setLevel(Integer levelID);
  void handleKeyPress(KeyEvent e);
  void handleKeyRelease(KeyEvent e);
}
