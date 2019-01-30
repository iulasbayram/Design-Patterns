package domain.observer;

import domain.state.ProcessState;
import domain.state.StateNew;

public class ObserverNewState implements IObserver{

	private ISubject subject;
	private ProcessState subjectState;
	
	public ObserverNewState() {
		
	}
	
	public ObserverNewState(ISubject subject) {
		this.subject = subject;
		subject.attachObserver(this);
		
	}

	@Override
	public void update(Process process, ProcessState newState) {
		
		if(newState instanceof StateNew) {
			process.setProcessState(newState);
		}
		
		subjectState = newState;		
	}

	public ISubject getSubject() {
		return subject;
	}

	@Override
	public void setSubject(ISubject subject) {
		this.subject = subject;
	}

	
	
}
