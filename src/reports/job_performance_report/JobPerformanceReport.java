package reports.job_performance_report;

import reports.Reports;
import java.time.LocalDate;

public class JobPerformanceReport extends Reports {

    /**
     *
     * @param report_type
     * @param from_date
     * @param to_date
     */
    public JobPerformanceReport(String report_type, LocalDate from_date, LocalDate to_date) {
        super(report_type, from_date, to_date);
    }

}