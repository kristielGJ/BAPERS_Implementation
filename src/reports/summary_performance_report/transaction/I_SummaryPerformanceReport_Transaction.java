package reports.summary_performance_report.transaction;

import java.time.LocalDate;
import java.util.HashMap;

public interface I_SummaryPerformanceReport_Transaction {

    HashMap<String, String[]> copy_room_performance(LocalDate from_date, LocalDate to_date);

    HashMap<String, String[]> development_performance(LocalDate from_date, LocalDate to_date);

    HashMap<String, String[]> finishing_performance(LocalDate from_date, LocalDate to_date);

    HashMap<String, String[]> packing_performance(LocalDate from_date, LocalDate to_date);

}
