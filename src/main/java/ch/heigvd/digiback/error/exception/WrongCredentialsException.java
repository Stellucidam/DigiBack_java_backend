package ch.heigvd.digiback.error.exception;

public class WrongCredentialsException extends ForbiddenException {
    public WrongCredentialsException() {
        super("The wrong credentials were provided.");
    }
}
