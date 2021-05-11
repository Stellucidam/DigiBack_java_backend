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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    @Id
    @Getter
    @GeneratedValue
    private int idUser;

    @Getter
    private String username;

    @Getter
    private String secret;

    @Getter
    private String salt;

    @Getter
    private String token;

    /** Verifies that a given idUser and token belong to the same User.
     *
     * @param UserRepository The User repository
     * @param idUser The given User id
     * @param token The given token
     * @return Returns a User if the token and the id match
     * @throws WrongCredentialsException is thrown when the token and id don't match or if the
     *         User doesn't exist.
     */
    public static User verifyUserWith(
            UserRepository UserRepository,
            Integer idUser,
            String token) throws WrongCredentialsException {

        Optional<User> modFromId = UserRepository.findById(idUser);
        if (modFromId.isEmpty() || ! modFromId.equals(UserRepository.findByToken(token))) {
            throw new WrongCredentialsException();
        }

        return modFromId.get();
    }
}
