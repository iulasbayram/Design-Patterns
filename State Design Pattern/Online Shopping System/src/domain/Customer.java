package domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private int id;
	private String name;
	private String address;
	private Double savings;
	private String phone;
	private String email;
	private String password;
	private List<Order> orders;
	
	public Customer() {
		orders = new ArrayList<Order>();
	}
	
	public Customer(int id, String name, String address, Double savings,
					String phone, String email, String password) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.savings = savings;
		this.phone = phone;
		this.email = email;
		this.password = password;
		orders = new ArrayList<Order>();
	}
	
	public void saveOrder(Order newOrder) {
		orders.add(newOrder);
		
		OrderState savedOrder = new SavedOrder();
		savedOrder.prepareOrder(newOrder, this);

		newOrder.setState(savedOrder);		
	}
	
	public void submitOrder(Order selectedOrder) {
		
		PlacedOrder placedOrder = new PlacedOrder();
		placedOrder.prepareOrder(selectedOrder, this);
		
		selectedOrder.setState(placedOrder);

	}
	
	public void addSavings(double moneyAmount) {
		double savings = getSavings();
		savings += moneyAmount;
		setSavings(savings);
	}

	public void calcelOrDeleteOrder(Order selectedOrder) {
		
		double savins = getSavings();
		
		if(selectedOrder.getState() instanceof ChargedOrder) { //if order is in charged state, we should update savings of the customer.
			savings += selectedOrder.getTotalPrice();
		}
		
		setSavings(savings);
		
		OrderState newOrderState = new CancelledOrder();
		newOrderState.prepareOrder(selectedOrder, this);
		
		selectedOrder.setState(newOrderState);
		
	}
		
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
				+ email + ", password=" + password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	
	
}
