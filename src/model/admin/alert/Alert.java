package model.admin.alert;

import model.Model;
import java.time.LocalDateTime;

public class Alert extends Model {
	private String name;
	private String message;
	private LocalDateTime time;
	private int jobId;

	public Alert(int id, String name, String message, LocalDateTime time, int jobId) {
		setId(id);
		this.name = name;
		this.message = message;
		this.time = time;
		this.jobId = jobId;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	protected void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getJobId() {
		return jobId;
	}

	protected void setJobId(int jobId) {
		this.jobId = jobId;
	}
}