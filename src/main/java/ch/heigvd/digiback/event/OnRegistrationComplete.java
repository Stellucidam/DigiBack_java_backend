package ch.heigvd.digiback.event;

import ch.heigvd.digiback.business.user.User;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationComplete extends ApplicationEvent {
    @Getter
    @Setter
    private User user;

    private static final Logger logger = LoggerFactory.getLogger(OnRegistrationComplete.class);
    public OnRegistrationComplete(User user) {
        super(user);

        this.user = user;
        logger.info("Create OnRegistrationComplete Event");
    }
}
