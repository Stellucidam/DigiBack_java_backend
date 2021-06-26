package ch.heigvd.digiback.verification;

import ch.heigvd.digiback.auth.credential.TokenCredential;
import ch.heigvd.digiback.error.exception.UnknownUserCredentialsException;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class VerificationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    /**
     * Logs a user in, provided that they give their username and their password.
     *
     * @param credentials The credentials object that is received.
     * @return An authentication token for the provided account.
     * @throws UnknownUserCredentialsException If the provided credentials are unknown to the app.
     */
    @PostMapping("/verify")
    public Status login(@RequestBody TokenCredential credentials)
            throws UnknownUserCredentialsException {

        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(credentials.getToken());
        /** TODO : voir si besoin
        Optional<String> actualSecret = verificationToken.map(User::getSalt)
                .map(TokenUtils::base64Decode)
                .map(salt -> TokenUtils.getSecret(credentials.getPassword(), salt));
         */

        if (verificationToken.isPresent() && verificationToken.get().getUser().getIdUser().equals(credentials.getIdUser())) {
           Optional<User> user = userRepository.findById(credentials.getIdUser());
            if (user.isPresent()) {
                user.get().setEnabled(true);
                userRepository.saveAndFlush(user.get());
            }
        } else {
            throw new UnknownUserCredentialsException();
        }
        return Status.builder()
                .status(StatusType.SUCCESS)
                .message("The user email address was successfully verified.")
                .build();
    }
}
