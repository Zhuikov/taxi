package core;

public class PersonInfo {

    private final String fName;
    private final String sName;
    private final String phone;

    public PersonInfo(String fName, String sName, String phone) {
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
}
