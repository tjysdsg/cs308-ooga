package ooga.model.exceptions;

public class TypeNotFoundException extends ModelException {
  public TypeNotFoundException(String type) {
    super(String.format("The type: %s was not found. Please Define it in the objects directory.%n", type));
  }
}
