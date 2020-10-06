package taxiApp.core;

public enum OrderStatus {
    WAIT_FOR_DRIVER,
    ACCEPTED,
    FINISHED,
    DECLINED;

    @Override
    public String toString() {
        switch (this) {
            case ACCEPTED: return "Accepted";
            case FINISHED: return "Finished";
            case DECLINED: return "Declined";
            case WAIT_FOR_DRIVER: return "Wait for driver";
        }
        return "Unreachable";
    }
}
