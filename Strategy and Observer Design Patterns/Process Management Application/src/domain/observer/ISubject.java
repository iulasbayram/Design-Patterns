package domain.observer;

import domain.state.ProcessState;

public interface ISubject {

	public void attachObserver(IObserver newObserver);//Attaches a new Observer to Subject.
	
	public void detachObserver(IObserver observer);//Detaches an Observer from Subject.
	
	public void notifyObservers(ProcessState newState);//Notifies all state of Subject as newState.
	
	public void setProcessState(ProcessState newState);//Updates state of Subject with newState and notifies state of Subject as newState.
	
	public ProcessState getProcessState();//Gives the current state of Subject.
	
}
