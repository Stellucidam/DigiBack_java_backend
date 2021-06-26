package ch.heigvd.digiback.business.movement;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class MovementData {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovementData;

    @Getter
    private Float time;

    @Getter
    private Float xLinearAcc;

    @Getter
    private Float yLinearAcc;

    @Getter
    private Float zLinearAcc;

    @Getter
    private Float absoluteLinearAcc;

}
