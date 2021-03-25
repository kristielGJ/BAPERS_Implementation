package reports.individual_performance_report.transaction;

import java.time.LocalDate;
import java.util.HashMap;

public interface I_IndividualPerformanceReport_Transaction {

    HashMap<String, HashMap<Integer, String[][]>> generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date);

}
