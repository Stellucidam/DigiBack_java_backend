package ch.heigvd.digiback.error.exception;

public class UnknownUserCredentialsException extends ForbiddenException {
    public UnknownUserCredentialsException() {
        super();
    }

    public UnknownUserCredentialsException(String message) {
        super(message);
    }
}
