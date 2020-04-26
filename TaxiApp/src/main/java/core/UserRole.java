package core;

public enum UserRole {
    CLIENT,
    DRIVER,
    MANAGER,
    OWNER;

    @Override
    public String toString() {
        switch (this) {
            case CLIENT: return "Client";
            case OWNER: return "Cars Owner";
            case DRIVER: return "Driver";
            case MANAGER: return "Manager";
        }
        return "Unreachable";
    }
}
