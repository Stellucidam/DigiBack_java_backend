package ch.heigvd.digiback.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;


public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findByDate(Date date);
}
