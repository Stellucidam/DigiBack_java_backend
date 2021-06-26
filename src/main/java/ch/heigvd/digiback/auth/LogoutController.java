package ch.heigvd.digiback.auth;

import ch.heigvd.digiback.auth.credential.UserCredential;
import ch.heigvd.digiback.error.exception.UnknownUserCredentialsException;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static ch.heigvd.digiback.auth.TokenUtils.*;

@RestController
@RequestMapping("/auth")
public class LogoutController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Logs a user out, provided that they give their username and their password.
     *
     * @param credentials The credentials object that is received.
     * @return An authentication token for the provided account.
     * @throws UnknownUserCredentialsException If the provided credentials are unknown to the app.
     */
    @PostMapping("/logout")
    public Status logout(
            @RequestBody UserCredential credentials)
            throws UnknownUserCredentialsException {

        Optional<User> user = userRepository.findByUsername(credentials.getUsername());
        Optional<String> actualSecret = user
                .map(User::getSalt)
                .map(TokenUtils::base64Decode)
                .map(salt -> getSecret(credentials.getPassword(), salt));

        if (user.isPresent() && user.map(User::getSecret).equals(actualSecret)) {

            String newSalt = base64Encode(generateRandomSalt());
            String newSecret = getSecret(credentials.getPassword(), base64Decode(newSalt));
            String newToken = base64Encode(generateRandomToken());

            User updated = User.builder()
                    .idUser(user.get().getIdUser())
                    .username(user.get().getUsername())
                    .token(newToken)
                    .salt(newSalt)
                    .secret(newSecret)
                    .build();

            userRepository.saveAndFlush(updated);

            return Status.builder()
                    .status(StatusType.SUCCESS)
                    .message("User successfully logged out.")
                    .build();
        } else {
            throw new UnknownUserCredentialsException();
        }
    }
}
