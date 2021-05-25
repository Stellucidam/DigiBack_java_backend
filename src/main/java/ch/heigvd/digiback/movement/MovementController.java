package ch.heigvd.digiback.movement;

import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import ch.heigvd.digiback.movement.credential.MovementCredential;
import ch.heigvd.digiback.pain.Pain;
import ch.heigvd.digiback.pain.PainRepository;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Optional;

@RestController
@RequestMapping("/movement")
public class MovementController {

    private UserRepository userRepository;
    private PainRepository painRepository;
    private MovementRepository movementRepository;
    private MovementDataRepository movementDataRepository;

    public MovementController(
            MovementRepository movementRepository,
            MovementDataRepository movementDataRepository,
            UserRepository userRepository,
            PainRepository painRepository
    ) {
        this.userRepository = userRepository;
        this.painRepository = painRepository;
        this.movementRepository = movementRepository;
        this.movementDataRepository = movementDataRepository;
    }

    @PostMapping("/user/{idUser}/upload/level/{level}")
    public Status uploadMovementDataWithPain(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @PathVariable(name = "level") int level,
            @RequestBody MovementCredential movementCredential){
        if (level < 0 || level > 10) {
            return Status.builder()
                    .status(StatusType.FAIL)
                    .message("The given pain level is invalid (must be between 0 and 10 inclusive).")
                    .build();
        }
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            Pain pain = Pain.builder()
                    .movementType(movementCredential.getType())
                    .date(movementCredential.getDate())
                    .user(authenticated)
                    .level(level)
                    .build();

            painRepository.saveAndFlush(pain);

            saveMovement(movementCredential, authenticated, pain);
        } catch (WrongCredentialsException e) {
            return Status.builder()
                    .status(StatusType.FAIL)
                    .message("The wrong credentials were provided")
                    .build();
        } catch (NullPointerException e) {
            return Status.builder()
                    .status(StatusType.FAIL)
                    .message("NullPointerException : " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Status.builder()
                    .status(StatusType.FAIL)
                    .message(e.getMessage())
                    .build();
        }

        return Status.builder()
                .status(StatusType.SUCCESS)
                .message("The movement data and pain level was uploaded successfully.")
                .build();
    }

    @PostMapping("/user/{idUser}/upload")
    public Status uploadMovementData(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody MovementCredential movementCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            saveMovement(movementCredential, authenticated, null);
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
                .message("The movement data was uploaded successfully.")
                .build();
    }

    private void saveMovement(MovementCredential movementCredential, User authenticated, Pain pain) {
        LinkedList<MovementData> movementDatas = new LinkedList<>();

        movementCredential.getMovementDataCredentials().forEach(movementDataCredential -> {
            MovementData movementData = MovementData.builder()
                    .yLinearAcc(movementDataCredential.getYLinearAcc())
                    .zLinearAcc(movementDataCredential.getZLinearAcc())
                    .xLinearAcc(movementDataCredential.getXLinearAcc())
                    .absoluteLinearAcc(movementDataCredential.getAbsoluteLinearAcc())
                    .time(movementDataCredential.getTime())
                    .build();
            movementDataRepository.saveAndFlush(movementData);
            movementDatas.add(movementData);
        });
        Movement movement = Movement.builder()
                .type(movementCredential.getType())
                .date(movementCredential.getDate())
                .movementData(movementDatas)
                .user(authenticated)
                .pain(pain)
                .build();
        // save file data to database
        movementRepository.saveAndFlush(movement);
    }

    private Optional<User> findVerifiedUserByIdAndToken(Long idUser, String token) {
        return userRepository.findByToken(token)
                .filter(user -> user.getIdUser().equals(idUser));
    }
}
