package model.jobs.job;

import model.Model;
import java.time.LocalDateTime;

/**
 *
 * @author Manpreet
 */

public class Job extends Model {

	private int job_ID;
	private String job_desc;
	private String priority;
	private String special_instructions;
	private String job_status;
	private String payment_status;
	private LocalDateTime completion_deadline;
	private Double price;

	/**
	 * constructor
	 *
	 * @param new_job_ID
	 * @param new_job_desc
	 * @param new_priority
	 * @param new_special_instructions
	 * @param new_job_status
	 * @param new_payment_status
	 * @param new_completion_deadline
	 * @param new_price
	 */
	public Job(int new_job_ID, String new_job_desc, String new_priority, String new_special_instructions, String new_job_status, String new_payment_status, LocalDateTime new_completion_deadline, Double new_price) {
		job_ID = new_job_ID;
		job_desc = new_job_desc;
		priority = new_priority;
		special_instructions = new_special_instructions;
		job_status = new_job_status;
		payment_status = new_payment_status;
		completion_deadline = new_completion_deadline;
		price = new_price;
	}

	/**
	 * setters and getters
	 *
	 * @param job_ID
	 */
	public void setJob_ID(int job_ID){ this.job_ID = job_ID; }

	public int getJob_ID(){ return this.job_ID; }

	public void setJob_desc(String job_desc){ this.job_desc = job_desc; }

	public String getJob_desc(){ return this.job_desc; }

	public void setPriority(String priority) { this.priority = priority; }

	public String getPriority() { return this.priority;	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getJob_status() {
		return this.job_status;
	}

	public void setSpecial_instructions(String special_instructions) {
		this.special_instructions = special_instructions;
	}

	public String getSpecial_instructions() {
		return this.special_instructions;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPayment_status() {
		return this.payment_status;
	}

	public LocalDateTime getCompletion_deadline() {
		return completion_deadline;
	}

	public void setCompletion_deadline(LocalDateTime completion_deadline){ this.completion_deadline = completion_deadline; }

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}