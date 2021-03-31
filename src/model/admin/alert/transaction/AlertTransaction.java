package model.admin.alert.transaction;

import model.Model;
import model.Utils;
import model.admin.alert.Alert;
import model.admin.userAccount.UserAccount;
import model.database.DB_Connection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AlertTransaction implements I_AlertTransaction {

    Connection conn;

    public AlertTransaction(DB_Connection conn) {
        this.conn = conn.getConn();
    }

    @Override
    public Alert create(String name, String message, LocalDateTime time, int jobId) {
        Alert alert = null;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO Alert (name, message, time, JobJob_ID) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, name);
            st.setString(2, message);
            st.setTimestamp(3, Timestamp.valueOf(time));
            st.setInt(4, jobId);
            st.executeUpdate();
            int id = Utils.getGeneratedKey(st);
            alert = new Alert(id, name, message, time, jobId);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }

    @Override
    public Alert read(int id) {
            Alert alert = null;
            try {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM Alert WHERE alert_id = ?");
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                alert = new Alert(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getInt(5)
                );
                rs.close();
                st.close();
                System.out.println("Queried " + alert.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return alert;
    }

    @Override
    public Alert update(int id, String name, String message, LocalDateTime time, int jobId) {
        Alert alert = null;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "UPDATE Alert SET name=?, message=?, time=?, JobJob_ID=? WHERE alert_id=?");
            st.setString(1, name);
            st.setString(2, message);
            st.setTimestamp(3, Timestamp.valueOf(time));
            st.setInt(4, jobId);
            st.setInt(5, id);
            st.executeUpdate();
            alert = new Alert(id, name, message, time, jobId);
            st.close();
            System.out.println("Updated " + alert.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }

    @Override
    public ArrayList<Alert> getAll() {
        ArrayList<Alert> alerts = null;
        try {
            alerts = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Alert");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Alert account = new Alert(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getInt(5)
                );
                alerts.add(account);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alerts;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM Alert WHERE alert_id=?");
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
