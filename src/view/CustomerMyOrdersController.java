package view;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.EmptyComboBoxException;
import Exceptions.IllegelInputException;
import controller.PrimaryController;
import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import Model.Customer;
import Model.Delivery;
import Model.Dish;
import Model.Order;
import Model.Restaurant;

public class CustomerMyOrdersController implements Initializable {
	private static final String Input = "Rest.ser";

	/*The controller to add and remove everything from the GUI to the database*/
	PrimaryController control=new PrimaryController();
	//the customer currently logged in
	static Customer customer;
	/***********New order Page************/
	@FXML
	private ComboBox<String> starters;
	
	@FXML
	private ComboBox<String> MainDishes;	

	@FXML
	private ComboBox<String> desserts;
	/***********Remove order Page************/
	@FXML
	private ComboBox<String> currentOrders;

	
	/*******************Orders history page********************/
	@FXML
	private TableView<Order> ordersTable;
	@FXML
	private TableColumn<Order,Integer> orderID;
	@FXML
	private TableColumn<Order, Customer> orderCustomer;
	@FXML
	private TableColumn<Order, ArrayList<Dish>> orderDishes;
	@FXML
	private TableColumn<Order, Delivery> orderDelivery;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(customer);
		/**************Load list of dishes in system*********/
		ArrayList<String> startersDB = new ArrayList<>();
		ArrayList<String> mainDishesDB = new ArrayList<>();
		ArrayList<String> dessertsDB = new ArrayList<>();

		for(Dish d :  Restaurant.getInstance().getDishes().values()) {
			if(d.getType().toString().equals("Starter")) {
				startersDB.add(d.getDishName());
			}
			if(d.getType().toString().equals("Main")) {
				mainDishesDB.add(d.getDishName());
			}
			
			if(d.getType().toString().equals("Dessert")) {
				dessertsDB.add(d.getDishName());
			}
			
		}
		
		/************load list of orders in the system **************/
		ArrayList<String> ordersDB = new ArrayList<>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			if(o.getCustomer().getId() == customer.getId()) {
				ordersDB.add(o.toString());			
			}
		}
		ObservableList<String> ObservableListOrders = FXCollections.observableArrayList();
		ObservableListOrders.addAll(ordersDB);
		currentOrders.setItems(ObservableListOrders);

	
		ObservableList<String> ObservableListStarters = FXCollections.observableArrayList();
		ObservableList<String> ObservableListMainDishes = FXCollections.observableArrayList();
		ObservableList<String> ObservableListDesserts = FXCollections.observableArrayList();

		ObservableListStarters.addAll(startersDB);
		starters.setItems(ObservableListStarters);
		
		
		ObservableListMainDishes.addAll(mainDishesDB);
		MainDishes.setItems(ObservableListMainDishes);

		ObservableListDesserts.addAll(dessertsDB);
		desserts.setItems(ObservableListDesserts);
		
		
		/*set in the table all the orders data from database for each of their fields*/
		orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		orderCustomer.setCellValueFactory(new PropertyValueFactory<Order, Customer>("customer"));
		orderDishes.setCellValueFactory(new PropertyValueFactory<Order, ArrayList<Dish>>("dishes"));
		orderDelivery.setCellValueFactory(new PropertyValueFactory<Order, Delivery>("delivery"));
		ordersTable.setItems(getOrders());
		
		
	}
	
	/**************Remove an Order*************/

	public void removeOrder(ActionEvent e) {
		String section = "Order";
		try {
			if(currentOrders.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = currentOrders.getValue();
			//Extract only the Order ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);
			
			if(control.removeOrderFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the orders database!");
			}
			
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
//		refreshGui();
	}
	
	/*get all the orders from the database*/
	private ObservableList<Order> getOrders(){
		ObservableList<Order> orders=FXCollections.observableArrayList();
		ArrayList<Order> query=new ArrayList<Order>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			if(o.getCustomer().getId() == customer.getId()) {
				query.add(o);			
			}
		}
		orders.addAll(query);
		return orders;	
	}
	
	
	public void successRemove(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Removed Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successUpdate(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Updated Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSelection(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to select : " + content);
		al.setHeaderText(header);
		al.setTitle("ComboBox");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failUpdate(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void fail(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to add : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}

	public void goodSound() {
		Sounds s = new Sounds();
		try {
			s.successSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void badSound() {
		Sounds s = new Sounds();
		try {
			s.errorSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void successSound() {
		Sounds s = new Sounds();
		try {
			s.addSound();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	

}
