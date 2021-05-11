package ch.heigvd.digiback.auth;

import ch.heigvd.digiback.auth.credential.TokenCredentials;
import ch.heigvd.digiback.auth.credential.UserCredentials;
import ch.heigvd.digiback.error.exception.CredentialsTooShortException;
import ch.heigvd.digiback.error.exception.DuplicateUsernameException;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

import static ch.heigvd.digiback.auth.TokenUtils.*;
import static org.postgresql.shaded.com.ongres.scram.common.ScramStringFormatting.base64Encode;

@RestController
public class RegistrationController {

    private UserRepository users;

    public RegistrationController(UserRepository users) {
        this.users = users;
    }

    /**
     * Registers a new user, assuming we got some user credentials.
     *
     * @param credentials The credentials to use for registration.
     * @return An authentication token for the provided account.
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @Transactional
    public TokenCredentials register(@RequestBody UserCredentials credentials)
            throws DuplicateUsernameException, CredentialsTooShortException {

        // Ensure that the username has a proper length.
        if (credentials.getUsername().length() < 4 || credentials.getPassword().length() < 4) {
            throw new CredentialsTooShortException();
        }

        // Does this username already exist ?
        if (users.findByUsername(credentials.getUsername()).isPresent()) {
            throw new DuplicateUsernameException();
        }

        byte[] salt = generateRandomSalt();
        String base64Salt = base64Encode(salt);
        String hashedSecret = getSecret(credentials.getPassword(), salt);
        String token = base64Encode(generateRandomToken());

        User user = User.builder()
                .username(credentials.getUsername())
                .secret(hashedSecret)
                .salt(base64Salt)
                .token(token)
                .build();

        try {
            User inserted = users.saveAndFlush(user);
            return TokenCredentials.builder()
                    .token(inserted.getToken())
                    .idUser(inserted.getIdUser())
                    .build();
        } catch (Throwable t) {
            throw new DuplicateUsernameException();
        }
    }
}
