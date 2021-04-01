package reports.summary_performance_report;

import reports.Reports;
import java.time.LocalDate;

/**
 *
 * @author Manpreet
 */

public class SummaryPerformanceReport extends Reports {

	private LocalDate date;
	private long copy_room_time;
	private long development_time;
	private long finishing_time;
	private long packing_time;

	/**
	 * constructor
	 *
	 * @param report_type
	 * @param from_date
	 * @param to_date
	 * @param new_date
	 * @param new_copy_room_time
	 * @param new_development_time
	 * @param new_finishing_time
	 * @param new_packing_time
	 */
	public SummaryPerformanceReport(String report_type, LocalDate from_date, LocalDate to_date, LocalDate new_date, long new_copy_room_time, long new_development_time, long new_finishing_time, long new_packing_time) {
		super(report_type, from_date, to_date);
		date = new_date;
		copy_room_time = new_copy_room_time;
		development_time = new_development_time;
		finishing_time = new_finishing_time;
		packing_time = new_packing_time;
	}

	/**
	 *getters and setters
	 *
	 */
	public long getPacking_time() {
		return packing_time;
	}

	public void setPacking_time(long packing_time) {
		this.packing_time = packing_time;
	}

	public long getFinishing_time() {
		return finishing_time;
	}

	public void setFinishing_time(long finishing_time) {
		this.finishing_time = finishing_time;
	}

	public long getDevelopment_time() {
		return development_time;
	}

	public void setDevelopment_time(long development_time) {
		this.development_time = development_time;
	}

	public long getCopy_room_time() {
		return copy_room_time;
	}

	public void setCopy_room_time(long copy_room_time) {
		this.copy_room_time = copy_room_time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}