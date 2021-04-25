package ooga.model.actions.Handlers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.exceptions.UnknownPlayerAction;

public class HandlerFactory {

  public static final String HANDLER_SUFFIX = "Handler";

  public static MovementActionHandler buildHandler(String action) {
    try {
      Class clazz = Class.forName(HandlerFactory.class.getPackageName() + "." + action + HANDLER_SUFFIX);
      return (MovementActionHandler) clazz.getConstructor().newInstance();
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new UnknownPlayerAction(action);
    }
  }
}
