package ooga.model.exceptions;

public abstract class ModelException extends RuntimeException {
  ModelException(String message) {
    super(message);
  }
}
