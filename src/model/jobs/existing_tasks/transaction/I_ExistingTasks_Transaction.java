package model.jobs.existing_tasks.transaction;

import model.Transaction;
import model.jobs.existing_tasks.ExistingTasks;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_ExistingTasks_Transaction extends Transaction {

    //creates a new existing task
    void extendTaskList(String task_description, double task_price, int task_duration, String department_name);

    //remove a existing task
    void removeExistingTask(int existing_task_ID);

    //updates a existing task
    void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name);

    //returns a list of existing tasks
    ArrayList<ExistingTasks> getExistingTasks();

}
