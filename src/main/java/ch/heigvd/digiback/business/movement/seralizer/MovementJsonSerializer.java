package ch.heigvd.digiback.business.movement.seralizer;

import ch.heigvd.digiback.business.movement.Movement;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MovementJsonSerializer extends JsonSerializer<Movement> {

    @Override
    public void serialize(
            Movement movement,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("idMovement", movement.getIdMovement());
        jsonGenerator.writeNumberField("idUser", movement.getUser().getIdUser());
        jsonGenerator.writeStringField("type", movement.getType().toString());
        jsonGenerator.writeStringField("date", movement.getDate().toString());
        jsonGenerator.writeEndObject();
    }
}
