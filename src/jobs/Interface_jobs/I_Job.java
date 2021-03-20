package jobs.Interface_jobs;

import java.time.LocalDateTime;

public interface I_Job {

    void createJob(int job_ID, String job_desc, String priority, LocalDateTime completion_deadline, String special_instructions, String job_status, LocalDateTime start_time, String payment_status, int customer_account_no);

    String[] retrieveJob(int job_ID);

    void updateJobStatus(int job_ID, String job_status);

    void updatePriority(int job_ID, String priority);

    void addCompletion_time(int job_ID);

    void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline);

}
