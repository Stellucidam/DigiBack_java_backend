package ch.heigvd.digiback.user;

import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Getter
    private String username;

    @Getter
    private String email;

    @Getter
    private String secret;

    @Getter
    private String salt;

    @Getter
    private String token;

    @Getter
    @Setter
    private boolean enabled;

    /** Verifies that a given idUser and token belong to the same User.
     *
     * @param userRepository The User repository
     * @param idUser The given User id
     * @param token The given token
     * @return Returns a User if the token and the id match
     * @throws WrongCredentialsException is thrown when the token and id don't match or if the
     *         User doesn't exist.
     */
    public static User verifyUserWith(
            UserRepository userRepository,
            Long idUser,
            String token) throws WrongCredentialsException {

        Optional<User> modFromId = userRepository.findById(idUser);
        if (modFromId.isEmpty() || ! modFromId.equals(userRepository.findByToken(token))) {
            throw new WrongCredentialsException();
        }

        return modFromId.get();
    }
}
