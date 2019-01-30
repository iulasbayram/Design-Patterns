package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fileAccess.DataAccessLayer;
import presentation.Menu;

public class ProjectPortfolloManager implements IProjectPortfolloManager{

	private List<Project> projects;
	private List<Resource> resources;
	private Menu menu;
	private DataAccessLayer DAL;
	
	public ProjectPortfolloManager() {
		projects = new ArrayList<Project>();;
		resources = new ArrayList<Resource>();
		menu = new Menu(this);
		DAL = new DataAccessLayer(this);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public Menu getMenu() {
		return menu;
	}
	
	public Project getProjectByIndex(int index) {
		Project project = projects.get(index);
		return project;
	}
	
	public Resource getResourceByIndex(int index) {
		Resource resource = resources.get(index);
		return resource;
	}
	
	public Resource getResourceById(int givenId) {
		Resource resultingResource = null;
		for(Resource resource: resources) {
			if(resource.getId() == givenId) {
				resultingResource =  resource;
			}
		}
		return resultingResource;
	}

	public void start() {
		DAL.readProjectDetails();//All project details are read from files which are saved before.
		menu.init(); //menu is shown to user and the user can manage projects and resources.
		DAL.saveProjects(); //after user exit from programme, all projects' details are saved to a file.
	}
	
	@Override
	public void addProject(String projectName, String projectDescription, Date projectStartDate) {
		Project project = new Project(this, projectName, projectDescription, projectStartDate);
		projects.add(project);
	}

	@Override
	public void removeProject(Project selectedProject) {
		projects.remove(selectedProject);
	}
	
	@Override
	public void addActivity(Project selectedProject, int activityNumber, String activityDescription, Date activityStartDate, String deliverable) {
		Activity newActivity = new Activity(activityNumber, activityDescription, activityStartDate, deliverable);
		selectedProject.addActivity(newActivity);
	}

	@Override
	public void removeActivity(Project selectedProject, Activity selectedActivity) {
		selectedProject.removeActivity(selectedActivity);
	}

	@Override
	public void updateActivity(Project selectedProject, Activity selectedActivity, int activityNumber, String activityDescription, Date activityStartDate, String deliverable) {
		selectedActivity.setNumber(activityNumber);
		selectedActivity.setDesc(activityDescription);
		selectedActivity.setStartDate(activityStartDate);
		selectedActivity.setDeliverable(deliverable);
	}
	
	@Override
	public void addTask(Activity selectedActivity, int taskNumber,  String taskDescription, Date taskStartDate, int hours) {
		Task newTask = new Task(taskNumber, taskDescription, taskStartDate, hours); 
		selectedActivity.addTask(newTask);
	}

	@Override
	public void removeTask(Project selectedProject, Activity selectedActivity, int selectedTaskIndex) {
		Task selectedTask = selectedActivity.getTaskByIndex(selectedTaskIndex);
		selectedActivity.removeTask(selectedTask);
		if(getResourceById(selectedTask.getResourceId()) != null) { //if removed task is assigned to a resource, the task is removed from tasks list of that resource.
			Resource resourceOfTask = getResourceById(selectedTask.getResourceId());
			resourceOfTask.getTasks().remove(selectedTask);
		}
	}

	@Override
	public void updateTask(Project selectedProject, Activity selectedActivity, int selectedTaskIndex, int taskNumber, String taskDescription, Date taskStartDate, int hours) {
		Task selectedTask = selectedActivity.getTaskByIndex(selectedTaskIndex);
		selectedTask.setNumber(taskNumber);
		selectedTask.setDesc(taskDescription);
		selectedTask.setStartDate(taskStartDate);
		selectedTask.setHours(hours);
	}
	
	@Override
	public void addResource(int resourceChoice, String resourceName, int resourceId) {	
		if(resourceChoice == 0) {//Resource is type of Employee
			Resource resource = new Employee(this, resourceId, resourceName);
			resources.add(resource);
		} else { //Resource is type of Consultant
			Resource resource = new Consultant(this, resourceId, resourceName);
			resources.add(resource);
		}
	}

	@Override
	public void removeResource(Resource selectedResource) {
		resources.remove(selectedResource);
		for(Task task: selectedResource.getTasks()) { //if selected Resource is assigned to a task, resourceId of that Task is assigned to -1(no resource).
			task.setResourceId(-1);
		}
	}
		
	@Override
	public void updateResource(Resource selectedResource, String resourceName, int resourceId) {
		selectedResource.setId(resourceId);
		selectedResource.setName(resourceName);
		for(Task task: selectedResource.getTasks()) { //Id of the resource is changed and related Task's resourceId is also changed.
			task.setResourceId(resourceId);
		}
	}
	
	public void assignResourceToTask(Task selectedTask, Resource selectedResource) {
		selectedTask.assignAResource(selectedResource);
		selectedResource.getTasks().add(selectedTask); //assigned task is added to tasks list of the resource.
	}
	
	public void unassignResourceFromTask(Task selectedTask, Resource selectedResource) {
		selectedTask.unassignResource();
		selectedResource.getTasks().remove(selectedTask);//selected task is removed from tasks list of the unassigned resource.
	}
	
	@Override
	public int calculateProjectDuration(Project selectedProject) {
		return selectedProject.getDuration(); //Total durations of activities gives project duration. 
	}

	@Override
	public int calculateActivityDuration(Activity selectedActivity) {
		return selectedActivity.getDuration(); //Total durations of tasks gives activity duration. 
	}

	@Override
	public int calculateTaskDuration(Task selectedTask) {
		return selectedTask.getHours(); //hours attribute of the task gives task duration.
	}
	
	@Override
	public int findEmployeeNumberOfProject(Project selectedProject) { //Total employee number of activities gives project employee number.
		int employeeNumber = 0;
		for(Activity activity: selectedProject.getActivities()){
			employeeNumber += findEmployeeNumberOfActivity(activity);
		}
		return employeeNumber;
	}
	
	@Override
	public int findConsultantNumberOfProject(Project selectedProject) { //Total consultant number of activities gives project consultant number.
		int consultantNumber = 0;
		for(Activity activity: selectedProject.getActivities()){
			consultantNumber += findConsultantNumberOfActivity(activity);
		}
		return consultantNumber;
	}

	@Override
	public int findEmployeeNumberOfActivity(Activity selectedActivity) {//Total employee number of tasks gives activity employee number.
		int employeeNumber = 0;
		for(Task task: selectedActivity.getTasks()){
			if(task.getResourceId() != 0) {
				Resource resourceOfTask = getResourceById(task.getResourceId());
				if(resourceOfTask instanceof Employee) {
					employeeNumber++;
				}
			}
		}
		return employeeNumber;
	}
	
	@Override
	public int findConsultantNumberOfActivity(Activity selectedActivity) {//Total consultant number of tasks gives activity consultant number.
		int consultantNumber = 0;
		for(Task task: selectedActivity.getTasks()){
			if(task.getResourceId() != 0) {
				Resource resourceOfTask = getResourceById(task.getResourceId());
				if(resourceOfTask instanceof Consultant) {
					consultantNumber++;
				}
			}
		}
		return consultantNumber;
	}
	
	@Override
	public int findEmployeeNumberOfTask(Task selectedTask) {//A Task can have 1 employee or 1 consultant
		int employeeNumber = 0;
		for(Resource resource: resources){ //resourceID of task = ID of resource
			if(resource.getId() == selectedTask.getResourceId()) {
				Resource resourceOfTask = getResourceById(selectedTask.getResourceId());
				if(resourceOfTask instanceof Employee) {
					employeeNumber++;
				}
			}
		}
		return employeeNumber;
	}
	
	@Override
	public int findConsultantNumberOfTask(Task selectedTask) { //A Task can have 1 employee or 1 consultant
		int consultantNumber = 0;
		for(Resource resource: resources){ //resourceID of task = ID of resource
			if(resource.getId() == selectedTask.getResourceId()) {
				Resource resourceOfTask = getResourceById(selectedTask.getResourceId());
				if(resourceOfTask instanceof Consultant) {
					consultantNumber++;
				}
			}
		}
		return consultantNumber;
	}
		
}
