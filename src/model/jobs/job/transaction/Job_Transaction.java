package model.jobs.job.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.job.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Job_Transaction implements I_Job_Transaction {

    private DB_Connection db = new DB_Connection();
    private Connection conn = db.connect();
    private PreparedStatement Stm = null;

    public Job_Transaction(){}

    /**
     * creating a new job object
     *
     * @param job_desc
     * @param priority
     * @param time
     * @param special_instructions
     * @param customer_account_no
     */
    public void addJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no){

        new Job(job_ID, job_desc, priority, special_instructions, "Pending", "Pending");
        saveJob(job_ID, job_desc, priority, time, special_instructions, "Pending", customer_account_no);
    }

    /**
     * save the job details in the database
     *
     * @param job_desc
     * @param priority
     * @param time
     * @param special_instructions
     */
    public void saveJob(int job_ID, String job_desc, String priority, int time, String special_instructions, String job_status, int customer_account_no) {
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Job` (`Job_ID`, `Job_desc`, `Urgency_level`, `Completion_deadline`, `Special_instruction`, `Status`, `Payment_status`, `CustomerAccount_no`) VALUES (?,?,?,?,?,?,?,?) ");

            Stm.setInt(1,job_ID);
            Stm.setString(2, job_desc);
            Stm.setString(3, priority);
            PreparedStatement Stm2 = conn.prepareStatement("select Subtotal_price from Job where Job_ID = ? ");
            Stm2.setInt(1, job_ID);
            ResultSet rs2 = Stm2.executeQuery();
            double subtotal_price = rs2.getDouble("Subtotal_price");

            if (priority == "Urgent"){
                Stm.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now().plusHours(6)));
                subtotal_price = subtotal_price + (18*0.05);
            }
            else if (priority == "Custom"){
                Stm.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now().plusHours(time)));
                subtotal_price = subtotal_price + ((24 - time)*0.05);
            }
            else {
                Stm.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now().plusHours(24)));
            }
            Stm.setString(5, special_instructions);
            Stm.setString(6, job_status);
            Stm.setString(7, "Pending");
            Stm.setInt(8, customer_account_no);
            Stm.executeUpdate();

            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Subtotal_price = ? WHERE Job_ID =?;");
            Stm.setDouble(1, subtotal_price);
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieving the job details from the database
     *
     * @param job_ID
     */
    public String[] retrieveJob(int job_ID){
        String[] job_details = new String[15];
        try {
            Stm = conn.prepareStatement("select * from Job where Job_ID = ? ");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()) {
                job_details[0] = String.valueOf(rs.getInt(1));
                job_details[1] = rs.getString(2);
                job_details[2] = rs.getString(3);
                job_details[3] = rs.getString(4);
                job_details[4] = String.valueOf(rs.getTimestamp(5));
                job_details[5] = rs.getString(6);
                job_details[6] = String.valueOf(rs.getTimestamp(7));
                job_details[7] = String.valueOf(rs.getDouble(8));
                job_details[8] = String.valueOf(rs.getDouble(9));
                job_details[9] = String.valueOf( rs.getDouble(10));
                job_details[10] = String.valueOf(rs.getTimestamp(11));
                job_details[11] = String.valueOf(rs.getTimestamp(12));
                job_details[12] = rs.getString(13);
                job_details[13] = String.valueOf(rs.getInt(14));
                job_details[14] = String.valueOf(rs.getInt(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job_details;
    }

    /**
     * updating the job status in the database
     *
     * @param job_ID
     * @param job_status
     */
    public void updateJobStatus(int job_ID, String job_status){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Status = ? WHERE Job_ID =?;");
            Stm.setString(1, job_status);
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (job_status == "Processing"){
            addStart_time(job_ID);
        }

        if (job_status == "Completed"){
            addCompletion_time(job_ID);
        }
    }

    /**
     * updating the priority level in the database
     *
     * @param job_ID
     * @param priority
     */
    public void updatePriority(int job_ID, String priority){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Urgency_level = ? WHERE Job_ID =?;");
            Stm.setString(1, priority);
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adding the completion_time of the job in the database
     *
     * @param job_ID
     */
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

    /**
     * adding the completion_time of the job in the database
     *
     * @param job_ID
     */
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

    /**
     * adding the payment deadline in the database for valued customers
     *
     * @param job_ID
     * @param payment_deadline
     */
    public void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Payment_deadline = ? WHERE Job_ID =?;");
            Stm.setTimestamp(1, Timestamp.valueOf(payment_deadline));
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    rs.getString(13)
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
                        rs.getString(13)
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
