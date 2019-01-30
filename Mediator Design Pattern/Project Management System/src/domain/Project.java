package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

	private ProjectPortfolloManager mediator;
	
	private String name;
	private String desc;
	private Date startDate;
	
	private List<Activity> activities;
	
	public Project() {
	}
	
	public Project(ProjectPortfolloManager mediator, String name, String desc, Date startDate) {
		this.mediator = mediator;
		this.name = name;
		this.desc = desc;
		this.startDate = startDate;
		activities = new ArrayList<Activity>();
	}

	public int getDuration() {
		int projectDurationByHour = 0;
		for(Activity activity: activities) {
			projectDurationByHour += activity.getDuration();
		}
		return projectDurationByHour;
	}
	
	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return "Project [name=" + name + ", desc=" + desc + ", startDate=" + formatter.format(startDate) + "]";
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void removeActivity(Activity activity) {
		activities.remove(activity);
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public List<Activity> getActivities() {
		return activities;
	}

	
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	
	public Activity getActivityByIndex(int index) {
		Activity activity = activities.get(index);
		return activity;
	}
}
