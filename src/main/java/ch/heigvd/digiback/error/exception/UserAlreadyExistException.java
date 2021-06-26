package ch.heigvd.digiback.error.exception;

public class UserAlreadyExistException extends BadRequestException {

    public UserAlreadyExistException() {
        super("This user already exists.");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
