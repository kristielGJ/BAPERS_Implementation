package reports.job_performance_report.transaction;

import reports.job_performance_report.JobPerformanceReport;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_JobPerformanceReport_Transaction {

    //generates job sheet
    ArrayList<JobPerformanceReport> generateJobSheet(LocalDate from_date, LocalDate to_date, int customer_ID);

}
