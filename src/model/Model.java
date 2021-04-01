package model;

/**
 * A Model class that encapsulate all the similar attributes between Model objjects (i.e. as entries in a
 * database).
 */
public abstract class Model {
    private int id;

    @Override
    public String toString() {
        return "<" + this.getClass().getName() + " " + id + ">";
    }

    public int getId() {
        return id;
    }
    // Protected as this should not be accessed globally when the instance is created!
    protected void setId(int id) {
        this.id = id;
    }
}
