package ooga.model;

public interface ModelController {
  void setLevel(Integer levelID);
  void handleKeyPress(String code);
  void handleKeyRelease(String code);
}
