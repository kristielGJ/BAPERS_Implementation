package model.jobs.task.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.job.Job;
import model.jobs.task.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task_Transaction implements I_Task_Transaction {

    private DB_Connection db = new DB_Connection();
    private Connection conn = db.connect();
    private PreparedStatement Stm = null;

    /**
     * creating a new object Task
     *
     * @param task_ID
     * @param task_status
     * @param Job_ID
     * @param user_ID
     * @param ExistingTask_ID
     */
    public void addTask(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status) {
        new Task(task_ID, task_status);
        storeTaskDetails(task_ID, ExistingTask_ID, Job_ID, user_ID, task_status);
    }

    /**
     * store task details in the database
     *
     * @param task_ID
     * @param ExistingTask_ID
     * @param task_status
     * @param Job_ID
     */
    public void storeTaskDetails(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status) {
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Task` (`Task_ID` ,`Task_status`,`Task_start`, `User_accountUser_ID`,`Task_CatalogCatalog_ID`, `JobJob_ID`) VALUES (?,?,?,?,?,?) ");
            Stm.setInt(1, task_ID);
            Stm.setString(2, task_status);
            Stm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            Stm.setInt(4, user_ID);
            Stm.setInt(5, ExistingTask_ID);
            Stm.setInt(6, Job_ID);
            Stm.executeUpdate();
            calculateSub_price(Job_ID, ExistingTask_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieving the pre-stored task in the database
     *
     * @param task_ID
     */
    public String[] retrieveTasks(int task_ID) {
        String[] task_details = new String[8];
        try {
            Stm = conn.prepareStatement("select * from Task where Task_ID = ? ");
            Stm.setInt(1,task_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                task_details[0] = String.valueOf(rs.getInt(1));
                task_details[1] = rs.getString(2);
                task_details[2] = String.valueOf(rs.getDouble(3));
                task_details[3] = String.valueOf(rs.getTimestamp(4));
                task_details[4] = String.valueOf(rs.getTimestamp(5));
                task_details[5] = String.valueOf(rs.getInt(6));
                task_details[6] = String.valueOf(rs.getInt(7));
                task_details[7] = String.valueOf(rs.getInt(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task_details;
    }

    /**
     * updating task status in the database
     *
     * @param Task_status
     * @param Task_ID
     */
    public void updateTaskStatus(int Task_ID, String Task_status) {
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Task` SET Task_status = ? WHERE Task_ID =?;");
            Stm.setString(1, Task_status);
            Stm.setInt(2,Task_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Task_status == "Processing"){
            start(Task_ID);
        }

        if (Task_status == "Completed"){
            finish(Task_ID);
        }
    }

    public void calculateSub_price(int job_ID, int ExistingTask_ID){
        try {
            double task_price = 0;
            double subtotal_price = 0;

            Stm = conn.prepareStatement("select Price from Task_Catalog where Catalog_ID = ? ");
            Stm.setInt(1, ExistingTask_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                task_price = rs.getDouble("Price");
            }

            Stm = conn.prepareStatement("select Subtotal_price from Job where Job_ID = ? ");
            Stm.setInt(1, job_ID);
            ResultSet rs2 = Stm.executeQuery();
            while(rs2.next()){
                subtotal_price = rs2.getDouble("Subtotal_price");
            }

            subtotal_price = subtotal_price + task_price;

            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Subtotal_price = ? WHERE Job_ID =?;");
            Stm.setDouble(1, subtotal_price);
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * stores the finish_time of the task in the database
     *
     * @param Task_ID
     */
    public void start( int Task_ID) {
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Task` SET Task_start = ?WHERE Task_ID =?;");
            Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            Stm.setInt(2,Task_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * stores the finish_time of the task in the database
     *
     * @param Task_ID
     */
    public void finish( int Task_ID) {
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Task` SET Task_completion = ?WHERE Task_ID =?;");
            Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            Stm.setInt(2,Task_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task read(int id) {
        Task task = null;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Task WHERE Task_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            task = new Task(
                    rs.getInt(1),
                    rs.getString(2)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + task.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public ArrayList<Task> getAll() {
        ArrayList<Task> task_details = null;
        try {
            task_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Task");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt(1),
                        rs.getString(2)
                );
                task_details.add(task);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task_details;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            Stm = conn.prepareStatement("DELETE FROM Task WHERE Task_ID =?");
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
