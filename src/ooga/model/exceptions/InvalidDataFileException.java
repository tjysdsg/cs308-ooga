package ooga.model.exceptions;

public class InvalidDataFileException extends ModelException {

    private static final String format =
            "Error reading %s. Please check to make sure the file is formatted as a proper json file.%n";

    public InvalidDataFileException(String fileName) {
        super(String.format(format, fileName));
    }
}
