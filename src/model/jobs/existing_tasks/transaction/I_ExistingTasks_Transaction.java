package model.jobs.existing_tasks.transaction;

import model.Transaction;
import model.jobs.existing_tasks.ExistingTasks;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_ExistingTasks_Transaction extends Transaction {

    /**
     * creates a new existing task
     *
     * @param task_description
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    void extendTaskList(String task_description, double task_price, int task_duration, String department_name);

    /**
     * remove a existing task
     *
     * @param existing_task_ID
     */
    void removeExistingTask(int existing_task_ID);

    /**
     * updates a existing task
     *
     * @param existing_task_ID
     * @param task_name
     * @param task_price
     * @param task_duration
     * @param department_name
     */
    void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name);

    /**
     * returns a list of existing tasks
     *
     */
    ArrayList<ExistingTasks> getExistingTasks();

    ArrayList<ExistingTasks> getAll();

}
