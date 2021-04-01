package model.jobs.existing_tasks.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.existing_tasks.ExistingTasks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public class ExistingTasks_Transaction implements I_ExistingTasks_Transaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    /**
     * constructor
     *
     * @param conn
     */
    public ExistingTasks_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    /**
     * creating a new existing task
     *
     * @param task_description
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    public void extendTaskList(String task_description, double task_price, int task_duration, String department_name){
        new ExistingTasks(1, task_description, task_price, task_duration, department_name);
        saveTask(task_description, task_price, task_duration, department_name);
    }

    /**
     * saves the existing task in the database
     *
     * @param task_description
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    public void saveTask(String task_description, double task_price, int task_duration, String department_name){
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Task_Catalog` (`Task_name`, `Price`, `Task_department`, `Task_duration`) VALUES (?,?,?,?) ");
            Stm.setString(1, task_description);
            Stm.setDouble(2, task_price);
            Stm.setString(3, department_name);
            Stm.setInt(4, task_duration);
            Stm.executeUpdate();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * remove the existing task from the database
     *
     * @param existing_task_ID
     */
    public void removeExistingTask(int existing_task_ID) {
        try {
            Stm = conn.prepareStatement("DELETE FROM `bapers`.`Task_Catalog` WHERE Catalog_ID =?;");
            Stm.setInt(1,existing_task_ID);
            Stm.executeUpdate();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the existing task details
     *
     * @param existing_task_ID
     * @param task_name
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    public void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name) {
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Task_Catalog` SET Task_name = ?, Price = ?, Task_department = ?, Task_duration = ? WHERE Catalog_ID =?;");
            Stm.setString(1, task_name);
            Stm.setDouble(2, task_price);
            Stm.setString(3, department_name);
            Stm.setInt(4, task_duration);
            Stm.setInt(5, existing_task_ID);
            Stm.executeUpdate();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a list of exiting tasks
     *
     * @return task_catalog_details
     */
    public ArrayList<ExistingTasks> getExistingTasks() {
        ArrayList<ExistingTasks> task_catalog_details = null;
        try {
            task_catalog_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Task_Catalog");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                ExistingTasks existingTasks = new ExistingTasks(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(5),
                        rs.getString(4)
                );
                task_catalog_details.add(existingTasks);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task_catalog_details;
    }

    /**
     *
     * @param id
     * @return existingTasks
     */
    @Override
    public ExistingTasks read(int id) {
        ExistingTasks existingTasks = null;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Catalog_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            existingTasks = new ExistingTasks(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(5),
                    rs.getString(4)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + existingTasks.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existingTasks;
    }

    /**
     *
     * @return task_catalog_details
     */
    @Override
    public ArrayList<ExistingTasks> getAll() {
        ArrayList<ExistingTasks> task_catalog_details = null;
        try {
            task_catalog_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Task_Catalog");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                ExistingTasks existingTasks = new ExistingTasks(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(5),
                        rs.getString(4)
                );
                task_catalog_details.add(existingTasks);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task_catalog_details;
    }

    /**
     *
     * @param id
     * @return removed
     */
    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            Stm = conn.prepareStatement("DELETE FROM Task_Catalog WHERE Catalog_ID =?");
            Stm.setInt(1, id);
            int update = Stm.executeUpdate();
            Stm.close();
            System.out.println("Removed " + id);
            removed = update == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removed;
    }
}
