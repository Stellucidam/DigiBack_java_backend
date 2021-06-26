package ch.heigvd.digiback.business.user;

import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User user;
    private User saved;

    private String email = "email",
            secret = "password",
            salt = "password",
            token = "password",
            username = "username";


    @BeforeEach
    public void setUp() {
        user = User.builder()
                .username(username)
                .email(email)
                .secret(secret)
                .salt(salt)
                .token(token)
                .enabled(false)
                .build();
        try {
            saved = userRepository.saveAndFlush(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            saved = userRepository.findByUsername(username).orElse(null);
        }
    }

    @Test
    @DisplayName("Saving a User should work")
    public void testSave() {
        assertNotNull(saved, "The saved user should not be null.");
    }

    @Test
    @DisplayName("Saving a User with the same username should not work")
    public void testSaveDuplicate() {
        User user2 = User.builder()
                .username(username)
                .email(email)
                .secret(secret)
                .salt(salt)
                .token(token)
                .enabled(false)
                .build();
        assertThrows(Exception.class,
                () -> {userRepository.saveAndFlush(user2);},
                "Should not be able to create a second user with the same username.");
    }

    @Test
    @DisplayName("Find a User by username should work")
    public void testFindByUsername() {
        User found = userRepository.findByUsername(username).orElse(null);
        assertNotNull(found, "The saved user should not be null.");
    }

    @Test
    @DisplayName("Changing a Users should work")
    public void testChangeUser() {
        User original = userRepository.findByUsername(username).orElse(null);
        User mod = userRepository.findByUsername(username).orElse(null);
        mod.setEnabled(true);
        saved = userRepository.saveAndFlush(mod);

        assertNotNull(saved, "The saved user should not be null.");
        assertEquals(saved, mod, "The saved user should be correct.");
        assertNotEquals(saved, original, "The saved user should be correct.");
    }

}
