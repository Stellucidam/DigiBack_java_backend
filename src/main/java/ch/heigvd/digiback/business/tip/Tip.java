package ch.heigvd.digiback.business.tip;

import ch.heigvd.digiback.business.statistic.Stat;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tip {
    private TipType type;
    private Float duration;
    private int repetition;

    public static Tip getTipFromStat(Stat stat) {
        // TODO
        return null;
    }
}
