package ooga.model;

import java.util.ArrayList;
import java.util.List;

import com.squareup.moshi.Json;
import ooga.model.managers.ECManager;
import ooga.model.managers.SystemManager;
import ooga.model.managers.ActionManager;
import ooga.model.managers.StatsManager;
import ooga.model.managers.InputManager;
import ooga.model.objects.GameObject;
import ooga.model.observables.ObservableLevel;
import ooga.model.systems.BaseSystem;
import ooga.model.systems.CollisionSystem;
import ooga.model.systems.HealthSystem;
import ooga.model.systems.LifeCircleSystem;
import ooga.model.systems.TransformSystem;
import ooga.model.systems.creature.NPCSystem;
import ooga.model.systems.creature.PlayerSystem;
import ooga.model.systems.creature.SampleEnemySystem;

// TODO: implement methods
class GameLevel implements Level, ObservableLevel {

  // Moshi Items (add anything that is needed)
  private String name;
  private int levelID;
  private int height;
  private int width;
  private String background;

  private transient List<BaseSystem> systems = new ArrayList<>();
  @Json(name = "objects")
  private ECManager ecManager;
  private transient InputManager inputManager = new InputManager();
  private transient ActionManager actionManager = new ActionManager();
  private transient StatsManager statsManager = new StatsManager();
  private transient SystemManager systemManager;

  // MUST BE HERE!!! MOSHI USES THIS
  public GameLevel() {
  }

  public void init() {
    // MUST be created AFTER ecManger is init by Moshi
    systemManager = new SystemManager();
    systemManager.createSystem(HealthSystem.class, ecManager);
    systemManager.createSystem(LifeCircleSystem.class, ecManager);
    systemManager.createSystem(CollisionSystem.class, ecManager, actionManager);
    systemManager.createSystem(PlayerSystem.class, ecManager);
    systemManager.createSystem(TransformSystem.class, ecManager);
    systemManager.createSystem(SampleEnemySystem.class, ecManager);
    systemManager.createSystem(NPCSystem.class, ecManager);
    systems = systemManager.getAllSystems();

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
  public String getBackgroundID() {
    return background;
  }

  @Override
  public ObservableLevel asObservable() {
    return this;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
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
