package domain.products;

public class Computer {

	private Keyboard keyboard;
	private Mouse mouse;
	private Monitor monitor;
	private Headphone headphone;
	private CPU CPU;
	private RAM RAM;
	private double speed;
	private double price;
	
	public Computer() {
		
	}

	//Gives total price of computer, summing all prices of its devices.
	public double calculateTotalPrice() {
		
		double totalPrice = 0.0;
		
		totalPrice += keyboard.getPrice();
		totalPrice += mouse.getPrice();
		totalPrice += monitor.getPrice();
		totalPrice += headphone.getPrice();
		totalPrice += CPU.getPrice();
		totalPrice += RAM.getPrice();

		return totalPrice;
	}
	
	
	@Override
	public String toString() {
		return  "Computer [speed=" + speed + ", price=" + price + "]"
				+ "\n  Keyboard [price=" + keyboard.getPrice() + ", numberOfKeys=" + keyboard.getNumberOfKeys() + ", hasBacklight=" + keyboard.isHasBacklight() + "]" 
				+ "\n  Mouse [price=" + mouse.getPrice() + ", isWireless= " + mouse.isWireless() + "]"
				+ "\n  Monitor [price=" + monitor.getPrice() + ", inch= " + monitor.getInch() + "]"
				+ "\n  Headphone [price=" + headphone.getPrice() + ", isWireless=" + headphone.isWireless() + ", hasMicrophone=" + headphone.isHasMicrophone() + "]"
				+ "\n  CPU [price=" + CPU.getPrice() + ", version=" + CPU.getVersion() + ", frequency=" + CPU.getFrequency() + "]"
				+ "\n  RAM [price=" + RAM.getPrice() + ", capacity="+ RAM.getCapacity() + "]";
	}


	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Headphone getHeadphone() {
		return headphone;
	}

	public void setHeadphone(Headphone headphone) {
		this.headphone = headphone;
	}

	public CPU getCPU() {
		return CPU;
	}

	public void setCPU(CPU cPU) {
		CPU = cPU;
	}

	public RAM getRAM() {
		return RAM;
	}

	public void setRAM(RAM rAM) {
		RAM = rAM;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
}
