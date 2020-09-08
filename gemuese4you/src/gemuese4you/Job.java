package gemuese4you;

import java.util.Date;

public class Job {
	private String title;
	private String creatorName;
	private int distance;
	private int duration;
	private String exp_date;
	private String employmentType;
	private int salary;
	private String description;
	
	public Job(String title, String creatorName, int duration, int distance, String exp_date, String employmentType, int salary, String description) {
		this.title = title;
		this.creatorName = creatorName;
		this.duration = duration;
		this.distance = distance;
		this.exp_date = exp_date;
		this.employmentType = employmentType;
		this.salary = salary;
		this.description = description;
	}

	public String getCreatorName() {
		return creatorName;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public String getExpDate() {
		return exp_date;
	}
	
	public String getEmplyomentType() {
		return employmentType;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}
	
	public int getDuration() {
		return duration;
	}
	
}
