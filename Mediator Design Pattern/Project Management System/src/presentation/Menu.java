package presentation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import domain.Activity;
import domain.Project;
import domain.ProjectPortfolloManager;
import domain.Resource;
import domain.Task;

public class Menu {

	ProjectPortfolloManager mediator;

	public Menu(ProjectPortfolloManager mediator) {
		this.mediator = mediator;
	}

	public void showMainMenu() {//MAIN MENU
		System.out.println("__________MAIN MENU__________");
		System.out.println("1)Add a Project...\n2)Remove a Project...\n3)Update a Project...");
		System.out.println("4)Add a Resource...\n5)Remove a Resource...\n6)Update a Resource...");
		System.out.println("7)Show project details...\n8)Show resource details...\n9)Quit and Save Projects Details...");
		System.out.println("Choose an operation giving its number:");
	}

	public void showUpdateProjectMenu(Project project) {//UPDATE PROJECT MENU
		System.out.println("CHOOSE AN OPERATION TO UPDATE PROJECT: " + project.getName());
		System.out.println("1)Add an activity...\n2)Remove an activity...\n3)Update an activity...");
		System.out.println("4)Add a task...\n5)Remove a task...\n6)Update a task...");
		System.out.println("7)Assign a resource to task...");
		System.out.println("8)Unassign a resource from a task...");
		System.out.println("9)GO TO MAIN MENU...");
	}
		
	public void init() {//Menu is shown to user and user can manage projects and resources from there.

		List<Project> projects = mediator.getProjects();
		boolean menuFlag = true;
		Scanner chooseOperation = new Scanner(System.in);

		while (menuFlag) {

			showMainMenu();
			String operationChoiceFromMainMenu = chooseOperation.nextLine();

			if (operationChoiceFromMainMenu.equals("1")) { //ADD A PROJECT
				addProject();
				int projectIndex = mediator.getProjects().size() - 1;
				Project addedProject = mediator.getProjectByIndex(projectIndex);
				System.out.println("Activity of the project is creating...");
				addActivity(addedProject);
				int activityIndex = addedProject.getActivities().size() - 1;
				Activity addedActivity = addedProject.getActivityByIndex(activityIndex);
				if(addedActivity.getDeliverable().equalsIgnoreCase("Yes")) {
					System.out.println("Task of the activity is creating...");
					addTask(addedActivity);
				} else {
					System.out.println("That activity is not deliverable, you cannot add a task to that activity...");
				}
				
				System.out.println("A new project is added successfully...");

			} else if (operationChoiceFromMainMenu.equals("2")) { //REMOVE A PROJECT
				if (projects.size() > 0) {
					removeProject(projects);
					System.out.println("Selected project is removed successfully...");
				} else {
					System.out.println("There is no project in system. Please add a project first...");
				}
			} else if (operationChoiceFromMainMenu.equals("3")) { // FIND and UPDATE A PROJECT
				
				if (projects.size() > 0) {
					System.out.println("Choose number of project to update it:");
					showProjectList(projects);
					int selectedProjectIndex = getIndex(projects.size());
					Project selectedProject = mediator.getProjectByIndex(selectedProjectIndex);
					boolean updateMenuFlag = true;

					while (updateMenuFlag) {
						showUpdateProjectMenu(selectedProject);
						int operationChoiceFromUpdateMenu = getIndex(9) + 1;

						if (operationChoiceFromUpdateMenu == 1) { // ADD AN ACTIVITY
							addActivity(selectedProject);
							int activityIndex = selectedProject.getActivities().size() - 1;
							Activity addedActivity = selectedProject.getActivityByIndex(activityIndex);
							
							if(addedActivity.getDeliverable().equalsIgnoreCase("Yes")) {
								System.out.println("Task of the activity is creating...");
								addTask(addedActivity);
							} else {
								System.out.println("That activity is not deliverable, you cannot add a task to that activity...");
							}
							System.out.println("A new activity is added successfully to project...");

						} else if (operationChoiceFromUpdateMenu == 2) { // REMOVE AN ACTIVITY
							if(selectedProject.getActivities().size() > 0) {
								removeActivity(selectedProject);
								System.out.println("The Activity is removed successfully...");
							} else {
								System.out.println("There is no activity in that project...");
							}
							
						} else if (operationChoiceFromUpdateMenu == 3) { // UPDATE AN ACTIVITY
							if(selectedProject.getActivities().size() > 0) {
								updateActivity(selectedProject);
								System.out.println("The Activity is updated successfully...");
							} else {
								System.out.println("There is no activity in that project...");
							}

						} else if (operationChoiceFromUpdateMenu == 4) { // ADD A TASK
							if(selectedProject.getActivities().size() > 0) {
								System.out.println("Select an activity of that project to add a task to it...");
								showActivityListOfProject(selectedProject);
								int activityIndex = getIndex(selectedProject.getActivities().size());
								Activity selectedActivity = selectedProject.getActivityByIndex(activityIndex);
								addTask(selectedActivity);
								System.out.println("A new Task is added successfully...");
							} else {
								System.out.println("There is no activity in that project, Please add an activity first...");
							}
							
						} else if (operationChoiceFromUpdateMenu == 5) { // REMOVE A TASK
							if(selectedProject.getActivities().size() > 0) {
								System.out.println("Select an activity of that project to remove one of its task...");
								showActivityListOfProject(selectedProject);
								int selectedActivityIndex = getIndex(selectedProject.getActivities().size());
								Activity selectedActivity = selectedProject.getActivityByIndex(selectedActivityIndex);
								removeTask(selectedProject, selectedActivity);
								System.out.println("Selected Task is removed successfully...");
							} else {
								System.out.println("There is no activity in that project, Please add an activity first...");
							}
							
						} else if (operationChoiceFromUpdateMenu == 6) { // UPDATE A TASK
							if(selectedProject.getActivities().size() > 0) {
								System.out.println("Select an activity of that project to update one of its task...");
								showActivityListOfProject(selectedProject);
								int selectedActivityIndex = getIndex(selectedProject.getActivities().size());
								Activity selectedActivity = selectedProject.getActivityByIndex(selectedActivityIndex);
								updateTask(selectedProject, selectedActivity);
								System.out.println("Selected Task is updated successfully...");
							} else {
								System.out.println("There is no activity in that project, Please add an activity first...");
							}
							
						} else if (operationChoiceFromUpdateMenu == 7) { //ASSIGN A RESOURCE TO A TASK
							assignResourceToTask(selectedProject);
							
						} else if (operationChoiceFromUpdateMenu == 8) { //UNASSIGN A RESOURCE FROM A TASK
							unassignResourceFromTask(selectedProject);

						} else if (operationChoiceFromUpdateMenu == 9) { //GO TO MAIN MENU
							updateMenuFlag = false;

						} else { // INVALID INPUT CASE
							System.out.println("Please choose a valid operation!");
						}
					}
				} else { //There is no project to update in system, user should add a project first
					System.out.println("There is no project in system. Please add a project first...");
				}
			
			} else if (operationChoiceFromMainMenu.equals("4")) { //ADD A RESOURCE
				addResource();
				System.out.println("A new resource is added to the system...");
				
			} else if (operationChoiceFromMainMenu.equals("5")) { //REMOVE A RESOURCE
				if(mediator.getResources().size() > 0) {
					removeResource();
					System.out.println("The selected resource is removed from the system...");
					System.out.println("NOTE:You should upload tasks of that resource in main menu!");
					
				} else {removeResource();
				System.out.println("A new resource is removed from the system...");
					System.out.println("There is no resource in the system...");
				}
						
			} else if (operationChoiceFromMainMenu.equals("6")) { //UPDATE A RESOURCE
				if(mediator.getResources().size() > 0) {
					updateResource();
					System.out.println("The resource is updated successfully...");
				} else {
					System.out.println("There is no resource in the system...");
				}

			} else if (operationChoiceFromMainMenu.equals("7")) { //SHOW PROJECT DETAILS
				if (projects.size() > 0) {
					System.out.println("Choose number of project to get details:");
					showProjectList(projects);
					int selectedProjectIndex = getIndex(projects.size());
					Project selectedProject = mediator.getProjectByIndex(selectedProjectIndex);
					showProjectDetails(selectedProject);
				} else {
					System.out.println("There is no project in system. Please add a project first...");
				}

			} else if (operationChoiceFromMainMenu.equals("8")) { //SHOW DETAILS OF RESOURCES
				if(mediator.getResources().size() > 0) {
					System.out.println("Select one of the resource to see details:");
					showResourceList();
					int selectedResourceIndex = getIndex(mediator.getResources().size());
					Resource selectedResource = mediator.getResourceByIndex(selectedResourceIndex);
					showResourceDetails(selectedResource);
				} else {
					System.out.println("There is no resource in system, Please add a resource first...");
				}
				
				
			} else if (operationChoiceFromMainMenu.equals("9")) { // EXIT FROM PROGRAMME
				menuFlag = false;
			} else {
				System.out.println("Please choose a valid operation!");
			}

			if (menuFlag == false) {
				System.out.println("End of the application...");
			}

		} // menu ends...
		
		chooseOperation.close();
	}

	public void addProject() {
		System.out.println("Enter name of the project:");
		String projectName = getInput();
		System.out.println("Enter description of the project:");
		String projectDescription = getInput();
		System.out.println("Enter start date of the project:");
		Date startDate = getStartDate(null);
		mediator.addProject(projectName, projectDescription, startDate);
	}
		
	public void removeProject(List<Project> projects) {
		System.out.println("Choose number of project to remove it:");
		showProjectList(projects);
		int selectedProjectIndex = getIndex(projects.size());
		Project selectedProject = mediator.getProjectByIndex(selectedProjectIndex);
		mediator.removeProject(selectedProject);
	}
	
	public void addActivity(Project selectedProject) {
		System.out.println("Enter number of the activity:");
		int activityNumber = getIndex(999999);
		System.out.println("Enter description of the activity:");
		String activityDescription = getInput();
		System.out.println("Enter start date of the activity:");
		Date startDate = getStartDate(selectedProject.getStartDate());
		
		String deliverable = "";
		System.out.println("Is activity deliverable:\n1)Yes\n2)No:");
		int deliverableChoice = getIndex(2);
		if(deliverableChoice == 0) {
			deliverable = "Yes";
		} else {
			deliverable = "No";
		}
		
		mediator.addActivity(selectedProject, activityNumber, activityDescription, 
							 startDate, deliverable);
	}
	
	public void removeActivity(Project selectedProject) {
		System.out.println("Select an activity of that project to remove...");
		showActivityListOfProject(selectedProject);
		int selectedActivityIndex = getIndex(selectedProject.getActivities().size());	
		Activity selectedActivity = selectedProject.getActivityByIndex(selectedActivityIndex);
		mediator.removeActivity(selectedProject, selectedActivity);
	}
	
	public void updateActivity(Project selectedProject) {
		System.out.println("Select an activity of that project to update...");
		showActivityListOfProject(selectedProject);
		int selectedActivityIndex = getIndex(selectedProject.getActivities().size());
		Activity selectedActivity = selectedProject.getActivityByIndex(selectedActivityIndex);
		
		System.out.println("Enter new number of the activity:");
		int activityNumber = getIndex(999999);
		System.out.println("Enter new description of the activity:");
		String activityDescription = getInput();
		System.out.println("Enter start date of the activity:");
		Date startDate = getStartDate(selectedProject.getStartDate());
		
		String deliverable = "";
		System.out.println("Is activity deliverable:\n1)Yes2\nNo:");
		int deliverableChoice = getIndex(2);
		if(deliverableChoice == 1) {
			deliverable = "Yes";
		} else {
			deliverable = "No";
		}

		mediator.updateActivity(selectedProject, selectedActivity, activityNumber, activityDescription,
								startDate, deliverable);	
	}
	
	public void addTask(Activity selectedActivity) {
		if(selectedActivity.getDeliverable().equals("Yes")) {
			System.out.println("Enter number of the task:");
			int taskNumber = getIndex(999999);
			System.out.println("Enter description of the task:");
			String taskDescription = getInput();
			System.out.println("Enter start date of the task:");
			Date startDate = getStartDate(selectedActivity.getStartDate());
			System.out.println("Enter neccessary hours for the task:");
			int taskHours = getIndex(999999);
			
			mediator.addTask(selectedActivity, taskNumber, taskDescription, startDate,
				     taskHours);
			Task addedTask = selectedActivity.getTaskByIndex(selectedActivity.getTasks().size() - 1);
			System.out.println("A new task is added successfully...");
			
			System.out.println("Would you assign a resource to that task?\n1)Yes\n2)No:");
			int resourceAddingChoice = getIndex(2) + 1;
			if(resourceAddingChoice == 1) {
				if(mediator.getResources().size() > 0) {
					showResourceList();
					int selectedResourceIndex = getIndex(mediator.getResources().size());
					Resource selectedResource = mediator.getResourceByIndex(selectedResourceIndex);
					mediator.assignResourceToTask(addedTask, selectedResource);
					System.out.println("Resource is assigned to selected task successfully...");
					
				} else {
					System.out.println("There is no resource in the system...");
				}
			}
			
		} else {
			System.out.println("Selected activity is not deliverable,try another one...");
		}
		
	}
	
	public void removeTask(Project selectedProject, Activity selectedActivity) {
		System.out.println("Select a task to remove...");
		showTaskListOfActivity(selectedActivity);
		int selectedTaskIndex = getIndex(selectedActivity.getTasks().size());
		mediator.removeTask(selectedProject, selectedActivity, selectedTaskIndex);
		
	}
	
	public void updateTask(Project selectedProject, Activity selectedActivity) {
		System.out.println("Select a task to update...");
		showTaskListOfActivity(selectedActivity);
		int taskIndex = getIndex(selectedActivity.getTasks().size());

		System.out.println("Enter new number of the task:");
		int taskNumber = getIndex(999999);
		System.out.println("Enter new description of the task:");
		String taskDescription = getInput();
		System.out.println("Enter start date of task:");
		Date startDate = getStartDate(selectedActivity.getStartDate());
		System.out.println("Enter neccessary hours for the task:");
		int taskHours = getIndex(999999);
		
		Task selectedTask = selectedActivity.getTaskByIndex(taskIndex);
		Resource resourceOfTask = mediator.getResourceById(selectedTask.getResourceId());
		
		if(resourceOfTask != null) {
			mediator.unassignResourceFromTask(selectedTask, resourceOfTask);
		}
		
		if(mediator.getResources().size() > 0) {
			System.out.println("Select one of the resource to assign it to selected task:");
			showResourceList();
			int selectedResourceIndex = getIndex(mediator.getResources().size());
			Resource selectedResource = mediator.getResourceByIndex(selectedResourceIndex);
			mediator.assignResourceToTask(selectedTask, selectedResource);
		} else {
			System.out.println("There is no resource in the system...");
		}
		
		mediator.updateTask(selectedProject, selectedActivity, taskIndex, taskNumber, taskDescription,
							startDate, taskHours);
	}

	public void addResource() {
		System.out.println("Select resource type entering its number:\n1)Employee\n2)Consultant");
		int resourceChoice = getIndex(2);
		System.out.println("Enter name of resource:");
		String resourceName = getInput();
		System.out.println("Enter id of resource:");
		int resourceId = getIndex(999999);
		
		mediator.addResource(resourceChoice, resourceName, resourceId);
	}
	
	public void removeResource() {
		System.out.println("Select one of the resource to remove it from system:");
		showResourceList();
		int selectedResourceIndex = getIndex(mediator.getResources().size());
		Resource selectedResource = mediator.getResourceByIndex(selectedResourceIndex);
		mediator.removeResource(selectedResource);
	}

	public void updateResource() {
		System.out.println("Select one of the resource to update it:");
		showResourceList();
		int selectedResourceIndex = getIndex(mediator.getResources().size());
		Resource selectedResource = mediator.getResourceByIndex(selectedResourceIndex);
		
		System.out.println("Enter new name of the resource:");
		String resourceName = getInput();
		System.out.println("Enter new id of the resource:");
		int resourceId = Integer.parseInt(getInput());
		
		mediator.updateResource(selectedResource, resourceName, resourceId);
	}

	public void assignResourceToTask(Project selectedProject) {//User selects Project,Activity then Task and assigns a resource to selected task.
		if(selectedProject.getActivities().size() > 0) {
			System.out.println("Select an activity of that project to assign a resource to a task...");
			showActivityListOfProject(selectedProject);
			int selectedActivityIndex = getIndex(selectedProject.getActivities().size());
			Activity selectedActivity = selectedProject.getActivityByIndex(selectedActivityIndex);
			if(selectedActivity.getTasks().size() > 0) {
				System.out.println("Select a task to assign a resource...");
				showTaskListOfActivity(selectedActivity);
				int selectedTaskIndex = getIndex(selectedActivity.getTasks().size());
				Task selectedTask = selectedActivity.getTaskByIndex(selectedTaskIndex);
				if(mediator.getResourceById(selectedTask.getResourceId()) != null) {
					System.out.println("Select one of the resource to assign it to selected task:");
					showResourceList();
					int selectedResourceIndex = getIndex(mediator.getResources().size());
					Resource selectedResource = mediator.getResourceByIndex(selectedResourceIndex);
					mediator.assignResourceToTask(selectedTask, selectedResource);
					System.out.println("Resource is assigned to selected task successfully...");
					
				} else {
					System.out.println("There is already an assigned resource to that task...");
				}
			} else {
				System.out.println("There is no task in that activity...");
			}
		} else {
			System.out.println("There is no activity in that project...");
		}	
	}
	
	public void unassignResourceFromTask(Project selectedProject) {//User selects Project,Activity then Task and unassigns a resource from selected task.
		if(selectedProject.getActivities().size() > 0) {
			System.out.println("Select an activity of that project to unassign a resource from a task...");
			showActivityListOfProject(selectedProject);
			int selectedActivityIndex = getIndex(selectedProject.getActivities().size());
			Activity selectedActivity = selectedProject.getActivityByIndex(selectedActivityIndex);
			
			if(selectedActivity.getTasks().size() > 0) {
				System.out.println("Select a task to assign a resource...");
				showTaskListOfActivity(selectedActivity);
				int selectedTaskIndex = getIndex(selectedActivity.getTasks().size());
				Task selectedTask = selectedActivity.getTaskByIndex(selectedTaskIndex);
				
				if(mediator.getResourceById(selectedTask.getResourceId()) == null) {
					Resource resourceOfTask = mediator.getResourceById(selectedTask.getResourceId());
					mediator.unassignResourceFromTask(selectedTask, resourceOfTask);
					System.out.println("Resource is unassigned from selected task successfully...");
					
				} else {
					System.out.println("There is no assigned resource to that task...");
				}
			} else {
				System.out.println("There is no task in that activity...");
			}
		} else {
			System.out.println("There is no activity in that project...");
		}	
	}
	
	public String getInput() { //Getting input for adding a new item(Project,Activity,Task) to system.
		String input = "";
		boolean validInputFlag = true;
		Scanner scan = new Scanner(System.in);

		while (validInputFlag) {
			input = scan.nextLine();
			if (!input.matches(".*[\\p{Punct}].*") && input.matches("[\\S].*")) {
				validInputFlag = false;
			} else {
				System.out.println("Invalid input, try again...");
			}
		}
		return input;
	}

	public int getIndex(int maxIndex) {//Getting index from user to select one of the item in any list.
		String choice = "";
		Scanner input = new Scanner(System.in);
		boolean validInputFlag = false;

		while (!validInputFlag) {
			choice = input.nextLine();
			if (choice.matches("[0-9].*") && choice.matches("[\\S].*") && Integer.parseInt(choice) > 0
					&& Integer.parseInt(choice) <= maxIndex) {
				validInputFlag = true;
			} else {
				System.out.println("Enter a valid index...");
			}
		}
		return Integer.parseInt(choice) - 1;
	}
	
	public Date getStartDate(Date date) {//StartDate should be in desired format and StartDates should be in that order => Project<Activity<Task
		String dateInput = "";
		boolean validInputFlag = true;
		Scanner scan = new Scanner(System.in);

		Date givenDate = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		while (validInputFlag) {
			dateInput = scan.nextLine();
			if (validateDate(dateInput) && !dateInput.equals("")) {
				try {
					givenDate = formatter.parse(dateInput);
					
				} catch (ParseException e) {
					System.out.println("Error:" + e.getMessage());
				}
				if(date == null){date = givenDate;} // If that method is used for adding a project, Startdate can be any date, but when adding activity or task, startdates should be checked.
				long diffBetweenDates = givenDate.getTime() - date.getTime();
				if(diffBetweenDates >= 0) {
					validInputFlag = false;
				} else {
					System.out.println("Startdates should be is that order: ProjectStartDate < ActivityStartDate < TaskStartDate...");
				}
				
			} else {
				System.out.println("Invalid date, enter a date in format yyyy-MM-dd...");
			}
		}
				
		return givenDate;
	}
	
	public boolean validateDate(String date) {//Control whether given date is in desired format or not.
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		try{
	        formatter.parse(date); 
	    } catch (ParseException e) {
	        return false;
	    }
		return true;
	}
	
	public int getFilenameFromUser(List<String> filenames) {//Shows all files saved before, and user selects one of them and see details of all projects in that file.
		if(filenames.size() > 0) {
			System.out.println("Select one of the file from system to see details of projects...");
			System.out.println("Note:Files are listed from earliest saved file to latest saved file!");
			
			int fileNo = 1;
			for(String filename: filenames) {
				System.out.println(fileNo + ")" + filename);
				fileNo++;
			}
		} else {
			System.out.println("Welcome...There is no saved file of projects in system before...");
		}
		
		return getIndex(filenames.size());
	}
	
	public void showProjectList(List<Project> projects) { //Shows all projects in the system.
		int projectNo = 1;
		for (Project project : projects) {
			System.out.println(projectNo + ") " + project.toString());
			projectNo++;
		}
	}

	public void showActivityListOfProject(Project project) { //Shows all activities of a project.
		System.out.println("NOTE: Enter its number to select...");
		int activityNo = 1;
		for (Activity activity : project.getActivities()) {
			System.out.println(activityNo + ") " + activity.toString());
			activityNo++;
		}
	}

	public void showTaskListOfActivity(Activity selectedActivity) {//Shows all tasks of an activity.
		System.out.println("NOTE: Enter its number to select...");
		int taskNo = 1;
		for (Task task : selectedActivity.getTasks()) {
			System.out.println(taskNo + ") " + task.getDesc());
			taskNo++;
		}
	}

	public void showResourceList() { //Shows all resources in a system.
		System.out.println("NOTE: Enter its number to select...");
		int resourceNo = 1;
		List<Resource> resources = mediator.getResources();
		for(Resource resource: resources) {
			System.out.println(resourceNo + ") " + resource.toString());
			resourceNo++;
		}
	}
	
	public void showProjectDetails(Project selectedProject) {//Shows all details of projects,activities of projects, tasks of activities and resources assigned to these tasks.
		System.out.println("------PROJECT DETAILS------");
		System.out.println("**" + selectedProject.toString());
		System.out.println("==>Number of Employees in Project: " + mediator.findEmployeeNumberOfProject(selectedProject));
		System.out.println("==>Number of Consultants in Project: " + mediator.findConsultantNumberOfProject(selectedProject));
		System.out.println("------DETAILS OF ACTIVITIES OF THE PROJECT------");
		for(Activity activity: selectedProject.getActivities()) {
			System.out.println("***" + activity.toString());
			System.out.println("==>Duration of the Activity: " + mediator.calculateActivityDuration(activity) + " hours.");
			System.out.println("==>Number of Employees in Activity: " + mediator.findEmployeeNumberOfActivity(activity));
			System.out.println("==>Number of Consultants in Activity: " + mediator.findConsultantNumberOfActivity(activity));
			System.out.println("------DETAILS OF TASKS OF THE ACTIVITY------");
			for(Task task: activity.getTasks()) {
				System.out.println("****" + task.toString());
				System.out.println("==>Duration of the Task:" + mediator.calculateTaskDuration(task) + " hours.");
				System.out.println("==>Number of Employees in Task: " + mediator.findEmployeeNumberOfTask(task));
				System.out.println("==>Number of Consultants in Task: " + mediator.findConsultantNumberOfTask(task));
				Resource resourceOfTask = mediator.getResourceById(task.getResourceId());
				if(resourceOfTask != null) {
					System.out.println("-----Details of the resource who is assigneg to Task-----");
					System.out.println("*****" + resourceOfTask.toString());
					System.out.println();
				} else {
					System.out.println("There is no resource assigned to that task...");
				}
			}
		}
	}
	
	private void showResourceDetails(Resource selectedResource) {//Shows id and name of the selected resource.
		System.out.println("------RESOURCE DETAILS------");
		System.out.println(selectedResource.toString());
		System.out.println();
	}
			
}
