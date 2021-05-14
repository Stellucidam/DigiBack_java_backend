package ch.heigvd.digiback.status;

public enum StatusType {
    FAIL,
    SUCCESS;

    @Override
    public String toString() {
        switch (this) {
            case FAIL: return "fail";
            case SUCCESS: return "success";
            default: throw new IllegalArgumentException();
        }
    }
}
