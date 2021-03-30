package reports.summary_performance_report.transaction;

import model.database.DB_Connection;
import reports.summary_performance_report.SummaryPerformanceReport;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public class SummaryPerformanceReport_Transaction implements I_SummaryPerformanceReport_Transaction {

    private PreparedStatement Stm = null;
    private LocalDate date;
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
                long copy_room = 0;
                long development = 0;
                long finishing = 0;
                long packing = 0;

                //copy room
                Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ?");
                Stm.setString(1, "Copy Room");
                ResultSet cr = Stm.executeQuery();
                while(cr.next()){
                    Stm = conn.prepareStatement("SELECT * FROM Task WHERE CAST(Task_start as DATE) = ? AND Task_CatalogCatalog_ID = ? AND Task_status = ? AND CAST(Task_start as TIME) BETWEEN ? AND ?");
                    Stm.setDate(1, Date.valueOf(date));
                    Stm.setInt(2, cr.getInt(1));
                    Stm.setString(3, "Completed");
                    Stm.setTime(4, Time.valueOf(from_time));
                    Stm.setTime(5, Time.valueOf(to_time));
                    ResultSet rs1 = Stm.executeQuery();
                    while (rs1.next()){
                        copy_room = Duration.between(rs1.getTimestamp(4).toLocalDateTime(), rs1.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }
                    rs1.close();
                }
                cr.close();
                Stm.close();

                //development
                Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ?");
                Stm.setString(1, "Development");
                ResultSet ds = Stm.executeQuery();
                while (ds.next()){
                    Stm = conn.prepareStatement("SELECT * FROM Task WHERE CAST(Task_start as DATE) = ? AND Task_CatalogCatalog_ID = ? AND Task_status = ? AND CAST(Task_start as TIME) BETWEEN ? AND ? ");
                    Stm.setDate(1, Date.valueOf(date));
                    Stm.setInt(2, ds.getInt(1));
                    Stm.setString(3, "Completed");
                    Stm.setTime(4, Time.valueOf(from_time));
                    Stm.setTime(5, Time.valueOf(to_time));
                    ResultSet rs2 = Stm.executeQuery();
                    while(rs2.next()){
                        development = Duration.between(rs2.getTimestamp(4).toLocalDateTime(), rs2.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }
                    rs2.close();
                }
                ds.close();
                Stm.close();

                //finishing
                Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ?");
                Stm.setString(1, "Finishing");
                ResultSet fs = Stm.executeQuery();
                while(fs.next()){
                    Stm = conn.prepareStatement("SELECT * FROM Task WHERE CAST(Task_start as DATE) = ?  AND Task_CatalogCatalog_ID = ? AND Task_status = ? AND CAST(Task_start as TIME) BETWEEN ? AND ? ");
                    Stm.setDate(1, Date.valueOf(date));
                    Stm.setInt(2, fs.getInt(1));
                    Stm.setString(3, "Completed");
                    Stm.setTime(4, Time.valueOf(from_time));
                    Stm.setTime(5, Time.valueOf(to_time));
                    ResultSet rs3 = Stm.executeQuery();
                    while (rs3.next()){
                        finishing = Duration.between(rs3.getTimestamp(4).toLocalDateTime(), rs3.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }
                    rs3.close();
                }
                fs.close();
                Stm.close();

                //Packing
                Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Task_department = ?");
                Stm.setString(1, "Packing");
                ResultSet ps = Stm.executeQuery();
                while(ps.next()){
                    Stm = conn.prepareStatement("SELECT * FROM Task WHERE CAST(Task_start as DATE) = ? AND Task_CatalogCatalog_ID = ? AND Task_status = ? AND CAST(Task_start as TIME) BETWEEN ? AND ?");
                    Stm.setDate(1, Date.valueOf(date));
                    Stm.setInt(2, ps.getInt(1));
                    Stm.setString(3, "Completed");
                    Stm.setTime(4, Time.valueOf(from_time));
                    Stm.setTime(5, Time.valueOf(to_time));
                    ResultSet rs4 = Stm.executeQuery();
                    while (rs4.next()){
                        packing = Duration.between(rs4.getTimestamp(4).toLocalDateTime(), rs4.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                    }
                    rs4.close();
                }
                ps.close();
                Stm.close();

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
}
