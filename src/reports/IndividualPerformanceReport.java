package reports;

import database.DB_Connection;
import reports.Interface_reports.I_IndividualPerformanceReport;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class IndividualPerformanceReport extends Reports implements I_IndividualPerformanceReport {

	private PreparedStatement Stm = null;

	/**
	 * @param conn
	 */
	public IndividualPerformanceReport(DB_Connection conn) {
		super(conn);
	}

	/**
	 *  @param from_date
	 * @param to_date
	 * @return
	 */
	public HashMap<String, HashMap<Integer, String[][]>> generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date) {
		HashMap<Integer, String[][]> details = new HashMap<Integer, String[][]>();
		HashMap<String, HashMap<Integer, String[][]>> report_details = new HashMap<>();
		String[] task_details = new String[2];
		String[] task_catalog_details = new String[1];

		try {
			Stm = conn.prepareStatement("select id , name from User_account ");
			ResultSet rs = Stm.executeQuery();

			while (rs.next()) {
				Stm = conn.prepareStatement("select Task_CatalogCatalog_ID, Task_start, Task_completion from Task where User_accountUser_ID = ? AND  CAST(Task_start as DATE) BETWEEN ? AND ? ");
				Stm.setInt(1, rs.getInt("id"));
				Stm.setDate(2, Date.valueOf(from_date));
				Stm.setDate(3, Date.valueOf(to_date));
				ResultSet rs1 = Stm.executeQuery();

				while (rs1.next()){
					task_details[0] = String.valueOf(rs1.getTimestamp("Task_start"));
					//total time taken
					long dur = Duration.between(rs1.getTimestamp("Task_start").toLocalDateTime(), rs1.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
					String total_time = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(dur), TimeUnit.SECONDS.toMinutes(dur) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dur)));
					task_details[1] = total_time;

					Stm = conn.prepareStatement("select Task_department from Task_Catalog where Catalog_ID = ?");
					Stm.setInt(1, rs1.getInt("Task_CatalogCatalog_ID"));
					ResultSet rs2 = Stm.executeQuery();

					while (rs2.next()){
						task_catalog_details[0] = rs2.getString("Task_department");
					}

					//task_details: Task_start, Total_time_taken(min)
					//task_catalog_details: Task_department
					details.put(rs1.getInt("Task_CatalogCatalog_ID"), new String[][]{task_details, task_catalog_details});
				}
				report_details.put(rs.getString("name"), details);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return report_details;
	}
}