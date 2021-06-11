package ch.heigvd.digiback.activity;

import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import ch.heigvd.digiback.user.User;
import ch.heigvd.digiback.user.UserRepository;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private UserRepository userRepository;
    private ActivityRepository activityRepository;

    public ActivityController(
            ActivityRepository activityRepository,
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @GetMapping("/user/{idUser}/date/{date}")
    public ActivityCredential getActivityFromDate(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @PathVariable(name = "date") String date) throws WrongCredentialsException {
        User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                .orElseThrow(WrongCredentialsException::new);
        Optional<Activity> activity = activityRepository.findByDate(Date.valueOf(date));
        return activity.map(value -> ActivityCredential.builder()
                .idUser(authenticated.getIdUser())
                .date(value.getDate())
                .nbrSteps(value.getNbrSteps())
                .nbrExercices(value.getNbrExercices())
                .nbrQuiz(value.getNbrQuiz())
                .build())
                .orElse(null);
    }

    @PostMapping("/user/{idUser}/upload")
    public Status uploadActivity(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody ActivityCredential activityCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            Activity activity =
              Activity.builder()
                  .date(activityCredential.getDate())
                  .user(authenticated)
                  .nbrExercices(activityCredential.getNbrExercices())
                  .nbrSteps(activityCredential.getNbrSteps())
                  .nbrQuiz(activityCredential.getNbrQuiz())
                  .build();

            activityRepository.saveAndFlush(activity);
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
                .message("The activity data was uploaded successfully.")
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
