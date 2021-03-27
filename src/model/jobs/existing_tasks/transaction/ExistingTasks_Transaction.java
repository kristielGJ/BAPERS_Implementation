package model.jobs.existing_tasks.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.existing_tasks.ExistingTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExistingTasks_Transaction implements I_ExistingTasks_Transaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    public ExistingTasks_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    /**
     * creating a new ExistingTasks object
     *
     * @param exg_Task_ID
     * @param task_description
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    public void extendTaskList(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
        new ExistingTasks(exg_Task_ID, task_description, task_price, task_duration, department_name);
        saveTask(exg_Task_ID, task_description, task_price, task_duration, department_name);
    }

    /**
     * save the task details in the database
     *
     * @param exg_Task_ID
     * @param task_description
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    public void saveTask(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Task_Catalog` (`Catalog_ID`, `Task_name`, `Price`, `Task_department`, `Task_duration`) VALUES (?,?,?,?,?) ");
            Stm.setInt(1, exg_Task_ID);
            Stm.setString(2, task_description);
            Stm.setDouble(3, task_price);
            Stm.setString(4, department_name);
            Stm.setInt(5, task_duration);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieving the task details from the database
     *
     * @param existing_task_ID
     */
    public String[] retrieveExistingTask(int existing_task_ID) {
        String[] existing_task_details = new String[5];
        try {
            Stm = conn.prepareStatement("select * from Task_Catalog where Catalog_ID = ? ");
            Stm.setInt(1,existing_task_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                existing_task_details[0] = String.valueOf(rs.getInt(1));
                existing_task_details[1] = rs.getString(2);
                existing_task_details[2] = String.valueOf(rs.getDouble(3));
                existing_task_details[3] = rs.getString(4);
                existing_task_details[4] = String.valueOf(rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return existing_task_details;
    }

    /**
     * removing the task from the database
     *
     * @param existing_task_ID
     */
    public void removeExistingTask(int existing_task_ID) {
        try {
            Stm = conn.prepareStatement("DELETE FROM `bapers`.`Task_Catalog` WHERE Catalog_ID =?;");
            Stm.setInt(1,existing_task_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * update the task details in the database
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public ArrayList<Model> getAll() {
        ArrayList<Model> task_catalog_details = null;
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
