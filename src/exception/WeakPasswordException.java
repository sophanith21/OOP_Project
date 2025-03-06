package src.exception;

public class WeakPasswordException extends UserException {
    public WeakPasswordException() {
        super("Password must be at least 8 characters long and include a mix of letters, numbers, and special characters.");
    }
}
