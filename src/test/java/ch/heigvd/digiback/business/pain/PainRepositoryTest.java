package ch.heigvd.digiback.business.pain;

import ch.heigvd.digiback.business.movement.MovementType;
import ch.heigvd.digiback.business.pain.Pain;
import ch.heigvd.digiback.business.pain.PainRepository;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PainRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;
    private User savedUser;

    private String email = "email",
            secret = "password",
            salt = "password",
            token = "password",
            username = "username";

    @Autowired
    private PainRepository painRepository;
    private Pain pain;
    private Pain savedPain;

    private String date = "2021-01-01";
    private int level = 2;


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
        try{
            savedUser = userRepository.saveAndFlush(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            savedUser = userRepository.findByUsername(username).orElse(null);
        }

        pain = Pain.builder()
                .movementType(MovementType.BACK_TILT)
                .level(level)
                .date(Date.valueOf(date))
                .user(savedUser)
                .build();
        try{
            savedPain = painRepository.saveAndFlush(pain);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            savedPain = painRepository.findAllByUser(savedUser).get(0);
        }

    }

    @Test
    @DisplayName("Saving a Pain should work")
    public void testSave() {
        assertNotNull(savedPain, "The saved pain should not be null.");
    }

    @Test
    @DisplayName("Saving a Pain should work")
    public void testPainUser() {
        assertEquals(savedPain.getUser(),
                userRepository.findByUsername(username).orElse(null),
                "The saved pains user should be the same original user.");
    }

    @Test
    @DisplayName("Find a Pain by id should work")
    public void testFindById() {
        Pain found = painRepository.findById(savedPain.getIdPain()).orElse(null);
        assertNotNull(found, "The saved pain should not be null.");
    }
}
