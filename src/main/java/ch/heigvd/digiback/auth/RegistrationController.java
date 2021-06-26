package ch.heigvd.digiback.auth;

import ch.heigvd.digiback.auth.credential.TokenCredential;
import ch.heigvd.digiback.auth.credential.UserCredential;
import ch.heigvd.digiback.error.exception.CredentialsTooShortException;
import ch.heigvd.digiback.error.exception.DuplicateUsernameException;
import ch.heigvd.digiback.error.exception.EmailException;
import ch.heigvd.digiback.event.OnRegistrationComplete;
import ch.heigvd.digiback.listener.RegistrationListener;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static ch.heigvd.digiback.auth.TokenUtils.*;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    /**
     * Registers a new user, assuming we got some user credentials.
     *
     * @param credentials The credentials to use for registration.
     * @return An authentication token for the provided account.
     */
    @PostMapping("/register")
    public TokenCredential register(@RequestBody UserCredential credentials,
                                    HttpServletRequest request)
            throws DuplicateUsernameException, CredentialsTooShortException, EmailException {

        // Ensure that the username has a proper length.
        if (credentials.getUsername().length() < 4 || credentials.getPassword().length() < 4) {
            throw new CredentialsTooShortException();
        }

        // Does this username already exist ?
        if (userRepository.findByUsername(credentials.getUsername()).isPresent()) {
            throw new DuplicateUsernameException();
        }

        byte[] salt = generateRandomSalt();
        String base64Salt = base64Encode(salt);
        String hashedSecret = getSecret(credentials.getPassword(), salt);
        String token = base64Encode(generateRandomToken());

        User user = User.builder()
                .username(credentials.getUsername())
                .email(credentials.getEmail())
                .enabled(false)
                .secret(hashedSecret)
                .salt(base64Salt)
                .token(token)
                .build();
        logger.info("Insert user : " + user.getUsername() + ", " + user.getEmail() );

        try {
            User inserted = userRepository.saveAndFlush(user);
            logger.info("Should send an email at some point...");

            try{
                eventPublisher.publishEvent(new OnRegistrationComplete(inserted));
            } catch (Exception ex) {
                throw new EmailException(ex.getMessage());
            }

            return TokenCredential.builder()
                    .token(inserted.getToken())
                    .idUser(inserted.getIdUser())
                    .build();
        } catch (EmailException e) {
            throw e;
        } catch (Throwable t) {
            throw new DuplicateUsernameException();
        }
    }
}
