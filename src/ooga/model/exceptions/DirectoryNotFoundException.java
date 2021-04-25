package ooga.model.exceptions;

public class DirectoryNotFoundException extends ModelException {

  public DirectoryNotFoundException(String directory) {
    super(String
        .format(
            "The %s directory was not found. Please make sure it is in the root directory of the game."
            , directory));
  }
}
