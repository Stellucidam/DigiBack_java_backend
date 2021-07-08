package ch.heigvd.digiback.business.statistic;

import ch.heigvd.digiback.business.movement.MovementRepository;
import ch.heigvd.digiback.business.user.UserRepository;
import ch.heigvd.digiback.python.PythonRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class StatisticController {

    private UserRepository userRepository;
    private MovementRepository movementRepository;

    public StatisticController(UserRepository userRepository, MovementRepository movementRepository) {
        this.userRepository = userRepository;
        this.movementRepository = movementRepository;
    }

    @GetMapping("/")
    public Stat getStats() {
        String stat = "";
        try {
            return Stat.builder().stat(PythonRunner.run()).build();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Stat.builder().stat("ERROR").build();
    }
}
