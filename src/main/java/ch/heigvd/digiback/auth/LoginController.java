package ch.heigvd.digiback.auth;

import java.util.Optional;
import javax.transaction.Transactional;

import ch.heigvd.digiback.auth.credential.TokenCredentials;
import ch.heigvd.digiback.auth.credential.UserCredentials;
import ch.heigvd.digiback.error.exception.UnknownUserCredentialsException;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserRepository users;

    public LoginController(UserRepository users) {
        this.users = users;
    }

    /**
     * Logs a user in, provided that they give their username and their password.
     *
     * @param credentials The credentials object that is received.
     * @return An authentication token for the provided account.
     * @throws UnknownUserCredentialsException If the provided credentials are unknown to the app.
     */
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public TokenCredentials login(@RequestBody UserCredentials credentials)
            throws UnknownUserCredentialsException {

        Optional<User> user = users.findByUsername(credentials.getUsername());
        Optional<String> actualSecret = user.map(User::getSalt)
                .map(TokenUtils::base64Decode)
                .map(salt -> TokenUtils.getSecret(credentials.getPassword(), salt));

        if (user.isPresent() && user.map(User::getSecret).equals(actualSecret)) {
            return TokenCredentials.builder()
                    .token(user.get().getToken())
                    .idUser(user.get().getIdUser())
                    .build();
        } else {
            throw new UnknownUserCredentialsException();
        }
    }
}
