package domain.strategy;

import java.util.ArrayList;
import java.util.List;

import domain.observer.Process;

public class StrategyFCFS implements IStrategy{

	@Override
	public List<Process> performScheduling(List<Process> processesInBatch) {
		
		List<Process> processTempList = new ArrayList<Process>(); 
		List<Process> processesInBatchTemp = new ArrayList<Process>();; 

		processesInBatchTemp.addAll(processesInBatch); 

		for(int i = 0; i < processesInBatch.size(); i++) {
			Process earliestArrivalTimeProcess = getEarliestArrivalTimeProcess(processesInBatchTemp);
			processesInBatchTemp.remove(earliestArrivalTimeProcess);
			processTempList.add(earliestArrivalTimeProcess);
		}
		
		return processTempList;
	}
	
	public Process getEarliestArrivalTimeProcess(List<Process> processesInBatch) {
	
		Process processTemp = null;
		int earliestArrivalTime = 21;
		
		for(Process process: processesInBatch) {
			if(process.getArrivalTime() < earliestArrivalTime) {
				earliestArrivalTime = process.getArrivalTime();
				processTemp = process;
			}
		}
		
		return processTemp;
		
	}

}
