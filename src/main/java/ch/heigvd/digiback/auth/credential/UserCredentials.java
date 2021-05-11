package ch.heigvd.digiback.auth.credential;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCredentials {

    private String username;
    private String password;
}
