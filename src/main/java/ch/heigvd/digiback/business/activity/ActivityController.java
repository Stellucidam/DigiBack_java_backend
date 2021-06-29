package ch.heigvd.digiback.business.activity;

import ch.heigvd.digiback.business.activity.credential.ActivityCredential;
import ch.heigvd.digiback.business.activity.credential.ExerciseCredential;
import ch.heigvd.digiback.business.activity.credential.QuizCredential;
import ch.heigvd.digiback.business.activity.credential.StepCredential;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import ch.heigvd.digiback.status.Status;
import ch.heigvd.digiback.status.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

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

    @PostMapping("/user/{idUser}/upload/steps")
    public Status uploadSteps(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody StepCredential stepCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            Activity activity = activityRepository
                    .findByUserAndDate(authenticated, stepCredential.getDate())
                    .orElse(Activity.builder()
                        .date(stepCredential.getDate())
                        .user(authenticated)
                        .nbrExercices(0L)
                        .nbrSteps(stepCredential.getNbrSteps())
                        .nbrQuiz(0L)
                        .build());
            activity.setNbrSteps(stepCredential.getNbrSteps());

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
                .message("The number of steps was uploaded successfully.")
                .build();
    }

    @PostMapping("/user/{idUser}/upload/quiz")
    public Status uploadQuiz(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody QuizCredential quizCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            Activity activity = activityRepository
                    .findByUserAndDate(authenticated, quizCredential.getDate())
                    .orElse(Activity.builder()
                            .date(quizCredential.getDate())
                            .user(authenticated)
                            .nbrExercices(0L)
                            .nbrSteps(0L)
                            .nbrQuiz(quizCredential.getNbrQuiz())
                            .build());
            activity.setNbrQuiz(quizCredential.getNbrQuiz());

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
                .message("The number of quiz was uploaded successfully.")
                .build();
    }


    @PostMapping("/user/{idUser}/upload/exercises")
    public Status uploadExercise(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody ExerciseCredential exerciseCredential) {
        try {
            User authenticated = findVerifiedUserByIdAndToken(idUser, token)
                    .orElseThrow(WrongCredentialsException::new);

            Activity activity = activityRepository
                    .findByUserAndDate(authenticated, exerciseCredential.getDate())
                    .orElse(Activity.builder()
                            .date(exerciseCredential.getDate())
                            .user(authenticated)
                            .nbrExercices(exerciseCredential.getNbrExercises())
                            .nbrSteps(0L)
                            .nbrQuiz(0L)
                            .build());
            activity.setNbrExercices(exerciseCredential.getNbrExercises());

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
                .message("The number of exercises was uploaded successfully.")
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
