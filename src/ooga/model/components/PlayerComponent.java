package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import ooga.model.objects.GameObject;

public class PlayerComponent extends Component {

  public static final int RIGHT_DIRECTION = 1;
  public static final int LEFT_DIRECTION = -1;
  private static final double DEFAULT_MAX_SPEED = 100;
  private static final double DEFAULT_JUMP_HEIGHT = 80;
  private static final double DEFAULT_JUMP_TIME = 0.4;

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
     * Rising in air
     */
    RISING,
    /**
     * Free falling
     */
    FALLING,
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

  private double jumpTimer = 0;

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
   * Max height the players can jump
   */
  private double jumpHeight = DEFAULT_JUMP_HEIGHT;

  /**
   * Time to reach jump apex
   */
  private double jumpTime = DEFAULT_JUMP_TIME;

  private List<GameObject> blocksBelow = new ArrayList<>();

  public List<GameObject> getBlocksBelow() {
    return blocksBelow;
  }

  public void resetBlocksBelow() {
    this.blocksBelow = new ArrayList<>();
  }

  public void incrementJumpTimer(double delta) {
    jumpTimer += delta;
  }

  public double getJumpTimer() {
    return jumpTimer;
  }

  public void resetJumpTimer() {
    this.jumpTimer = 0;
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

  public double getJumpHeight() {
    return jumpHeight;
  }

  public void setJumpHeight(double jumpHeight) {
    this.jumpHeight = jumpHeight;
  }

  public double getJumpTime() {
    return jumpTime;
  }

  public void setJumpTime(double jumpTime) {
    this.jumpTime = jumpTime;
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
