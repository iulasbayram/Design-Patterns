package domain;

import java.util.ArrayList;
import java.util.List;

import domain.observer.Process;
import domain.state.StateNew;
import domain.state.StateReady;
import domain.state.StateStarved;
import domain.strategy.IStrategy;

public class ProcessBatch {

	private List<Process> readyQueue;//Keeps all processes
	private IStrategy schedulingStrategy;
	
	public ProcessBatch() {
		readyQueue = new ArrayList<Process>();
		
	}
	
	public ProcessBatch(IStrategy schedulingStrategy) {
		readyQueue = new ArrayList<Process>();
		this.schedulingStrategy = schedulingStrategy;
		
	}
	
	public void scheduleProcesses() {
		this.readyQueue = schedulingStrategy.performScheduling(readyQueue);
		
		System.out.println("Processes will be executed in given order:");
		int counter = 1;
		for(Process p: readyQueue) {
			System.out.println(counter + ") Process with id:" + p.getId());
			counter++;
		}
		
	}
	
	public void determineWaitingTimes() {

		int waitingTime = 0;
		
		for(Process process: readyQueue) {
			process.setWaitingTime(waitingTime);
			waitingTime += process.getBurstTime();
			
			if(process.getWaitingTime() > 50) {
				process.notifyObservers(new StateStarved());

			} else {
				process.notifyObservers(new StateReady());
			}
			
		}
		
	}
	
	public int getUniqueIdForProcess() {
		
		int uniqueId = 1;
		
		for(Process p: readyQueue) {
			if(uniqueId == p.getId()) {
				uniqueId++;
			}
		}
		
		return uniqueId;
	}
	
	public void addProcess(Process process){
		readyQueue.add(process);	
		process.notifyObservers(new StateNew());
		
	}

	public void removeProcess(Process process){
		readyQueue.remove(process);	
		
	}
	
	public Process getProcessByIndex(int index) {
		return readyQueue.get(index);
	}
	
	public List<Process> getReadyQueue() {
		return readyQueue;
	}
	
	public IStrategy getSchedulingStrategy() {
		return schedulingStrategy;
	}

	public void setSchedulingStrategy(IStrategy schedulingStrategy) {
		this.schedulingStrategy = schedulingStrategy;
	}
		
}
