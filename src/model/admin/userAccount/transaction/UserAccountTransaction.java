package model.admin.userAccount.transaction;

import model.Utils;
import model.admin.userAccount.UserAccount;
import model.database.DB_Connection;

import java.sql.*;
import java.util.ArrayList;

public class UserAccountTransaction implements I_UserAccountTransaction {

    private final Connection conn;

    public UserAccountTransaction(DB_Connection conn) {
        this.conn = conn.getConn();
    }

    @Override
    public UserAccount create(String name, String email, String phone, String password, String role) {
        UserAccount account = null;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO User_account (name, email, phone, password, role) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, password);
            st.setString(5, role);
            st.executeUpdate();
            int id = Utils.getGeneratedKey(st);
            account = new UserAccount(id, name, email, phone, password, role);
            st.close();
            System.out.println("Created " + account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public UserAccount create(String name, String email, String phone, String password, String role, String department) {
        UserAccount account = null;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO User_account (name, email, phone, password, role, department) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, password);
            st.setString(5, role);
            st.setString(6, department);
            st.executeUpdate();
            int id = Utils.getGeneratedKey(st);
            account = new UserAccount(id, name, email, phone, password, role, department);
            st.close();
            System.out.println("Created " + account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public UserAccount authenticate(int id, String password) {
        UserAccount account = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM User_account WHERE user_account_id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    account = new UserAccount(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)
                    );
                }
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public UserAccount read(int id) {
        UserAccount account = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM User_account WHERE user_account_id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            account = new UserAccount(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            rs.close();
            st.close();
            System.out.println("Queried " + account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public UserAccount update(int id, String name, String email, String phone, String password, String role) {
        UserAccount account = null;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "UPDATE User_account SET name=?, email=?, phone=?, role=? WHERE user_account_id=?");
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, role);
            st.setInt(5, id);
            st.executeUpdate();
            account = new UserAccount(id, name, email, phone, password, role);
            st.close();
            System.out.println("Updated " + account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public UserAccount update(int id, String name, String email, String phone, String password, String role, String department) {
        UserAccount account = null;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "UPDATE User_account SET name=?, email=?, phone=?, role=?, department=? WHERE user_account_id=?");
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, role);
            st.setString(5, department);
            st.setInt(6, id);
            st.executeUpdate();
            account = new UserAccount(id, name, email, phone, password, role, department);
            st.close();
            System.out.println("Updated " + account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }


    @Override
    public ArrayList<UserAccount> getAll() {
        ArrayList<UserAccount> accounts = null;
        try {
            accounts = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM User_account");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserAccount account = new UserAccount(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                accounts.add(account);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM User_account WHERE user_account_id=?");
            st.setInt(1, id);
            int update = st.executeUpdate();
            st.close();
            System.out.println("Removed " + id);
            removed = update == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removed;
    }
}
