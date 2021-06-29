package ch.heigvd.digiback.business.activity;

import ch.heigvd.digiback.business.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class Activity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActivity;

    @Getter
    @Column(unique=true)
    private Date date;

    @Getter
    @Setter
    private Long nbrSteps;

    @Getter
    @Setter
    private Long nbrExercices;

    @Getter
    @Setter
    private Long nbrQuiz;

    @Getter
    @ManyToOne
    private User user;
}
