package reports;

import database.DB_Connection;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;

public class SummaryPerformanceReport extends Reports {

	private static DB_Connection db = new DB_Connection();
	private static Connection conn = db.connect();
	private static PreparedStatement Stm = null;

	/**
	 *
	 * @param report_type
	 * @param from_date
	 * @param to_date
	 */
	public SummaryPerformanceReport(String report_type, LocalDate from_date, LocalDate to_date) {
		super(report_type, from_date, to_date);
	}

	/**
	 *  @param from_date
	 * @param to_date
	 * @return
	 */
	public static HashMap<String, String[]> generateReport(LocalDate from_date, LocalDate to_date) {
		HashMap<String, String[]> report_details = new HashMap<>();
		String[] details = new String[4];
		long Day_shift1_Copy = 0;
		long Day_shift2_Copy = 0;
		long Night_shift1_Copy = 0;
		long Development_time = 0;
		long Finishing_time = 0;
		long Packing_time = 0;
		try {
			Stm = conn.prepareStatement("select Task_start, Task_CatalogCatalog_ID from Task WHERE CAST(Task_start as DATE) BETWEEN ? AND ? ");
			Stm.setDate(1, Date.valueOf(from_date));
			Stm.setDate(2, Date.valueOf(to_date));
			ResultSet rs = Stm.executeQuery();

			while (rs.next()) {
				// Copy_Room
				Stm = conn.prepareStatement("select Task_department, Catalog_ID from Task_Catalog where Catalog_ID = ? AND Task_department = ?");
				Stm.setInt(1, rs.getInt("Task_CatalogCatalog_ID"));
				Stm.setString(2,"Copy Room");
				ResultSet rs1 = Stm.executeQuery();

				while (rs1.next()){
					//copy_room day shift 1
					Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '05:00:00' AND '14:30:00' ");
					Stm.setInt(1, rs1.getInt("Catalog_ID"));
					ResultSet rs2 = Stm.executeQuery();
					Day_shift1_Copy += Duration.between(rs2.getTimestamp("Task_start").toLocalDateTime(), rs2.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

					//copy_room day shift 2
					Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
					Stm.setInt(1, rs1.getInt("Catalog_ID"));
					ResultSet rs3 = Stm.executeQuery();
					Day_shift2_Copy += Duration.between(rs3.getTimestamp("Task_start").toLocalDateTime(), rs3.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;

					//copy_room night shift 1
					Stm = conn.prepareStatement("select Task_start, Task_completion from Task where Task_CatalogCatalog_ID = ? AND CAST(Task_start as TIME) BETWEEN '14:30:00' AND '22:00:00' ");
					Stm.setInt(1, rs1.getInt("Catalog_ID"));
					ResultSet rs4 = Stm.executeQuery();
					Night_shift1_Copy += Duration.between(rs4.getTimestamp("Task_start").toLocalDateTime(), rs4.getTimestamp("Task_completion").toLocalDateTime()).getSeconds() / 60;
				}
				//String total_time_hm = String.format("%02d:%02d", TimeUnit.SECONDS.toHours(Copy_time), TimeUnit.SECONDS.toMinutes(Copy_time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Copy_time)));
				report_details.put(rs.getString("name"), details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report_details;
	}



}