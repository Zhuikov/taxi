package core;

abstract public class TaxiItem {
    protected final int id;

    public int getId() {
        return id;
    }

    public TaxiItem(int id) {
        this.id = id;
    }
}
