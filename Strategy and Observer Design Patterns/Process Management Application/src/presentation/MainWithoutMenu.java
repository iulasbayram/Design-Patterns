package presentation;

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

public class MainWithoutMenu {

	//
	public static void main(String[] args) {
		System.out.println("\nWelcome to Process Management Application...\n");

		//OBSERVERS
		ObserverNewState newStateObserver = new ObserverNewState();
		ObserverReadyState readyStateObserver = new ObserverReadyState();
		ObserverRunningState runningStateObserver = new ObserverRunningState();
		ObserverBlockedState blockedStateObserver = new ObserverBlockedState();
		ObserverTerminatedState terminatedStateObserver = new ObserverTerminatedState();
		ObserverStarvedState starvedStateObserver = new ObserverStarvedState();
		
		
		//SUBJECT
		Process p1 = new Process(1, false); 
		p1.attachObserver(newStateObserver);
		p1.attachObserver(readyStateObserver);
		p1.attachObserver(runningStateObserver);
		p1.attachObserver(blockedStateObserver);
		p1.attachObserver(terminatedStateObserver);
		p1.attachObserver(starvedStateObserver);
		
		for(IObserver o: p1.getObservers()) {
			o.setSubject(p1);
		}
		
		//SUBJECT
		Process p2 = new Process(2, false);
		p2.attachObserver(newStateObserver);
		p2.attachObserver(readyStateObserver);
		p2.attachObserver(runningStateObserver);
		p2.attachObserver(blockedStateObserver);
		p2.attachObserver(terminatedStateObserver);
		p2.attachObserver(starvedStateObserver);
		
		for(IObserver o: p2.getObservers()) {
			o.setSubject(p2);
		}
		
		//SUBJECT
		Process p3 = new Process(3, false);
		p3.attachObserver(newStateObserver);
		p3.attachObserver(readyStateObserver);
		p3.attachObserver(runningStateObserver);
		p3.attachObserver(blockedStateObserver);
		p3.attachObserver(terminatedStateObserver);
		p3.attachObserver(starvedStateObserver);
		
		for(IObserver o: p3.getObservers()) {
			o.setSubject(p3);
		}
		
		
		//SCHEDULING STRATEGY
		IStrategy FCFSStrategy = new StrategyFCFS();
		IStrategy SJFStrategy = new StrategySJF();
		IStrategy priorityStrategy = new StrategyPriority();
		
		ProcessBatch batch = new ProcessBatch();
		//batch.setSchedulingStrategy(priorityStrategy);
		//batch.setSchedulingStrategy(FCFSStrategy);
		batch.setSchedulingStrategy(SJFStrategy);

		batch.addProcess(p1);
		batch.addProcess(p2);
		batch.addProcess(p3);
		
		System.out.println("Default processes in the system:\n");
		for(Process p: batch.getReadyQueue()) {
			System.out.println(p.toString());
		}
				
		System.out.println("________________________________\n");
		
		System.out.println("Scheduling is starting...");
		batch.scheduleProcesses();
		System.out.println("Scheduling is performed successfully...");
		
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
		
		System.out.println("________________________________\n");
		
		System.out.println("End of the application...");
		
	}
	
}

