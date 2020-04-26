package core;

public class CV extends TaxiItem {

    private final int userId;
    private final int experience;

    public CV(int id, int userId, int experience) {
        super(id);
        this.userId = userId;
        this.experience = experience;
    }

    public int getUserId() {
        return userId;
    }

    public int getExperience() {
        return experience;
    }
}
