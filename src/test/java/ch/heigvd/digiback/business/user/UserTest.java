package ch.heigvd.digiback.business.user;

import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    private User user;

    private Long id = Long.valueOf(1);
    private String email = "email",
                    secret = "password",
                    salt = "password",
                    token = "password",
                    username = "username";

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .idUser(id)
                .username(username)
                .email(email)
                .secret(secret)
                .salt(salt)
                .token(token)
                .enabled(false)
                .build();
    }

    @Test
    @DisplayName("Simple User creation should work")
    public void testBuilder() {
        assertEquals(id, user.getIdUser(),
                "The id value should be correct.");
        assertEquals(username, user.getUsername(),
                "The username value should be correct.");
        assertEquals(email, user.getEmail(),
                "The email value should be correct.");
        assertEquals(secret, user.getSecret(),
                "The secret value should be correct.");
        assertEquals(salt, user.getSalt(),
                "The salt value should be correct.");
        assertEquals(token, user.getToken(),
                "The token value should be correct.");
        assertFalse(user.isEnabled(),
                "The enabled value should be correct. status");
    }
}
