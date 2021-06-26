package ch.heigvd.digiback.business.movement;

import ch.heigvd.digiback.business.pain.Pain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    Optional<Movement> findByPain(Pain pain);
}
