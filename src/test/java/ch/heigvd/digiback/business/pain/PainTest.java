package ch.heigvd.digiback.business.pain;

import ch.heigvd.digiback.business.movement.MovementType;
import ch.heigvd.digiback.business.pain.Pain;
import ch.heigvd.digiback.business.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PainTest {
    private Pain pain;
    private String date = "2021-01-01";
    private int level = 2;
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
        pain = Pain.builder()
                .idPain(id)
                .movementType(MovementType.BACK_TILT)
                .level(level)
                .date(Date.valueOf(date))
                .user(user)
                .build();
    }

    @Test
    @DisplayName("Simple Pain creation should work")
    public void testBuilder() {
        assertEquals(id, pain.getIdPain(),
                "The id value should be correct.");
        assertEquals(MovementType.BACK_TILT, pain.getMovementType(),
                "The id value should be correct.");
        assertEquals(level, pain.getLevel(),
                "The id value should be correct.");
        assertEquals(Date.valueOf(date), pain.getDate(),
                "The id value should be correct.");
        assertEquals(user, pain.getUser(),
                "The id value should be correct.");
    }
}
