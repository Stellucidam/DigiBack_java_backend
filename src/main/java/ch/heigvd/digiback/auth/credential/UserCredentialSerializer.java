package ch.heigvd.digiback.auth.credential;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class UserCredentialSerializer extends JsonSerializer<UserCredential> {

    @Override
    public void serialize(
            UserCredential userCredential,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("username", userCredential.getUsername());
        jsonGenerator.writeStringField("password", userCredential.getPassword());
        jsonGenerator.writeEndObject();
    }
}
