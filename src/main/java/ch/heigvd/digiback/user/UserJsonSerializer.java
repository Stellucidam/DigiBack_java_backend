package ch.heigvd.digiback.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserJsonSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(
            User user,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("idUser", user.getIdUser());
        jsonGenerator.writeEndObject();
    }
}
