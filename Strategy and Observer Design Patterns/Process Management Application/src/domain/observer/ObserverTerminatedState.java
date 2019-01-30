package domain.observer;

import domain.state.ProcessState;
import domain.state.StateTerminated;

public class ObserverTerminatedState implements IObserver{

	private ISubject subject;
	private ProcessState subjectState;
	
	public ObserverTerminatedState() {
		
	}
	
	public ObserverTerminatedState(ISubject subject) {
		this.subject = subject;
		subject.attachObserver(this);
	}

	@Override
	public void update(Process process, ProcessState newState) {
		
		if(newState instanceof StateTerminated) {
			process.setProcessState(newState);
		}
		
		this.subjectState = newState;
	}

	public ISubject getSubject() {
		return subject;
	}

	@Override
	public void setSubject(ISubject subject) {
		this.subject = subject;
	}

}
