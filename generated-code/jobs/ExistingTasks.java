package jobs;
import java.time.LocalDateTime;
public class ExistingTasks {

	private int task_ID;
	private String task_description;
	private double task_price;
	private LocalDateTime task_duration;
	private String Department_name;

	/**
	 * 
	 * @param task_ID
	 */
	public void setTask_ID(int task_ID) {
		this.task_ID = task_ID;
	}

	public int getTask_ID() {
		return this.task_ID;
	}

	/**
	 * 
	 * @param task_description
	 */
	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public String getTask_description() {
		return this.task_description;
	}

	/**
	 * 
	 * @param task_data
	 */
	public boolean saveTask(String task_data) {
		// TODO - implement ExistingTasks.saveTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param task_price
	 */
	public void setTask_price(double task_price) {
		this.task_price = task_price;
	}

	public double getTask_price() {
		return this.task_price;
	}

	/**
	 * 
	 * @param task_duration
	 */
	public void setTask_duration(int task_duration) {
		// TODO - implement ExistingTasks.setTask_duration
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getTask_duration() {
		return this.task_duration;
	}

	/**
	 * 
	 * @param department_name
	 */
	public void setDepartment_name(String department_name) {
		// TODO - implement ExistingTasks.setDepartment_name
		throw new UnsupportedOperationException();
	}

	public String getDepartment_name() {
		// TODO - implement ExistingTasks.getDepartment_name
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param task_data
	 */
	public ExistingTasks(String task_data) {
		// TODO - implement ExistingTasks.ExistingTasks
		throw new UnsupportedOperationException();
	}

}