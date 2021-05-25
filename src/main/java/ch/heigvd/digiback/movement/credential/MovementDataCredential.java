package ch.heigvd.digiback.movement.credential;

import lombok.*;

@Builder
@Data
public class MovementDataCredential {
    private Float time;
    private Float xLinearAcc;
    private Float yLinearAcc;
    private Float zLinearAcc;
    private Float absoluteLinearAcc;

}