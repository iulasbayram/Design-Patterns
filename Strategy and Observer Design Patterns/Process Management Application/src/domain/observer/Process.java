package domain.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.state.ProcessState;
import domain.state.StateRunning;

public class Process implements ISubject{

	private int id;
	private int arrivalTime;
	private int burstTime;
	private int completionTime;
	private int waitingTime;
	private int priority;
	private ProcessState processState;
	private List<IObserver> observers;
	private boolean isExecuted;
	
	public Process() {
		setArrivalTime(getRandomNumber(1, 20));
		setBurstTime(getRandomNumber(1, 20));
		setPriority(getRandomNumber(1, 10));
		setCompletionTime(getArrivalTime() + getBurstTime());
		observers = new ArrayList<IObserver>();
		
	}
	
	public Process(int id, boolean isExecuted) {
		this.id = id;
		setArrivalTime(getRandomNumber(1, 20));
		setBurstTime(getRandomNumber(1, 20));
		setPriority(getRandomNumber(1, 10));
		setCompletionTime(getArrivalTime() + getBurstTime());
		observers = new ArrayList<IObserver>();
		
	}
		
	@Override
	public void attachObserver(IObserver newObserver) {
		observers.add(newObserver);
		
	}

	@Override
	public void detachObserver(IObserver observer) {
		observers.remove(observer);
		
	}

	@Override
	public void notifyObservers(ProcessState newState) {
				
		for(IObserver observer: observers) {
			observer.update(this, newState);
		}
		
	}
	
	@Override
	public ProcessState getProcessState() {
		return processState;
	}
	
	@Override
	public void setProcessState(ProcessState newState) {
		this.processState = newState;
		
	}
	
	public boolean run() {
		if(processState instanceof StateRunning) {
			this.isExecuted = true;		
		} else {
			this.isExecuted = false; 
		}

		return this.isExecuted;
		
	}
	
	private int getRandomNumber(int lowerBound, int upperBound) {
		Random rnd = new Random();
		int deliveryDistance = rnd.nextInt(upperBound - lowerBound) + lowerBound;
		
		return deliveryDistance;
	}	
	

	@Override
	public String toString() {
		return "Process [id=" + id + ", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime + ", completionTime="
				+ completionTime + ", waitingTime=" + waitingTime + ", priority=" + priority + ", isExecuted="
				+ isExecuted + ", processState=" + processState.toString() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<IObserver> getObservers() {
		return observers;
	}
	
}
