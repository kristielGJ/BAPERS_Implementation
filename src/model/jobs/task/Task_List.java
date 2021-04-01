package model.jobs.task;

/**
 *
 * @author Manpreet
 *
 * existing task list
 */

public class Task_List{

    private int task_id;
    private String task_description;
    private String location;
    private String status;
    private double price;
    private int duration;

    /**
     * constructor
     *
     * @param new_task_id
     * @param new_task_description
     * @param new_location
     * @param new_status
     * @param new_price
     * @param new_duration
     */
    public Task_List( int new_task_id, String new_task_description, String new_location, String new_status, double new_price, int new_duration ) {
        task_id = new_task_id;
        task_description = new_task_description;
        location = new_location;
        status = new_status;
        price = new_price;
        duration = new_duration;
    }

    /**
     * getters and setters
     */
    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
