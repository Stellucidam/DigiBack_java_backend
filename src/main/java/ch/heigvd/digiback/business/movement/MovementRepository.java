package ch.heigvd.digiback.business.movement;

import ch.heigvd.digiback.business.pain.Pain;
import ch.heigvd.digiback.business.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.LinkedList;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    Optional<Movement> findByPain(Pain pain);

    LinkedList<Movement> findByUserAndDateAfter(User user, Date date);
}
