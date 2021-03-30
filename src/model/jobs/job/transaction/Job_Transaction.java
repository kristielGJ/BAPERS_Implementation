package model.jobs.job.transaction;

import model.Model;
import model.admin.alert.transaction.AlertTransaction;
import model.database.DB_Connection;
import model.jobs.job.Job;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static model.database.DB_Connection.printQuery;

/**
 *
 * @author Manpreet
 */

public class Job_Transaction implements I_Job_Transaction {

    private PreparedStatement Stm = null;
    private double subtotal_price = 0;
    private Connection conn;
    private AlertTransaction alertTransaction;

    //constructor
    public Job_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
        alertTransaction = new AlertTransaction(conn);
    }

   //creates a new job
    public void addJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no){
        LocalDateTime deadline;
        if (priority == "Urgent"){
            deadline = LocalDateTime.now().plusHours(6);
            subtotal_price = subtotal_price + (18*5);
        }
        else if (priority == "Custom"){
            deadline = LocalDateTime.now().plusHours(time);
            subtotal_price = subtotal_price + ((24 - time)*5);
        }
        else {
            deadline = LocalDateTime.now().plusHours(24);
        }

        new Job(job_ID, job_desc, priority, special_instructions, "Pending", "Pending", deadline, 0.0);
        saveJob(job_ID, job_desc, priority, time, special_instructions, "Pending", deadline, customer_account_no);
    }

    //save the job details in the database
    public void saveJob(int job_ID, String job_desc, String priority, int time, String special_instructions, String job_status, LocalDateTime deadline, int customer_account_no) {
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Job` (`Job_ID`, `Job_desc`, `Urgency_level`, `Completion_deadline`, `Special_instruction`, `Status`, `Payment_status`, `CustomerAccount_no`) VALUES (?,?,?,?,?,?,?,?) ");
            Stm.setInt(1,job_ID);
            Stm.setString(2, job_desc);
            Stm.setString(3, priority);
            Stm.setTimestamp(4, Timestamp.valueOf(deadline));
            Stm.setString(5, special_instructions);
            Stm.setString(6, job_status);
            Stm.setString(7, "Pending");
            Stm.setInt(8, customer_account_no);
            Stm.executeUpdate();

            PreparedStatement Stm2 = conn.prepareStatement("select Subtotal_price from Job where Job_ID = ? ");
            Stm2.setInt(1, job_ID);
            ResultSet rs2 = Stm2.executeQuery();
            while(rs2.next()){
                subtotal_price += rs2.getDouble("Subtotal_price");
            }

            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Subtotal_price = ? WHERE Job_ID =?;");
            Stm.setDouble(1, subtotal_price);
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
            alertTransaction.create("job", "", deadline, job_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   //update the job status
    public void updateJobStatus(int job_ID, String job_status){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Status = ? WHERE Job_ID =?;");
            Stm.setString(1, job_status);
            Stm.setInt(2,job_ID);
            if (job_status == "Processing"){
                Stm.executeUpdate();
                addStart_time(job_ID);
            }
            else if (job_status == "Completed"){
                Boolean update = true;
                //checking whether all tasks associated with the job are completed or not
                PreparedStatement Stm2 = conn.prepareStatement("SELECT * FROM Task WHERE JobJob_ID = ?;");
                Stm2.setInt(1,job_ID);
                ResultSet rs = Stm2.executeQuery();
                while (rs.next()){
                    if(!rs.getString(2).equals("Completed")){
                        update = false;
                    }
                }
                if (update == true){
                    Stm.executeUpdate();
                    Stm2 = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?;");
                    Stm2.setInt(1,job_ID);
                    ResultSet rs2 = Stm2.executeQuery();
                    while (rs2.next()){
                        PreparedStatement Stm3 = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?;");
                        Stm3.setInt(1,rs2.getInt(14));
                        ResultSet rs3 = Stm3.executeQuery();
                        while (rs3.next()){
                            if (rs3.getString(2).equals("Valued")){
                                addPaymentDeadline(job_ID, rs2.getTimestamp(7).toLocalDateTime().plusDays(30));
                            }
                        }
                    }
                    //adding completion time
                    addCompletion_time(job_ID);
                }
            }
            else {
                Stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //adding the completion_time of the job in the database
    public void addCompletion_time(int job_ID){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Completion_date_time = ? WHERE Job_ID =?;");
            Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //adding the start_time of the job in the database
    public void addStart_time(int job_ID){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Start = ? WHERE Job_ID =?;");
            Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   //adding the payment deadline in the database for valued customers
    public void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Payment_deadline = ? WHERE Job_ID =?;");
            Stm.setTimestamp(1, Timestamp.valueOf(payment_deadline));
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
            alertTransaction.create("payment", "", payment_deadline, job_ID);
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //returns a list of jobs associated with the customer
    public ArrayList<Job> getJobs(int customer_id, String type) {
        ArrayList<Job> job_details = null;
        try {
            job_details = new ArrayList<>();
            if (type == "all"){
                Stm = conn.prepareStatement("SELECT * FROM Job WHERE CustomerAccount_no = ? AND Payment_status = ? ");
                Stm.setInt(1,customer_id);
                Stm.setString(2,"Pending");
            }
            else if (type == "completed"){
                Stm = conn.prepareStatement("SELECT * FROM Job WHERE CustomerAccount_no = ? AND Payment_status = ? AND Status = ?");
                Stm.setInt(1,customer_id);
                Stm.setString(2,"Pending");
                Stm.setString(3,"Completed");
            }
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Job job = new Job(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(6),
                        rs.getString(4),
                        rs.getString(13),
                        rs.getTimestamp(12).toLocalDateTime(),
                        rs.getDouble(10)
                );
                job_details.add(job);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job_details;
    }

    //returns a list of active jobs (not completed)
    public ArrayList<Job> getActiveJobs() {
        ArrayList<Job> job_details = null;
        try {
            job_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Status != ?");
            Stm.setString(1,"Completed");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Job job = new Job(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(6),
                        rs.getString(4),
                        rs.getString(13),
                        rs.getTimestamp(12).toLocalDateTime(),
                        rs.getDouble(10)
                );
                job_details.add(job);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job_details;
    }

    @Override
    public Job read(int id) {
        Job job = null;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            job = new Job(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(6),
                    rs.getString(4),
                    rs.getString(13),
                    rs.getTimestamp(12).toLocalDateTime(),
                    rs.getDouble(10)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + job.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }

    @Override
    public ArrayList<Job> getAll() {
        ArrayList<Job> job_details = null;
        try {
            job_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Job");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Job job = new Job(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(6),
                        rs.getString(4),
                        rs.getString(13),
                        rs.getTimestamp(12).toLocalDateTime(),
                        rs.getDouble(10)
                );
                job_details.add(job);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job_details;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            Stm = conn.prepareStatement("DELETE FROM Job WHERE Job_ID =?");
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
