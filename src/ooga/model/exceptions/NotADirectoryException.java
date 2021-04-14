package ooga.model.exceptions;

public class NotADirectoryException extends RuntimeException {
    public NotADirectoryException(String dirName) {
        super(String.format("Error: %s must be a directory.", dirName));
    }
}
