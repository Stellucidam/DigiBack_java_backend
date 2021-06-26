package ch.heigvd.digiback.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementDataRepository extends JpaRepository<MovementData, Long> {
}
