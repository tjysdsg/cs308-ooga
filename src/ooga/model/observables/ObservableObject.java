package ooga.model.observables;

/**
 * This interface provides a way for the view to create a visual representation of a game object
 * @author Oliver Rodas
 */
public interface ObservableObject {

  /**
   * Adds a call back so that if a position is changed, the view is notified
   * @param callback
   */
  void setOnPositionUpdate(Runnable callback);

  /**
   * @return the x position of the object
   */
  double getX();

  /**
   * @return the y position of the object
   */
  double getY();

  /**
   * @return the height of the object
   */
  double getHeight();

  /**
   * @return the width of the object
   */
  double getWidth();

  /**
   * @return the id of the object
   */
  int getID();

  /**
   * @return the imageID of the object
   */
  String getImageID();
}
