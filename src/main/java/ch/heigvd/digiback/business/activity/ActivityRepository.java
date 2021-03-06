package ch.heigvd.digiback.business.activity;

import ch.heigvd.digiback.business.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findByDate(Date date);

    Optional<Activity> findByUserAndDate(User user, Date date);
}
