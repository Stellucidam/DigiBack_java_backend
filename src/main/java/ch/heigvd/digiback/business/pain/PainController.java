package ch.heigvd.digiback.business.pain;

import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pain")
public class PainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PainRepository painRepository;

    @PostMapping("/user/{idUser}/upload")
    public Status uploadPain(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody PainCredential painCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            Pain pain = Pain.builder()
                    .movementType(painCredential.getMovementType())
                    .date(painCredential.getDate())
                    .user(authenticated)
                    .level(painCredential.getLevel())
                    .build();

            painRepository.saveAndFlush(pain);
        } catch (WrongCredentialsException e) {
            return Status.builder()
                    .status(StatusType.FAIL)
                    .message("The wrong credentials were provided")
                    .build();
        } catch (Exception e) {
            return Status.builder()
                    .status(StatusType.FAIL)
                    .message(e.getMessage())
                    .build();
        }

        return Status.builder()
                .status(StatusType.SUCCESS)
                .message("The pain data was uploaded successfully.")
                .build();
    }

    private Optional<User> findVerifiedUserByIdAndToken(
            Long idUser,
            String token
    ) {
        return userRepository.findByToken(token)
                .filter(user -> user.getIdUser().equals(idUser));
    }
}
