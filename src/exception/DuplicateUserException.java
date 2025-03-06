package src.exception;

public class DuplicateUserException extends UserException {
    public DuplicateUserException(String entity) {
        super(entity + " already exists. Please use a unique value.");
    }
}
