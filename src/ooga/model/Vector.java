package ooga.model;

/**
 * Vectors are meant to be used to make vector calculations
 *
 * @author Oliver Rodas
 */
public class Vector {

  private double x;
  private double y;

  /**
   *  Create a new vector with 0,0
   */
  public Vector(){
  }


  /**
   *  Create a new vector with x and y as the coordinate
   * @param x x coordinate
   * @param y y coordinate
   */
  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * The distance between two points
   * @param a
   * @param b
   * @return the distance
   */
  public static double difference(Vector a, Vector b) {
    return a.difference(b).magnitude();
  }

  /**
   * @return the x coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Set the x coordinate
   * @param x
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * @return the y coordinate
   */
  public double getY() {
    return y;
  }

  /**
   * Set the y coordinate
   * @param y
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * The difference between two vectors
   * @param other vector
   * @return
   */
  public Vector difference(Vector other) {
    return new Vector(x - other.getX(),
        y - other.getY());
  }

  /**
   * Directional Vector
   * @return
   */
  public Vector unitVector() {
    Vector unit = new Vector(this.x, this.y);
    double magit = magnitude();
    unit.setX(this.x / magit);
    unit.setY(this.y / magit);
    return unit;
  }

  /**
   * @return Magnitude of the vector
   */
  public double magnitude() {
    return Math.hypot(x, y);
  }

  /**
   * The sine of the vector
   * @return
   */
  public double sin() {
    return y / magnitude();
  }

  /**
   * @return the cosine of the vector
   */
  public double cos() {
    return x / magnitude();
  }
}
