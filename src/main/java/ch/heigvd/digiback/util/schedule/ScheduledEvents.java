package ch.heigvd.digiback.util.schedule;

import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import ch.heigvd.digiback.verification.VerificationToken;
import ch.heigvd.digiback.verification.VerificationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * This class is used to enable the scheduled events. By convention we put all
 * scheduled events here.
 */
@Configuration
@EnableScheduling
public class ScheduledEvents {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledEvents.class);

    @Scheduled(fixedDelayString = "${digiback.schedule.TokenClean}")
    public void deleteBlackListedTokens() {
        Iterable<VerificationToken> tokens = verificationTokenRepository.findAll();
        tokens.forEach(t -> {
            // If the token is expired
            if (t.isExpired()) {
                Optional<User> user = userRepository.findByUsername(t.getUser().getUsername());
                if (user.isPresent() && !user.get().isEnabled()) {
                    userRepository.delete(t.getUser());
                }
                verificationTokenRepository.delete(t);
            }
        });
        logger.info("Deleted the expired tokens.");
    }


    /**
     * Methods to run after application starts up
     */
    @PostConstruct
    public void runAfterStartup() {
        /* TODO : may be useful
        roleService.generateRolesToDatabase();
        logger.info("Generate roles to database.");
        userService.createMainAdminUser();
        logger.info("Created administrator.");
        */
    }
}
