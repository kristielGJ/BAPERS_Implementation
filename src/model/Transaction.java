package model;

public interface Transaction {
    Model read(int id);
    boolean remove(int id);
}
