package reports.job_performance_report;

import java.time.LocalDateTime;

/**
 *
 * @author Manpreet
 */

//existing task list
public class JobPerformanceReport{

    private int job_id;
    private double price;
    private int task_id;
    private String location;
    private LocalDateTime start_time;
    private String time_taken;
    private String technician;

    //constructor
    public JobPerformanceReport( int new_job_id, double new_price, int new_task_id, String new_location, LocalDateTime new_start_time, String new_time_taken, String new_technician) {
        job_id = new_job_id;
        price = new_price;
        task_id = new_task_id;
        location = new_location;
        start_time = new_start_time;
        time_taken = new_time_taken;
        technician = new_technician;
    }

    //getters and setters
    public String getTime_taken() {
        return time_taken;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
