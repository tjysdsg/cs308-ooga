package ooga.model.systems;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputManager {
  private static final Logger logger = LogManager.getLogger(InputManager.class);

  private Multimap<String, Consumer<Boolean>> inputHandlerMap;

  public InputManager() {
    this.inputHandlerMap = ArrayListMultimap.create();
  }

  public void registerInput(String code, Consumer<Boolean> callback) {
    inputHandlerMap.put(code, callback);
  }

  public void handleCode(String code, boolean on) {
    inputHandlerMap.get(code).forEach(e -> e.accept(on));
    logger.info("Code {} handled for {} key", code, on);
  }

}
