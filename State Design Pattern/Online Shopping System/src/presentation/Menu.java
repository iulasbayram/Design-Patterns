package presentation;

import java.util.Scanner;

import domain.Customer;
import domain.DeliveredOrder;
import domain.Order;
import domain.PlacedOrder;
import domain.SavedOrder;
import domain.Shopping;

public class Menu {
	
	public Menu() {
		
	}

	public void showMainMenu() {//Main Menu for Shopping
		System.out.println("__________MAIN MENU__________");
		System.out.println("1)Save an Order...\n2)Submit a Saved Order...");
		System.out.println("3)Make Payment for a Placed Order...\n4)Show Savings in your Account...");
		System.out.println("5)Show all orders with their details...\n6)Add Savings into your Account...");
		System.out.println("7)Delete an Order...\n8)Log out...");
		System.out.println("Choose an operation giving its number:");
	}
		
	public void showLoginMenu() {//Login Menu
		System.out.println("__________MAIN MENU__________");
		System.out.println("1)Login to System...\n2)Register to System...\n3)Exit from the System...");
		System.out.println("Choose an operation giving its number:");
	}

	public void init(Shopping shopping) {//login menu is hown tu user, user can register and login to system.

		boolean loginPageFlag = true;
		Scanner mainScanner = new Scanner(System.in);

		while (loginPageFlag) {

			showLoginMenu();
			String chosenOperation = mainScanner.nextLine();

			if (chosenOperation.equalsIgnoreCase("1")) { //Login to System
				
				System.out.println("Customer email:");
				String customerEmail = getInput();
				
				System.out.println("Customer password:");
				String customerPassword = getInput();
				
				boolean loginFlag = shopping.login(customerEmail, customerPassword);
				
				if(loginFlag) {
					mainMenu(shopping);
					
				} else {
					System.out.println("Error in login operation...");
					System.out.println("Please register to system first or enter login informations correctly...");
				}
					
			} else if (chosenOperation.equalsIgnoreCase("2")) { //Register to System
								
				System.out.println("Customer name:");
				String customerName = getInput();

				System.out.println("Customer address:");
				String customerAddress = getInput();
				
				System.out.println("Customer savings($):");
				double customerSavings = getMoneyAmount();
				
				System.out.println("Customer phone:");
				String customerPhone = getInput();
				
				System.out.println("Customer email:");
				String customerEmail = getInput();
				
				System.out.println("Customer password:");
				String customerPassword = getInput();
				
				shopping.register(customerName, customerAddress, customerSavings, customerPhone, customerEmail, customerPassword);
				
				System.out.println("You have registered to system successfully...");
				
			} else if (chosenOperation.equalsIgnoreCase("3")) { // Exit from system
				loginPageFlag = false;
				
			} else {		
				System.out.println("Please choose a valid operation!");
			}
			
			if (loginPageFlag == false) {
				System.out.println("End of the application...");
			}

		} // menu ends...
		
		mainScanner.close();
	}
	
	public void mainMenu(Shopping shopping) {//After user login to system, user can manage its shopping operations here.

		System.out.println("You have logged in successfully...");
		System.out.println("Welcome to our online shopping system:" + shopping.getStore().getName());
		
		boolean mainMenuFlag = true;
		Scanner loginScanner = new Scanner(System.in);

		while (mainMenuFlag) {

			showMainMenu();
			String chosenOperation = loginScanner.nextLine();

			if (chosenOperation.equalsIgnoreCase("1")) { //Save an Order
				System.out.println("Shipping address of the order:");
				String orderShippingAddress = getInput();
				
				System.out.println("Weight of the order:");
				double orderWeight = Double.parseDouble(getInput());
				
				shopping.saveOrder(orderShippingAddress, orderWeight);
				
				System.out.println("A new order is added successfully...");
				
			} else if (chosenOperation.equalsIgnoreCase("2")) { //Submit a Saved Order
				if(shopping.getSystemUser().getOrders().size() > 0) {
					
					System.out.println("Select an order giving its number to submit it:");
					showAllOrdersOfCustomer(shopping.getSystemUser());
					int selectedOrderIndex = getIndex(shopping.getSystemUser().getOrders().size());
						
					Order selectedOrder = shopping.getSystemUser().getOrders().get(selectedOrderIndex);
					if(selectedOrder.getState() instanceof SavedOrder) {
												
						boolean chargeResult = shopping.performShoppingForAnOrder(selectedOrderIndex);
						
						if(!chargeResult) { //PRINTLERI DUZENLE.....
							System.out.println("Charging operation has been failed...");
							System.out.println("Please add neccessary savings into your account and try submitting again...");
						} 
						
					} else {
						System.out.println("The order is in " + selectedOrder.getState().toString() + ", it cannot be submitted again...");
					}
					
				} else {
					System.out.println("There is no saved order before, please save an order first...");
				}
				
			} else if (chosenOperation.equalsIgnoreCase("3")) { // Make Payment for a Placed Order.
					
				if(shopping.getSystemUser().getOrders().size() > 0) {
					
					System.out.println("Select an order giving its number to make payment for it:");
					showAllOrdersOfCustomer(shopping.getSystemUser());
					int selectedOrderIndex = getIndex(shopping.getSystemUser().getOrders().size());
						
					Order selectedOrder = shopping.getSystemUser().getOrders().get(selectedOrderIndex);
					if(selectedOrder.getState() instanceof PlacedOrder) {
												
						boolean chargeResult = shopping.chargeCustomer(selectedOrder);
						
						if(!chargeResult) {
							System.out.println("Charging operation has been failed...");
							System.out.println("Please add neccessary savings into your account and try submitting again...");
						} 
						
					} else {
						System.out.println("The order is in " + selectedOrder.getState().toString() + ", it cannot be submitted again...");
					}
					
				} else {
					System.out.println("There is no saved order before, please save an order first...");
				}	
		
			} else if (chosenOperation.equalsIgnoreCase("4")) { // Show savings in a customer account.
				System.out.println("You have " + shopping.getSystemUser().getSavings() + "$ in your account..." );
				
			} else if (chosenOperation.equalsIgnoreCase("5")) { // Show all orders of a customer.
				if(shopping.getSystemUser().getOrders().size() > 0) {
					System.out.println("All orders with their details are at below:");
					showAllOrdersOfCustomer(shopping.getSystemUser());
				} else {
					System.out.println("There is no saved order before, please save an order first...");
				}
				
			} else if (chosenOperation.equalsIgnoreCase("6")) { // Add savings into your Account.
				System.out.println("How many dollars would you add into your account($):");
				double moneyAmount = getMoneyAmount();
				shopping.addSavingIntoUserAccount(moneyAmount);
				System.out.println("You have added " + moneyAmount + " dollars into your account...");
				
			} else if (chosenOperation.equalsIgnoreCase("7")) { // Delete or Cancel an Order.
				
				if(shopping.getSystemUser().getOrders().size() > 0) {
					System.out.println("Select an order giving its number to delete or cancel it:");
					showAllOrdersOfCustomer(shopping.getSystemUser());
					int selectedOrderIndex = getIndex(shopping.getSystemUser().getOrders().size());
						
					Order selectedOrder = shopping.getSystemUser().getOrders().get(selectedOrderIndex);		
					if(selectedOrder.getState() instanceof SavedOrder) {
						shopping.calcelOrDeleteOrder(selectedOrderIndex);
						System.out.println("The selected order is cancelled successfully...");
						
					} else {
						System.out.println("The order is in " + selectedOrder.getState().toString() + " state, it cannot be deleted or canceled...");
					}
					
				} else {
					System.out.println("There is no saved order before, please save an order first...");
				}
				
			} else if (chosenOperation.equalsIgnoreCase("8")) { // Go to login page
				shopping.logout();
				mainMenuFlag = false;
				
			} else {
				System.out.println("Please choose a valid operation!");
			}

			if (mainMenuFlag == false) { // Go to login page
				System.out.println("You logged out successfully...");
			}
		} 
		
	}

	public void showAllOrdersOfCustomer(Customer user) {
		int orderNo = 1;
		for(Order order: user.getOrders()) {
			System.out.println(orderNo+") " + order.toString());
			orderNo++;		
		}
	}
	
	public String getInput() {
		String input = "";
		boolean validInputFlag = true;
		Scanner scan = new Scanner(System.in);

		while (validInputFlag) {
			input = scan.nextLine();
			if (!input.matches(".*[\\p{Punct}].*") && input.matches("[\\S].*")) {
				validInputFlag = false;
			} else {
				System.out.println("Invalid input, try again...");
			}
		}
		return input;
	}
	
	public int getIndex(int maxIndex) {
		String choice = "";
		Scanner input = new Scanner(System.in);
		boolean validInputFlag = false;

		while (!validInputFlag) {
			choice = input.nextLine();
			if (choice.matches("[0-9].*") && choice.matches("[\\S].*") 
					&& Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= maxIndex) {
				validInputFlag = true;
			} else {
				System.out.println("Enter a valid index...");
			}
		}
		return Integer.parseInt(choice) - 1;
	}
	
	public double getMoneyAmount() {
		String choice = "";
		Scanner input = new Scanner(System.in);
		boolean validInputFlag = false;

		while (!validInputFlag) {
			choice = input.nextLine();
			if (choice.matches("[0-9].*") && choice.matches("[\\S].*")) {
				validInputFlag = true;
			} else {
				System.out.println("Enter a valid dollars amount...");
			}
		}
		return Double.parseDouble(choice);
	}
}
