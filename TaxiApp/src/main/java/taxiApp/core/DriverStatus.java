package taxiApp.core;

public enum DriverStatus {
    BUSY,
    FREE;

    @Override
    public String toString() {
        switch (this) {
            case BUSY: return "BUSY";
            case FREE: return "FREE";
        }
        return "Unreachable";
    }
}
