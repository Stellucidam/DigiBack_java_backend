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
public class ExerciseCredentialDeserializer extends JsonDeserializer<ExerciseCredential> {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseCredentialDeserializer.class);
    @Override
    public ExerciseCredential deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long idUser = node.get("idUser").longValue();
        String date = node.get("date").asText();
        Long nbrExercises = node.get("nbrExercises").longValue();

        logger.info("Deserialized exercise");
        return ExerciseCredential.builder()
                .date(Date.valueOf(date))
                .nbrExercises(nbrExercises)
                .build();
    }
}
