package ch.heigvd.digiback.error.exception;

public class DuplicateUsernameException extends BadRequestException {

    public DuplicateUsernameException() {
        super("This username already exists.");
    }
}
