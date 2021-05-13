package ch.heigvd.digiback.movement.credential;

import ch.heigvd.digiback.movement.MovementType;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class MovementCredential {
    private Long idUser;
    private MovementType type;
    private Date date;
}
