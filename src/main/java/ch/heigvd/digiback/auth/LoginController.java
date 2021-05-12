package ch.heigvd.digiback.auth;

import java.util.Optional;

import ch.heigvd.digiback.auth.credential.TokenCredentials;
import ch.heigvd.digiback.auth.credential.UserCredentials;
import ch.heigvd.digiback.error.exception.UnknownUserCredentialsException;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
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
    @PostMapping("/login")
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
