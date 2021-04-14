package ooga.model.components;

import ooga.model.objects.GameObject;

public class PlayerComponent extends Component {

  public static final int RIGHT_DIRECTION = 1;
  public static final int LEFT_DIRECTION = -1;
  private static final int DEFAULT_MAX_SPEED = 100;

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

  public enum PlayerType{
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

  public enum CharacterStatus{
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
     * Falling in air
     */
    FALLING,
    /**
     * Jumping, different from FALLING because the player position is rising
     */
    RISING,
  }

  /**
   * which horizontal the player is facing, 1 for right, -1 for left
   */
  private int direction = RIGHT_DIRECTION;


  /**
   * Status of the player in terms of vertical movement
   */
  private VerticalMovementStatus verticalStatus = VerticalMovementStatus.GROUNDED;

  /**
   * Status of the player in terms of horizontal movement
   */
  private HorizontalMovementStatus horizontalStatus = HorizontalMovementStatus.STILL;

  /**
   * Max horizontal movement speed
   */
  private double maxSpeed = DEFAULT_MAX_SPEED;

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

  public void switchDirection() {
    direction = -direction;
  }

  public void setHorizontalStatus(HorizontalMovementStatus horizontalStatus) {
    this.horizontalStatus = horizontalStatus;
  }

  public HorizontalMovementStatus getHorizontalStatus() {
    return horizontalStatus;
  }
}
