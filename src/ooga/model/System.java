package ooga.model;

/**
 * The 'S' of ECS
 */
public interface System {

  /**
   * Called during the initialization of the game
   */
  void init();

  /**
   * Called every frame
   */
  void update();
}
