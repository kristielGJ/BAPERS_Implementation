package Admin;

public class Alerts {

	private int alert_ID;
	private String alert_name;
	private String alert_message;
	private boolean seen = False;

	/**
	 * 
	 * @param alert_ID
	 */
	public void setAlert_ID(int alert_ID) {
		this.alert_ID = alert_ID;
	}

	public int getAlert_ID() {
		return this.alert_ID;
	}

	/**
	 * 
	 * @param alert_name
	 */
	public void setAlert_name(String alert_name) {
		this.alert_name = alert_name;
	}

	public String getAlert_name() {
		return this.alert_name;
	}

	/**
	 * 
	 * @param alert_message
	 */
	public void setAlert_message(String alert_message) {
		this.alert_message = alert_message;
	}

	public String getAlert_message() {
		return this.alert_message;
	}

	/**
	 * 
	 * @param message
	 */
	public void alert(String message) {
		// TODO - implement Alerts.alert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param seen
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public boolean getSeen() {
		return this.seen;
	}

	/**
	 * 
	 * @param alert_ID
	 * @param alert_name
	 * @param alert_message
	 * @param seen
	 */
	public Alerts(int alert_ID, String alert_name, String alert_message, boolean seen) {
		// TODO - implement Alerts.Alerts
		throw new UnsupportedOperationException();
	}

}