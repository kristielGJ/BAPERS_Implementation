package model.jobs.existing_tasks.transaction;

import model.Transaction;

public interface I_ExistingTasks_Transaction extends Transaction {

    void extendTaskList(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name);

    String[] retrieveExistingTask(int existing_task_ID);

    void removeExistingTask(int existing_task_ID);

    void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name);

}
