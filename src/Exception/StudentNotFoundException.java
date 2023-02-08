package Exception;

public class StudentNotFoundException extends RuntimeException{

    private final String message;

    public StudentNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
