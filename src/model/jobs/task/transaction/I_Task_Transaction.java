package model.jobs.task.transaction;

import model.Transaction;
import model.jobs.task.Task_List;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_Task_Transaction extends Transaction {

    /**
     * created a new task
     *
     * @param existing_task
     * @param Job_ID
     * @param technician
     * @param task_status
     */
    void addTask(String existing_task, int Job_ID, String technician, String task_status);

    /**
     * updates a task status
     *
     * @param Task_ID
     * @param Task_status
     */
    void updateTaskStatus(int Task_ID, String Task_status);

    /**
     * returns a list of existing task names
     *
     * @return
     */
    String[] retrieveExistingTasks();

    /**
     * returns a list of technician names
     *
     * @param existing_task
     * @return
     */
    String[] retrieveTechnicians(String existing_task);

    /**
     * returns a list of task which are associated with the job
     *
     * @param job_ID
     * @return
     */
    ArrayList<Task_List> getAllTasks(int job_ID);

}
