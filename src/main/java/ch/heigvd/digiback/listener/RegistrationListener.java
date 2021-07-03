package ch.heigvd.digiback.listener;

import ch.heigvd.digiback.event.OnRegistrationComplete;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationComplete> {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService service;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    @Override
    public void onApplicationEvent(OnRegistrationComplete event) {
        logger.info("OnRegistrationComplete Event triggered");
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationComplete event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        // TODO send mail message - done
        String recipientAddress = user.getEmail();
        SimpleMailMessage email = new SimpleMailMessage();
        String subject = "Registration Confirmation for DigiBack";
        String confirmationUrl = "/auth/verify/user/" + user.getIdUser() + "?token=" + token;
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("\r\n" + "http://localhost:8080" + confirmationUrl);

        logger.info(email.getText());


        logger.info("Sending an email to : " + recipientAddress + "...");
        mailSender.send(email);
        logger.info("Sent");
    }
}
