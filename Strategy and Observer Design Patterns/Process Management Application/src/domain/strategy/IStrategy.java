package domain.strategy;

import java.util.List;

import domain.observer.Process;

public interface IStrategy {

	public List<Process> performScheduling(List<Process> processes);
	
}
