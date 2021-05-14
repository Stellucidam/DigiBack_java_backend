package ch.heigvd.digiback.movement;

import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import ch.heigvd.digiback.movement.credential.MovementCredential;
import ch.heigvd.digiback.movement.credential.MovementDataCredential;
import ch.heigvd.digiback.movement.seralizer.MovementDataCsvSerializer;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movement")
public class MovementController {

    private UserRepository userRepository;
    private MovementRepository movementRepository;
    private MovementDataRepository movementDataRepository;
    private MovementDataCsvSerializer movementDataCsvSerializer = new MovementDataCsvSerializer();

    public MovementController(
            MovementRepository movementRepository,
            MovementDataRepository movementDataRepository,
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.movementRepository = movementRepository;
        this.movementDataRepository = movementDataRepository;
    }

    @PostMapping("/user/{idUser}/upload")
    public Status uploadMovementData(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody MovementCredential movementCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

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
                    .build();
            // save file data to database
            movementRepository.saveAndFlush(movement);
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

    private Optional<User> findVerifiedUserByIdAndToken(
            Long idUser,
            String token
    ) {
        return userRepository.findByToken(token)
                .filter(user -> user.getIdUser().equals(idUser));
    }
}
