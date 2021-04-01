package reports.individual_performance_report.transaction;

import reports.individual_performance_report.IndividualPerformanceReport;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_IndividualPerformanceReport_Transaction {

    /**
     * generate individual performance report
     * @param from_date
     * @param to_date
     */
    ArrayList<IndividualPerformanceReport> generateIndividualPerformance_Report(LocalDate from_date, LocalDate to_date);

}
