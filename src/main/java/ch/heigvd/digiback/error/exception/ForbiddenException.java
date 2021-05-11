package ch.heigvd.digiback.error.exception;

import ch.heigvd.digiback.error.ClientException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ClientException {

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

    public ForbiddenException() {
        this("Access to this resource is forbidden.");
    }
}
