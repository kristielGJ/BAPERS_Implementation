package reports.summary_performance_report;

import reports.Reports;
import java.time.LocalDate;

public class SummaryPerformanceReport extends Reports {

	/**
	 *
	 * @param report_type
	 * @param from_date
	 * @param to_date
	 */
	public SummaryPerformanceReport(String report_type, LocalDate from_date, LocalDate to_date) {
		super(report_type, from_date, to_date);
	}

}