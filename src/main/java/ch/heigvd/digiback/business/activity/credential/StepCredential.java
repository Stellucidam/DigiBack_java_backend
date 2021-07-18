package ch.heigvd.digiback.business.activity.credential;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class StepCredential {
    private Date date;
    private Long nbrSteps;
}
