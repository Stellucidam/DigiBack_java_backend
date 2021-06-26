package ch.heigvd.digiback.business.pain;

import ch.heigvd.digiback.business.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PainRepository extends JpaRepository<Pain, Long> {
    List<Pain> findAllByUser(User user);
}
