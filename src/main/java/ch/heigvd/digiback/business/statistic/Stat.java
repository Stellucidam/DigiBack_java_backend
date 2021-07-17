package ch.heigvd.digiback.business.statistic;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Stat {
    private Long id;
    private String stat;
    private float highestAngle;
    private float angleAverage;
}
