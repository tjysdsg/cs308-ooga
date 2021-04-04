package ooga.model.components;

import com.google.common.collect.Multimap;
import ooga.model.GameObject;

public class PlayerComponent extends Component {
  //TODO: Probably find some way to move to abstract class
  private Multimap<String, Runnable> functionMaps;

  public PlayerComponent(GameObject obj) {
    super(obj);
  }

  public void registerMaps(Multimap<String, Runnable> functionMaps) {
    this.functionMaps = functionMaps;
    functionMaps.put("left", this::forward);
    functionMaps.put("jump", this::jump);
  }
  public void forward() {

  }

  public void jump() {

  }

}
