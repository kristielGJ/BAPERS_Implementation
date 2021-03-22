package admin;

import java.sql.ResultSet;

public interface I_UserAccount {

    void createUserAccount(String name, String email, int phone, String role);
    void removeUserAccount(int id);
    ResultSet getAllUsers();
    void update(int id, String name, String email, int phone, String role);
}
