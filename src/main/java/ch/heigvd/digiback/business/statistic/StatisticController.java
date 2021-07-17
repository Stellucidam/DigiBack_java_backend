package ch.heigvd.digiback.business.statistic;

import ch.heigvd.digiback.business.movement.Movement;
import ch.heigvd.digiback.business.movement.MovementRepository;
import ch.heigvd.digiback.business.user.User;
import ch.heigvd.digiback.business.user.UserRepository;
import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import ch.heigvd.digiback.python.PythonRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/stat")
public class StatisticController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovementRepository movementRepository;

    @GetMapping("/")
    public Stat getStats() {
        String stat = "";
        try {
            return Stat.builder()
                    .stat(PythonRunner.run())
                    .build();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Stat.builder().stat("ERROR").build();
    }

    @GetMapping("/user/{idUser}/days/{daysNbr}")
    public Stat getStatsForUserAndDays(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @PathVariable(name = "daysNbr") int daysNbr)
            throws WrongCredentialsException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -daysNbr);
        LinkedList<Movement> movements =  movementRepository.findByUserAndDateAfter(
                findVerifiedUserByIdAndToken(idUser, token).orElseThrow(WrongCredentialsException::new),
                (Date) cal.getTime());

        AtomicReference<Float> highestAngle = new AtomicReference<>((float) 0);
        AtomicReference<Float> anglesSum = new AtomicReference<>((float) 0);
        AtomicReference<Float> nbrAngles = new AtomicReference<>((float) 0);

        movements.forEach(movement -> movement.getAngles().forEach(angle -> {
            if (angle.getAngle() > highestAngle.get()) {
                highestAngle.set(angle.getAngle());
            }
            anglesSum.set(anglesSum.get() + angle.getAngle());
            nbrAngles.set(nbrAngles.get() + 1);
        }));

        return Stat.builder()
                .highestAngle(highestAngle.get())
                .angleAverage(anglesSum.get() / nbrAngles.get())
                .build();
    }

    private Optional<User> findVerifiedUserByIdAndToken(Long idUser, String token) {
        return userRepository.findByToken(token).filter(user -> user.getIdUser().equals(idUser));
    }
}
