package ch.heigvd.digiback.auth.credential;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class TokenCredentialsSerializer extends JsonSerializer<TokenCredentials> {

    @Override
    public void serialize(
            TokenCredentials tokenCredentials,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("token", tokenCredentials.getToken());
        jsonGenerator.writeNumberField("idUser", tokenCredentials.getIdUser());
        jsonGenerator.writeEndObject();
    }
}
