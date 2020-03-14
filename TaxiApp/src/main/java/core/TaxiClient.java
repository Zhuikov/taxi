package core;


// No registration in system
public class TaxiClient {

    public TaxiClient(String fName, String sName, String phone) {
        this.fName = fName;
        this.sName = sName;
        this.phone = phone;
    }

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }

    public String getPhone() {
        return phone;
    }

    private final String fName;
    private final String sName;
    private final String phone;

}
