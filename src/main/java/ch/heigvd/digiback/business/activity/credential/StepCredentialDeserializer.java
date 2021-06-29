package ch.heigvd.digiback.business.activity.credential;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.sql.Date;

@JsonComponent
public class StepCredentialDeserializer extends JsonDeserializer<StepCredential> {

    private static final Logger logger = LoggerFactory.getLogger(StepCredentialDeserializer.class);
    @Override
    public StepCredential deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long idUser = node.get("idUser").longValue();
        String date = node.get("date").asText();
        Long nbrSteps = node.get("nbrSteps").longValue();

        logger.info("Deserialized step");
        return StepCredential.builder()
                .idUser(idUser)
                .date(Date.valueOf(date))
                .nbrSteps(nbrSteps)
                .build();
    }
}
