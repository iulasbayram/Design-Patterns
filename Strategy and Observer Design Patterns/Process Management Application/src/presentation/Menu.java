package presentation;

import java.util.List;
import java.util.Scanner;

import domain.CPU;
import domain.Dispatcher;
import domain.ProcessBatch;
import domain.observer.IObserver;
import domain.observer.ObserverBlockedState;
import domain.observer.ObserverNewState;
import domain.observer.ObserverReadyState;
import domain.observer.ObserverRunningState;
import domain.observer.ObserverStarvedState;
import domain.observer.ObserverTerminatedState;
import domain.observer.Process;
import domain.strategy.IStrategy;
import domain.strategy.StrategyFCFS;
import domain.strategy.StrategyPriority;
import domain.strategy.StrategySJF;

public class Menu {

	ProcessBatch batch;

	public Menu(ProcessBatch batch) {
		this.batch = batch;
	}
	
	public void showMainMenu() {//MAIN MENU
		System.out.println("__________MAIN MENU__________");
		System.out.println("1)Add a Process...\n2)Remove a Process...");
		System.out.println("3)Show All Processes in the ProcessBatch...");
		System.out.println("4)Perform Execution of Processes in the system and Exit...");
		System.out.println("Choose an operation giving its number:");
	}

	public void init() {//Menu is shown to user and user can manage projects and resources from there.

		List<Process> processes = batch.getReadyQueue();
		
		boolean menuFlag = true;
		Scanner chooseOperation = new Scanner(System.in);

		while (menuFlag) {

			showMainMenu();
			String operationChoiceFromMainMenu = chooseOperation.nextLine();

			if (operationChoiceFromMainMenu.equals("1")) { //Add a process to ProcessBatch.
				
				//OBSERVERS
				ObserverNewState newStateObserver = new ObserverNewState();
				ObserverReadyState readyStateObserver = new ObserverReadyState();
				ObserverRunningState runningStateObserver = new ObserverRunningState();
				ObserverBlockedState blockedStateObserver = new ObserverBlockedState();
				ObserverTerminatedState terminatedStateObserver = new ObserverTerminatedState();
				ObserverStarvedState starvedStateObserver = new ObserverStarvedState();
				
				int uniqueId = batch.getUniqueIdForProcess();
				
				//SUBJECT
				Process newProcess = new Process(uniqueId, false); 
				newProcess.attachObserver(newStateObserver);
				newProcess.attachObserver(readyStateObserver);
				newProcess.attachObserver(runningStateObserver);
				newProcess.attachObserver(blockedStateObserver);
				newProcess.attachObserver(terminatedStateObserver);
				newProcess.attachObserver(starvedStateObserver);
				
				for(IObserver o: newProcess.getObservers()) {
					o.setSubject(newProcess);
				}
				
				batch.addProcess(newProcess);
				System.out.println("\nA new Process is added successfully...");
				System.out.println("New Process:");
				System.out.println(newProcess.toString());
				System.out.println();

			} else if (operationChoiceFromMainMenu.equals("2")) { //Remove a process from ProcessBatch.
				if (processes.size() > 0) {
					removeProcess(processes);
					System.out.println("\nSelected Process is removed successfully...\n");
					
				} else {
					System.out.println("\nThere is no Process in system. Please add a Process first...\n");

				}
				
			} else if (operationChoiceFromMainMenu.equals("3")) { //Show all processes in ProcessBatch.
				
				if (processes.size() > 0) {
					System.out.println("\nAll Processes in ProcessBatch:");
					showProcessesInSystem();
					System.out.println();
					
				} else { //There is no Process in the ProcessBatch, user should add a Process first.
					System.out.println("\nThere is no Process in system. Please add a Process first...\n");
					
				}
			
			} else if (operationChoiceFromMainMenu.equals("4")) { //Perform execution of processes in the ProcessBatch.
				
				if (processes.size() > 0) {
					IStrategy schedulingStrategy = getSchedulingStrategy();
					batch.setSchedulingStrategy(schedulingStrategy);
					performExecutionOfProcesses();
					
					System.out.println("________________________________\n");
					System.out.println("\nExecution of Processes is completed successfully...\n");
					
					menuFlag = false;
					
				} else { //There is no Process in the ProcessBatch, user should add a Process first.
					System.out.println("\nThere is no Process in system. Please add a Process first...\n");
					
				}
				
			} else {
				System.out.println("\nPlease choose a valid operation!\n");
			}

			if (menuFlag == false) {
				System.out.println("\nEnd of the application...\n");
			}

		} // menu ends...
		
		chooseOperation.close();
	}

	//Getting scheduling algorithm from user.
	private IStrategy getSchedulingStrategy() {
		
		System.out.println("Select one of the Scheduling Algorithm below:(Giving its number)");
		System.out.println("1)First Come First Serve Algorithm.");
		System.out.println("2)Shortest Job First Algorithm.");
		System.out.println("3)Priority Algorithm");

		int index = getIndex(3);
		IStrategy selectedAlgorithm = null;
		
		if(index == 0) {
			selectedAlgorithm = new StrategyFCFS();
			
		} else if(index == 1) {
			selectedAlgorithm = new StrategySJF();
			
		} else {
			selectedAlgorithm = new StrategyPriority();
		}
		
		return selectedAlgorithm;
	}

	//Performs execution of all processes in ProcessBatch.
	private void performExecutionOfProcesses() {
		System.out.println("\nProcesses in the system:\n");
		for(Process p: batch.getReadyQueue()) {
			System.out.println(p.toString());
		}
		
		System.out.println("\nScheduling is starting...");
		batch.scheduleProcesses();
		System.out.println("Scheduling is completed successfully...");
		
		System.out.println("________________________________\n");

		System.out.println("Waiting Times are determining...");
		batch.determineWaitingTimes();
		System.out.println("Waiting Times of Processes:");
		for(Process p: batch.getReadyQueue()) {
			System.out.println("Process [id=" + p.getId() 
							  + ", waitingTime=" + p.getWaitingTime()
							  + ", processState=" + p.getProcessState().toString() + "]");
		}
		
		System.out.println("________________________________\n");

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.extractScheduledProcesses(batch);
		
		CPU cpu = new CPU();
		dispatcher.sendProcessesToCPU(cpu);
			
		System.out.println("________________________________\n");
		
		System.out.println("Process states after execution:");
		for(Process p: batch.getReadyQueue()) {
			System.out.println("Process [id=" + p.getId() 
							  + ", processState=" + p.getProcessState().toString() + "]");
		}
	}

	//Removes selected process from ProcessBatch.
	private void removeProcess(List<Process> processes) {
		System.out.println("Select a Process to remove from ProcessBatch...");
		System.out.println("NOTE: Enter its number to select...");
		
		showProcessesInSystem();
		int selectedProcessIndex = getIndex(processes.size());	
		Process selectedProcess = batch.getProcessByIndex(selectedProcessIndex);
		batch.removeProcess(selectedProcess);
	}
	
	//Shows all processes in the ProcessBatch.
	public void showProcessesInSystem() { //Shows all activities of a project.
		int pNo = 1;
		for (Process p : batch.getReadyQueue()) {
			System.out.println(pNo + ") " + p.toString());
			pNo++;
		}
	}
	
	//An index is taken from user.
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
	
}
