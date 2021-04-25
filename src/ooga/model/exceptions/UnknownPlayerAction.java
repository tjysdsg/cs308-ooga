package ooga.model.exceptions;

public class UnknownPlayerAction extends RuntimeException {

  public UnknownPlayerAction(String action) {
    super(String.format("Unknown Player Action: %s. Actions are capitalized in the config file.", action));
  }
}
