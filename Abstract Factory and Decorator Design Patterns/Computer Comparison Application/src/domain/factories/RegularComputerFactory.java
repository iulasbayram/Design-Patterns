package domain.factories;

import java.util.Random;

import domain.products.*;

//This assembles a computer with copper cables that has speed 10 mps without taking any money.
//That factory can be decorated with GoldCopper or SilverCopper updating cost and speed.
public class RegularComputerFactory implements ComputerAbstractFactory, ComputerFactory{

	public RegularComputerFactory() {

	}

	//Implementation of Factory method for Keyboard.
	@Override
	public Keyboard createKeyboard() {

		int randomNumberOfKeys = getRandomNumber(84, 109);

		double randomPrice = getRandomNumber(80, 451);

		return new RegularKeyboard(randomNumberOfKeys, false, randomPrice);
	}

	//Implementation of Factory method for Mouse.
	@Override
	public Mouse createMouse() {

		double randomPrice = getRandomNumber(27, 79);

		return new RegularMouse(true, randomPrice);
	}

	//Implementation of Factory method for Monitor.
	@Override
	public Monitor createMonitor() {

		double randomPrice = getRandomNumber(900, 1501);

		int randomNumForInch = getRandomNumber(1, 3);

		int inch = 18;

		if(randomNumForInch == 1) {
			inch = 18;

		} else if(randomNumForInch == 2) {
			inch = 21;

		}

		return new RegularMonitor(inch, randomPrice);
	}

	//Implementation of Factory method for Headphone.
	@Override
	public Headphone createHeadphone() {

		double randomPrice = getRandomNumber(40, 401);

		return new GamingHeadphone(false, false, randomPrice);
	}

	//Implementation of Factory method for CPU.
	@Override
	public CPU createCPU() {

		int randomVersion = getRandomNumber(1, 3);

		CPUVersion version = CPUVersion.i3;

		if(randomVersion == 1) {
			version = CPUVersion.i3;

		} else if(randomVersion == 2) {
			version = CPUVersion.i5;

		}

		double randomPrice = getRandomNumber(8000, 12001);

		return new RegularCPU(version, 3.6, randomPrice);
	}

	//Implementation of Factory method for RAM.
	@Override
	public RAM createRAM() {

		int randomNum = getRandomNumber(1, 4);

		int capacity = 32;

		if(randomNum == 1) {
			capacity = 32;

		} else if(randomNum == 2) {
			capacity = 64;

		} else if(randomNum == 3) {
			capacity = 128;
		}

		double randomPrice = getRandomNumber(1500, 3501);

		return new RegularRAM(capacity, randomPrice);
	}

	//Implementation of Factory method for Computer.
	@Override
	public Computer assembleComputer() {

		Keyboard regularKeyboard = createKeyboard();
		Mouse regularMouse =  createMouse();

		Monitor regularMonitor = createMonitor();
		Headphone regularHeadphone = createHeadphone();

		CPU regularCPU = createCPU();
		RAM regularRAM = createRAM();

		Computer newComputer = new Computer();

		newComputer.setKeyboard(regularKeyboard);
		newComputer.setMouse(regularMouse);

		newComputer.setMonitor(regularMonitor);
		newComputer.setHeadphone(regularHeadphone);

		newComputer.setCPU(regularCPU);
		newComputer.setRAM(regularRAM);

		double totalPrice = newComputer.calculateTotalPrice();
		
		newComputer.setPrice(totalPrice);
		newComputer.setSpeed(10);
		
		return newComputer;
	}

	//That will be used to determine random attributes of computer devices.
	public int getRandomNumber(int lowerBound, int upperBound) {

		Random rnd = new Random();
		int randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;

		return randomNumber;
	}


}
