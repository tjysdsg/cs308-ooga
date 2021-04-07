package ooga.model;

/**
 * The 'S' of ECS
 */
public interface System {

  /**
   * Called every frame
   *
   * @param deltaTime Time between the previous frame and the next frame
   */
  void update(double deltaTime);
}
