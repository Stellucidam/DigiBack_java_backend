package ch.heigvd.digiback.business.tip;

import ch.heigvd.digiback.business.statistic.Stat;
import ch.heigvd.digiback.business.statistic.StatisticController;
import ch.heigvd.digiback.error.exception.WrongCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tip")
public class TipController {
    private static final int DEFAULT_PERIOD = 5;

    @Autowired
    private StatisticController statisticController;

    @GetMapping("/user/{idUser}")
    public Tip getTip(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser)
            throws WrongCredentialsException {
        Stat userStat = statisticController.getStatsForUserAndDays(token, idUser, DEFAULT_PERIOD);
        return Tip.getTipFromStat(userStat);
    }

    @GetMapping("/user/{idUser}/days/{daysNbr}")
    public Tip getTipForGivenPeriod(
            @RequestParam(name = "token") String token,
            @PathVariable(name = "idUser") Long idUser,
            @PathVariable(name = "daysNbr") int daysNbr)
            throws WrongCredentialsException {
        Stat userStat = statisticController.getStatsForUserAndDays(token, idUser, daysNbr);
        return Tip.getTipFromStat(userStat);
    }
}
