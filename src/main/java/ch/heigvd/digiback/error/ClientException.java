package ch.heigvd.digiback.error;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

public class ClientException extends Exception {

    private final HttpStatus status;

    public ClientException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @JsonComponent
    public static class Serializer extends JsonSerializer<ClientException> {

        @Override
        public void serialize(
                ClientException exception,
                JsonGenerator jsonGenerator,
                SerializerProvider serializerProvider
        ) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("error", exception.getMessage());
            jsonGenerator.writeEndObject();
        }
    }
}
