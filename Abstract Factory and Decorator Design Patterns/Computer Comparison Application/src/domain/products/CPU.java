package domain.products;

public class CPU extends HardwareDevice{

	private CPUVersion version;
	private double frequency;
	private double price;

	public CPU() {
		super();
	}

	public CPU(CPUVersion version, double frequency, double price) {
		super();
		this.version = version;
		this.frequency = frequency;
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CPUVersion getVersion() {
		return version;
	}

	public void setVersion(CPUVersion version) {
		this.version = version;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}


}
