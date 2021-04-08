package ooga.model.systems;

import com.google.common.collect.Multimap;
import java.util.function.Consumer;

public class InputManager {

  private Multimap<String, Consumer<Boolean>> inputHandlerMap;

  public void registerInput(String code, Consumer<Boolean> callback) {
    inputHandlerMap.put(code, callback);
  }

  public void handleCode(String code, boolean on) {
    inputHandlerMap.get(code).forEach(e -> e.accept(on));
  }

}
