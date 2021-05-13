package ch.heigvd.digiback.movement;

public enum MovementType {
    FRONT_TILT,
    BACK_TILT,
    LEFT_TILT,
    RIGHT_TILT,
    RIGHT_ROTATION,
    LEFT_ROTATION;

    @Override
    public String toString() {
        switch(this) {
            case FRONT_TILT: return "FRONT_TILT";
            case BACK_TILT: return "BACK_TILT";
            case LEFT_TILT: return "LEFT_TILT";
            case RIGHT_TILT: return "RIGHT_TILT";
            case RIGHT_ROTATION: return "RIGHT_ROTATION";
            case LEFT_ROTATION: return "LEFT_ROTATION";
            default: throw new IllegalArgumentException();
        }
    }
}
