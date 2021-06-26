package ch.heigvd.digiback.business.pain;

import ch.heigvd.digiback.business.movement.MovementType;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class PainCredential {
    private Long idUser;
    private MovementType movementType;
    private Date date;
    private int level;
}
