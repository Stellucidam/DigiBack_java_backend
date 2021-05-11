package ch.heigvd.digiback.error.exception;

public class CredentialsTooShortException extends BadRequestException {
    public CredentialsTooShortException() {
        super("The credentials must be at least 4 characters long.");
    }
}
