package domain.products;

public class Mouse extends InputDevice{

	private boolean isWireless;
	private double price;
	
	public Mouse() {
		super();
	}
	
	public Mouse(boolean isWireless, double price) {
		this.isWireless = isWireless;
		this.price = price;
	}
	
	@Override
	public double getPrice() {
		return price;
	}
	
	public boolean isWireless() {
		return isWireless;
	}

	public void setWireless(boolean isWireless) {
		this.isWireless = isWireless;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
