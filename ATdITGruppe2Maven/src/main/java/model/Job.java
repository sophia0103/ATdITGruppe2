package model;

import java.util.Date;

/**
 * @author Luis
 * Represents an object from the database entity 'jobs'.
 */
public class Job {
	private String title;
	private String creator;
	private int distance;
	private int duration;
	private String exp_date;
	private String employmentType;
	private double salary;
	private String description;
	
	public Job(String title, String creator, int duration, int distance, String exp_date, String employmentType, double salary, String description) {
		this.title = title;
		this.creator = creator;
		this.duration = duration;
		this.distance = distance;
		this.exp_date = exp_date;
		this.employmentType = employmentType;
		this.salary = salary;
		this.description = description;
	}

	/**
	 * @return Returns the creator of the job offer.
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * @return Returns the distance of the job offer.
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * @return Returns the expiration date of the offer.
	 */
	public String getExpDate() {
		return exp_date;
	}
	
	/**
	 * @return Returns the employment type of the job offer.
	 */
	public String getEmploymentType() {
		return employmentType;
	}
	
	/**
	 * @return Returns the salary of the job offer.
	 */
	public double getSalary() {
		return salary;
	}
	
	/**
	 * @return Returns the description of the job offer.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the title of the job offer.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return Returns the duration of the job offer.
	 */
	public int getDuration() {
		return duration;
	}
	
}
