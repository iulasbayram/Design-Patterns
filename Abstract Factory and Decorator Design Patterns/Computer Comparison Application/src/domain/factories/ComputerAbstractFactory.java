package domain.factories;

import domain.products.HardwareDevice;
import domain.products.InputDevice;
import domain.products.OutputDevice;

public interface ComputerAbstractFactory {

	//Factory method for Keyboard.
	public InputDevice createKeyboard();
	
	//Factory method for Mouse.
	public InputDevice createMouse();

	//Factory method for Monitor.
	public OutputDevice createMonitor();
	
	//Factory method for Headphone.
	public OutputDevice createHeadphone();
	
	//Factory method for CPU.
	public HardwareDevice createCPU();
	
	//Factory method for RAM.
	public HardwareDevice createRAM();
	
}
