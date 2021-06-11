package ch.heigvd.digiback.activity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class ActivityCredential {
    private Long idUser;
    private Date date;
    private Long nbrSteps;
    private Long nbrExercices;
    private Long nbrQuiz;
}
