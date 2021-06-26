package ch.heigvd.digiback.auth.credential;

import ch.heigvd.digiback.listener.RegistrationListener;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class UserCredentialDeserializer extends JsonDeserializer<UserCredential> {

    private static final Logger logger = LoggerFactory.getLogger(UserCredentialDeserializer.class);
    @Override
    public UserCredential deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String username = node.get("username").asText();
        String email = null;
        try {
            email = node.get("email").asText();
        } catch (Exception e) {
            logger.warn("No email address was given.");
        }
        String password = node.get("password").asText();
        return UserCredential.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
