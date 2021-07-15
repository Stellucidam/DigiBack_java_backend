package ch.heigvd.digiback.business.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngleRepository extends JpaRepository<Angle, Long> {
}
