package ch.heigvd.digiback.error.exception;
import ch.heigvd.digiback.error.ClientException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ClientException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException() {
        this("The requested resource could not be found.");
    }
}
