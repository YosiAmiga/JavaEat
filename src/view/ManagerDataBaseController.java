package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.*;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.Vehicle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ManagerDataBaseController implements Initializable {
	
	/****************Customer Table****************/

	@FXML
	private TableView<Customer> customersTable;
	@FXML
	private TableColumn<Customer,Integer> theCustomerID;
	@FXML
	private TableColumn<Customer, String> customerFirstName;
	@FXML
	private TableColumn<Customer, String> customerLastName;
	@FXML
	private TableColumn<Customer, LocalDate> customerBD;
	@FXML
	private TableColumn<Customer, Gender> customerGender;
	@FXML
	private TableColumn<Customer, String> customerPassword;
	@FXML
	private TableColumn<Customer, Neighberhood> customerNeighborhood;
	@FXML
	private TableColumn<Customer, String> customerLactose;
	@FXML
	private TableColumn<Customer, String> customerGluten;
	
	/****************Delivery Persons Table****************/

	@FXML
	private TableView<DeliveryPerson> deliveryPersonsTable;
	@FXML
	private TableColumn<DeliveryPerson,Integer> deliveryPersonID;
	@FXML
	private TableColumn<DeliveryPerson, String> deliveryPersonFirstName;
	@FXML
	private TableColumn<DeliveryPerson, String> deliveryPersonLastName;
	@FXML
	private TableColumn<DeliveryPerson, LocalDate> deliveryPersonBD;
	@FXML
	private TableColumn<DeliveryPerson, Gender> deliveryPersonGender;
	@FXML
	private TableColumn<DeliveryPerson, Vehicle> deliveryPersonVehicle;
	@FXML
	private TableColumn<DeliveryPerson, DeliveryArea> deliveryPersonArea;

	
	/****************Cooks Table****************/

	@FXML
	private TableView<Cook> cooksTable;
	@FXML
	private TableColumn<Cook,Integer> cookID;
	@FXML
	private TableColumn<Cook, String> cookFirstName;
	@FXML
	private TableColumn<Cook, String> cookLastName;
	@FXML
	private TableColumn<Cook, LocalDate> cookBD;
	@FXML
	private TableColumn<Cook, Gender> cookGender;
	@FXML
	private TableColumn<Cook, Expertise> cookExpertise;
	@FXML
	private TableColumn<Cook, String> cookIsChef;

	
	/****************Components Table****************/
	@FXML
	private TableView<Component> componentTable;
	@FXML
	private TableColumn<Component,Integer> componentID;
	@FXML
	private TableColumn<Component, String> componentName;
	@FXML
	private TableColumn<Component, String> componentLactose;
	@FXML
	private TableColumn<Component, String> componentGluten;
	@FXML
	private TableColumn<Component, Double> componentPrice;
	
	
	/****************Dishes Table****************/
	@FXML
	private TableView<Dish> dishesTable;
	@FXML
	private TableColumn<Dish,Integer> dishID;
	@FXML
	private TableColumn<Dish, String> dishName;
	@FXML
	private TableColumn<Dish, DishType> dishType;
	@FXML
	private TableColumn<Dish, ArrayList<Component>> dishComponents;
	@FXML
	private TableColumn<Dish, Integer> dishTime;
	
	/*******************Orders Table********************/
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
	
	/****************Express Delivery Table****************/

	@FXML
	private TableView<ExpressDelivery> expDeliveryTable;
	@FXML
	private TableColumn<ExpressDelivery, Integer> expDeliveryID;
	@FXML
	private TableColumn<ExpressDelivery, DeliveryPerson> expDeliveryDelPerson;
	@FXML
	private TableColumn<ExpressDelivery, DeliveryArea> expDeliveryDelArea;
	@FXML
	private TableColumn<ExpressDelivery, String> expDeliveryIsDelivered;
	@FXML
	private TableColumn<ExpressDelivery, Order> expDeliveryOrder;
	@FXML
	private TableColumn<ExpressDelivery, Double> expDeliveryPostage;
	@FXML
	private TableColumn<ExpressDelivery, LocalDate> expDeliveryDate;
	
	/****************Regular Delivery Table****************/

	@FXML
	private TableView<RegularDelivery> regDeliveryTable;
	@FXML
	private TableColumn<RegularDelivery, Integer> regDeliveryID;
	@FXML
	private TableColumn<RegularDelivery, TreeSet<Order>> regDeliveryOrders;
	@FXML
	private TableColumn<RegularDelivery, DeliveryPerson> regDeliveryDelPerson;
	@FXML
	private TableColumn<RegularDelivery, DeliveryArea> regDeliveryDelArea;
	@FXML
	private TableColumn<RegularDelivery, String> regDeliveryIsDelivered;
	@FXML
	private TableColumn<RegularDelivery, LocalDate> regDeliveryDate;
	
	/****************Delivery Area Table****************/

	@FXML
	private TableView<DeliveryArea> deliveryAreaTable;
	@FXML
	private TableColumn<DeliveryArea, Integer> deliveryAreaID;
	@FXML
	private TableColumn<DeliveryArea, String> deliveryAreaName;
	@FXML
	private TableColumn<DeliveryArea, HashSet<Neighberhood>> deliveryAreaHoods;
	@FXML
	private TableColumn<DeliveryArea, HashSet<DeliveryPerson>> deliveryAreaDelPersons;
	@FXML
	private TableColumn<DeliveryArea, Integer> deliveryAreaTime;
	@FXML
	private TableColumn<DeliveryArea, HashSet<Delivery>> deliveryAreaDeliveries;
	
	
/****************Blacklist Table****************/
	
	@FXML
	private TableView<Customer> blacklistTable;
	@FXML
	private TableColumn<Customer,Integer> blacklistID;
	@FXML
	private TableColumn<Customer, String> blacklistFirstName;
	@FXML
	private TableColumn<Customer, String> blacklistLastName;
	@FXML
	private TableColumn<Customer, LocalDate> blacklistBD;
	@FXML
	private TableColumn<Customer, Gender> blacklistGender;
	@FXML
	private TableColumn<Customer, String> blacklistPassword;
	@FXML
	private TableColumn<Customer, Neighberhood> blacklistNeighborhood;
	@FXML
	private TableColumn<Customer, String> blacklistLactose;
	@FXML
	private TableColumn<Customer, String> blacklistGluten;

	
	/***************Initialize all the tables***************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*set in the table all the customers data from database for each of their fields*/
		
		theCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		theCustomerID.setEditable(true);
		customerFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		customerLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		customerBD.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("birthDay"));
		customerGender.setCellValueFactory(new PropertyValueFactory<Customer, Gender>("gender"));
		customerPassword.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
		customerNeighborhood.setCellValueFactory(new PropertyValueFactory<Customer, Neighberhood>("neighberhood"));
		customerLactose.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isSensitiveToLactose())));
		customerGluten.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isSensitiveToGluten())));
		customersTable.setItems(getCustomers());
		customersTable.setEditable(true);
		
		/*set in the table all the delivery persons data from database for each of their fields*/
		deliveryPersonID.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Integer>("id"));
		deliveryPersonFirstName.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("firstName"));
		deliveryPersonLastName.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("lastName"));
		deliveryPersonBD.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, LocalDate>("birthDay"));
		deliveryPersonGender.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Gender>("gender"));
		deliveryPersonVehicle.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Vehicle>("vehicle"));
		deliveryPersonArea.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, DeliveryArea>("area"));
		deliveryPersonsTable.setItems(getDeliveryPerson());
		
		
		/*set in the table all the cooks data from database for each of their fields*/
		cookID.setCellValueFactory(new PropertyValueFactory<Cook, Integer>("id"));
		cookFirstName.setCellValueFactory(new PropertyValueFactory<Cook, String>("firstName"));
		cookLastName.setCellValueFactory(new PropertyValueFactory<Cook, String>("lastName"));
		cookBD.setCellValueFactory(new PropertyValueFactory<Cook, LocalDate>("birthDay"));
		cookGender.setCellValueFactory(new PropertyValueFactory<Cook, Gender>("gender"));
		cookExpertise.setCellValueFactory(new PropertyValueFactory<Cook, Expertise>("expert"));
		cookIsChef.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isChef())));
		cooksTable.setItems(getCooks());


		/*set in the table all the components data from database for each of their fields*/
		componentID.setCellValueFactory(new PropertyValueFactory<Component, Integer>("id"));
		componentName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		componentLactose.setCellValueFactory(new PropertyValueFactory<Component, String>("hasLactose"));
		componentGluten.setCellValueFactory(new PropertyValueFactory<Component, String>("hasGluten"));
		componentPrice.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
		componentTable.setItems(getComponents());
		
		
		/*set in the table all the dishes data from database for each of their fields*/
		dishID.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
		dishName.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		dishType.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		dishComponents.setCellValueFactory(new PropertyValueFactory<Dish, ArrayList<Component>>("componenets"));
		dishTime.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("timeToMake"));
		dishesTable.setItems(getDishes());
		
		/*set in the table all the orders data from database for each of their fields*/
		orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		orderCustomer.setCellValueFactory(new PropertyValueFactory<Order, Customer>("customer"));
		orderDishes.setCellValueFactory(new PropertyValueFactory<Order, ArrayList<Dish>>("dishes"));
		orderDelivery.setCellValueFactory(new PropertyValueFactory<Order, Delivery>("delivery"));
		ordersTable.setItems(getOrders());

		/*set in the table all the express deliveries data from database for each of their fields*/
		expDeliveryID.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Integer>("id"));
		expDeliveryDelPerson.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, DeliveryPerson>("deliveryPerson"));
		expDeliveryDelArea.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, DeliveryArea>("area"));
		expDeliveryIsDelivered.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isDelivered())));
		expDeliveryOrder.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Order>("order"));
		expDeliveryPostage.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Double>("postage"));
		expDeliveryDate.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, LocalDate>("deliveredDate"));
		expDeliveryTable.setItems(getExpDelivery());

		/*set in the table all the regular deliveries data from database for each of their fields*/
		regDeliveryID.setCellValueFactory(new PropertyValueFactory<RegularDelivery, Integer>("id"));
		regDeliveryDelPerson.setCellValueFactory(new PropertyValueFactory<RegularDelivery, DeliveryPerson>("deliveryPerson"));
		regDeliveryDelArea.setCellValueFactory(new PropertyValueFactory<RegularDelivery, DeliveryArea>("area"));
		regDeliveryIsDelivered.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isDelivered())));
		regDeliveryOrders.setCellValueFactory(new PropertyValueFactory<RegularDelivery, TreeSet<Order>>("orders"));
		regDeliveryDate.setCellValueFactory(new PropertyValueFactory<RegularDelivery, LocalDate>("deliveredDate"));
		regDeliveryTable.setItems(getRegDelivery());



		
		//TODO fix viewing the HashSet as a toString
		/*set in the table all the delivery areas data from database for each of their fields*/
		deliveryAreaID.setCellValueFactory(new PropertyValueFactory<DeliveryArea, Integer>("id"));
		deliveryAreaName.setCellValueFactory(new PropertyValueFactory<DeliveryArea, String>("areaName"));
		deliveryAreaHoods.setCellValueFactory(new PropertyValueFactory<DeliveryArea, HashSet<Neighberhood>>("neighberhoods"));
		deliveryAreaDelPersons.setCellValueFactory(new PropertyValueFactory<DeliveryArea, HashSet<DeliveryPerson>>("delPersons"));
//		deliveryAreaDeliveries.setCellValueFactory(new PropertyValueFactory<DeliveryArea, HashSet<Delivery>>("delivers"));
		deliveryAreaTime.setCellValueFactory(new PropertyValueFactory<DeliveryArea, Integer>("deliverTime"));
		deliveryAreaTable.setItems(getDeliveryAreas());


		/*set in the table all the blacklister data from database for each of their fields*/
		//TODO FIX BOOLEAN
		blacklistID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		blacklistFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		blacklistLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		blacklistBD.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("birthDay"));
		blacklistGender.setCellValueFactory(new PropertyValueFactory<Customer, Gender>("gender"));
		blacklistPassword.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
		blacklistNeighborhood.setCellValueFactory(new PropertyValueFactory<Customer, Neighberhood>("neighberhood"));
		blacklistLactose.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isSensitiveToLactose())));
		blacklistGluten.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isSensitiveToGluten())));
		blacklistTable.setItems(getBlacklist());
		

		

	}
	
	
	/**********Getting the data from the database methods********/
	
	
	/*get all the customers from the database*/
	private ObservableList<Customer> getCustomers(){
		ObservableList<Customer> customers=FXCollections.observableArrayList();
		ArrayList<Customer> query=new ArrayList<Customer>(Restaurant.getInstance().getCustomers().values());
		customers.addAll(query);
		return customers;	
	}	
	
	/*get all the cooks from the database*/
	private ObservableList<Cook> getCooks(){
		ObservableList<Cook> cooks=FXCollections.observableArrayList();
		ArrayList<Cook> query=new ArrayList<Cook>(Restaurant.getInstance().getCooks().values());
		cooks.addAll(query);
		return cooks;	
	}	
	
	/*get all the delivery persons from the database*/
	private ObservableList<DeliveryPerson> getDeliveryPerson(){
		ObservableList<DeliveryPerson> deliveryPerson=FXCollections.observableArrayList();
		ArrayList<DeliveryPerson> query=new ArrayList<DeliveryPerson>(Restaurant.getInstance().getDeliveryPersons().values());
		deliveryPerson.addAll(query);
		return deliveryPerson;	
	}	
	
	/*get all the components from the database*/
	private ObservableList<Component> getComponents(){
		ObservableList<Component> components=FXCollections.observableArrayList();
		ArrayList<Component> query=new ArrayList<Component>(Restaurant.getInstance().getComponenets().values());
		components.addAll(query);
		return components;	
	}	
	
	/*get all the dishes from the database*/
	private ObservableList<Dish> getDishes(){
		ObservableList<Dish> dishes=FXCollections.observableArrayList();
		ArrayList<Dish> query=new ArrayList<Dish>(Restaurant.getInstance().getDishes().values());
		dishes.addAll(query);
		return dishes;	
	}
	
	/*get all the orders from the database*/
	private ObservableList<Order> getOrders(){
		ObservableList<Order> orders=FXCollections.observableArrayList();
		ArrayList<Order> query=new ArrayList<Order>(Restaurant.getInstance().getOrders().values());
		orders.addAll(query);
		return orders;	
	}
	
	/*get all the express deliveries from the database*/
	private ObservableList<ExpressDelivery> getExpDelivery(){
		ObservableList<ExpressDelivery> expDel=FXCollections.observableArrayList();
		ArrayList<ExpressDelivery> query=new ArrayList<ExpressDelivery>();
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			if(d instanceof ExpressDelivery) {
				query.add((ExpressDelivery)d);
			}
		}
		expDel.addAll(query);
		return expDel;	
	}
	
	/*get all the regular deliveries from the database*/
	private ObservableList<RegularDelivery> getRegDelivery(){
		ObservableList<RegularDelivery> regDel=FXCollections.observableArrayList();
		ArrayList<RegularDelivery> query=new ArrayList<RegularDelivery>();
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			if(d instanceof RegularDelivery) {
				query.add((RegularDelivery)d);
			}
		}
		regDel.addAll(query);
		return regDel;	
	}
	
	
	/*get all the delivery areas from the database*/
	private ObservableList<DeliveryArea> getDeliveryAreas(){
		ObservableList<DeliveryArea> areas=FXCollections.observableArrayList();
		ArrayList<DeliveryArea> query=new ArrayList<DeliveryArea>(Restaurant.getInstance().getAreas().values());
		areas.addAll(query);
		return areas;	
	}	 
	
	
	/*get all the customers from the database*/
	private ObservableList<Customer> getBlacklist(){
		ObservableList<Customer> blacklisters=FXCollections.observableArrayList();
		ArrayList<Customer> query=new ArrayList<Customer>(Restaurant.getInstance().getBlackList());
		blacklisters.addAll(query);
		return blacklisters;	
	}	
	



}
