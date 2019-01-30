package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Store {

	private String name;
	private List<Customer> customers;
	
	public Store(String name) {
		this.name = name;
		customers = new ArrayList<Customer>();
	}

	public Customer login(String email, String password) {
		
		Customer user = null;
		
		for(Customer customer: customers) {
			if(customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equalsIgnoreCase(password)) {
				user = customer;	
			}
		}
		
		return user;
	}
	
	public void register(Customer newCustomer) {
		
		customers.add(newCustomer);
	
	}
	
	public boolean chargeCustomer(Customer customer, Order selectedOrder) {
		
		OrderState newOrderState = new ChargedOrder();
		newOrderState.prepareOrder(selectedOrder, customer);
		selectedOrder.setState(newOrderState);
		
		if(customer.getSavings() >= selectedOrder.getTotalPrice()) {
			
			double updatedSavings = customer.getSavings() - selectedOrder.getTotalPrice();
			customer.setSavings(updatedSavings);
			
			return true;
		} else {
			return false;
		}
	}
	
	public void cancelPayment(Customer customer, Order selectedOrder) {

		double updatedSavings = customer.getSavings() + selectedOrder.getTotalPrice();
		
		OrderState newOrderState = new PlacedOrder();
		newOrderState.prepareOrder(selectedOrder, customer);
		selectedOrder.setState(newOrderState);
	}

	public void shipOrder(Customer customer, Order selectedOrder) {

		OrderState newOrderState = new ShippedOrder();
		selectedOrder.setState(newOrderState);
		newOrderState.prepareOrder(selectedOrder, customer);
	}

	public void deliverOrder(Customer customer, Order selectedOrder) {

		OrderState newOrderState = new DeliveredOrder();
		newOrderState.prepareOrder(selectedOrder, customer);
		selectedOrder.setState(newOrderState);
	}

	public int getUniqueIdForCustomer() {
		
		int uniqueId = 1;
		
		for(Customer customer: customers) {
			if(uniqueId == customer.getId()) {
				uniqueId++;
			}
		}
		
		return uniqueId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	
	
}
