package ch.heigvd.digiback.business.movement.seralizer;

import ch.heigvd.digiback.business.movement.credential.AngleCredential;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;

public class MovementDataJsonDeserializer extends JsonDeserializer<AngleCredential> {

    @Override
    public AngleCredential deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Float time = node.get("time").floatValue();
        Float xLinearAcc = node.get("xLinearAcc").floatValue();
        Float yLinearAcc = node.get("yLinearAcc").floatValue();
        Float zLinearAcc = node.get("zLinearAcc").floatValue();
        Float absoluteLinearAcc = node.get("absoluteLinearAcc").floatValue();

        return AngleCredential.builder()
                .time(time)
                .xLinearAcc(xLinearAcc)
                .yLinearAcc(yLinearAcc)
                .zLinearAcc(zLinearAcc)
                .absoluteLinearAcc(absoluteLinearAcc)
                .build();
    }
}
