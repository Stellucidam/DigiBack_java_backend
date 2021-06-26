package ch.heigvd.digiback.business.statistic;

import ch.heigvd.digiback.business.movement.MovementRepository;
import ch.heigvd.digiback.business.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class StatisticController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovementRepository movementRepository;

}
