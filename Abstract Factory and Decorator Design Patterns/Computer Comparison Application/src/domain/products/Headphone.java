package domain.products;

public class Headphone extends OutputDevice{

	private boolean isWireless;
	private boolean hasMicrophone;
	private double price;

	public Headphone() {
		super();
	}
	
	public Headphone(boolean isWireless, boolean hasMicrophone, double price) {
		super();
		this.isWireless = isWireless;
		this.hasMicrophone = hasMicrophone;
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

	public boolean isHasMicrophone() {
		return hasMicrophone;
	}

	public void setHasMicrophone(boolean hasMicrophone) {
		this.hasMicrophone = hasMicrophone;
	}

	public void setPrice(double price) {
		this.price = price;
	}
		
}
