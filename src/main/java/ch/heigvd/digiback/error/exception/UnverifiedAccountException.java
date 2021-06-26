package ch.heigvd.digiback.error.exception;

public class UnverifiedAccountException extends ForbiddenException {
    public UnverifiedAccountException() {
        super("Your email address has not yet been verified.");
    }
}
