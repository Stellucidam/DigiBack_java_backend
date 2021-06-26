package ch.heigvd.digiback.business.movement;

import ch.heigvd.digiback.business.movement.Movement;
import ch.heigvd.digiback.business.movement.MovementType;
import ch.heigvd.digiback.business.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MovementTest {
    private Movement movement;

    private final String date = "2021-01-01";
    private final User user = User.builder()
            .idUser(1L)
            .username("username")
            .email("email")
            .secret("secret")
            .salt("salt")
            .token("token")
            .enabled(false)
            .build();

    private final Long id = 1L;

    @BeforeEach
    public void setUp() {
        movement = Movement.builder()
                .idMovement(id)
                .user(user)
                .type(MovementType.BACK_TILT)
                .date(Date.valueOf(date))
                .build();
    }

    @Test
    @DisplayName("Simple Movement creation should work")
    public void testBuilder() {
        assertEquals(id, movement.getIdMovement(),
                "The id value should be correct.");
        assertEquals(user, movement.getUser(),
                "The id value should be correct.");
        assertEquals(MovementType.BACK_TILT, movement.getType(),
                "The id value should be correct.");
        assertEquals(Date.valueOf(date), movement.getDate(),
                "The id value should be correct.");
        assertNull(movement.getMovementData(), "The id value should be correct.");
        assertNull(movement.getPain(), "The id value should be correct.");
    }
}
