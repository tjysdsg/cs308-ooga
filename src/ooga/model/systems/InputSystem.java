package ooga.model.systems;

import com.google.common.collect.Multimap;
import java.util.function.Consumer;

public class InputSystem {

  private Multimap<String, Consumer<Boolean>> functionMaps;

  public void registerInput(String code, Consumer<Boolean> runnable) {
    // TODO:
  }

  public void handleCode(String code, boolean on) {
    functionMaps.get(code).forEach(e -> e.accept(on));
  }

}
