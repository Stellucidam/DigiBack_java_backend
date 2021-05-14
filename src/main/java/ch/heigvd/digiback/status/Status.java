package ch.heigvd.digiback.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.ws.rs.core.Response;

@AllArgsConstructor
@Builder
@Data
public class Status {
    @Setter
    private StatusType status;
    @Setter
    private String message;
}
