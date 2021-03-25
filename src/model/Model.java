package model;

public abstract class Model {
    private int id;

    @Override
    public String toString() {
        return "<" + this.getClass().getName() + " " + id + ">";
    }

    public int getId() {
        return id;
    }
    protected void setId(int id) {
        this.id = id;
    }
}
