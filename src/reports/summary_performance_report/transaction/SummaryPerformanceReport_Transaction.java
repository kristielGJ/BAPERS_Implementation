package reports.summary_performance_report.transaction;

import model.database.DB_Connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SummaryPerformanceReport_Transaction implements I_SummaryPerformanceReport_Transaction {

    private DB_Connection db = new DB_Connection();
    private Connection conn = db.connect();
    private PreparedStatement Stm = null;
    private HashMap<String, String[]> summary = new HashMap<>();
    private String[] details = new String[4];
    private LocalDate date;
    private long Day_shift1 = 0;
    private long Day_shift2= 0;
    private long Night_shift1 = 0;
    private long total_time = 0;

    /**
     *  @param from_date
     * @param to_date
     * @return
     */
    public HashMap<String, String[]> copy_room_performance(LocalDate from_date, LocalDate to_date) {
        date = from_date;
        try {
            while ((date.isAfter(from_date) || date.isEqual(from_date)) && (date.isBefore(to_date) || date.isEqual(to_date))){

                Stm = conn.prepareStatement("select Task_start, Task_CatalogCatalog_ID from Task WHERE CAST(Task_start as DATE) = ? ");
                Stm.setDate(1, Date.valueOf(date));
                ResultSet rs = Stm.executeQuery();

                while (rs.next()) {
                    Stm = conn.prepareStatement("select Task_department, Catalog_ID from Task_Catalog where Catalog_ID = ? AND Task_department = ?");
                    Stm.setInt(1, rs.getInt("Task_CatalogCatalog_ID"));
                    Stm.setString(2,"Copy Room");
                    ResultSet rs1 = Stm.executeQuery();

                    while (rs1.next()){
                        //day shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '05:00:00' AND '14:30:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs2 = Stm.executeQuery();
                        Day_shift1 += Duration.between(rs2.getTimestamp("Task_start").toLocalDateTime(), rs2.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //day shift 2
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs3 = Stm.executeQuery();
                        Day_shift2 += Duration.between(rs3.getTimestamp("Task_start").toLocalDateTime(), rs3.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //night shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs4 = Stm.executeQuery();
                        Night_shift1 += Duration.between(rs4.getTimestamp("Task_start").toLocalDateTime(), rs4.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
                    }
                    details[0] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift1), TimeUnit.SECONDS.toMinutes(Day_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift1)));
                    details[1] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift2), TimeUnit.SECONDS.toMinutes(Day_shift2) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift2)));
                    details[2] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Night_shift1), TimeUnit.SECONDS.toMinutes(Night_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Night_shift1)));
                    total_time = Day_shift1 + Day_shift2 + Night_shift1;
                    details[3] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(total_time), TimeUnit.SECONDS.toMinutes(total_time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total_time)));
                    //details: day_shift1, day_shift2, night_shift1, total_time
                    summary.put(String.valueOf(date), details);
                }
                date = date.plusDays(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }

    /**
     *  @param from_date
     * @param to_date
     * @return
     */
    public HashMap<String, String[]> development_performance(LocalDate from_date, LocalDate to_date) {
        date = from_date;
        try {
            while ((date.isAfter(from_date) || date.isEqual(from_date)) && (date.isBefore(to_date) || date.isEqual(to_date))){

                Stm = conn.prepareStatement("select Task_start, Task_CatalogCatalog_ID from Task WHERE CAST(Task_start as DATE) = ? ");
                Stm.setDate(1, Date.valueOf(date));
                ResultSet rs = Stm.executeQuery();

                while (rs.next()) {
                    Stm = conn.prepareStatement("select Task_department, Catalog_ID from Task_Catalog where Catalog_ID = ? AND Task_department = ?");
                    Stm.setInt(1, rs.getInt("Task_CatalogCatalog_ID"));
                    Stm.setString(2,"Development");
                    ResultSet rs1 = Stm.executeQuery();

                    while (rs1.next()){
                        //day shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '05:00:00' AND '14:30:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs2 = Stm.executeQuery();
                        Day_shift1 += Duration.between(rs2.getTimestamp("Task_start").toLocalDateTime(), rs2.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //day shift 2
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs3 = Stm.executeQuery();
                        Day_shift2 += Duration.between(rs3.getTimestamp("Task_start").toLocalDateTime(), rs3.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //night shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs4 = Stm.executeQuery();
                        Night_shift1 += Duration.between(rs4.getTimestamp("Task_start").toLocalDateTime(), rs4.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
                    }
                    details[0] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift1), TimeUnit.SECONDS.toMinutes(Day_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift1)));
                    details[1] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift2), TimeUnit.SECONDS.toMinutes(Day_shift2) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift2)));
                    details[2] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Night_shift1), TimeUnit.SECONDS.toMinutes(Night_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Night_shift1)));
                    total_time = Day_shift1 + Day_shift2 + Night_shift1;
                    details[3] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(total_time), TimeUnit.SECONDS.toMinutes(total_time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total_time)));
                    //details: day_shift1, day_shift2, night_shift1, total_time
                    summary.put(String.valueOf(date), details);
                }
                date = date.plusDays(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }

    /**
     *  @param from_date
     * @param to_date
     * @return
     */
    public HashMap<String, String[]> finishing_performance(LocalDate from_date, LocalDate to_date) {
        date = from_date;
        try {
            while ((date.isAfter(from_date) || date.isEqual(from_date)) && (date.isBefore(to_date) || date.isEqual(to_date))){

                Stm = conn.prepareStatement("select Task_start, Task_CatalogCatalog_ID from Task WHERE CAST(Task_start as DATE) = ? ");
                Stm.setDate(1, Date.valueOf(date));
                ResultSet rs = Stm.executeQuery();

                while (rs.next()) {
                    Stm = conn.prepareStatement("select Task_department, Catalog_ID from Task_Catalog where Catalog_ID = ? AND Task_department = ?");
                    Stm.setInt(1, rs.getInt("Task_CatalogCatalog_ID"));
                    Stm.setString(2,"Finishing");
                    ResultSet rs1 = Stm.executeQuery();

                    while (rs1.next()){
                        //day shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '05:00:00' AND '14:30:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs2 = Stm.executeQuery();
                        Day_shift1 += Duration.between(rs2.getTimestamp("Task_start").toLocalDateTime(), rs2.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //day shift 2
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs3 = Stm.executeQuery();
                        Day_shift2 += Duration.between(rs3.getTimestamp("Task_start").toLocalDateTime(), rs3.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //night shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs4 = Stm.executeQuery();
                        Night_shift1 += Duration.between(rs4.getTimestamp("Task_start").toLocalDateTime(), rs4.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
                    }
                    details[0] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift1), TimeUnit.SECONDS.toMinutes(Day_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift1)));
                    details[1] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift2), TimeUnit.SECONDS.toMinutes(Day_shift2) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift2)));
                    details[2] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Night_shift1), TimeUnit.SECONDS.toMinutes(Night_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Night_shift1)));
                    total_time = Day_shift1 + Day_shift2 + Night_shift1;
                    details[3] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(total_time), TimeUnit.SECONDS.toMinutes(total_time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total_time)));
                    //details: day_shift1, day_shift2, night_shift1, total_time
                    summary.put(String.valueOf(date), details);
                }
                date = date.plusDays(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }

    /**
     *  @param from_date
     * @param to_date
     * @return
     */
    public HashMap<String, String[]> packing_performance(LocalDate from_date, LocalDate to_date) {
        date = from_date;
        try {
            while ((date.isAfter(from_date) || date.isEqual(from_date)) && (date.isBefore(to_date) || date.isEqual(to_date))){

                Stm = conn.prepareStatement("select Task_start, Task_CatalogCatalog_ID from Task WHERE CAST(Task_start as DATE) = ? ");
                Stm.setDate(1, Date.valueOf(date));
                ResultSet rs = Stm.executeQuery();

                while (rs.next()) {
                    Stm = conn.prepareStatement("select Task_department, Catalog_ID from Task_Catalog where Catalog_ID = ? AND Task_department = ?");
                    Stm.setInt(1, rs.getInt("Task_CatalogCatalog_ID"));
                    Stm.setString(2,"Packing");
                    ResultSet rs1 = Stm.executeQuery();

                    while (rs1.next()){
                        //day shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '05:00:00' AND '14:30:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs2 = Stm.executeQuery();
                        Day_shift1 += Duration.between(rs2.getTimestamp("Task_start").toLocalDateTime(), rs2.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //day shift 2
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs3 = Stm.executeQuery();
                        Day_shift2 += Duration.between(rs3.getTimestamp("Task_start").toLocalDateTime(), rs3.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

                        //night shift 1
                        Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
                        Stm.setInt(1, rs1.getInt("Catalog_ID"));
                        ResultSet rs4 = Stm.executeQuery();
                        Night_shift1 += Duration.between(rs4.getTimestamp("Task_start").toLocalDateTime(), rs4.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
                    }
                    details[0] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift1), TimeUnit.SECONDS.toMinutes(Day_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift1)));
                    details[1] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Day_shift2), TimeUnit.SECONDS.toMinutes(Day_shift2) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Day_shift2)));
                    details[2] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Night_shift1), TimeUnit.SECONDS.toMinutes(Night_shift1) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Night_shift1)));
                    total_time = Day_shift1 + Day_shift2 + Night_shift1;
                    details[3] = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(total_time), TimeUnit.SECONDS.toMinutes(total_time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total_time)));
                    //details: day_shift1, day_shift2, night_shift1, total_time
                    summary.put(String.valueOf(date), details);
                }
                date = date.plusDays(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }
}
