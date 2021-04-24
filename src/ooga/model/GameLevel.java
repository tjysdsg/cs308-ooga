package ooga.model;

import java.util.ArrayList;
import java.util.List;

import com.squareup.moshi.Json;
import ooga.model.objects.GameObject;
import ooga.model.systems.ActionManager;
import ooga.model.systems.BaseSystem;
import ooga.model.systems.CollisionSystem;
import ooga.model.systems.ECManager;
import ooga.model.systems.HealthSystem;
import ooga.model.systems.InputManager;
import ooga.model.systems.StatsManager;
import ooga.model.systems.creature.NPCSystem;
import ooga.model.systems.creature.PlayerSystem;
import ooga.model.systems.TransformSystem;
import ooga.model.systems.creature.SampleEnemySystem;

class GameLevel implements Level {

  private String name;
  int levelID;
  String background;
  private transient List<BaseSystem> systems = new ArrayList<>();
  @Json(name = "objects")
  private ECManager ecManager;
  private transient InputManager inputManager = new InputManager();
  private transient ActionManager actionManager = new ActionManager();
  private transient StatsManager statsManager = new StatsManager();

  // MUST BE HERE!!! MOSHI USES THIS
  public GameLevel() {
  }

  public void init() {
    systems.add(new HealthSystem(ecManager));
    systems.add(new CollisionSystem(ecManager, actionManager));
    systems.add(new PlayerSystem(ecManager));
    systems.add(new TransformSystem(ecManager));
    systems.add(new SampleEnemySystem(ecManager));
    systems.add(new NPCSystem(ecManager));

    ecManager.registerExistingComponents(ecManager.getEntities());

    for (var s : systems) {
      s.registerAllInputs(inputManager);
      s.registerAllActions(actionManager);
      s.registerAllStats(statsManager);
    }
  }

  public void handleCode(String k, boolean on) {
    inputManager.handleCode(k, on);
  }

  @Override
  public String getBackground() {
    return background;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getID() {
    return levelID;
  }

  @Override
  public void update(double deltaTime) {
    for (BaseSystem s : systems) {
      s.update(deltaTime);
    }
  }

  @Override
  public List<GameObject> generateObjects() {
    return ecManager.getEntities();
  }

  @Override
  public ECManager getECManager() {
    return ecManager;
  }

  public StatsManager getStatsManager() {
    return statsManager;
  }

  public InputManager getInputManager() {
    return inputManager;
  }
}
