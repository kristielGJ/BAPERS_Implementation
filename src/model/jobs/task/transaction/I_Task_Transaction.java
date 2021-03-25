package model.jobs.task.transaction;

import model.Transaction;

public interface I_Task_Transaction extends Transaction {

    void addTask(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status);

    String[] retrieveTasks(int task_ID);

    void updateTaskStatus(int Task_ID, String Task_status);

}
