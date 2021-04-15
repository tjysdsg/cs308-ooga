package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import ooga.model.objects.GameObject;

public class PlayerComponent extends Component {

  public static final int RIGHT_DIRECTION = 1;
  public static final int LEFT_DIRECTION = -1;
  private static final double DEFAULT_MAX_SPEED = 100;
  private static final double DEFAULT_JUMP_IMPULSE = 130;

  public enum HorizontalMovementStatus {
    /**
     * Not moving
     */
    STILL,
    /**
     * Running at maxSpeed
     */
    RUNNING,
  }

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

  public enum VerticalMovementStatus {
    /**
     * Standing/running on ground
     */
    GROUNDED,
    /**
     * In air
     */
    AIRBORNE,
  }

  /**
   * which horizontal the player is facing, 1 for right, -1 for left
   */
  protected int direction = RIGHT_DIRECTION;


  protected PlayerType playerType = PlayerType.PLAYER;

  protected CharacterStatus characterStatus = CharacterStatus.Active;

  /**
   * Status of the player in terms of vertical movement
   */
  protected VerticalMovementStatus verticalStatus = VerticalMovementStatus.GROUNDED;

  /**
   * Status of the player in terms of horizontal movement
   */
  protected HorizontalMovementStatus horizontalStatus = HorizontalMovementStatus.STILL;

  public void setPlayerType(PlayerType playerType) {
    this.playerType = playerType;
  }

  public void setCharacterStatus(CharacterStatus characterStatus) {
    this.characterStatus = characterStatus;
  }

  /**
   * Max horizontal movement speed
   */
  private double maxSpeed = DEFAULT_MAX_SPEED;

  /**
   * The initial vertical velocity that player has when it jumps
   */
  private double jumpImpulse = DEFAULT_JUMP_IMPULSE;

  private List<GameObject> touchedGround = new ArrayList<>();

  public List<GameObject> getTouchedGround() {
    return touchedGround;
  }

  public void resetTouchedGround(List<GameObject> touchedGround) {
    this.touchedGround = new ArrayList<>();
  }

  // only for moshi
  protected PlayerComponent() {
    super();
  }

  public PlayerComponent(int id, GameObject owner) {
    super(id, owner);
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public double getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public double getJumpImpulse() {
    return jumpImpulse;
  }

  public void setJumpImpulse(double jumpImpulse) {
    this.jumpImpulse = jumpImpulse;
  }

  public void switchDirection() {
    direction = -direction;
  }

  public void setHorizontalStatus(HorizontalMovementStatus horizontalStatus) {
    this.horizontalStatus = horizontalStatus;
  }

  public HorizontalMovementStatus getHorizontalStatus() {
    return horizontalStatus;
  }

  public void setVerticalStatus(VerticalMovementStatus verticalStatus) {
    this.verticalStatus = verticalStatus;
  }

  public VerticalMovementStatus getVerticalStatus() {
    return verticalStatus;
  }

  public String typeUnerasure() {
    return PlayerComponent.class.getName();
  }
}
