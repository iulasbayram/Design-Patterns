package domain;

import java.util.ArrayList;
import java.util.List;

import domain.observer.Process;
import domain.state.StateBlocked;
import domain.state.StateReady;
import domain.state.StateRunning;

public class Dispatcher {

	private List<Process> readyQueue;
	
	public Dispatcher() {
		readyQueue = new ArrayList<Process>();
	}
	
	
	public void extractScheduledProcesses(ProcessBatch processBatch){
		this.readyQueue = processBatch.getReadyQueue();
		
	}
	
	public void sendProcessesToCPU(CPU cpu) {
		
		for(Process process: readyQueue) {
			if(process.getProcessState() instanceof StateReady) {
				process.notifyObservers(new StateRunning()); //State of processes which are in ready queue updated as Running.
				cpu.runProcess(process);					 //And sent to the CPU.
				
			} else { //if processState = Starved
				process.notifyObservers(new StateBlocked());
				
			}
			
		}
		
	}
	
}
