package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import Model.*;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.Vehicle;
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
	/*Order(int id,Customer customer, ArrayList<Dish> dishes, Delivery delivery)*/
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
//	@FXML
//	private TableColumn<DeliveryArea, HashSet<Delivery>> deliveryAreaDeliveries;
	
	
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
		//TODO FIX BOOLEAN
		theCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		customerFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		customerLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		customerBD.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("birthDay"));
		customerGender.setCellValueFactory(new PropertyValueFactory<Customer, Gender>("gender"));
		customerPassword.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
		customerNeighborhood.setCellValueFactory(new PropertyValueFactory<Customer, Neighberhood>("neighberhood"));
//		customerLactose.setCellValueFactory(new PropertyValueFactory<Customer, String>("isSensitiveToLactose"));
//		customerGluten.setCellValueFactory(new PropertyValueFactory<Customer, String>("isSensitiveToGluten"));
		customersTable.setItems(getCustomers());
		
		
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
		//TODO FIX BOOLEAN
		cookID.setCellValueFactory(new PropertyValueFactory<Cook, Integer>("id"));
		cookFirstName.setCellValueFactory(new PropertyValueFactory<Cook, String>("firstName"));
		cookLastName.setCellValueFactory(new PropertyValueFactory<Cook, String>("lastName"));
		cookBD.setCellValueFactory(new PropertyValueFactory<Cook, LocalDate>("birthDay"));
		cookGender.setCellValueFactory(new PropertyValueFactory<Cook, Gender>("gender"));
		cookExpertise.setCellValueFactory(new PropertyValueFactory<Cook, Expertise>("expert"));
//		cookIsChef.setCellValueFactory(new PropertyValueFactory<Cook, String>("isChef"));
		cooksTable.setItems(getCooks());


		/*set in the table all the components data from database for each of their fields*/
		//TODO FIX BOOLEAN
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
//		orderDelivery.setCellValueFactory(new PropertyValueFactory<Order, Delivery>("delivery"));
		ordersTable.setItems(getOrders());



		
		
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
//		blacklistLactose.setCellValueFactory(new PropertyValueFactory<Customer, String>("isSensitiveToLactose"));
//		blacklistGluten.setCellValueFactory(new PropertyValueFactory<Customer, String>("isSensitiveToGluten"));
		blacklistTable.setItems(getBlacklist());
		

		
//		//populate Orders Table from DB
//		ordersID.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Integer>("id"));
//		ordersOwner.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("owner"));
//		ordersQuantity.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Integer>("quantity"));
//		ordersPrice.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Double>("Price"));
//		OrdersFlights.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("flights"));
//		OrderAccom.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("accommodations"));
//		ordersTrips.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("groupTrips"));
//		orders.setItems(getItems());

		
//		//populating Accommodations Table from DB
//		accID.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Long>("businessId"));
//		accName.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, String>("displayName"));
//		accRooms.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Integer>("numberOfRooms"));
//		accPeople.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Integer>("numberOfPeoplePerRoom"));
//		accPrice.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Double>("pricePerPerson"));
//		accLocation.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Destination>("location"));
//		accType.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, String>("type"));
//		accOccupied.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Integer>("currentNumberOfOccupiedRooms"));
//		accommodations.setItems(getAcc());
//		//Populating Destination Table from DB
//		destCountry.setCellValueFactory(new PropertyValueFactory<Destination, String>("country"));
//		destCity.setCellValueFactory(new PropertyValueFactory<Destination, String>("city"));
//		destinations.setItems(getDests());
//		//populating customer table from DB
//		customerID.setCellValueFactory(new PropertyValueFactory<Customer, Long>("id"));
//		customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
//		customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
//		customerBirth.setCellValueFactory(new PropertyValueFactory<Customer, Date>("birthDate"));
//		customerPass.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
//		customerAnswer.setCellValueFactory(new PropertyValueFactory<Customer, String>("answer"));
//		customerOrders.setCellValueFactory(new PropertyValueFactory<Customer, HashSet<Order>>("orders"));
//		customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
//		customerAdd.setCellValueFactory(new PropertyValueFactory<Customer, Address>("address"));
//		customers.setItems(getCustomers());
//		//populating Guide table from DB
//		guideID.setCellValueFactory(new PropertyValueFactory<Guide, Long>("id"));
//		guideFirst.setCellValueFactory(new PropertyValueFactory<Guide, String>("firstName"));
//		guideLast.setCellValueFactory(new PropertyValueFactory<Guide, String>("surname"));
//		guideBirth.setCellValueFactory(new PropertyValueFactory<Guide, Date>("birthDate"));
//		guidePass.setCellValueFactory(new PropertyValueFactory<Guide, String>("password"));
//		guideAnswer.setCellValueFactory(new PropertyValueFactory<Guide, String>("answer"));
//		guideExp.setCellValueFactory(new PropertyValueFactory<Guide, HashSet<Destination>>("experiencedDestinations"));
//		guideStart.setCellValueFactory(new PropertyValueFactory<Guide, Date>("startDate"));
//		guideEmail.setCellValueFactory(new PropertyValueFactory<Guide, String>("email"));
//		guideAdd.setCellValueFactory(new PropertyValueFactory<Guide, Address>("address"));
//		guideTrips.setCellValueFactory(new PropertyValueFactory<Guide, HashSet<GroupTrip>>("groupsTrip"));
//		guides.setItems(getGuides());
//		//Populating GroupTrip table from DB
//		tripID.setCellValueFactory(new PropertyValueFactory<GroupTrip, Integer>("tripId"));
//		tripDescription.setCellValueFactory(new PropertyValueFactory<GroupTrip, String>("description"));
//		tripDate.setCellValueFactory(new PropertyValueFactory<GroupTrip, Date>("date"));
//		tripLocation.setCellValueFactory(new PropertyValueFactory<GroupTrip, Destination>("location"));
//		tripGuide.setCellValueFactory(new PropertyValueFactory<GroupTrip, Guide>("guide"));
//		tripPrice.setCellValueFactory(new PropertyValueFactory<GroupTrip, Double>("price"));
//		tripCapacity.setCellValueFactory(new PropertyValueFactory<GroupTrip, Integer>("maxCapacity"));
//		tripOccupied.setCellValueFactory(new PropertyValueFactory<GroupTrip, Integer>("currentNumberOfTourists"));
//		trips.setItems(getTrips());
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
	

	

	
//	private ObservableList<GroupTrip> getTrips() {
//		ObservableList<GroupTrip> trips=FXCollections.observableArrayList();
//		ArrayList<GroupTrip> query=new ArrayList<GroupTrip>(Shared.getInstance().getTrips().values());
//
//		trips.addAll(query);
//		return trips;	
//	}

//	private ObservableList<Guide> getGuides() {
//		ObservableList<Guide> guides=FXCollections.observableArrayList();
//		ArrayList<Guide> query=new ArrayList<Guide>(Shared.getInstance().getGuides().values());
//
//		guides.addAll(query);
//		return guides;	
//	}
//
//	private ObservableList<Customer> getCustomers() {
//		ObservableList<Customer> customer=FXCollections.observableArrayList();
//		ArrayList<Customer> query=new ArrayList<Customer>(Shared.getInstance().getCustomers().values());
//
//		customer.addAll(query);
//		return customer;	
//	}
//
//	private ObservableList<Destination> getDests() {
//		ObservableList<Destination> dest=FXCollections.observableArrayList();
//		ArrayList<String> query=new ArrayList<String>(Shared.getInstance().getDestinations().keySet());
//		ArrayList<Destination> tmp=new ArrayList<Destination> ();
//		for(String s:query)
//			tmp.addAll(Shared.getInstance().getDestinations().get(s));
//		dest.addAll(tmp);
//		return dest;	
//	}
//
//	private ObservableList<AccommodationForTable> getAcc() {
//		ObservableList<AccommodationForTable> acc=FXCollections.observableArrayList();
//		ArrayList<Accommodation> query=new ArrayList<Accommodation>(Shared.getInstance().getAccommodations().values());
//		ArrayList<AccommodationForTable> convertedForTable=new ArrayList<AccommodationForTable>();
//		for(Accommodation a:query)
//			convertedForTable.add(new AccommodationForTable(a));
//		acc.addAll(convertedForTable);
//		return acc;
//	}
//

//
//	private ObservableList<TravelPackageForTable> getPackageItems() {
//		ObservableList<TravelPackageForTable> packages=FXCollections.observableArrayList();
//		ArrayList<TravelPackage> query=new ArrayList<TravelPackage>(Shared.getInstance().getPackages().values());
//		ArrayList<TravelPackageForTable> convertedForTable=new ArrayList<TravelPackageForTable>();
//		for(TravelPackage o:query)
//			convertedForTable.add(new TravelPackageForTable(o));
//		packages.addAll(convertedForTable);
//		return packages;
//	}
//
//	private ObservableList<OrdersForTable> getItems() {
//		ObservableList<OrdersForTable> orders=FXCollections.observableArrayList();
//		ArrayList<Order> query=new ArrayList<Order>(Shared.getInstance().getOrders().values());
//		ArrayList<OrdersForTable> convertedForTable=new ArrayList<OrdersForTable>();
//		for(Order o:query)
//			convertedForTable.add(new OrdersForTable(o));
//		orders.addAll(convertedForTable);
//		System.out.println(convertedForTable.get(1).getOwner());
//		return orders;

//	}

}
