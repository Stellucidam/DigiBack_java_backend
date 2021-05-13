package ch.heigvd.digiback.auth.credential;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenCredential {

    private String token;
    private Long idUser;
}
