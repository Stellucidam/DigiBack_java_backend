package ch.heigvd.digiback.error.exception;

import ch.heigvd.digiback.error.ClientException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ClientException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException() {
        this("The server will not process the request due to a client error.");
    }
}
