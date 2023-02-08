package Exception;

public class StudentParseException extends Exception {

    private final String message;

    public StudentParseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
