package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import ooga.model.objects.GameObject;
import ooga.model.systems.creature.ActionPair;

public class PlayerComponent extends Component {

  public enum PlayerType {
    /**
     * Neutral NPC
     */
    NEUTRAL,
    /**
     * Player ccontrolled
     */
    PLAYER,
    /**
     * Enemies
     */
    ENEMY
  }

  public enum CharacterStatus {
    /**
     * Player controlled
     */
    Active,
    /**
     * Waited for switch
     */
    StandBy
  }

  protected PlayerType playerType = PlayerType.PLAYER;
  protected CharacterStatus characterStatus = CharacterStatus.Active;
  private List<ActionPair> actionMappings = new ArrayList<>();

  public List<ActionPair> getActionMapping() {
    return actionMappings;
  }

  public void setPlayerType(PlayerType playerType) {
    this.playerType = playerType;
  }

  public void setCharacterStatus(CharacterStatus characterStatus) {
    this.characterStatus = characterStatus;
  }

  // only for moshi
  protected PlayerComponent() {
    super();
  }

  public PlayerComponent(int id, GameObject owner) {
    super(id, owner);
  }

  public String typeUnerasure() {
    return PlayerComponent.class.getName();
  }
}
