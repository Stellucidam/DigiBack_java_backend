package ch.heigvd.digiback.movement;

import ch.heigvd.digiback.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class Movement {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;

    @Getter
    private MovementType type;

    @Getter
    private Date date;

    @Getter
    @OneToMany
    private List<MovementData> movementData;

    @Getter
    @ManyToOne
    private User user;
}
