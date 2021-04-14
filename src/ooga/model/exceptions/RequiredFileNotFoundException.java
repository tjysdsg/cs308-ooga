package ooga.model.exceptions;

public class RequiredFileNotFoundException extends RuntimeException {
    public RequiredFileNotFoundException(String file) {
        super(String
                .format("The %s file was not found. Please make sure it is in the root directory of the game."
                        , file));
    }
}
