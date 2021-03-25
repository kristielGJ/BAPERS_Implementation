package reports.job_performance_report.transaction;

import java.time.LocalDate;
import java.util.HashMap;

public interface I_JobPerformanceReport_Transaction {

    HashMap<Integer, HashMap<Integer, String[][]>> generateJobPerformanceReport(LocalDate from_date, LocalDate to_date);

}
