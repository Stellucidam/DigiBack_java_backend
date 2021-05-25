package ch.heigvd.digiback.pain;

import ch.heigvd.digiback.movement.MovementType;
import ch.heigvd.digiback.movement.credential.MovementDataCredential;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Builder
@Data
public class PainCredential {
    private Long idUser;
    private MovementType movementType;
    private Date date;
    private int level;
}
