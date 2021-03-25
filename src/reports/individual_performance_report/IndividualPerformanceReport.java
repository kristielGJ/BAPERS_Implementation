package reports.individual_performance_report;

import reports.Reports;
import java.time.LocalDate;

public class IndividualPerformanceReport extends Reports {

	public IndividualPerformanceReport(String report_type, LocalDate from_date, LocalDate to_date) {
		super(report_type, from_date, to_date);
	}
}