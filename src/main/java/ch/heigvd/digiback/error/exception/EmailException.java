package ch.heigvd.digiback.error.exception;

public class EmailException extends BadRequestException{
    public EmailException() {
        super("An error occurred with the given email address.");
    }
    public EmailException(String message) {
        super(message);
    }
}
