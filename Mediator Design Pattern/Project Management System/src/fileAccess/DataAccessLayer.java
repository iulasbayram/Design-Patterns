package fileAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.Activity;
import domain.Consultant;
import domain.Employee;
import domain.Project;
import domain.ProjectPortfolloManager;
import domain.Resource;
import domain.Task;

public class DataAccessLayer {

	private ProjectPortfolloManager mediator;
	List<String> filenames;
	
	public DataAccessLayer(ProjectPortfolloManager mediator) {
		this.mediator = mediator;
		filenames = new ArrayList<String>();
		setFilenames(filenames);//gives all saved files before and add them to filenames list.
	}

	public void readProjectDetails() {
		try {
			if(filenames.size() > 0) { //if there is a file saved before, find details of projects in that file.
				List<Project> projects = mediator.getProjects();
				List<Resource> resources = mediator.getResources();
				
				String selectedFilename = filenames.get(mediator.getMenu().getFilenameFromUser(filenames));
				Scanner readFile = new Scanner(new File(selectedFilename));
				
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				Project project = null;
				Activity activity = null;
				Resource resource = null;
				
				while(readFile.hasNextLine()){
					String line = readFile.nextLine();
					StringTokenizer st = null;
				
					if(line.equals("_PROJECT")) {
						line = readFile.nextLine();
						st = new StringTokenizer(line, " ");
						String name = st.nextToken();
						String description = st.nextToken();
						try {
							Date startDate = formatter.parse(st.nextToken()); //String to Date
							project = new Project(mediator, name.replace("_", " "), description.replace("_", " "), startDate); 
							projects.add(project); //Project object is created and added to projects list.
						} catch (ParseException e) {
							System.out.println("Error:" + e.getMessage());
						}
						
					} else if(line.equals("__ACTIVITY")) {
						line = readFile.nextLine();
						st = new StringTokenizer(line, " ");
						int number = Integer.parseInt(st.nextToken());
						String description = st.nextToken();
						Date startDate = null;
						try {
							startDate = formatter.parse(st.nextToken());
						} catch (ParseException e) {
							System.out.println("Error:" + e.getMessage());
						}						
						String deliverable = st.nextToken();
						activity = new Activity(number, description.replace("_", " "), startDate, deliverable);
						project.getActivities().add(activity); //Activity object is created and added to project's activities list.
						
					} else if(line.equals("___TASK")) {
						line = readFile.nextLine();
						st = new StringTokenizer(line, " ");
						int number = Integer.parseInt(st.nextToken());
						String description = st.nextToken();
						Date startDate = null;
						try {
							startDate = formatter.parse(st.nextToken());
						} catch (ParseException e) {
							System.out.println("Error:" + e.getMessage());
						}							
						int hours = Integer.parseInt(st.nextToken());
						int resourceId = Integer.parseInt(st.nextToken());
						Task task = new Task(number, description.replace("_", " "), startDate, hours, resourceId);
						activity.getTasks().add(task); //Task object is created and added to activity's tasks list.
						
					} else if(line.equals("____RESOURCE")) {
						line = readFile.nextLine();
						st = new StringTokenizer(line, " ");
						String resourceType = st.nextToken();
						int id = Integer.parseInt(st.nextToken());
						String name = st.nextToken();
						if(resourceType.equalsIgnoreCase("employee")) {
							resource = new Employee(mediator, id, name.replace("_", " "));
						} else {
							resource = new Consultant(mediator, id, name.replace("_", " "));
						}
						
						resources.add(resource); //Resource object is created and added to resources list.
						
						for(Project prj: projects) { //Add all Tasks of Resource to its Tasks List.
							for(Activity act: prj.getActivities()) {
								for(Task tsk: act.getTasks()) {
									if(tsk.getResourceId() == resource.getId()) {
										resource.getTasks().add(tsk);	
									}
								}
							}
						}
						
					}
				}
				readFile.close();
				System.out.println("Projects are read from file successfully...");
			} else {
				System.out.println("Welcome...There is no saved file of projects in system before...");
			}
						
		} catch (FileNotFoundException e) {
			System.out.println("Error is:" + e.getMessage());
		}
	}
	
	public void saveProjects() {
		try {
			List<Project> projects = mediator.getProjects();
			List<Resource> resources = mediator.getResources();
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String fileDate = formatter.format(date);
			
			int fileNo = 0;
			
			String fileName = "Projects_" + fileDate + ".txt";
			String newFilename = fileName;
			
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			} else {
				while(file.exists()){ //if a file is exist with same name, fileNo specifies saving order of them.
		            fileNo++;
		            file = new File("Projects_" + fileDate + "(" + fileNo + ").txt");
		            newFilename = "Projects_" + fileDate + "(" + fileNo + ").txt";
		        }
			}
			
			FileOutputStream fos = new FileOutputStream(newFilename);
			PrintWriter pw = new PrintWriter(fos);
			
			for(Project project : projects) { //That loop saves all details of projects to file in a format specified in README file.
				pw.println("_PROJECT");
				pw.println(project.getName().replace(" ", "_") + " " + project.getDesc().replace(" ", "_") + " " + formatter.format(project.getStartDate()));
				for(Activity activity: project.getActivities()) {
					pw.println("__ACTIVITY");
					pw.println(activity.getNumber() + " " + activity.getDesc().replace(" ", "_") + " " 
							   + formatter.format(activity.getStartDate()) + " " + activity.getDeliverable());
				
					for(Task task: activity.getTasks()) {
						pw.println("___TASK");
						pw.println(task.getNumber() + " " + task.getDesc().replace(" ", "_") + " " + formatter.format(task.getStartDate()) + " " 
						           + task.getHours() + " " + task.getResourceId() );
					}
				}
					
			}
			
			for(Resource resource: resources) { //After all details of projects are saved, details of resources also saved to end of the file.
				pw.println("____RESOURCE");
				if(resource instanceof Employee) {
					pw.println("Employee " + resource.getId() + " " + resource.getName().replace(" ", "_"));
				} else {
					pw.println("Consultant " + resource.getId() + " " + resource.getName().replace(" ", "_"));
				}
			}
			
			pw.close();
			System.out.println("Details of Projects saved to file:" + fileName);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setFilenames(List<String> filenames) {
		String projectDir = System.getProperty("user.dir");
		File folder = new File(projectDir);
		File[] fileList = folder.listFiles(); 
		for(File file: fileList){ 
			if(file.toString().contains(".txt")) { //if a file is in format .txt, add its name to filenames.
				String filename = file.toString();
				filenames.add(filename);
			}	 
		}
	}
	
	public List<String> getFilenames() {
		return filenames;
	}
}
