package ooga.model.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.objects.GameObject;
import ooga.model.systems.creature.ActionPair;

public class PlayerComponent extends Component {

  public static final int RIGHT_DIRECTION = 1;
  public static final int LEFT_DIRECTION = -1;
  public static final int OBSTACLE_KEY_LEFT = 0;
  public static final int OBSTACLE_KEY_RIGHT = 1;
  public static final int OBSTACLE_KEY_TOP = 2;
  public static final int OBSTACLE_KEY_BOTTOM = 3;

  private static final double DEFAULT_MAX_SPEED = 100;
  private static final double DEFAULT_JUMP_HEIGHT = 200;
  private static final double DEFAULT_JUMP_TIME = 0.4;

  private List<ActionPair> actionMappings = new ArrayList<>();

  public List<ActionPair> getActionMapping() {
    return actionMappings;
  }

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
   * Obstacles the player currently collide with
   *
   * <ol>
   *   <li>key OBSTACLE_KEY_LEFT maps to the obstacle on the left</li>
   *   <li>key OBSTACLE_KEY_BOTTOM maps to the obstacle on the bottom</li>
   *   <li>...</li>
   * </ol>
   */
  private Map<Integer, GameObject> obstacles = new HashMap<>();


  /**
   * Time to reach jump apex
   */
  private double jumpTime = DEFAULT_JUMP_TIME;

  public GameObject getObstacle(int obstacleDirection) {
    return obstacles.getOrDefault(obstacleDirection, null);
  }

  public void setObstacle(int obstacleDirection, GameObject go) {
    obstacles.put(obstacleDirection, go);
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
