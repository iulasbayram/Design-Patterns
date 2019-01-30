package domain;

import java.util.Date;

public class Order {

	private int id;
	private int trackingNumber;
	private int customerId;
	private String customerName;
	private String shippingAddress;
	private Double weight;
	private Date dateShipped;
	private Date dateDelivered;
	private Double productPrice;
	private Double cargoPrice;
	private Double totalPrice;
	private OrderState state;
	
	
	public Order(String  shippingAddress, Double weight) {
		this.weight = weight;
		this.shippingAddress = shippingAddress;
	}
	
	public Order(int id, int trackingNumber, int customerId, String customerName, Double weight, String shippingAddress,
			Date dateShipped, Date dateDelivered, Double productPrice, Double cargoPrice,
			Double totalPrice, OrderState state) {
		super();
		this.id = id;
		this.trackingNumber = trackingNumber;
		this.customerId = customerId;
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.weight = weight;
		this.dateShipped = dateShipped;
		this.dateDelivered = dateDelivered;
		this.productPrice = productPrice;
		this.cargoPrice = cargoPrice;
		this.totalPrice = totalPrice;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", trackingNumber=" + trackingNumber + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", shippingAddress=" + shippingAddress + ", weight=" + weight
				+ ", dateShipped=" + dateShipped + ", dateDelivered=" + dateDelivered + ", productPrice=" + productPrice
				+ ", cargoPrice=" + cargoPrice + ", totalPrice=" + totalPrice + ", stateOfOrder= " + state + "]";
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(int trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Date getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}

	public Date getDateDelivered() {
		return dateDelivered;
	}

	public void setDateDelivered(Date dateDelivered) {
		this.dateDelivered = dateDelivered;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getCargoPrice() {
		return cargoPrice;
	}

	public void setCargoPrice(Double cargoPrice) {
		this.cargoPrice = cargoPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
