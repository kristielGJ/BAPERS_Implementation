package reports;

import java.time.LocalDate;

/**
 *
 * @author Manpreet
 */

public class Reports {

	private String report_type;
	private LocalDate from_date;
	private LocalDate to_date;

	/**
	 * constructor
	 *
	 * @param new_report_type
	 * @param new_from_date
	 * @param new_to_date
	 */
	public Reports(String new_report_type, LocalDate new_from_date, LocalDate new_to_date) {
		report_type = new_report_type;
		from_date = new_from_date;
		to_date = new_to_date;
	}

	/**
	 * getters and setters
	 *
	 */
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getReport_type() {
		return this.report_type;
	}

	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}

	public LocalDate getFrom_date() {
		return this.from_date;
	}

	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}

	public LocalDate getTo_date(){ return this.to_date; }

}