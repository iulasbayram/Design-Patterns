package domain;

import java.util.Date;

public interface IProjectPortfolloManager {

	public Project getProjectByIndex(int selectedProjectIndex); //returns Project in given index.
	
	public Resource getResourceByIndex(int selectedResourceIndex); //returns Resource in given index.
	
	public void addProject(String projectName, String projectDescription, Date projectStartDate); //A new Project is created and added to projects list. 
	
	public void removeProject(Project selectedProject); //selected Project is removed from projects list.
		
	public void addActivity(Project selectedProject, int activityNumber, String activityDescription, //A new Activity is created and added to selected Project.
						    Date activityStartDate, String deliverable);
		
	
	public void removeActivity(Project selectedProject, Activity selectedActivity); //Selected Activity is removed from selected project.

	
	public void updateActivity(Project selectedProject, Activity selectedActivity, int activityNumber,  //Selected Activity's attributes are updated with new values taken from user.
			                  String activityDescription, Date activityStartDate, String deliverable);

	
	public void addTask(Activity selectedActivity, int taskNumber, //A new Task is created and added to selected Activity.
			            String taskDescription, Date taskStartDate, int hours);


	public void removeTask(Project selectedProject, Activity selectedActivity, int selectedTaskIndex); //Selected Task is removed from its related Activity.

	
	public void updateTask(Project selectedProject, Activity selectedActivity, int selectedTaskIndex, //Selected Task's attributes are updated with new values taken from user.
						   int taskNumber, String taskDescription, Date taskStartDate, int hours);
	
	
	public void addResource(int resourceChoice, String resourceName, int resourceId); //resourceChoice parameter specifies whether resource is Employee or Consultant.
	
	public void removeResource(Resource selectedResource); //Selected Resource is removed from system.
		
	public void updateResource(Resource selectedResource, String resourceName, int resourceId);//Id of the resource is updated and assigned tasks' resourceId of that resource are also updated.
	
	public void assignResourceToTask(Task selectedTask, Resource selectedResource); //ResourceId of the selected task is assigned to Id of selectedResource. And selected task is added to tasks list of the resource.
						
	public void unassignResourceFromTask(Task selectedTask, Resource selectedResource); //resourceId of the task is assigned to -1, and task is removed form tasks list of the resource.
	
	public int calculateProjectDuration(Project selectedProject);//Adding all durations of activities of the selected project gives project duration.
	
	public int calculateActivityDuration(Activity selectedActivity);//Adding all durations of tasks of the selected activity gives activity duration.
	
	public int calculateTaskDuration(Task selectedTask);//hours attribute of the selected task gives task duration.
	
	public int findEmployeeNumberOfProject(Project selectedProject);//Adding all employees of activities of the selected project gives employee number of the project.
	
	public int findConsultantNumberOfProject(Project selectedProject);//Adding all consultant of activities of the selected project gives consultant number of the project.
	
	public int findEmployeeNumberOfActivity(Activity selectedActivity);//Adding all employees of tasks of the selected activity gives employee number of the activity.
	
	public int findConsultantNumberOfActivity(Activity selectedActivity);//Adding all consultant of tasks of the selected activity gives consultant number of the activity.
	
	public int findEmployeeNumberOfTask(Task selectedTask);//A task can have 1 employee or 1 consultant.
	
	public int findConsultantNumberOfTask(Task selectedTask);//A task can have 1 employee or 1 consultant.
}
