package model.jobs.job;

import model.Model;
import java.time.LocalDateTime;

public class Job extends Model {

	private int job_ID;
	private String job_desc;
	private String priority;
	private String special_instructions;
	private String job_status;
	private String payment_status;

	/**
	 * constructor
	 *
	 */
	public Job(int new_job_ID, String new_job_desc, String new_priority, String new_special_instructions, String new_job_status, String new_payment_status) {
		job_ID = new_job_ID;
		job_desc = new_job_desc;
		priority = new_priority;
		special_instructions = new_special_instructions;
		job_status = new_job_status;
		payment_status = new_payment_status;
	}

	/**
	 *
	 * @param job_ID
	 */
	public void setJob_ID(int job_ID){ this.job_ID = job_ID; }

	public int getJob_ID(){ return this.job_ID; }

	/**
	 *
	 * @param job_desc
	 */
	public void setJob_desc(String job_desc){ this.job_desc = job_desc; }

	public String getJob_desc(){ return this.job_desc; }

	/**
	 *
	 * @param priority
	 */
	public void setPriority(String priority) { this.priority = priority; }

	public String getPriority() { return this.priority;	}

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
	 * @param payment_status
	 */
	public void setPaymentStatus(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPaymentStatus() {
		return this.payment_status;
	}


}