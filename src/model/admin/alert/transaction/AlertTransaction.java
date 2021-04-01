package model.admin.alert.transaction;

import model.Utils;
import model.admin.alert.Alert;
import model.database.DB_Connection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An AlertTransaction class, responsible for the interaction between the database and Alert class.
 */

public class AlertTransaction implements I_AlertTransaction {

    private Connection conn;
    public AlertTransaction(DB_Connection conn) {
        this.conn = conn.getConn();
    }

    /**
     * A create function to create a new Alert in the database.
     * @param name
     * @param message
     * @param time
     * @param jobId
     * @return An Alert object that reflects the newly stored Alert in the database.
     */
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

    /**
     * Quries the database for a specific Alert.
     * @param id Alert ID relating to specific Alert record.
     * @return Alert object based on the specific id.
     */
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

    /**
     * Updates an Alert record based on id.
     * @param id ALert id that will be updated.
     * @param name The new name for the Alert.
     * @param message The new message for the Alert.
     * @param time The new time for the Alert.
     * @param jobId the new jobId for the Alert.
     * @return An Alert object with the updated fields.
     */
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

    /**
     * Returns all Alert objects.
     * @return An ArrayList of all Alert objects in the database.
     */
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

    /**
     * Removes an ALert from the database based on an ID.
     * @param id The ID to remove.
     * @return A Boolean based on whether it was successful or not.
     */
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
