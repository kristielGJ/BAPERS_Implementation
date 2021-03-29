package reports.individual_performance_report.transaction;

import model.database.DB_Connection;
import reports.individual_performance_report.IndividualPerformanceReport;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Manpreet
 */

public class IndividualPerformanceReport_Transaction implements I_IndividualPerformanceReport_Transaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    //constructor
    public IndividualPerformanceReport_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    //generates individual performance report
    public ArrayList<IndividualPerformanceReport> generateIndividualPerformance_Report(LocalDate from_date, LocalDate to_date) {
        ArrayList<IndividualPerformanceReport> report = null;
        try {
            report = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM User_account WHERE role = ?");
            Stm.setString(1, "Technician");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Stm = conn.prepareStatement("SELECT * FROM Task WHERE User_accountUser_ID = ? AND CAST(Task_start as DATE) BETWEEN ? AND ?");
                Stm.setInt(1, rs.getInt(1));
                Stm.setDate(2, Date.valueOf(from_date));
                Stm.setDate(3, Date.valueOf(to_date));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Catalog_ID = ?");
                    Stm.setInt(1, rs1.getInt(7));
                    ResultSet rs2 = Stm.executeQuery();
                    while (rs2.next()){
                        long dur = Duration.between(rs1.getTimestamp(4).toLocalDateTime(), rs1.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                        String total_time = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(dur), TimeUnit.SECONDS.toMinutes(dur) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dur)));
                        IndividualPerformanceReport details = new IndividualPerformanceReport(
                                "Individual Performance Report",
                                from_date,
                                to_date,
                                rs.getString(2),
                                rs2.getInt(1),
                                rs2.getString(4),
                                rs1.getTimestamp(4).toLocalDateTime(),
                                total_time,
                                dur
                        );
                        report.add(details);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
}
