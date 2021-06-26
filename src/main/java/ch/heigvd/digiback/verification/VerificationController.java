package ch.heigvd.digiback.verification;

import ch.heigvd.digiback.error.exception.UnknownUserCredentialsException;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class VerificationController {

    private static final Logger logger = LoggerFactory.getLogger(VerificationController.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    /**
     * Logs a user in, provided that they give their username and their password.
     *
     * @return An authentication token for the provided account.
     * @throws UnknownUserCredentialsException If the provided credentials are unknown to the app.
     */
    @PostMapping("/verify/user/{userId}")
    public Status verify(@PathVariable Long userId,
                         @RequestParam(name = "token") String token)
            throws UnknownUserCredentialsException {

        logger.info("Received confirmation");
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        /** TODO : voir si besoin
        Optional<String> actualSecret = verificationToken.map(User::getSalt)
                .map(TokenUtils::base64Decode)
                .map(salt -> TokenUtils.getSecret(credentials.getPassword(), salt));
         */

        if (verificationToken.isPresent() && verificationToken.get().getUser().getIdUser().equals(userId)) {
           Optional<User> user = userRepository.findById(userId);
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
