package ooga.view;


import ooga.model.Model;

import java.util.List;
import java.util.Map;

public class Controller implements ModelController {
    private Map<String,String> code2action;// code -> actions
    private Model model;

    public Controller(Model model){
        this.model=model;
    }
    //TODO: proxy model
    @Override
    public void setLevel(String levelName) {
        model.setLevel(levelName);
    }

    //TODO:Wait for the model
    @Override
    public void handleKeyPress(String code) {
        model.handleCode(code2action.get(code), true);
    }

    @Override
    public void handleKeyRelease(String code) {

        model.handleCode(code2action.get(code), false);
    }

    //from model
    @Override
    public List<String> getAvailbleActions() {
        return null;
    }

    //Self
    @Override
    public void setStrokeMapping(String code, String action) {
        code2action.put(code,action);
    }

    //proxy: model
    @Override
    public List<String> getLevels() {
        return model.getLevels();
    }

    @Override
    public void step() {
        model.step();
    }
}
