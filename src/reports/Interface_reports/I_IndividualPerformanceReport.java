package reports.Interface_reports;

import java.time.LocalDate;
import java.util.HashMap;

public interface I_IndividualPerformanceReport {

    HashMap<String, HashMap<Integer, String[][]>> generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date);

}
