package domain;

import domain.observer.Process;
import domain.state.StateTerminated;

public class CPU {

	private Process runningProcess;
	
	public CPU() {
		
	}
	
	public CPU(Process process) {
		this.runningProcess = process;
	}
	
	public void runProcess(Process process) {
				
		this.runningProcess = process;
		
		boolean executionResult = process.run();
		
		if(executionResult) {
			System.out.println("Process [id:" + process.getId() 
							    + ", state:" + process.getProcessState() 
							    + "] is executed successfully...");
			process.notifyObservers(new StateTerminated());
			
		} else {
			System.out.println("Process with id:" + process.getId() + " has been failed...");
			System.out.println("The process is in Blocked State...");

		}
		
	}

	public Process getProcess() {
		return runningProcess;
	}

	public void setProcess(Process process) {
		this.runningProcess = process;
	}
		
}
