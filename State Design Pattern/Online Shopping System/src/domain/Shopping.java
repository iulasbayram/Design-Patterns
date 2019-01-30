package domain;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.json.simple.parser.ParseException;
import org.omg.Messaging.SyncScopeHelper;

import fileAccess.DataAccessLayer;
import presentation.Menu;

public class Shopping {

	private Store store;
	private Customer systemUser;
	private DataAccessLayer DAL;
	private Menu menu;	

	public Shopping(Store store, DataAccessLayer DAL) {
		this.store = store;
		this.DAL = DAL;
		menu = new Menu();
	}

	public void init() {
		try {
			List<Customer> customersInSystem = DAL.readCustomers();
			if(customersInSystem != null) {
				store.setCustomers(customersInSystem);
			}
			
		} catch (IOException | ParseException | java.text.ParseException e) {
			System.out.println("Error is:" + e.getMessage());
		}
		
		menu.init(this);
	}
	
	public void register(String customerName, String customerAddress, Double customerSavings, String customerPhone, String customerEmail, String customerPassword) {
		int uniqueId = store.getUniqueIdForCustomer();
		
		Customer newUser = new Customer(uniqueId, customerName, customerAddress, customerSavings, customerPhone, customerEmail, customerPassword);
		store.register(newUser);
		try {
			DAL.saveCustomer(newUser);
		} catch (IOException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		} 
	}
	
	public boolean login(String email, String password) {
		systemUser = store.login(email, password);
		
		if(systemUser != null) {
			return true;
		} else {
			return false;
		}
	
	}
		
	public void saveOrder(String shippingAddress, Double weight) {		
		Double orderWeight = weight;
		String orderShippingAddress = shippingAddress;
		
		Order order = new Order(orderShippingAddress, orderWeight);
		systemUser.saveOrder(order);
		
	}
	
	public boolean performShoppingForAnOrder(int selectedOrderIndex) {
		Order selectedOrder = systemUser.getOrders().get(selectedOrderIndex);
		systemUser.submitOrder(selectedOrder);
		
		boolean chargeResult = chargeCustomer(selectedOrder);
			
		return chargeResult; //if charging operation cannot be performed, the user should add neccessary savings into his account for order.
							 //if charging operation is performed successfully, the other steps are in hands of store, and the user can follow state of order from menu.
	}
	
	public boolean chargeCustomer(Order selectedOrder) {
		
		boolean chargeResult = store.chargeCustomer(systemUser, selectedOrder);
		
		if(chargeResult) {
			store.shipOrder(systemUser, selectedOrder);//if charging has successfull,makes state of order placed.
			store.deliverOrder(systemUser, selectedOrder);
			try {
				DAL.saveOrder(selectedOrder);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
		} else {
			store.cancelPayment(systemUser, selectedOrder);//if charging has failed,makes state of order placed.
		}
		
		return chargeResult;
	}
	
	public void addSavingIntoUserAccount(double moneyAmount) {
		systemUser.addSavings(moneyAmount);	
	}
	
	public void calcelOrDeleteOrder(int selectedOrderIndex) {
		Order selectedOrder = systemUser.getOrders().get(selectedOrderIndex);
		systemUser.calcelOrDeleteOrder(selectedOrder);		
	}
	
	public void logout() {
		try {
			DAL.updateCustomer(systemUser);
		} catch (IOException | ParseException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Customer getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(Customer systemUser) {
		this.systemUser = systemUser;
	}
	
	
}
