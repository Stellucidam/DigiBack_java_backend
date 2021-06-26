package ch.heigvd.digiback.pain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PainRepository extends JpaRepository<Pain, Long> {
}
