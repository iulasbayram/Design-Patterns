package domain.strategy;

import java.util.ArrayList;
import java.util.List;

import domain.observer.Process;

public class StrategySJF implements IStrategy{

	@Override
	public List<Process> performScheduling(List<Process> processesInBatch) {
		
		List<Process> processTempList = new ArrayList<Process>(); 
		List<Process> processesInBatchTemp = new ArrayList<Process>();; 

		processesInBatchTemp.addAll(processesInBatch);

		for(int i = 0; i < processesInBatch.size(); i++) {
			Process shortestBurstTimeProcess = getShortestBurstTimeProcess(processesInBatchTemp);
			processesInBatchTemp.remove(shortestBurstTimeProcess);
			processTempList.add(shortestBurstTimeProcess);
		}
		
		return processTempList;
	}
	
	public Process getShortestBurstTimeProcess(List<Process> processesInBatch) {
	
		Process processTemp = null;
		int shortestBurstTime = 21;
		
		for(Process process: processesInBatch) {
			if(process.getBurstTime() < shortestBurstTime) {
				shortestBurstTime = process.getBurstTime();
				processTemp = process;
			}
		}
		
		return processTemp;
		
	}

}
