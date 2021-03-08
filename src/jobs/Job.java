package jobs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Job {

	private String priority;
	private LocalDateTime total_time;
	private LocalDateTime completion_deadline;
	private String special_instructions;
	private String job_status;
	private double price;
	private String payment_status;
	private Task task;

	/**
	 *
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return this.priority;
	}

	/**
	 *
	 * @param total_time
	 */
	public void setTotal_time(LocalDateTime total_time) {
		this.total_time = total_time;
	}

	public String getTotal_time() {
		//formatting the total_time in the format dd-MM-yyyy HH:mm
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatTotal_Time = this.total_time.format(format);
		return formatTotal_Time;
	}

	/**
	 *
	 * @param completion_deadline
	 */
	public void setCompletion_deadline(LocalDateTime completion_deadline) {
		this.completion_deadline = completion_deadline;
	}

	public String getCompletion_deadline() {
		//formatting the completion_deadline in the format dd-MM-yyyy HH:mm
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatCompletion_deadline = this.completion_deadline.format(format);
		return formatCompletion_deadline;
	}

	/**
	 *
	 * @param job_status
	 */
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getJob_status() {
		return this.job_status;
	}

	/**
	 *
	 * @param special_instructions
	 */
	public void setSpecial_instructions(String special_instructions) {
		this.special_instructions = special_instructions;
	}

	public String getSpecial_instructions() {
		return this.special_instructions;
	}

	/**
	 *
	 *  @param price
	 */
	public void calculatePrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 *
	 * @param payment_status
	 */
	public void setPaymentStatus(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPaymentStatus() {
		return this.payment_status;
	}


	/**
	 *
	 * @param job_ID
	 * @param job_data
	 */
	public void saveJob(int job_ID, String job_data) {
		// TODO - implement Job.saveJob
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param task_status
	 */
	public void addTask(String task_status) {
		task.addTask(task_status);
	}

	/**
	 *
	 * @param task_ID
	 */
	public void removeTask(int task_ID) {
		// TODO - implement Job.removeTask
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param task_data
	 */
	public void updateTask(String task_data) {
		// TODO - implement Job.updateTask
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param new_priority
	 * @param new_total_time
	 * @param new_completion_deadline
	 * @param new_special_instructions
	 * @param new_job_status
	 */
	public Job(String new_priority, LocalDateTime new_total_time, LocalDateTime new_completion_deadline, String new_special_instructions ,String new_job_status, double new_price, String new_payment_status) {
		priority = new_priority;
		total_time = new_total_time;
		completion_deadline = new_completion_deadline;
		special_instructions = new_special_instructions;
		job_status = new_job_status;
		price = new_price;
		payment_status = new_payment_status;
	}

}