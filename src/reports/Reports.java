package reports;
import java.util.Date;

public class Reports {

	private String report_type;
	private Date from_date;
	private Date to_date;

	/**
	 *
	 * @param report_type
	 * @param from_date
	 * @param to_date
	 */
	public Reports(String report_type, Date from_date, Date to_date) {
		// TODO - implement Reports.Reports
		throw new UnsupportedOperationException();
	}

    public Reports() {
		throw new UnsupportedOperationException();
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

	public Date getTo_date(){ return this.to_date; }



}