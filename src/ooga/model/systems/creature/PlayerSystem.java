package ooga.model.systems.creature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.actions.Handlers.HandlerFactory;
import ooga.model.actions.Handlers.MovementActionHandler;
import ooga.model.annotations.Track;
import ooga.model.components.MovementComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.managers.ECManager;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: add tests for this
@Track({PlayerComponent.class, MovementComponent.class})
public class PlayerSystem extends ComponentBasedSystem {

  private static final Logger logger = LogManager.getLogger(PlayerSystem.class);

  protected ComponentMapper<PlayerComponent> playerMapper;
  protected ComponentMapper<MovementComponent> movementMapper;
  private Map<String, Map<String, MovementActionHandler>> handlers = new HashMap<>();

  // TODO: support active and inactive players

  public PlayerSystem(ECManager ecManager) {
    super(ecManager);
    playerMapper = getComponentMapper(PlayerComponent.class);
    movementMapper = getComponentMapper(MovementComponent.class);

    for (PlayerComponent comp : playerMapper.getComponents()) {
      registerAction(comp);
    }
  }

  private void registerAction(PlayerComponent player) {
    for (ActionPair mapping : player.getActionMapping()) {
      int goId = player.getOwner().getId();
      MovementComponent movementComp = movementMapper.get(goId);
      logger.error(
          "PlayerComponent requires a MovementComponent on the its game object, but object {} doesn't",
          goId
      );

      String input = mapping.getInput();
      String action = mapping.getAction();

      handlers.putIfAbsent(input, new HashMap<>());

      Map<String, MovementActionHandler> inputHandlers = handlers.get(input);
      inputHandlers.putIfAbsent(action, HandlerFactory.buildHandler(action));

      MovementActionHandler handler = inputHandlers.get(action);

      handler.addListener(movementComp);

      addMapping(input, (on) -> {
        inputHandlers.values().forEach((currHandler) -> currHandler.handleAction(on));
      });
    }
  }

  public List<PlayerComponent> getPlayers() {
    return playerMapper.getComponents();
  }

  public void initPlayerType(PlayerComponent.PlayerType playerType) {
    List<PlayerComponent> players = getPlayers();
    for (PlayerComponent p : players) {
      p.setPlayerType(playerType);
    }
  }

  @Override
  public void update(double deltaTime) {
  }
}
