package domain.factories;

import java.util.Random;

import domain.products.*;

//This assembles a computer with copper cables that has speed 10 mps without taking any money.
//That factory can be decorated with GoldCopper or SilverCopper updating cost and speed.
public class GamingComputerFactory implements ComputerAbstractFactory, ComputerFactory{

	public GamingComputerFactory() {
	
	}
	
	//Implementation of Factory method for Keyboard.
	@Override
	public Keyboard createKeyboard() {

		int randomNumberOfKeys = getRandomNumber(84, 109);

		double randomPrice = getRandomNumber(500, 1501);

		return new GamingKeyboard(randomNumberOfKeys, true, randomPrice);
	}

	//Implementation of Factory method for Mouse.
	@Override
	public Mouse createMouse() {
		
		double randomPrice = getRandomNumber(300, 851);

		return new GamingMouse(false, randomPrice);
	}

	//Implementation of Factory method for Monitor.
	@Override
	public Monitor createMonitor() {
		
		double randomPrice = getRandomNumber(2500, 3501);
		
		int randomNumForInch = getRandomNumber(1, 5);
		
		int inch = 18;
		
		if(randomNumForInch == 1) {
			inch = 18;
			
		} else if(randomNumForInch == 2) {
			inch = 21;
			
		} else if(randomNumForInch == 3) {
			inch = 25;
			
		} else {
			inch = 27;
		}
		
		return new GamingMonitor(inch, randomPrice);
	}

	//Implementation of Factory method for Headphone.
	@Override
	public Headphone createHeadphone() {
		
		double randomPrice = getRandomNumber(600, 1901);
		
		return new GamingHeadphone(true, true, randomPrice);
	}

	//Implementation of Factory method for CPU.
	@Override
	public CPU createCPU() {
		
		int randomVersion = getRandomNumber(1, 3);

		CPUVersion version = CPUVersion.i5;
		
		if(randomVersion == 1) {
			version = CPUVersion.i5;

		} else if(randomVersion == 2) {
			version = CPUVersion.i7;

		}
		
		double randomPrice = getRandomNumber(14000, 24001);

		return new GamingCPU(version, 5.4, randomPrice);
	}

	//Implementation of Factory method for RAM.
	@Override
	public RAM createRAM() {
		
		int randomNum = getRandomNumber(1, 3);

		int capacity = 128;
		
		if(randomNum == 1) {
			capacity = 128;

		} else if(randomNum == 2) {
			capacity = 256;
		
		} 
		
		double randomPrice = getRandomNumber(7000, 19001);

		return new GamingRAM(capacity, randomPrice);
	}
	
	//Implementation of Factory method for Computer.
	@Override
	public Computer assembleComputer() {
		
		Keyboard gamingKeyboard = createKeyboard();
		Mouse gamingMouse =  createMouse();

		Monitor gamingMonitor = createMonitor();
		Headphone gamingHeadphone = createHeadphone();
		
		CPU gamingCPU = createCPU();
		RAM gamingRAM = createRAM();
		
		Computer newComputer = new Computer();
		
		newComputer.setKeyboard(gamingKeyboard);
		newComputer.setMouse(gamingMouse);
		
		newComputer.setMonitor(gamingMonitor);
		newComputer.setHeadphone(gamingHeadphone);
		
		newComputer.setCPU(gamingCPU);
		newComputer.setRAM(gamingRAM);
	
		double totalPrice = newComputer.calculateTotalPrice();
		
		newComputer.setPrice(totalPrice);
		newComputer.setSpeed(10); //10mps
		
		return newComputer;
	}

	//That will be used to determine random attributes of computer devices.
	public int getRandomNumber(int lowerBound, int upperBound) {

		Random rnd = new Random();
		int randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;

		return randomNumber;
	}

}
