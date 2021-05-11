package ch.heigvd.digiback.auth.credential;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenCredentials {

    private String token;
    private int idUser;
}
