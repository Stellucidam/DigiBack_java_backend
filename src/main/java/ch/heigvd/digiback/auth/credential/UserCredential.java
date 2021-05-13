package ch.heigvd.digiback.auth.credential;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCredential {

    private String username;
    private String password;
}
