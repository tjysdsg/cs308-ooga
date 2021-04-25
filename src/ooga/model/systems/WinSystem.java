package ooga.model.systems;

import java.util.List;
import java.util.function.Consumer;
import ooga.model.annotations.Track;
import ooga.model.components.HealthComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.components.ScoreComponent;
import ooga.model.components.WinComponent;
import ooga.model.managers.ECManager;

@Track({PlayerComponent.class, HealthComponent.class, ScoreComponent.class, WinComponent.class})
public class WinSystem extends ComponentBasedSystem{
  private ComponentMapper<PlayerComponent> playerMapper;
  private ComponentMapper<HealthComponent> healthMapper;
  private ComponentMapper<ScoreComponent> scoreMapper;
  private ComponentMapper<WinComponent> winMapper;

  private WinStatus myStatus = WinStatus.NEITHER;

  public WinSystem(ECManager ecManager, Consumer<Boolean> setOnLevelEnd) {
    super(ecManager);
    playerMapper=getComponentMapper(PlayerComponent.class);
    healthMapper = getComponentMapper(HealthComponent.class);
    scoreMapper = getComponentMapper(ScoreComponent.class);
    winMapper = getComponentMapper(WinComponent.class);

    addCollisionMapping(
        "lose_game",
        event -> loseGame()
    );

    addCollisionMapping(
        "win_game",
        event -> winGame()
    );
  }


  public enum WinStatus{
    WIN,
    LOSE,
    NEITHER
  }

  @Override
  public void update(double deltaTime) {
    List<WinComponent> winComps = winMapper.getComponents();

    if(winComps.size() == 0){
      myStatus = WinStatus.LOSE;
      return;
    }

    for(WinComponent w: winComps){

    }

  }

  private void loseGame(){
    myStatus = WinStatus.LOSE;

  }

  private void winGame(){
    myStatus = WinStatus.WIN;
  }

  public WinStatus getWinStatus(){
    return myStatus;
  }
}
