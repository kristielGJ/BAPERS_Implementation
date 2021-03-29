package reports.summary_performance_report.transaction;

import reports.summary_performance_report.SummaryPerformanceReport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_SummaryPerformanceReport_Transaction {

    ArrayList<SummaryPerformanceReport> generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date, LocalTime from_time, LocalTime to_time);

}
