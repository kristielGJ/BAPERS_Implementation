package reports.Interface_reports;

        import java.time.LocalDate;
        import java.util.HashMap;

public interface I_JobPerformanceReport {

    HashMap<Integer, HashMap<Integer, String[][]>> generateJobPerformanceReport(LocalDate from_date, LocalDate to_date);

}
