package ch.heigvd.digiback.business.user;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Getter
    @Column(unique = true)
    private String username;

    @Getter
    private String email;

    @Getter
    private String secret;

    @Getter
    private String salt;

    @Getter
    private String token;

    @Getter
    @Setter
    private boolean enabled;
}
