package core;

public enum MessageType {
    ACK,
    NACK,
    ORDER,
    CV,
    DRIVER;

    @Override
    public String toString() {
        switch (this) {
            case ACK: return "Accept";
            case NACK: return "Reject";
            case ORDER: return "Order";
            case CV: return "CV";
            case DRIVER: return "Driver";
        }
        return "Unreachable";
    }
}
