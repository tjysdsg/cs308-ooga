package ooga.model.systems;

import java.util.List;
import java.util.function.Consumer;
import ooga.model.annotations.Track;
import ooga.model.components.HealthComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.components.ScoreComponent;
import ooga.model.components.WinComponent;
import ooga.model.components.enemy.HateComponent;
import ooga.model.managers.ECManager;

@Track({PlayerComponent.class, HealthComponent.class, ScoreComponent.class, WinComponent.class,
    HateComponent.class})
public class WinSystem extends ComponentBasedSystem{
  private ComponentMapper<PlayerComponent> playerMapper;
  private ComponentMapper<HealthComponent> healthMapper;
  private ComponentMapper<ScoreComponent> scoreMapper;
  private ComponentMapper<WinComponent> winMapper;
  private ComponentMapper<HateComponent> hateMapper;
  private Consumer<Boolean> setOnLevelEnd;

  private boolean hasWon = false;

  public WinSystem(ECManager ecManager) {
    super(ecManager);
    playerMapper=getComponentMapper(PlayerComponent.class);
    healthMapper = getComponentMapper(HealthComponent.class);
    scoreMapper = getComponentMapper(ScoreComponent.class);
    winMapper = getComponentMapper(WinComponent.class);
    hateMapper = getComponentMapper(HateComponent.class);

    addCollisionMapping(
        "lose_game",
        event -> loseGame()
    );

    addCollisionMapping(
        "win_game",
        event -> winGame()
    );
  }

  @Override
  public void update(double deltaTime) {
    List<WinComponent> winComps = winMapper.getComponents();

    if(winComps.size() == 0){
      return;
    }

    for(WinComponent w: winComps){
      checkWin(w);
    }

  }

  private void checkWin(WinComponent w) {
    List<WinCondition> winConditions = w.getWinConds();
    for(WinCondition wCond: winConditions){

      if(wCond.getCondition().equals("score")){
        double score = scoreMapper.get(w.getOwner().getId()).getScore();
        boolean comp = wCond.checkCondition(score);
        executeWinOrLose(comp, wCond);
        return;
      }

      else if(wCond.getCondition().equals("health")){
        double health = healthMapper.get(w.getOwner().getId()).getHealth();
        boolean comp = wCond.checkCondition(health);
        executeWinOrLose(comp, wCond);
        return;
      }

      else if(wCond.getCondition().equals("enemy")) {
        boolean comp = wCond.checkCondition(hateMapper.getComponents().size());
        executeWinOrLose(comp, wCond);
        return;
      }
    }

  }
  public void setSetOnLevelEnd(Consumer<Boolean> setOnLevelEnd){
    this.setOnLevelEnd = setOnLevelEnd;
  }

  private void executeWinOrLose(boolean comp, WinCondition wCond) {
    if(comp && wCond.isWin()){
      winGame();
    }
    if(comp && !wCond.isWin()){
      loseGame();
    }
  }

  private void loseGame(){
    if(!hasWon){
      hasWon = true;
      setOnLevelEnd.accept(false);
    }
  }

  private void winGame(){
    if(!hasWon){
      hasWon = true; 
      setOnLevelEnd.accept(true);
    }
  }



}
