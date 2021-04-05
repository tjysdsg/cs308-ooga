package ooga.model.systems;

import java.util.ArrayList;
import java.util.List;

// TODO: implement methods
public class IDManager extends BaseSystem {

  private List<Integer> ids;

  @Override
  public void init() {
    ids = new ArrayList<>();
  }

  /**
   * Return a new unique ID
   */
  public int getNewId() {
    return 0;
  }

  /**
   * Remove an ID
   */
  public void removeId() {
  }
}
