package ch.heigvd.digiback.business.movement;

import ch.heigvd.digiback.business.pain.Pain;
import ch.heigvd.digiback.business.user.User;
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
    private List<Angle> angles;

    @Getter
    @ManyToOne
    private User user;

    @Getter
    @OneToOne(optional = true)
    private Pain pain;
}
