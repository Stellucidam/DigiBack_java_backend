package ch.heigvd.digiback.business.movement;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class Angle {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAngle;

    @Getter
    private int position;

    @Getter
    private Float angle;

}
