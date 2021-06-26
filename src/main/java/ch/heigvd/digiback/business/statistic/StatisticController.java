package ch.heigvd.digiback.business.statistic;

import ch.heigvd.digiback.business.movement.MovementRepository;
import ch.heigvd.digiback.business.user.UserRepository;
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
}
