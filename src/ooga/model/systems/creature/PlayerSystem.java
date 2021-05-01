package ooga.model.systems.creature;
/**
 * @author Tinglong Zhu
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import ooga.model.actions.ObjectSpawner;
import ooga.model.actions.PlayerAction;
import ooga.model.annotations.Track;
import ooga.model.components.MovementComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.objects.ObjectInstance;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.managers.ECManager;
import ooga.model.systems.MovementSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: add tests for this
@Track({PlayerComponent.class, MovementComponent.class})
public class PlayerSystem extends ComponentBasedSystem {

  private Map<String, List<PlayerAction>> handlers = new HashMap<>();
  private static final Logger logger = LogManager.getLogger(PlayerSystem.class);

  protected ComponentMapper<PlayerComponent> playerMapper;
  protected ComponentMapper<MovementComponent> movementMapper;

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
    for (PlayerAction mapping : player.getActionMapping()) {
      String input = mapping.getInput();

      int goId = player.getOwner().getId();
      mapping.setOwner(movementMapper.get(goId));
      mapping.setManager(getECManager());

      handlers.putIfAbsent(input, new ArrayList<>());
      handlers.get(input).add(mapping);

      addMapping(input, (on) -> {
        handlers.get(input).forEach( (currHandler) -> currHandler.handleAction(on));
      });
    }

  }
  @Override
  public void update(double deltaTime) {

  }
}
