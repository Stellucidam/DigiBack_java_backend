package ch.heigvd.digiback.business.activity.credential;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class ExerciseCredential {
    private Date date;
    private Long nbrExercises;
}
