package ch.heigvd.digiback.business.activity;

import ch.heigvd.digiback.business.activity.Activity;
import ch.heigvd.digiback.business.activity.ActivityRepository;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ActivityRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user, savedUser;

    private String email = "email",
            secret = "password",
            salt = "password",
            token = "password",
            username = "username";

    @Autowired
    private ActivityRepository activityRepository;
    private Activity activity, savedActivity;

    private String date = "2021-01-01";
    private int level = 2;
    private Long nbrSteps = 2L,
            nbrExercices = 3L,
            nbrQuiz = 4L;

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

        activity = Activity.builder()
                .date(Date.valueOf(date))
                .nbrSteps(nbrSteps)
                .nbrExercices(nbrExercices)
                .nbrQuiz(nbrQuiz)
                .user(savedUser)
                .build();
        try{
            savedActivity = activityRepository.saveAndFlush(activity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            savedActivity = activityRepository.findByDate(Date.valueOf(date)).orElse(null);
        }

    }

    @Test
    @DisplayName("Saving a Activity should work")
    public void testSave() {
        assertNotNull(savedActivity, "The saved activity should not be null.");
    }

    @Test
    @DisplayName("Saving a Activity should work")
    public void testActivityUser() {
        assertEquals(savedActivity.getUser(),
                userRepository.findByUsername(username).orElse(null),
                "The saved activity user should be the same original user.");
    }

    @Test
    @DisplayName("Find a Activity by id should work")
    public void testFindById() {
        Activity found = activityRepository.findById(savedActivity.getIdActivity()).orElse(null);
        assertNotNull(found, "The saved activity should not be null.");
    }
}
