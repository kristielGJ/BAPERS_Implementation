package model;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface Transaction {
    ArrayList<Model> getAll();
    Model read(int id);
    boolean remove(int id);
}
