package reports.summary_performance_report.transaction;

import model.database.DB_Connection;
import reports.summary_performance_report.SummaryPerformanceReport;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public class SummaryPerformanceReport_Transaction implements I_SummaryPerformanceReport_Transaction {

    private PreparedStatement Stm = null;
    private LocalDate date;
    private long copy_room = 0;
    private long development = 0;
    private long finishing = 0;
    private long packing = 0;
    private Connection conn;

    //constructor
    public SummaryPerformanceReport_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    //generates summary performance report
    public ArrayList<SummaryPerformanceReport> generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date, LocalTime from_time, LocalTime to_time) {
        ArrayList<SummaryPerformanceReport> report = null;
        date = from_date;
        try {
            report = new ArrayList<>();
            while ((date.isAfter(from_date) || date.isEqual(from_date)) && (date.isBefore(to_date) || date.isEqual(to_date))){
                Stm = conn.prepareStatement("SELECT * FROM Task WHERE CAST(Task_start as DATE) ? AND Task_status = ? AND CAST(Task_start as TIME) BETWEEN ? AND ?;");
                Stm.setDate(1, Date.valueOf(date));
                Stm.setString(2, "Completed");
                Stm.setTime(3, Time.valueOf(from_time));
                Stm.setTime(4, Time.valueOf(to_time));
                ResultSet rs = Stm.executeQuery();
                while (rs.next()){
                    //copy room
                    Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ? AND Catalog_ID = ?;");
                    Stm.setString(1, "Copy Room");
                    Stm.setInt(1, rs.getInt(7));
                    ResultSet cr = Stm.executeQuery();
                    while(cr.next()){
                        copy_room += Duration.between(rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }

                    //development
                    Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ? AND Catalog_ID = ?;");
                    Stm.setString(1, "Development");
                    Stm.setInt(1, rs.getInt(7));
                    ResultSet dr = Stm.executeQuery();
                    while(dr.next()){
                        development += Duration.between(rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }

                    //finishing
                    Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ? AND Catalog_ID = ?;");
                    Stm.setString(1, "Finishing");
                    Stm.setInt(1, rs.getInt(7));
                    ResultSet fr = Stm.executeQuery();
                    while(fr.next()){
                        finishing += Duration.between(rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }

                    //packing
                    Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ? AND Catalog_ID = ?;");
                    Stm.setString(1, "Packing");
                    Stm.setInt(1, rs.getInt(7));
                    ResultSet pr = Stm.executeQuery();
                    while(pr.next()){
                        packing += Duration.between(rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }
                }
                SummaryPerformanceReport details = new SummaryPerformanceReport(
                        "Summary Performance Report",
                        from_date,
                        to_date,
                        date,
                        copy_room,
                        development,
                        finishing,
                        packing
                );
                report.add(details);
                date = date.plusDays(1);
                System.out.println(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
}
