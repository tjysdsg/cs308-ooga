package ooga.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import ooga.model.Model;

public class Controller implements ModelController {
  private Map<String, String> code2action; // code -> actions
  private Model model;

  public Controller(Model model) {
    this.model = model;
  }
  // TODO: proxy model
  @Override
  public void setCurrentLevel(String levelName) throws FileNotFoundException {
    model.setCurrentLevel(levelName);
  }



  // TODO:Wait for the model
  @Override
  public void handleKeyPress(String code) {
    model.handleCode("left", true);
  }

  @Override
  public void handleKeyRelease(String code) {

    model.handleCode(code2action.get(code), false);
  }

  // from model
  @Override
  public List<String> getAvailableActions() {
    return null;
  }

  // Self
  @Override
  public void setStrokeMapping(String code, String action) {
    code2action.put(code, action);
  }

  // proxy: model
  @Override
  public List<String> getLevels() {
    // return model.getLevels();
    return null;
  }

  @Override
  public void step(double delta) {
    model.step(delta);
  }

  @Override
  public void setGame(File gameDirectory) throws FileNotFoundException {
    model.setGame(gameDirectory);
  }
}
