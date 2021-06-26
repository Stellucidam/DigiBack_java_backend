package ch.heigvd.digiback.business.activity;

import ch.heigvd.digiback.business.activity.Activity;
import ch.heigvd.digiback.business.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityTest {
    private Activity activity;
    private String date = "2021-01-01";
    private Long nbrSteps = 2L,
            nbrExercices = 3L,
            nbrQuiz = 4L;

    private User user = User.builder()
            .idUser(Long.valueOf(1))
            .username("username")
            .email("email")
            .secret("secret")
            .salt("salt")
            .token("token")
            .enabled(false)
            .build();

    private Long id = Long.valueOf(1);

    @BeforeEach
    public void setUp() {
        activity = Activity.builder()
                .idActivity(id)
                .date(Date.valueOf(date))
                .nbrSteps(nbrSteps)
                .nbrExercices(nbrExercices)
                .nbrQuiz(nbrQuiz)
                .user(user)
                .build();
    }

    @Test
    @DisplayName("Simple Activity creation should work")
    public void testBuilder() {
        assertEquals(id, activity.getIdActivity(),
                "The id value should be correct.");
        assertEquals(Date.valueOf(date), activity.getDate(),
                "The date value should be correct.");
        assertEquals(nbrSteps, activity.getNbrSteps(),
                "The nbrSteps value should be correct.");
        assertEquals(nbrExercices, activity.getNbrExercices(),
                "The nbrExercices value should be correct.");
        assertEquals(nbrQuiz, activity.getNbrQuiz(),
                "The nbrQuiz value should be correct.");
        assertEquals(user, activity.getUser(),
                "The user should be correct.");
    }
}
