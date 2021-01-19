package taxiApp.springapp.services.representations;

// Response
public class ErrorMessage {
    private String error;

    public ErrorMessage() {}

    public ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
