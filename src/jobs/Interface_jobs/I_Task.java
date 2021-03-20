package jobs.Interface_jobs;

public interface I_Task {

    void addTask(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status);

    String[] retrieveTasks(int task_ID);

    void updateTaskStatus(int Task_ID, String Task_status);



}
