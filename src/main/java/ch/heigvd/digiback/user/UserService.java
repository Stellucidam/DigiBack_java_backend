package ch.heigvd.digiback.user;

import ch.heigvd.digiback.auth.credential.UserCredential;
import ch.heigvd.digiback.error.exception.CredentialsTooShortException;
import ch.heigvd.digiback.error.exception.DuplicateUsernameException;
import ch.heigvd.digiback.error.exception.UserAlreadyExistException;
import ch.heigvd.digiback.verification.VerificationToken;
import ch.heigvd.digiback.verification.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static ch.heigvd.digiback.auth.TokenUtils.*;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public User registerNewUserAccount(UserCredential credentials)
            throws UserAlreadyExistException, CredentialsTooShortException, DuplicateUsernameException {

        // Ensure that the username has a proper length.
        if (credentials.getUsername().length() < 4 || credentials.getPassword().length() < 4) {
            throw new CredentialsTooShortException();
        }

        // Does this username already exist ?
        if (repository.findByUsername(credentials.getUsername()).isPresent()) {
            throw new DuplicateUsernameException();
        }

        if (emailExist(credentials.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + credentials.getEmail());
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
        return repository.saveAndFlush(user);
    }

    private boolean emailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Override
    public User getUser(String verificationToken) {
        Optional<VerificationToken> verificationTokenOptional = tokenRepository.findByToken(verificationToken);
        User user = null;
        if (verificationTokenOptional.isPresent()) {
            user = verificationTokenOptional.get().getUser();
        }
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken).orElse(null);
    }

    @Override
    public void saveRegisteredUser(User user) {
        repository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
