package reports;

import database.DB_Connection;
import reports.Interface_reports.I_JobPerformanceReport;
import reports.Interface_reports.I_SummaryPerformanceReport;

import java.sql.*;
import java.time.LocalDate;

public class SummaryPerformanceReport extends Reports implements I_SummaryPerformanceReport {

    private PreparedStatement Stm = null;

    /**
     * @param conn
     */
    public SummaryPerformanceReport(DB_Connection conn) {
        super(conn);
    }

    /**
     *  @param from_date
     * @param to_date
     * @return
     */
    public void generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date) {
        throw new UnsupportedOperationException();
    }

}