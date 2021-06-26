package ch.heigvd.digiback.verification;

import ch.heigvd.digiback.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByUser(User user);
    Optional<VerificationToken> findByToken(String token);
}
