package reports;

import database.DB_Connection;
import reports.Interface_reports.I_JobPerformanceReport;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class JobPerformanceReport extends Reports implements I_JobPerformanceReport {

    private PreparedStatement Stm = null;

    /**
     * @param conn
     */
    public JobPerformanceReport(DB_Connection conn) {
        super(conn);
    }

    /**
     *  @param from_date
     * @param to_date
     * @return
     */
    public HashMap<Integer, HashMap<Integer, String[][]>> generateJobPerformanceReport(LocalDate from_date, LocalDate to_date) {
        HashMap<Integer, String[][]> details = new HashMap<>();
        HashMap<Integer, HashMap<Integer, String[][]>> report_details = new HashMap<>();
        String[] task_details = new String[2];
        String[] task_catalog_details = new String[2];
        String[] user_details = new String[1];

        try {
            Stm = conn.prepareStatement("select Job_ID from Job where CAST(Start as DATE) BETWEEN ? AND ?");
            Stm.setDate(1, Date.valueOf(from_date));
            Stm.setDate(2, Date.valueOf(to_date));
            ResultSet rs = Stm.executeQuery();

            while (rs.next()) {
                Stm = conn.prepareStatement("select Task_CatalogCatalog_ID, Task_start, Task_completion, User_accountUser_ID from Task where JobJob_ID = ?");
                Stm.setInt(1, rs.getInt("Job_ID"));
                ResultSet rs1 = Stm.executeQuery();

                while (rs1.next()){
                    task_details[0] = String.valueOf(rs1.getTimestamp("Task_start"));
                    //total time taken
                    long dur = Duration.between(rs1.getTimestamp("Task_start").toLocalDateTime(), rs1.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
                    String total_time = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(dur), TimeUnit.SECONDS.toMinutes(dur) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dur)));
                    task_details[1] = total_time;

                    Stm = conn.prepareStatement("select Catalog_ID, Price, Task_department from Task_Catalog where Catalog_ID = ?");
                    Stm.setInt(1, rs1.getInt("Task_CatalogCatalog_ID"));
                    ResultSet rs2 = Stm.executeQuery();

                    while (rs2.next()){
                        task_catalog_details[0] = String.valueOf(rs2.getDouble("Price"));
                        task_catalog_details[1] = rs2.getString("Task_department");
                    }
                    Stm = conn.prepareStatement("select name  from User_account where id = ?");
                    Stm.setInt(1, rs1.getInt("User_accountUser_ID"));
                    ResultSet rs3 = Stm.executeQuery();

                    while (rs3.next()){
                        user_details[0] = rs3.getString("name");
                    }

                    //task_details: Task_start, Total_time_taken(min)
                    //task_catalog_details: Price, Task_department
                    //user_details: User_name
                    details.put(rs1.getInt("Task_CatalogCatalog_ID"), new String[][]{task_details, task_catalog_details, user_details});
                }
                report_details.put(rs.getInt("Job_ID"), details);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return report_details;
    }

}