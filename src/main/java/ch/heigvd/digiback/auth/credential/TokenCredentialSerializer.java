package ch.heigvd.digiback.auth.credential;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class TokenCredentialSerializer extends JsonSerializer<TokenCredential> {

    @Override
    public void serialize(
            TokenCredential tokenCredential,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("token", tokenCredential.getToken());
        jsonGenerator.writeNumberField("idUser", tokenCredential.getIdUser());
        jsonGenerator.writeEndObject();
    }
}
