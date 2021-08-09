package view;



import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

import Exceptions.IllegalCustomerException;
import Exceptions.IllegelInputException;
import Exceptions.IllegelPasswordException;
import Exceptions.PasswordMismatchException;
import Exceptions.SimilarIDInSystemException;
import Utils.*;
import controller.PrimaryController;
import controller.Sounds;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import Model.*;

public class ManagerAddPageController implements Initializable {
	private static final String Input = "Rest.ser";
	
	/*The controller to add and remove everything from the GUI to the database*/
	PrimaryController control=new PrimaryController();
	
	
	/**************************************Customer Page*****************************************/
	@FXML
	private TextField customerId;
	@FXML
	private TextField customerFirst;
	@FXML
	private TextField customerLast;
	@FXML
	private DatePicker customerBirth;
	@FXML
	private TextField customerPass;
	@FXML
	private TextField customerPassVerify;
	@FXML
	private ComboBox<String> customerGenderCombo;
	@FXML
	private ComboBox<String> customerHoodCombo;
	@FXML
	private CheckBox customerGluten;
	@FXML
	private CheckBox customerLactose;
	
	@FXML
	private Button addCustomer;
	@FXML 
	private TextField delCustomerID;
	
	@FXML
	private ComboBox<String> customerDelete;	
	@FXML
	private Button delCustomer;
	
	
	/**************************************Delivery Person Page****************************************/

	@FXML 
	private TextField delPersonId;
	@FXML 
	private TextField delPersonFName;
	@FXML 
	private TextField delPersonLName;
	@FXML
	private DatePicker delPersonBirth;
	//use the gender combo from customer
	@FXML
	private ComboBox<String> delPersonVehicle;
	@FXML
	private ComboBox<String> delPersonArea;
	
	
	@FXML
	private Button addDelPerson;
	
	@FXML
	private ComboBox<String> delPersonDelete;
	@FXML
	private Button deleteDelPerson;
	@FXML
	private TextField deleteDelPersonID;
	

	
	/**************************************Cook Page*****************************************/
	@FXML
	private TextField cookId;
		
	@FXML
	private TextField cookFirstName;
		
	@FXML
	private TextField cookLastName;
		
	@FXML
	private DatePicker cookDate;
		
	@FXML
	private ComboBox<String> cookGender;
		
	@FXML
	private ComboBox<String> cookExpertise;
		
	@FXML
	private CheckBox isChef;
		
	@FXML
	private Button addCook;
	
	@FXML
	private ComboBox<String> cooksInSys;
	@FXML
	private TextField cookIDtoDelete;
	@FXML
	private Button removeCookBtn;
	

	
	/**************************************Component Page****************************************/
	@FXML
	private TextField componentID;
	@FXML
	private TextField componentName;	
	@FXML
	private CheckBox hasLactose;
	@FXML
	private CheckBox hasGluten;
	@FXML
	private TextField componentPrice;
	@FXML
	private ComboBox<String> genderCombo;
	@FXML
	private ComboBox<Integer> componentsDelete;
	
	@FXML
	private Button addComponent;
	
	@FXML
	private Button delComponent;
	@FXML
	private TextField delComponentID;
	
	
	@FXML
	private Button updateComponentData;
	@FXML
	private TextField newComponentId;
	

	/**************************************Dish Page*****************************************/
	
	@FXML
	private TextField dishId;
	
	@FXML
	private TextField dishName;
	
	@FXML
	private TextField timeToMake;
	
	@FXML
	private ComboBox<String> TypeOfTheDish;
		
	@FXML
	private ComboBox<Integer> componentsInDish;
	
	@FXML
	private TextArea componentsList;
	@FXML
	private Button addComponentToList;
	@FXML
	private Button clearComponentsList;
	@FXML
	private Button addDish;
	
	@FXML
	private ComboBox<String> dishIDToDelete;
	@FXML
	private TextField deleteDishTextField;
	@FXML
	private Button removeDish;
	
	/**************************************Order Page*****************************************/
	
	@FXML
	private TextField orderId;
	
	
	@FXML
	private TextField orderIdToRemove;//
	
	
	@FXML
	private TextField customerForOrderId;
	
	@FXML
	private ComboBox<String> customersForOrder ;
	
	@FXML
	private ComboBox<String> currentOrders ;
	
	@FXML
	private ComboBox<String> dishesInOrder ;// multiple choice 
	
	@FXML
	private ComboBox<String> deliveriesInOrder ;
	
	@FXML
	private Button addOrder ;
	
	@FXML
	private Button addDishesInOrder ;

	@FXML
	private Button addDeliveriesInOrder ;
	
	@FXML
	private Button removeOrder ;
	
	@FXML
	private TextArea dishesInOrderShow;
	
	@FXML
	private TextArea deliveriesInOrderShow;
	
	@FXML
	private Button clearDishesInOrder;
	
	@FXML
	private Button clearDeliveriesInOrder;
	
	/************************************Delivery Page*******************************************/
	
	@FXML
	private TextField deliveryId;
	
	@FXML
	private TextField ordersToAddDelivery;
	
	@FXML
	private ComboBox<String> deliveryPerson ;
	
	@FXML
	private ComboBox<String> deliveryArea ;
	

	@FXML
	private DatePicker deliveryDate;
	
	@FXML
	private CheckBox isDelivered;
	
	@FXML
	private ComboBox<Integer> currentOrds ;
	
	
	/**************************************Delivery Area Page****************************************/

	@FXML
	private TextField delAreaID;
	@FXML
	private TextField delAreaName;
	@FXML
	private TextField delAreaTime;
	
	@FXML
	private ComboBox<String> delAreaDelPersons;
	@FXML
	private Button addDelPersonToArea;
	@FXML
	private Button clearDelPersons;
	@FXML
	private TextArea delPersonsList;
	
	@FXML
	private ComboBox<String> delAreaHoods;
	@FXML
	private Button addHoodToArea;
	@FXML
	private Button clearHoods;
	@FXML
	private TextArea hoodList;
	
	@FXML
	private ComboBox<String> delAreaDeliveries;
	@FXML
	private Button addDeliveriesToArea;
	@FXML
	private Button clearDeliveries;
	@FXML
	private TextArea deliveriesList;
	
	@FXML
	private Button addDeliveryArea;
	
	@FXML
	private ComboBox<String> deliveryAreasByID;
	@FXML
	private TextField oldAreaID;
	@FXML
	private TextField newAreaID;
	
	@FXML
	private Button areaToReplace;


	/*************************************Blacklist Page*************************************/
	@FXML
	private TextField customerToBlacklist;
	@FXML
	private ComboBox<String> customerList;
	@FXML
	private ComboBox<String> blacklistList;
	@FXML
	private Button addToBlackList;

	/*#######################################################*/


	/*check if ok*/
	public static boolean require(Object... values){
		for (Object value : values)
			if(value == null)
				return false;

		return true;
	}

//	PrimaryController control=new PrimaryController();
	ArrayList<String> GuideExp=new ArrayList<String>();
	ArrayList<String> packageFlightList=new ArrayList<String>();
	ArrayList<Integer> packageTripList=new ArrayList<Integer>();
	ArrayList<Long> packageAccommodationList=new ArrayList<Long>();
	ArrayList<String> hoodsInDeliveryArea = new ArrayList<>();
	ArrayList<Integer> componentsInDishList = new ArrayList<>();
	ArrayList<String> dishesInOrderList = new ArrayList<>();
	ArrayList<String> DeliveriesInOrderList = new ArrayList<>();
	ArrayList<Integer> ordersList = new ArrayList<>();

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		/**************Load list of dishes in system*********/
		ArrayList<String> dishesDB = new ArrayList<>();
		for(Dish d :  Restaurant.getInstance().getDishes().values()) {
			dishesDB.add(d.getDishName());
		}
		ObservableList<String> ObservableListDishes = FXCollections.observableArrayList();
		ObservableListDishes.addAll(dishesDB);
		dishIDToDelete.setItems(ObservableListDishes);//current orders
		dishesInOrder.setItems(ObservableListDishes);
		
		/************load list of orders in the system **************/
		ArrayList<String> ordersDB = new ArrayList<>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			ordersDB.add(o.toString());
		}
		ObservableList<String> ObservableListOrders = FXCollections.observableArrayList();
		ObservableListOrders.addAll(ordersDB);
		currentOrders.setItems(ObservableListOrders);
//		currentOrds.setItems(ObservableListOrders);// addOrderToDelivery

		
		/***************Load list of cooks in system*********/
		ArrayList<String> cooksDB = new ArrayList<>();
		for(Cook c :  Restaurant.getInstance().getCooks().values()) {
			cooksDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> ObservableListCooks = FXCollections.observableArrayList();
		ObservableListCooks.addAll(cooksDB);
		cooksInSys.setItems(ObservableListCooks);
		
		
		
		/***************Load list of delivery persons in system*********/
		ArrayList<String> delPersonDB = new ArrayList<>();
		for(DeliveryPerson dp :  Restaurant.getInstance().getDeliveryPersons().values()) {
			delPersonDB.add(dp.getFirstName() + " " + dp.getLastName());
		}
		ObservableList<String> ObservableListDelPersons = FXCollections.observableArrayList();
		ObservableListDelPersons.addAll(delPersonDB);
		delPersonDelete.setItems(ObservableListDelPersons);
		delAreaDelPersons.setItems(ObservableListDelPersons);
		deliveryPerson.setItems(ObservableListDelPersons);

		/***************Load list of delivery areas in system*********************/
		ArrayList<String> areasDB = new ArrayList<>();
		for(DeliveryArea da :  Restaurant.getInstance().getAreas().values()) {
			areasDB.add(da.getAreaName());
		}
		ObservableList<String> comboAreas = FXCollections.observableArrayList();
		comboAreas.addAll(areasDB);
		delPersonArea.setItems(comboAreas);
		deliveryAreasByID.setItems(comboAreas);
		deliveryArea.setItems(comboAreas);
		
		/***************Load list of components in the system***************/
		//will be only the id of the component
		ArrayList<Integer> componentsDB = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB.add(c.getId());
		}
		ObservableList<Integer> ObservableListComponents = FXCollections.observableArrayList();
		ObservableListComponents.addAll(componentsDB);
		componentsDelete.setItems(ObservableListComponents);
		componentsInDish.setItems(ObservableListComponents);
		
		/***************Load list of dish types*********/
		ArrayList<String> dishTypes = new ArrayList<>();
		for(DishType dt : DishType.values() ) {
			dishTypes.add(String.valueOf(dt));
		}
		ObservableList<String> ObservableListDishType=FXCollections.observableArrayList();
		ObservableListDishType.addAll(dishTypes);
		TypeOfTheDish.setItems(ObservableListDishType);
				
		/***************Load list of customers in the system***************/
		ArrayList<String> customerDB = new ArrayList<>();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			customerDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> ObservableListCustomers = FXCollections.observableArrayList();
		ObservableListCustomers.addAll(customerDB);
		customerDelete.setItems(ObservableListCustomers);
		customerList.setItems(ObservableListCustomers);
		customersForOrder.setItems(ObservableListCustomers);
		

		/***************Load the Blacklist in the system***************/
		ArrayList<String> blacklistDB = new ArrayList<>();
		for(Customer c : Restaurant.getInstance().getBlackList()) {
			blacklistDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> ObservableListBlacklist = FXCollections.observableArrayList();
		ObservableListBlacklist.addAll(blacklistDB);
		blacklistList.setItems(ObservableListBlacklist);

		/******************Load Neighborhood enum****************/
		ArrayList<String> neighberhoodsDB=new ArrayList<String>();
		for(Neighberhood n : Neighberhood.values()) {

			neighberhoodsDB.add(String.valueOf(n));
		}

		ObservableList<String> ObservableListNeighborhoods=FXCollections.observableArrayList();
		ObservableListNeighborhoods.addAll(neighberhoodsDB);
		customerHoodCombo.setItems(ObservableListNeighborhoods);
		delAreaHoods.setItems(ObservableListNeighborhoods);
		
		/******************Load genders enum****************/
		ArrayList<String> gendersDB=new ArrayList<String>();
		for(Gender g : Gender.values()) {

			gendersDB.add(String.valueOf(g));
		}		
		ObservableList<String> observableListGenders=FXCollections.observableArrayList();
		observableListGenders.addAll(gendersDB);
		customerGenderCombo.setItems(observableListGenders);
		genderCombo.setItems(observableListGenders);
		cookGender.setItems(observableListGenders);
		
		/******************Load Expertise enum****************/
		ArrayList<String> cookExpertiseAL = new ArrayList<>();
		for(Expertise exp: Expertise.values()){
			cookExpertiseAL.add(String.valueOf(exp));
		}
		ObservableList<String> ObservableListExpertise=FXCollections.observableArrayList();
		ObservableListExpertise.addAll(cookExpertiseAL);
		cookExpertise.setItems(ObservableListExpertise);

		/******************Load Vehicle enum****************/
		ArrayList<String> vehicleDB = new ArrayList<>();
		
		for(Vehicle v : Vehicle.values()) {
			vehicleDB.add(String.valueOf(v));
		}
		ObservableList<String> ObservableListVehicles=FXCollections.observableArrayList();
		ObservableListVehicles.addAll(vehicleDB);
		delPersonVehicle.setItems(ObservableListVehicles);
		
		
		ArrayList<String> deliveriesDb = new ArrayList<>();
		
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			deliveriesDb.add(String.valueOf(d));
		}
		ObservableList<String> ObservableListDeliveries=FXCollections.observableArrayList();
		ObservableListDeliveries.addAll(deliveriesDb);
		deliveriesInOrder.setItems(ObservableListDeliveries);
		
		
		

		

	
		
		
		
//		expCertificateLabel.setFont(new Font(20));
//		expCertificateLabel.setText("Add Destination Certificate to procceed");	
//		ObservableList<String> Hostellist=FXCollections.observableArrayList("Short","Long");
//		hostelType.setItems(Hostellist);
//
//		ObservableList<String> list=FXCollections.observableArrayList();
//		refreshGui();
//		for(String country:Shared.getInstance().getDestinations().keySet())
//		{
//
//			ArrayList<Destination> dests=Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(new String(d.getCountry()+"_"+ d.getCity()));
//			}
//
//		}
//		guideDestinations.setItems(list);
//		ArrayList<String> tripsDB=new ArrayList<String>() ;
//		ArrayList<String> flightsDB=new ArrayList<String>(Shared.getInstance().getFlights().keySet());
//		ArrayList<String> AccommodationDB=new ArrayList<String>();
//		for(Long l:Shared.getInstance().getAccommodations().keySet())
//		{
//			AccommodationDB.add(String.valueOf(l));
//		}
//		for(Integer i:Shared.getInstance().getTrips().keySet())
//		{
//			tripsDB.add(String.valueOf(i));
//		}
//		ObservableList<Long> guideIdComboTrips=FXCollections.observableArrayList();
//		ArrayList<Long> guides =new ArrayList<Long>(Shared.getInstance().getGuides().keySet());
//		guideIdComboTrips.addAll(guides);
//		tripGuideId.setItems(guideIdComboTrips);
//		guideExpId.setItems(guideIdComboTrips);
//		ObservableList<String> comboTrips=FXCollections.observableArrayList();
//		ObservableList<String> comboFlights=FXCollections.observableArrayList();
//		ObservableList<String> comboAccommodations=FXCollections.observableArrayList();
//		comboTrips.addAll(tripsDB);
//		comboFlights.addAll(flightsDB);
//		packageGroupTrip.setItems(comboTrips);
//		comboAccommodations.addAll(AccommodationDB);
//		ArrayList<String> countyList=new ArrayList<String>(Shared.getInstance().getDestinations().keySet());
//		ObservableList<String> comboCountryList=FXCollections.observableArrayList();
//		comboCountryList.addAll(countyList);
//		hotelCountry.setItems(comboCountryList);
//		motelCountry.setItems(comboCountryList);
//		hostelCountry.setItems(comboCountryList);
//		flightToCountry.setItems(comboCountryList);
//		flightFromCountry.setItems(comboCountryList);
//		guideExpCountry.setItems(comboCountryList);
//		tripCountry.setItems(comboCountryList);
//		packageFlights.setItems(comboFlights);
//		packageAccommodations.setItems(comboAccommodations);
//

	}
	/*************Add to Blacklist*******/
	public void addCustomerToBlacklist(ActionEvent e) {
		String section = "Customer";
		try {
			int id = Integer.parseInt(customerToBlacklist.getText());
			if(control.addToBlacklistFromGUI(id)) {
				successAdded(section,"Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the customers database!");
			}
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(Restaurant.getInstance().getBlackList());
		refreshGui();
	}
	
	
	
	/*************Remove a Dish**********/
	public void removeDish(ActionEvent e) {
		String section = "Dish";
		try {
			int id = Integer.parseInt(deleteDishTextField.getText());
			if(control.removeDishFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the dishes database!");
			}
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}
	
	
	/**************Add a Dish************/
	public void addDish(ActionEvent e) {
		String section = "Dish";
		try {
			
			int id = Integer.parseInt(dishId.getText());
			String dName = dishName.getText();
			String type = TypeOfTheDish.getValue();
			DishType selectedD = null;
			int toMake = Integer.parseInt(timeToMake.getText());
			for(DishType g : DishType.values()) {
				if(g.toString().equals(type)) {
					selectedD = g;
				}
			}
			
			if(control.addDishFromGUI(id, dName,selectedD, componentsInDishList, toMake)) {
				successAdded(section,"Success");
				Restaurant.save(Input);	
			}
			else {
				fail(section, "This id already exists in the dishes database!");
			}			
			System.out.println(Restaurant.getInstance().getDishes().values());
		}catch(IllegelInputException e1) {
			System.out.println("IllegelInputException()");
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}
	
	
	
	
	
	/**************replace a Delivery Area*********/
	//TODO Finish method + in PrimaryController
	public void replaceDeliveryArea(ActionEvent e) {
		String section = "Delivery Area";
		try {
			int idOldArea = Integer.parseInt(oldAreaID.getText());
			int idNewArea = Integer.parseInt(newAreaID.getText());

			if(control.replaceDeliveryAreaGUI(idOldArea, idNewArea)){
				successRemove(section, "Success");
				Restaurant.save(Input);	
			}
			else {
				fail(section, "Could not replace old area with new one!");
			}
		}catch(Exception e1) {
			System.out.println("hey in del area");
		}
		refreshGui();
	}
	/**************Add a Delivery Area************/
	public void addDeliveryArea(ActionEvent e) {
//		/*constructor for GUI*/
//		public DeliveryArea(int id, String areaName, HashSet<Neighberhood> neighberhoods, int deliverTime) {
		String section = "Delivery Area";
		try {
			int id = Integer.parseInt(delAreaID.getText());
			String aName = delAreaName.getText();
			int deliveryTime = Integer.parseInt(delAreaTime.getText());
			String aHood = delAreaHoods.getValue();

			if(control.addDeliveryAreaGUI(id, aName, hoodsInDeliveryArea, deliveryTime)) {
				successAdded(section,"Success");
				Restaurant.save(Input);	
			}
			else {
				fail(section, "This id does not exists in the delivery area database!");
			}
			
			System.out.println(Restaurant.getInstance().getAreas().values());
		}catch(Exception e1) {
			System.out.println("hey in del area");
		}
		refreshGui();
	}

	/**************Remove a Delivery Person*******/
	public void removeDeliveryPerson(ActionEvent e) {
		String section = "Delivery Person";
		try {
			int id = Integer.parseInt(deleteDelPersonID.getText());
			if(control.removeDeliveryPersonGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the delivery person database!");
			}
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**************Add a Delivery Person**********/
	public void addDeliveryPerson(ActionEvent e) {
//		public DeliveryPerson(int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
//		DeliveryArea area)
		String section = "Delivery Person";
		try {			
			int id = Integer.parseInt(delPersonId.getText());			
			String fName = delPersonFName.getText();
			String lName = delPersonLName.getText();
			LocalDate localDate = delPersonBirth.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			String gender = genderCombo.getValue();
			Gender selectedG = null;
			String vehicle = delPersonVehicle.getValue();
			Vehicle selectedV = null;
			String areaName = delPersonArea.getValue();
			String dArea = delPersonArea.getValue();
			for(Gender g : Gender.values()) {
				if(g.toString().equals(gender)) {
					selectedG = g;
				}
			}
			for(Vehicle v : Vehicle.values()) {
				if(v.toString().equals(vehicle)) {
					selectedV = v;
				}
			}
			//add method in primary controller
			if(control.addDeliveryPersonGUI(id, fName, lName, localDate, selectedG, selectedV,dArea)) {
				successAdded(section,"Success");
				System.out.println(Restaurant.getInstance().getDeliveryPersons().values());
				Restaurant.save(Input);								
			}
			else {
				fail(section,"This id already exists in the delivery persons database!");
			}
								
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}
	
	/**************Update a component*************/
	public void updateComponent(ActionEvent e) {
		String section = "Component";
		try {
			int id = Integer.parseInt(componentID.getText());
			String cName = componentName.getText();
			boolean lactose = hasLactose.isSelected();
			boolean gluten = hasGluten.isSelected();
			double price = Double.parseDouble(componentPrice.getText());
			//if a new id was entered to be updated
			
			if(!newComponentId.getText().isBlank()) {
				int newID = Integer.parseInt(newComponentId.getText());
				if(control.updateComponentGUI(id, cName, lactose, gluten, price, newID)) {
					successUpdate(section, "Success");
					Restaurant.save(Input);
				}
			}
			//if no new id, use the same id to be updated as the "new id"
			if(control.updateComponentGUI(id, cName, lactose, gluten, price, id)) {
				successUpdate(section, "Success");
				Restaurant.save(Input);
			}
		}catch(Exception e1) {
			failUpdate(section, e1.toString());
		}
		refreshGui();
	}
	
	/**************Delete a component*************/
	public void delComponent(ActionEvent e) {
		String section = "Component";
		try {
			int id = Integer.parseInt(delComponentID.getText());
			//remove method in primary controller
			if(control.removeComponentGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the components database!");
			}
			System.out.println(Restaurant.getInstance().getComponenets());
			refreshGui();
		}catch (Exception e1) {
			fail(section, e1.toString());
		}
		refreshGui();
	}
	
	/**************Add a component***************/
	public void addComponent(ActionEvent e) {
		String section = "Component";
//		public Component(int id, String componentName, boolean hasLactose, boolean hasGluten, double price)
		try {
			int id = Integer.parseInt(componentID.getText());
			String cName = componentName.getText();
			boolean lactose = hasLactose.isSelected();
			boolean gluten = hasGluten.isSelected();
			double price = Double.parseDouble(componentPrice.getText());
			//add method in primary controller
			if(control.addComponentGUI(id, cName, lactose, gluten, price)) {
				successAdded(section,"Success");
				System.out.println(Restaurant.getInstance().getComponenets());
				Restaurant.save(Input);				
			}
			else {
				fail(section,"This id already exists in the customer database!");
			}
								
		}catch(Exception e1) {
			System.out.println("hey");
		}
		refreshGui();
	}

	
	/**************Delete a customer*************/
	public void delCustomer(ActionEvent e) {
		String section = "Customer";
		try {
			int id = Integer.parseInt(delCustomerID.getText());
			if(control.removeCustomerGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			//if the id is not in the customer database, we cant delete it
			else {
				fail(section,"This id does not exists in the customer database!");
			}
			System.out.println(Restaurant.getInstance().getCustomers());
			
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		refreshGui();
	}

	/**************Add a customer*************/
	//working
	public void addCustomer(ActionEvent e)
	{
		String section = "Customer";
		try {

			int id=Integer.parseInt(customerId.getText());
			String password=customerPass.getText();
			String passwordVerify=customerPassVerify.getText();
			if(!password.equals(passwordVerify)) {
				throw new PasswordMismatchException();
			}
			String firstName=customerFirst.getText();
			String LastName=customerLast.getText();
			LocalDate localDate =customerBirth.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			String gender = customerGenderCombo.getValue();
			Gender selectedG = null;
			String neighberhood = customerHoodCombo.getValue();
			Neighberhood selectedN = null;
			
			for(Gender g : Gender.values()) {
				if(g.toString().equals(gender)) {
					selectedG = g;
				}
			}
			for(Neighberhood n : Neighberhood.values()) {
				if(n.toString().equals(neighberhood)) {
					selectedN = n;
				}
			}
			boolean lactose = customerLactose.isSelected();
			boolean gluten = customerGluten.isSelected();

			if(control.addCustomerFromGUI(id,firstName, LastName, localDate, selectedG, password, passwordVerify, selectedN, lactose, gluten)) {
				successAdded(section, "Success");
				Restaurant.save(Input);				
			}
			//if could not add customer
			else {
				fail(section,"This id already exists in the customer database!");
			}
//			Customer custToAdd = new Customer(firstName,LastName,localDate,selectedG,selectedN,lactose,gluten);
//			if(Restaurant.getInstance().addCustomer(custToAdd)) {
//			}

			System.out.println(Restaurant.getInstance().getCustomers());
			refreshGui();

			//pop up with success
			//exception-Customer adding failed,Customer already exists/illegal input
		}
		catch(IllegelInputException e1) {
			badSound();
			fail(section, e1.toString());
		}
		catch(PasswordMismatchException e1) {
			badSound();
			fail(section, e1.toString());
		}
		catch(IllegelPasswordException e1) {
			badSound();
			fail(section,e1.toString());
		}
		catch(IllegalCustomerException e1) {
			badSound();
			fail(section,e1.toString());
		}
		catch(SimilarIDInSystemException e1) {
			badSound();
			fail(section,e1.toString());
		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, "Person"+e1.toString());
//		}
		catch(NumberFormatException e1) {
			badSound();
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			badSound();
			fail(section, e1.toString());
		}
	}
	
	
	/**************Remove a Cook*************/
	public void removeCook(ActionEvent e) {
		String section = "Cook";
		try {
			int id = Integer.parseInt(cookIDtoDelete.getText());
			if(control.removeCookFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			//if the id is not in the cook database, we can't delete it
			else {
				fail(section,"This id does not exists in the customer database!");
			}
			
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		refreshGui();
	}
	
	/**************Add a Cook*************/
	public void addCook(ActionEvent e)
	{
		String section = "Cook";
		try {
			int id=Integer.parseInt(cookId.getText());// get id
			String firstName=cookFirstName.getText();//get the first name
			String LastName=cookLastName.getText();// get the last name
			LocalDate localDate =cookDate.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));// get the date
			Date date = Date.from(instant);
			String gender = cookGender.getValue();
			Gender selectedG = null;
			String expertise = cookExpertise.getValue();
			Expertise selectedN = null;
			boolean isChef=false;// create an option to choose if chef or not
			
			for(Gender g : Gender.values()) {
				if(g.toString().equals(gender)) {
					selectedG = g;
				}
			}
			for(Expertise n : Expertise.values()) {
				if(n.toString().equals(expertise)) {
					selectedN = n;
				}
			}

			if(control.addCookFromGUI(id,firstName, LastName, localDate, selectedG,selectedN, isChef)) {
				successAdded(section, "Success");
				Restaurant.save(Input);				
			}
			//if could not add customer
			else {
				fail(section,"This id already exists in the customer database!");
			}
			System.out.println("cooks: " + Restaurant.getInstance().getCooks());
			refreshGui();

		}
		catch(IllegelInputException e1) {
			badSound();
			fail(section, e1.toString());
		}
		catch(SimilarIDInSystemException e1) {
			badSound();
			fail(section,e1.toString());
		}
		catch(NumberFormatException e1) {
			badSound();
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			badSound();
			fail(section, e1.toString());
		}
	}
	
	
	
	public void addDelivery(ActionEvent e)
	{
		String section = "Delivery";
		try {
			int id=Integer.parseInt(deliveryId.getText());// get id
			
			String delPeronAddDelivery= deliveryPerson.getValue(); // get the delivery person of the of the delivery we want to add
			
			boolean isDeliver=false;// default is false and if selected change to true
			
			isDeliver=isDelivered.isSelected();// create an option to choose if chef or not

//			if(control.addDeliveryFromGUI(id,firstName, LastName, localDate, selectedG,selectedN, isChef)) {
//				successAdded(section, "Success");
//				Restaurant.save(Input);				
//			}
//			//if could not add customer
//			else {
//				fail(section,"This id already exists in the customer database!");
//			}
			System.out.println("cooks: " + Restaurant.getInstance().getCooks());
			refreshGui();

		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(section, e1.toString());
//		}
//		catch(SimilarIDInSystemException e1) {
//			badSound();
//			fail(section,e1.toString());
//		}
		catch(NumberFormatException e1) {
			badSound();
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			badSound();
			fail(section, e1.toString());
		}
	}
	
	
	
	
	

	
	/**************Add an Order*************/
	public void addOrder(ActionEvent e)
	{
		String section = "Order";
		try {
			int id=Integer.parseInt(orderId.getText());// get id			
			int custForOrder=Integer.parseInt(customerForOrderId.getText());//get the customer's id after viewing the combo box	
			String deliveryInOrderForGui= deliveriesInOrder.getValue();
					
			if(control.addOrderFromGUI(id,custForOrder, dishesInOrderList, deliveryInOrderForGui)) {
				successAdded(section, "Success");
				Restaurant.save(Input);				//customerForOrderId
			}
			//if could not add customer
			else {
				fail(section,"This id already exists in the customer database!");
			}
			System.out.println("orders : " + Restaurant.getInstance().getOrders().values());
			refreshGui();

		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(SimilarIDInSystemException e1) {
			fail(section,e1.toString());
		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
	}
	
	
	
	public void removeOrder(ActionEvent e) {
		String section = "Order";
		try {
			int id = Integer.parseInt(orderIdToRemove.getText());
			
			if(control.removeOrderFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the orders database!");
			}
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}

	//working
	public void addLocation(ActionEvent e)
	{
//		String a = "Location";
//		try {
//			if(locationCountry.getText().equals("") || LocationCity.getText().equals(""))
//				throw new IllegelInputException();
//			control.addLocation(locationCountry.getText().toLowerCase(), LocationCity.getText().toLowerCase());
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			refreshGui();
//			//pop up with success
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		} catch (ObjectExistException e1) {
//			badSound();
//			fail(a, a+e1.toString());
//		} catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}

	

	
	
	public void removeGuide() {
		
	}
	
	
	//working
	public void addGuide(ActionEvent e)
	{
//		String a = "Guide";
//		try {
//			long id=Long.parseLong(guideId.getText());
//			String password=guidePass.getText();
//			String passwordVerify=guidePassVerify.getText();
//			String answer=guideQuestion.getText();
//			String firstName=guideFirst.getText();
//			String LastName=guideLast.getText();
//			LocalDate localDate =guideBirth.getValue();
//			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//			Date date = Date.from(instant);
//			String email=guideEmail.getText();
//			String country=guideCountry.getText();
//			String city=guideCity.getText();
//			String street=guideStreet.getText();
//			int houseNumber=Integer.parseInt(guideHouse.getText());
//			String phoneNumber=guidePhone.getText();
//			LocalDate localDateStart =startDate.getValue();
//			Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
//			Date dateStart = Date.from(instantStart);
//
//			control.addGuide(id,password,passwordVerify,answer,firstName,LastName,date,email,country,city,street,houseNumber,phoneNumber,dateStart,GuideExp);
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			GuideExp.removeAll(GuideExp);
//			refreshGui();
//
//			//pop up with success
//			//exception-Guide adding failed,Guide already exists/illegal input
//
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//			GuideExp.removeAll(GuideExp);
//		}
//		catch(PasswordMismatchException e1) {
//			badSound();
//			fail(a, e1.toString());
//			GuideExp.removeAll(GuideExp);
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, "Person"+e1.toString());
//			GuideExp.removeAll(GuideExp);
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//			GuideExp.removeAll(GuideExp);
//		}
//		catch(GuideExperienceMismatchException e1) {
//			badSound();
//			fail(a, e1.toString());
//			GuideExp.removeAll(GuideExp);
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//			GuideExp.removeAll(GuideExp);
//		}
	}
	public void addToExpList(ActionEvent e)
	{


	}
	public void ClearExpList(ActionEvent e)
	{

	}


	public void addFlight(ActionEvent e)
	{

	}
	//working
	public void addHotel(ActionEvent e)
	{

	}

	//working
	public void addMotel(ActionEvent e)
	{

	}


	//working
	public void addHostel(ActionEvent e)
	{

	}


	//working
	public void addGroupTrip(ActionEvent e)
	{

	}
	//working
	public void addGuideExp(ActionEvent e)
	{

	}


	public void addPackage(ActionEvent e)
	{

	}

	/**
	 * a method to show all the components in the dish
	 * @param e
	 */
	public void addComponentToList(ActionEvent e) {
		//dish can have several components
		
		componentsInDishList.add(componentsInDish.getValue());
		String list="";		
		for(int s : componentsInDishList) {
			list += s+"\n";
		}
		componentsList.setText(list);
	}
	
//	public void addComponentToList(ActionEvent e) {
//		//dish can have several components
//		
//		componentsInDishList.add(componentsInDish.getValue());
//		String list="";
//		for(int s : componentsInDishList) {
//			list += s+"\n";
//		}
//		componentsList.setText(list);
//	}
	
	public void addCDishesInOrderToList(ActionEvent e) {
		//order can have several dishes
		
		dishesInOrderList.add(dishesInOrder.getValue());
		String list="";
		for(String s : dishesInOrderList) {
			list += s+"\n";
		}
		dishesInOrderShow.setText(list);
	}
	
	
	public void addOrderToList(ActionEvent e) {
		//dish can have several components
		
		componentsInDishList.add(componentsInDish.getValue());
		String list="";
		for(int s : componentsInDishList) {
			list += s+"\n";
		}
		componentsList.setText(list);
	}
	
	
	public void addCDeliveriesInOrderToList(ActionEvent e) {
		//order can have several dishes
		
		DeliveriesInOrderList.add(deliveriesInOrder.getValue());
		String list="";
		for(String s : DeliveriesInOrderList) {
			list += s+"\n";
		}
		deliveriesInOrderShow.setText(list);
	}
	/**
	 * a method to clear the list of dishes in the list view
	 * @param e 
	 */
	public void clearDishesInOrderList(ActionEvent e) {
		dishesInOrderList.removeAll(dishesInOrderList);
		dishesInOrderShow.setText("");
	}
	
	public void clearDeliveriesInOrderList(ActionEvent e) {
		DeliveriesInOrderList.removeAll(dishesInOrderList);
		deliveriesInOrderShow.setText("");
	}
	
	public void clearComponentsInDishesList(ActionEvent e) {
		componentsInDishList.removeAll(componentsInDishList);
		componentsList.setText("");
	}
	
	/**
	 * a method to show all the neighborhoods in the delivery area
	 * @param e
	 */
	public void addHoodToAreaList(ActionEvent e) {
		if(!hoodsInDeliveryArea.contains(delAreaHoods.getValue())) {
			hoodsInDeliveryArea.add(delAreaHoods.getValue());
		}
		String list="";
		for(String s : hoodsInDeliveryArea) {
			list += s+"\n";
		}
		hoodList.setText(list);
	}
	/**
	 * a method to clear the list of neighborhoods in the list view
	 * @param e
	 */
	public void clearHoodsInArea(ActionEvent e) {
		hoodsInDeliveryArea.removeAll(hoodsInDeliveryArea);
		hoodList.setText("");
	}
	
	
	public void addToPackageFlightList(ActionEvent e)
	{


	}
	public void ClearFlightList(ActionEvent e)
	{

	}

	public void addToAccommodationFlightList(ActionEvent e)
	{

	}
	public void ClearAccommodationList(ActionEvent e)
	{


	}

	public void addToPackageTripList(ActionEvent e)
	{


	}
	public void ClearTripList(ActionEvent e)
	{


	}
	



	public void showCitiesHotel(ActionEvent e)
	{

	}
	public void showCitiesHostel(ActionEvent e)
	{

	}
	public void showCitiesMotel(ActionEvent e)
	{

	}

	public void showCitiesTrip(ActionEvent e)
	{

	}
	public void showCitiesExperiencedDestinations(ActionEvent e)
	{

	}
	public void showFromCitiesFlight(ActionEvent e)
	{

	}
	public void showToCitiesFlight(ActionEvent e)
	{

	}

	public void uploadCertificate(ActionEvent e)
	{
//		FileChooser fc=new FileChooser();
//		fc.getExtensionFilters().add(new ExtensionFilter("PDF Files","*.pdf") );
//		File file=fc.showOpenDialog(null);
//
//
//		if(file!=null)
//		{
//			expCertificateLabel.setFont(new Font(20));
//			expCertificateLabel.setText("Destination Certificate Was Added!");	
//
//			File toFile = new File("./Certificates/"+file.getName());
//
//
//			try {
//
//				java.nio.file.Files.move( 
//						file.toPath(), 
//						toFile.toPath() ,StandardCopyOption.REPLACE_EXISTING);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//
//		}
//		else
//		{
//			expCertificateLabel.setFont(new Font(20));
//			expCertificateLabel.setText("Add Destination Certificate to procceed");	
//		}
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

	/*A method to refresh the GUI after adding to the database*/
	public void refreshGui()
	{     


		
		/**Resetting the Customer**/
		customerId.setText("");
		customerFirst.setText("");
		customerLast.setText("");		
		customerPass.setText("");
		customerPassVerify.setText("");
		customerLactose.setSelected(false);
		customerGluten.setSelected(false);
		delCustomerID.setText("");
		
	
		/**Resetting the Delivery Person**/
		delPersonId.setText("");		
		delPersonFName.setText("");
		delPersonLName.setText("");
		deleteDelPersonID.setText("");
		
		
		/**Reseting the Cook**/
		cookId.setText("");		
		cookFirstName.setText("");
		cookLastName.setText("");
		/*TODO cookDate reset*/
		isChef.setSelected(false);;
		cookIDtoDelete.setText("");

		
		/**Resetting the Component**/

		componentID.setText("");
		componentName.setText("");
		componentPrice.setText("");
		hasLactose.setSelected(false);
		hasGluten.setSelected(false);
		delComponentID.setText("");

		

		
//		/************WORKS*********/
//		ArrayList<String> neighberhoodsDB=new ArrayList<String>();
//		for(Neighberhood n : Neighberhood.values()) {
//			neighborhoods.add(n);
//			neighberhoodsDB.add(String.valueOf(n));
//		}
//
//		ObservableList<String> comboNeighborhoods=FXCollections.observableArrayList();
//		comboNeighborhoods.addAll(neighberhoodsDB);
//		customerHoodCombo.setItems(comboNeighborhoods);
//		
//		
//		/************WORKS*********/
//		ArrayList<String> gendersDB=new ArrayList<String>();
//		for(Gender g : Gender.values()) {
//			gendersDB.add(String.valueOf(g));
//		}
//
//		ObservableList<String> comboGenders=FXCollections.observableArrayList();
//		comboGenders.addAll(gendersDB);
//		customerGenderCombo.setItems(comboGenders);
//		/*********************/


/****************************************************************************/		
//		//resetting all the location textfields
//		locationCountry.setText("");
//		LocationCity.setText("");
//
//		//resetting all the guide textfields
//		guideListOfExpDestinations.setText("");
//		guideId.setText("");
//		guideFirst.setText("");
//		guideLast.setText("");
//		guideCity.setText("");
//		guideCountry.setText("");
//		guideEmail.setText("");
//		guideHouse.setText("");
//		guidePass.setText("");
//		guidePassVerify.setText("");
//		guidePhone.setText("");
//		guideQuestion.setText("");
//		guideStreet.setText("");
//		//resetting all the hotel textfields
//		hotelStars.setText("");
//		hotelId.setText("");
//		hotelName.setText("");
//		hotelPeoplePerRoom.setText("");
//		hotelPrice.setText("");
//		hotelRooms.setText("");
//		HotelBreakfast.setSelected(false);
//		hotelPool.setSelected(false);
//		//resetting all the motel textfields
//
//		motelId.setText("");
//		motelName.setText("");
//		motelPeoplePerRoom.setText("");
//		motelPricePerPerson.setText("");
//		motelNumOfRooms.setText("");
//		motelKitchen.setSelected(false);
//		motelWIFI.setSelected(false);
//		//resetting all the hostel textfields
//		hostelId.setText("");
//		hostelName.setText("");
//		hostelNumOfPeople.setText("");
//		hostelPricePerPerson.setText("");
//		hostelNumOfRooms.setText("");
//		//resetting all the grouptrip textfields
//		tripId.setText("");
//		tripDescription.setText("");
//		tripPeople.setText("");
//		tripPrice.setText("");
//		//resetting all the package textfields
//		packageName.setText("");
//		packageNumOfPeople.setText("");
//		packagePrice.setText("");
//		packageFlightListView.setText("");
//		packageAccommodationListView.setText("");
//		packagetTripsListView.setText("");
//		//resetting all the flight textfields
//		flightNum.setText("");
//		flightSeats.setText("");
//		flightPrice.setText("");





//
//
//		//resetting all the comboboxes
//		expCertificateLabel.setFont(new Font(20));
//		expCertificateLabel.setText("Add Destination Certificate to procceed");	
//		ObservableList<String> Hostellist=FXCollections.observableArrayList("Short","Long");
//		hostelType.setItems(Hostellist);
//
//		ObservableList<String> list=FXCollections.observableArrayList();
//		for(String country:Shared.getInstance().getDestinations().keySet())
//		{
//
//			ArrayList<Destination> dests=Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(new String(d.getCountry()+"_"+ d.getCity()));
//			}
//
//		}
//		guideDestinations.setItems(list);
//		ArrayList<String> tripsDB=new ArrayList<String>() ;
//		ArrayList<String> flightsDB=new ArrayList<String>(Shared.getInstance().getFlights().keySet());
//		ArrayList<String> AccommodationDB=new ArrayList<String>();
//		for(Long l:Shared.getInstance().getAccommodations().keySet())
//		{
//			AccommodationDB.add(String.valueOf(l));
//		}
//		for(Integer i:Shared.getInstance().getTrips().keySet())
//		{
//			tripsDB.add(String.valueOf(i));
//		}
//		ObservableList<Long> guideIdComboTrips=FXCollections.observableArrayList();
//		ArrayList<Long> guides =new ArrayList<Long>(Shared.getInstance().getGuides().keySet());
//		guideIdComboTrips.addAll(guides);
//		tripGuideId.setItems(guideIdComboTrips);
//		guideExpId.setItems(guideIdComboTrips);
//		ObservableList<String> comboTrips=FXCollections.observableArrayList();
//		ObservableList<String> comboFlights=FXCollections.observableArrayList();
//		ObservableList<String> comboAccommodations=FXCollections.observableArrayList();
//		comboTrips.addAll(tripsDB);
//		comboFlights.addAll(flightsDB);
//		comboAccommodations.addAll(AccommodationDB);
//		ArrayList<String> countyList=new ArrayList<String>(Shared.getInstance().getDestinations().keySet());
//		comboCountryList.addAll(countyList);
//		ObservableList<String> comboCountryList=FXCollections.observableArrayList();
//		hotelCountry.setItems(comboCountryList);
//		motelCountry.setItems(comboCountryList);
//		hostelCountry.setItems(comboCountryList);
//		flightToCountry.setItems(comboCountryList);
//		flightFromCountry.setItems(comboCountryList);
//		guideExpCountry.setItems(comboCountryList);
//		tripCountry.setItems(comboCountryList);
//		packageFlights.setItems(comboFlights);
//		packageAccommodations.setItems(comboAccommodations);
//		packageGroupTrip.setItems(comboTrips);
	}

}
