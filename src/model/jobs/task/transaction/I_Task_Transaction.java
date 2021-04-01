package model.jobs.task.transaction;

import model.Transaction;
import model.jobs.task.Task_List;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_Task_Transaction extends Transaction {

    //created a new task
    void addTask(String existing_task, int Job_ID, String technician, String task_status);

    //updates a task status
    void updateTaskStatus(int Task_ID, String Task_status);

    //returns a list of existing task names
    String[] retrieveExistingTasks();

    //returns a list of technician names
    String[] retrieveTechnicians(String existing_task);

    //returns a list of task which are associated with the job
    ArrayList<Task_List> getAllTasks(int job_ID);

}
