package model.jobs.job.transaction;

import model.Transaction;

import java.time.LocalDateTime;

public interface I_Job_Transaction extends Transaction {

    void addJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no);

    String[] retrieveJob(int job_ID);

    void updateJobStatus(int job_ID, String job_status);

    void updatePriority(int job_ID, String priority);

    void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline);

}

