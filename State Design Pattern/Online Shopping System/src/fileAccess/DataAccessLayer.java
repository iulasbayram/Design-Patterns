package fileAccess;

import java.lang.Math.*;
import java.text.SimpleDateFormat;

import domain.Customer;
import domain.Order;
import domain.OrderState;
import domain.DeliveredOrder;
import domain.ChargedOrder;
import domain.SavedOrder;
import domain.ShippedOrder;
import domain.PlacedOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class DataAccessLayer {

	public DataAccessLayer() throws IOException{

	}

	public void saveCustomer(Customer customer) throws FileNotFoundException, IOException, ParseException  {
		File f = new File("customers.json");
		FileWriter fileWriter;

		if (f.exists() && !f.isDirectory()) {  // If customers.json file exists, we can apply append operation for next customer.
			if (f.length() != 0) {

				JSONParser jsonParser = new JSONParser();
				Object obj = jsonParser.parse(new FileReader("customers.json"));
				JSONArray jsonArray = (JSONArray)obj;

				JSONObject customerToJSON = new JSONObject();
				customerToJSON.put("Id", customer.getId());
				customerToJSON.put("Name", customer.getName());
				customerToJSON.put("Address", customer.getAddress());
				customerToJSON.put("Savings", customer.getSavings());
				customerToJSON.put("Phone", customer.getPhone());
				customerToJSON.put("Email", customer.getEmail());
				customerToJSON.put("Password", customer.getPassword());

				jsonArray.add(customerToJSON);

				FileWriter file = new FileWriter("customers.json");
				file.write(jsonArray.toJSONString());
				file.flush();
				file.close();

			} else{ // We create customers.json file within adding first customer informations.
				JSONArray customerArrayToJSON = new JSONArray();
				JSONObject customerToJSON = new JSONObject();
				customerToJSON.put("Id", customer.getId());
				customerToJSON.put("Name", customer.getName());
				customerToJSON.put("Address", customer.getAddress());
				customerToJSON.put("Savings", customer.getSavings());
				customerToJSON.put("Phone", customer.getPhone());
				customerToJSON.put("Email", customer.getEmail());
				customerToJSON.put("Password", customer.getPassword());
				customerArrayToJSON.add(customerToJSON);

				fileWriter = new FileWriter("customers.json");
				fileWriter.write(customerArrayToJSON.toString());
				fileWriter.close();
			}	
		}
		System.out.println("The Customer is saved successfully the file customers.json...");

	}

	public void saveOrder(Order order) throws IOException, ParseException {
		File f = new File("orders.json");
		FileWriter fileWriter;
		
		if (f.exists() && !f.isDirectory()) { // If orders.json file exists, we can apply append operation for next order.
			if (f.length() != 0) {
				JSONParser jsonParser = new JSONParser();
				Object obj = jsonParser.parse(new FileReader("orders.json"));
				JSONArray jsonArray = (JSONArray)obj;

				JSONObject orderToJSON = new JSONObject();
				orderToJSON.put("Id", order.getId());
				orderToJSON.put("Tracking Number", order.getTrackingNumber());
				orderToJSON.put("Customer Id", order.getCustomerId());
				orderToJSON.put("Customer Name", order.getCustomerName());
				orderToJSON.put("Weight", order.getWeight());
				orderToJSON.put("Shipping Address", order.getShippingAddress());
				orderToJSON.put("Date Shipped", order.getDateShipped().toString());
				orderToJSON.put("Date Delivered", order.getDateDelivered().toString());
				orderToJSON.put("Product Price", order.getProductPrice());
				orderToJSON.put("Cargo Price", order.getCargoPrice());
				orderToJSON.put("Total Price", order.getTotalPrice());
				orderToJSON.put("Order State", order.getState().toString());

				jsonArray.add(orderToJSON);

				FileWriter file = new FileWriter("orders.json");
				file.write(jsonArray.toJSONString());
				file.flush();
				file.close();

			} else{ // We create orders.json file within adding first order informations.
				JSONArray orderArrayToJSON = new JSONArray();
				JSONObject orderToJSON = new JSONObject();
				orderToJSON.put("Id", order.getId());
				orderToJSON.put("Tracking Number", order.getTrackingNumber());
				orderToJSON.put("Customer Id", order.getCustomerId());
				orderToJSON.put("Customer Name", order.getCustomerName());
				orderToJSON.put("Weight", order.getWeight());
				orderToJSON.put("Shipping Address", order.getShippingAddress());
				orderToJSON.put("Date Shipped", order.getDateShipped().toString());
				orderToJSON.put("Date Delivered", order.getDateDelivered().toString());
				orderToJSON.put("Product Price", order.getProductPrice());
				orderToJSON.put("Cargo Price", order.getCargoPrice());
				orderToJSON.put("Total Price", order.getTotalPrice());
				orderToJSON.put("Order State", order.getState().toString());
				orderArrayToJSON.add(orderToJSON);

				fileWriter = new FileWriter("orders.json");
				fileWriter.write(orderArrayToJSON.toString());
				fileWriter.close();
			}
		}
		System.out.println("The Order is saved successfully the file orders.json...");

	}

	public List<Customer> readCustomers() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
		File f = new File("customers.json");
		List<Customer> customerList = new ArrayList<Customer>(); // Creating customer List to append customer informations from customers.json file to this.
		JSONParser jsonParser = new JSONParser();

		if (f.length() != 0) {
			JSONArray arr = (JSONArray) jsonParser.parse(new FileReader("customers.json"));

			for (Object obj : arr){
				JSONObject cstmr = (JSONObject) obj;

				// We use the strings ,that is got from customers.json file, as customer's parameters.
				Customer customer = new Customer(Math.toIntExact((long) cstmr.get("Id")),(String)cstmr.get("Name"),(String)cstmr.get("Address"),(Double)cstmr.get("Savings"),(String)cstmr.get("Phone"),(String)cstmr.get("Email"),(String)cstmr.get("Password"));
				customerList.add(customer);
			}

			List<Order> orderList = readOrders(); // Creating order list by reading orders.json file
			for (Customer customer : customerList) {
				for (Order order : orderList) {
					if (customer.getId() == order.getCustomerId()) { // If customer id of the order is equal to customer's own id
						customer.getOrders().add(order); // then we add the order to customer's order list.
					}
				}
			}

		}
		System.out.println("Customers and Orders are read successfully...");

		return customerList;
	}

	public List<Order> readOrders() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
		File f = new File("orders.json");

		List<Order> orderList = new ArrayList<Order>();
		JSONParser jsonParser = new JSONParser();

		if (f.length() != 0) {
			
			JSONArray arr = (JSONArray) jsonParser.parse(new FileReader("orders.json"));

			for (Object obj : arr){
				JSONObject ordr = (JSONObject) obj;
				String strShipped = (String) ordr.get("Date Shipped");
				String strDelivered = (String) ordr.get("Date Delivered");
				Date dateShipped = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(strShipped); // Converting date with string type to date object
				Date dateDelivered = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(strDelivered); // Converting date with string type to date object


				String strOrderState = (String) ordr.get("Order State"); // Order state with string type from orders.json
				OrderState state;
				if (strOrderState == "Saved Order") { // If order state is equal to saved order
					state = new SavedOrder(); 
				}else if (strOrderState == "Placed Order") { // If order state is equal to placed order
					state = new PlacedOrder();
				}else if (strOrderState == "Charged Order") { // If order state is equal to charged order
					state = new ChargedOrder();
				}else if (strOrderState == "Shipped Order") { // If order state is equal to shipped order
					state = new ShippedOrder();
				}else { // If order state is equal to delivered order
					state = new DeliveredOrder();
				}

				// We use the strings ,that is got from orders.json file, as order's parameters.
				Order order = new Order(Math.toIntExact((long) ordr.get("Id")),Math.toIntExact((long) ordr.get("Tracking Number")),
						Math.toIntExact((long) ordr.get("Customer Id")),(String) ordr.get("Customer Name"),(Double) ordr.get("Weight"),(String) ordr.get("Shipping Address"),
						dateShipped,dateDelivered,(Double) ordr.get("Product Price"),(Double) ordr.get("Cargo Price"),(Double) ordr.get("Total Price"),state);

				orderList.add(order);
			}
		}
		return orderList;
	}
	
	public void updateCustomer(Customer customer) throws FileNotFoundException, IOException, ParseException{
		File f = new File("customers.json");
		List<Customer> customerList = new ArrayList<Customer>(); 
		JSONParser jsonParser = new JSONParser();

		if (f.length() != 0) {
			JSONArray arr = (JSONArray) jsonParser.parse(new FileReader("customers.json"));

			for (Object obj : arr){
				JSONObject cstmr = (JSONObject) obj;

				Customer cust = new Customer(Math.toIntExact((long) cstmr.get("Id")),(String)cstmr.get("Name"),(String)cstmr.get("Address"),(Double)cstmr.get("Savings"),(String)cstmr.get("Phone"),(String)cstmr.get("Email"),(String)cstmr.get("Password"));
				customerList.add(cust);
			}
			
			PrintWriter writer = new PrintWriter("customers.json");
			writer.print("");
			writer.close();
			File file = new File("customers.json");
			file.createNewFile();
			for (Customer cust : customerList) {
				if (cust.getId() == customer.getId()) {
					saveCustomer(customer);
				}else {
					saveCustomer(cust);
				}
			}
			
			
			
		}
	}

}
