package domain.strategy;

import java.util.ArrayList;
import java.util.List;

import domain.observer.Process;

public class StrategyPriority implements IStrategy{

	@Override
	public List<Process> performScheduling(List<Process> processesInBatch) {
		
		List<Process> processTempList = new ArrayList<Process>(); 
		List<Process> processesInBatchTemp = new ArrayList<Process>();; 

		processesInBatchTemp.addAll(processesInBatch);
		
		for(int i = 0; i < processesInBatch.size(); i++) {
			Process highestPriorityProcess = getHighestPriorityProcess(processesInBatchTemp);
			processesInBatchTemp.remove(highestPriorityProcess);
			processTempList.add(highestPriorityProcess);
		}
		
		return processTempList;
	}
	
	public Process getHighestPriorityProcess(List<Process> processesInBatch) {
	
		Process processTemp = null;
		int highestPriority = 11;
		
		for(Process process: processesInBatch) {
			if(process.getPriority() < highestPriority) {
				highestPriority = process.getPriority();
				processTemp = process;
			}
			
		}
		
		return processTemp;
		
	}
	

}
