package Reports;

public class Reports {

	private int report_ID;
	private String report_type;
	private Date from_date;
	private Date to_date;

	/**
	 * 
	 * @param report_ID
	 */
	public void setReport_ID(int report_ID) {
		this.report_ID = report_ID;
	}

	public int getReport_ID() {
		return this.report_ID;
	}

	/**
	 * 
	 * @param report_type
	 */
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getReport_type() {
		return this.report_type;
	}

	/**
	 * 
	 * @param from_date
	 */
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getFrom_date() {
		return this.from_date;
	}

	/**
	 * 
	 * @param to_date
	 */
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	/**
	 * 
	 * @param report_ID
	 * @param report_type
	 * @param from_date
	 * @param to_date
	 */
	public Reports(int report_ID, String report_type, Date from_date, Date to_date) {
		// TODO - implement Reports.Reports
		throw new UnsupportedOperationException();
	}

}