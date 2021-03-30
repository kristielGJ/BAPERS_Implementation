package reports.job_performance_report.transaction;

import model.database.DB_Connection;
import reports.job_performance_report.JobPerformanceReport;
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

public class JobPerformanceReport_Transaction implements I_JobPerformanceReport_Transaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    //constructor
    public JobPerformanceReport_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    //generates job sheet
    public ArrayList<JobPerformanceReport> generateJobSheet(LocalDate from_date, LocalDate to_date, int customer_ID) {
        ArrayList<JobPerformanceReport> report = null;
        try {
            report = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE CustomerAccount_no = ? AND Status = ? AND  CAST(Start as DATE) BETWEEN ? AND ?");
            Stm.setInt(1, customer_ID);
            Stm.setString(2, "Completed");
            Stm.setDate(3, Date.valueOf(from_date));
            Stm.setDate(4, Date.valueOf(to_date));
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Stm = conn.prepareStatement("SELECT * FROM Task WHERE JobJob_ID = ?");
                Stm.setInt(1, rs.getInt(1));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    Stm = conn.prepareStatement("SELECT * FROM User_account WHERE user_account_id = ?");
                    Stm.setInt(1, rs1.getInt(6));
                    ResultSet rs2 = Stm.executeQuery();
                    while (rs2.next()){
                        Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Catalog_ID = ?");
                        Stm.setInt(1, rs1.getInt(7));
                        ResultSet rs3 = Stm.executeQuery();
                        while (rs3.next()){
                            long dur = Duration.between(rs1.getTimestamp(4).toLocalDateTime(), rs1.getTimestamp(5).toLocalDateTime()).getSeconds() / 60;
                            String total_time = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(dur), TimeUnit.SECONDS.toMinutes(dur) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dur)));
                            JobPerformanceReport details = new JobPerformanceReport(
                                    rs.getInt(1),
                                    rs3.getDouble(3),
                                    rs3.getInt(1),
                                    rs3.getString(4),
                                    rs1.getTimestamp(4).toLocalDateTime(),
                                    total_time,
                                    rs2.getString(2)
                            );
                            report.add(details);
                        }
                        rs3.close();
                    }
                    rs2.close();
                }
                rs1.close();
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }

}
