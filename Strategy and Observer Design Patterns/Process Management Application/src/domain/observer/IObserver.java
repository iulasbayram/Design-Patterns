package domain.observer;

import domain.state.ProcessState;

public interface IObserver {

	public void setSubject(ISubject subject);
	
	public void update(Process process, ProcessState newState);//Updates state of Subject as newState, and state of Observer become consistent with state of Subject.
	
}
