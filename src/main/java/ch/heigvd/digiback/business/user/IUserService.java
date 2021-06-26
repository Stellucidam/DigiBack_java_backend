package ch.heigvd.digiback.business.user;

import ch.heigvd.digiback.auth.credential.UserCredential;
import ch.heigvd.digiback.error.exception.CredentialsTooShortException;
import ch.heigvd.digiback.error.exception.DuplicateUsernameException;
import ch.heigvd.digiback.error.exception.UserAlreadyExistException;
import ch.heigvd.digiback.verification.VerificationToken;

public interface IUserService {
    User registerNewUserAccount(UserCredential userCredential)
            throws UserAlreadyExistException, CredentialsTooShortException, DuplicateUsernameException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
