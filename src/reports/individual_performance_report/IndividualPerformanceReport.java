package reports.individual_performance_report;

import reports.Reports;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Manpreet
 */

public class IndividualPerformanceReport extends Reports {

	private String technician;
	private int task_ID;
	private String location;
	private LocalDateTime start;
	private String time_taken;
	private long dur;

	/**
	 * constructor
	 *
	 * @param report_type
	 * @param from_date
	 * @param to_date
	 * @param new_technician
	 * @param new_task_ID
	 * @param new_location
	 * @param new_start
	 * @param new_time_taken
	 * @param new_dur
	 */
	public IndividualPerformanceReport(String report_type, LocalDate from_date, LocalDate to_date, String new_technician, int new_task_ID, String new_location,  LocalDateTime new_start, String new_time_taken, long new_dur){
		super(report_type, from_date, to_date);
		technician = new_technician;
		task_ID = new_task_ID;
		location = new_location;
		start = new_start;
		time_taken = new_time_taken;
		dur = new_dur;
	}

	/**
	 * setters and getters
	 *
	 */
	public String getTime_taken() {
		return time_taken;
	}

	public void setTime_taken(String time_taken) {
		this.time_taken = time_taken;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public int getTask_ID() {
		return task_ID;
	}

	public void setTask_ID(int task_ID) {
		this.task_ID = task_ID;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getLocation() { return location;	}

	public void setLocation(String location) { this.location = location; }

	public long getDur() { return dur; }

	public void setDur(long dur) { this.dur = dur;	}
}