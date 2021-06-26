package ch.heigvd.digiback.business.pain;

import ch.heigvd.digiback.business.movement.MovementType;
import ch.heigvd.digiback.business.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class Pain {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPain;

    @Getter
    private MovementType movementType;

    @Getter
    private Date date;

    @Getter
    @ManyToOne
    private User user;

    @Getter
    private int level;
}
