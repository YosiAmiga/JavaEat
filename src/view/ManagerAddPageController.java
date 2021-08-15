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

import Exceptions.EmptyTextFieldException;
import Exceptions.IllegalCustomerException;
import Exceptions.IllegelInputException;
import Exceptions.IllegelPasswordException;
import Exceptions.NoComponentsExceptions;
import Exceptions.PasswordMismatchException;
import Exceptions.SensitiveException;
import Exceptions.SimilarIDInSystemException;
import Exceptions.expressDeliveryMissMatchException;
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
	private ComboBox<String> componentsDelete;
	
	@FXML
	private Button addComponent;
	
	@FXML
	private Button delComponent;

	
	
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
	private ComboBox<String> componentsInDish;
	
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
	private Button removeDish;
	
	/**************************************Order Page*****************************************/
	
	@FXML
	private TextField orderId;
	
	@FXML
	private TextField customerForOrderId;
	
	@FXML
	private ComboBox<String> customersForOrder;
	
	@FXML
	private ComboBox<String> currentOrders;
	
	@FXML
	private ComboBox<String> dishesInOrder;// multiple choice 
	
	@FXML
	private ComboBox<String> deliveriesInOrder;
	@FXML
	private TextField deliveryIDToOrder;
	
	@FXML
	private Button addOrder;
	
	@FXML
	private Button addDishesInOrder;

	@FXML
	private Button addDeliveriesInOrder;
	
	@FXML
	private Button removeOrder;
	
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
	private TextField deliveryID;
	@FXML
	private ComboBox<String> deliveryPersonInDelivery;
	
	@FXML
	private ComboBox<String> deliveryAreaInDelivery;
	
	
	@FXML
	private ComboBox<String> ordersInDelivery;	

	@FXML
	private Button addOrderToList;
	@FXML
	private Button clearOrdersList;
	@FXML
	private TextArea ordersListInDelivery;

	@FXML
	private DatePicker deliveryDate;
	
	@FXML
	private CheckBox isExpress;
	@FXML
	private TextField customPostage;
	
	@FXML
	private CheckBox isDelivered;
	
	@FXML
	private Button addDelivery;
	
	@FXML
	private ComboBox<String> currentDelivery;
	@FXML
	private TextField delIDToRemove;
	@FXML
	private Button removeDelivery;
	
	
	/**************************************Delivery Area Page****************************************/

	@FXML
	private TextField delAreaID;
	@FXML
	private TextField delAreaName;
	@FXML
	private TextField delAreaTime;
	

	
	@FXML
	private ComboBox<String> delAreaHoods;
	@FXML
	private Button addHoodToArea;
	@FXML
	private Button clearHoods;
	@FXML
	private TextArea hoodList;
	

	
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
	ArrayList<String> componentsInDishToShow = new ArrayList<>();
	ArrayList<Integer> dishesInOrderList = new ArrayList<>();
	ArrayList<String> dishesInOrderText = new ArrayList<>();
	ArrayList<String> DeliveriesInOrderList = new ArrayList<>();
	HashSet<Integer> ordersListToDelivery = new HashSet<>();
	ArrayList<String> ordersListShow = new ArrayList<>();

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		/**************Load list of dishes in system*********/
		ArrayList<String> dishesDB = new ArrayList<>();
		for(Dish d :  Restaurant.getInstance().getDishes().values()) {
			dishesDB.add("ID: " + d.getId() + " Name: " + d.getDishName());
		}
		ObservableList<String> ObservableListDishes = FXCollections.observableArrayList();
		ObservableListDishes.addAll(dishesDB);
		dishIDToDelete.setItems(ObservableListDishes);		
		dishesInOrder.setItems(ObservableListDishes);
		
		/************load list of orders in the system **************/
		ArrayList<String> ordersDB = new ArrayList<>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			ordersDB.add(o.toString());
		}
		ObservableList<String> ObservableListOrders = FXCollections.observableArrayList();
		ObservableListOrders.addAll(ordersDB);
		currentOrders.setItems(ObservableListOrders);
		ordersInDelivery.setItems(ObservableListOrders);

		
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
			delPersonDB.add("ID: "+ dp.getId() + " Name: "+dp.getFirstName() + " " + dp.getLastName());
		}
		ObservableList<String> ObservableListDelPersons = FXCollections.observableArrayList();
		ObservableListDelPersons.addAll(delPersonDB);
		delPersonDelete.setItems(ObservableListDelPersons);
		deliveryPersonInDelivery.setItems(ObservableListDelPersons);

		/***************Load list of delivery areas in system*********************/
		ArrayList<String> areasDB = new ArrayList<>();
		ArrayList<String> areasDB2 = new ArrayList<>();
		for(DeliveryArea da :  Restaurant.getInstance().getAreas().values()) {
			areasDB.add(da.getAreaName());
		}
		ObservableList<String> comboAreas = FXCollections.observableArrayList();
		comboAreas.addAll(areasDB);
		delPersonArea.setItems(comboAreas);
		deliveryAreaInDelivery.setItems(comboAreas);
		
		ObservableList<String> comboAreas2 = FXCollections.observableArrayList();
		for(DeliveryArea da :  Restaurant.getInstance().getAreas().values()) {
			areasDB2.add("ID:" + da.getId() + " Name: "+da.getAreaName());
		}
		comboAreas2.addAll(areasDB2);
		deliveryAreasByID.setItems(comboAreas2);
		
		/***************Load list of components in the system***************/
		//will be only the id of the component
		ArrayList<String> componentsDB = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB.add("ID: "+c.getId() + " Name: " + c.getComponentName());
		}
		ObservableList<String> ObservableListComponents = FXCollections.observableArrayList();
		ObservableListComponents.addAll(componentsDB);
		componentsInDish.setItems(ObservableListComponents);
		
		ArrayList<String> componentsDB2 = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB2.add("ID:" + c.getId() + " Name: " + c.getComponentName());
		}
		ObservableList<String> ObservableListComponents2 = FXCollections.observableArrayList();
		ObservableListComponents2.addAll(componentsDB2);
		componentsDelete.setItems(ObservableListComponents2);
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
		currentDelivery.setItems(ObservableListDeliveries);
	}
	
	/**************************************Methods*****************************************/
	/**************************************Customer Methods*****************************************/

	
	/**************Delete a customer*************/
	public void delCustomer(ActionEvent e) {
		String section = "Customer";
		try {
			//Save the data from the current customer combo box
			String str = customerDelete.getValue();
			//Extract only the customer ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);

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
				fail(section,"Could not add customer to system!");
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
			fail(section, e1.toString());
		}
		catch(PasswordMismatchException e1) {
			fail(section, e1.toString());
		}
		catch(IllegelPasswordException e1) {
			fail(section,e1.toString());
		}
		catch(IllegalCustomerException e1) {
			fail(section,e1.toString());
		}
		catch(SimilarIDInSystemException e1) {
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
	
	/**************************************Delivery Person Methods****************************************/

	
	/**************Remove a Delivery Person*******/
	public void removeDeliveryPerson(ActionEvent e) {
		String section = "Delivery Person";
		try {
			//Save the data from the current Delivery Person combo box
			String str = delPersonDelete.getValue();
			//Extract only the Delivery Person ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);

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
	
	
	/**************************************Cook Methods****************************************/
	
	/**************Remove a Cook*************/
	public void removeCook(ActionEvent e) {
		String section = "Cook";
		try {
			//Save the data from the current Cook combo box
			String str = cooksInSys.getValue();
			//Extract only the Cook ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);
			if(control.removeCookFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			//if the id is not in the cook database, we can't delete it
			else {
				fail(section,"This id does not exists in the cooks database!");
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
			//if could not add cook
			else {
				fail(section,"This id already exists in the cooks database!");
			}
			System.out.println("cooks: " + Restaurant.getInstance().getCooks());
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
	/**************************************Component Methods****************************************/

	
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
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			failUpdate(section, e1.toString());
		}
		refreshGui();
	}
	
	/**************Delete a component*************/
	public void removeComponent(ActionEvent e) {
		String section = "Component";
		try {
			String str = componentsDelete.getValue();
			//Extract only the component ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);
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
		}

		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		refreshGui();
	}
	
	/**************Add a component***************/
	public void addComponent(ActionEvent e) {
		String section = "Component";
		try {
			int id = Integer.parseInt(componentID.getText());
			String cName = componentName.getText();
			boolean lactose = hasLactose.isSelected();
			boolean gluten = hasGluten.isSelected();
			double price = Double.parseDouble(componentPrice.getText());
			if(control.addComponentGUI(id, cName, lactose, gluten, price)) {
				successAdded(section,"Success");
				System.out.println(Restaurant.getInstance().getComponenets());
				Restaurant.save(Input);				
			}
			else {
				fail(section,"This id already exists in the components database!");
			}
								
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}

		refreshGui();
	}
	
	
	
	
	/**************************************Dish Methods****************************************/
	
	/*************Remove a Dish**********/
	public void removeDish(ActionEvent e) {
		String section = "Dish";
		try {
			String str = dishIDToDelete.getValue();
			//Extract only the Dish ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);
			if(control.removeDishFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the dishes database!");
			}
			
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
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
			String str = componentsInDish.getValue();
			String numberOnly= str.replaceAll("[^0-9]", "");
			System.out.println(numberOnly);
			System.out.println("-----------------------");
			if(control.addDishFromGUI(id, dName,selectedD, componentsInDishList, toMake)) {
				successAdded(section,"Success");
				Restaurant.save(Input);	
			}
			else {
				fail(section, "This id already exists in the dishes database!");
			}			
			System.out.println(Restaurant.getInstance().getDishes().values());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}
	/**************************************Order Methods****************************************/
	
	/**************Add an Order*************/
	public void addOrder(ActionEvent e)
	{
		String section = "Order";
		try {
			int id=Integer.parseInt(orderId.getText());// get id			
			
			String custID = customersForOrder.getValue();
			//Extract only the Order ID in order to remove him
			String numberOnlyCustomer= custID.replaceAll("[^0-9]", "");	
			int custForOrder=Integer.parseInt(numberOnlyCustomer);//get the customer's id after viewing the combo box	
			
//			int deliveryID = Integer.parseInt(deliveryIDToOrder.getText());		
			
			if(control.addOrderFromGUI(id,custForOrder, dishesInOrderList)) {
				successAdded(section, "Success");
				Restaurant.save(Input);		
			}
			//if could not add customer
			else {
				fail(section,"This id already exists in the orders database!");
			}
			System.out.println("orders : " + Restaurant.getInstance().getOrders().values());
			refreshGui();

		}
		catch(SensitiveException e1) {
			fail(section, e1.toString());
		}
		catch(IllegalCustomerException e1) {
			fail(section, e1.toString());
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
	
	
	/**************Remove an Order*************/

	public void removeOrder(ActionEvent e) {
		String section = "Order";
		try {
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
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}


	/**************************************Delivery Methods****************************************/
	public void removeDelivery(ActionEvent e) {
		String section = "Delivery";
		try {
			
			int id = Integer.parseInt(delIDToRemove.getText());
			if(control.removeDeliveryFromGUI(id)) {
				successAdded(section, "Success");
				Restaurant.save(Input);	
			}
			
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
			e1.printStackTrace();
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		
	
	}
	/**********Add a delivery********/
	public void addDelivery(ActionEvent e){
		String section = "Delivery";
		try {
			int id = Integer.parseInt(deliveryID.getText());
			
			String delPerId = deliveryPersonInDelivery.getValue();
			//Extract only the Order ID in order to remove him
			String numberOnlyDelPer= delPerId.replaceAll("[^0-9]", "");	
			
			int dpID = Integer.parseInt(numberOnlyDelPer);
			
			System.out.println(dpID);
			String dArea = deliveryAreaInDelivery.getValue();	
			
			LocalDate delDate = deliveryDate.getValue();
			boolean isSent = isDelivered.isSelected();
			boolean isEXP = isExpress.isSelected();
			//if the combo box of express delivery is selected, then use the express delivery method
			if(isEXP) {
				double custPost = Double.parseDouble(customPostage.getText());
				System.out.println(custPost);
				if(custPost == 0) {				
					if(control.addDeliveryFromGUI(id, dpID, dArea, isSent, delDate, isEXP, 5, ordersListToDelivery)) {
						successAdded(section, "Success");
						Restaurant.save(Input);	
					}
					else {
						fail(section,"This id already exists in the deliveries database!");
					}
					
				}
				else {				
					if(control.addDeliveryFromGUI(id, dpID, dArea, isSent, delDate, isEXP, custPost, ordersListToDelivery)) {
						successAdded(section, "Success");
						Restaurant.save(Input);	
					}
					else {
						fail(section,"This id already exists in the deliveries database!");
					}				
				}
			}
			//if the combo box of express delivery is NOT selected, then use the regular delivery method
			else {
				if(control.addRegularDeliveryFromGUI(id, dpID, dArea, isSent, delDate, ordersListToDelivery)) {
					successAdded(section, "Success");
					Restaurant.save(Input);	
				}
				else {
					fail(section,"This id already exists in the deliveries database!");
				}
				
			}
			System.out.println(Restaurant.getInstance().getDeliveries().values());
			refreshGui();

		}
		catch(expressDeliveryMissMatchException e1) {
			fail(section, e1.toString());
		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
			e1.printStackTrace();
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		
	}
	/**************************************Delivery Area Methods****************************************/
	
	/**************Add a Delivery Area************/
	public void addDeliveryArea(ActionEvent e) {

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
				fail(section, "This id already exists in the delivery areas database!");
			}
			
			System.out.println(Restaurant.getInstance().getAreas().values());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			fail(section, e1.toString());
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
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			System.out.println("hey in del area");
		}
		refreshGui();
	}
	/**************************************Blacklist Methods****************************************/
	

	/*************Add to Blacklist*******/
	public void addCustomerToBlacklist(ActionEvent e) {
		String section = "Customer";
		try {
			
			String cutomerToBlacklist = customerList.getValue();
			//Extract only the Order ID in order to remove him
			String numberOnlyCustomer= cutomerToBlacklist.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnlyCustomer);
			if(control.addToBlacklistFromGUI(id)) {
				successAdded(section,"Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the customers database!");
			}
			
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(Restaurant.getInstance().getBlackList());
		refreshGui();
	}
	
	/****************************Add and Clear methods*******************************************/
	
	public void clearOrdersInDelivery(ActionEvent e) {
		ordersListShow.removeAll(ordersListShow);
		ordersListToDelivery.removeAll(ordersListToDelivery);
		ordersListInDelivery.setText("");
	}
	
	public void addOrderIDToDelivery(ActionEvent e) {
		String str = ordersInDelivery.getValue();
		if(!ordersListShow.contains(str)){
			ordersListShow.add(str);		
		}
		
		//Extract only the component ID in order to add him to the dish
		String numberOnly= str.replaceAll("[^0-9]", "");		
		ordersListToDelivery.add(Integer.parseInt(numberOnly));
		System.out.println(ordersListToDelivery);
		String list="";		
		for(String i : ordersListShow) {
			list += i+"\n";
		}
		ordersListInDelivery.setText(list);
	}

	/**
	 * a method to show all the components in the dish
	 */
	public void addComponentToList(ActionEvent e) {
		//dish can have several components
		//show all the components value in the text area
		String str = componentsInDish.getValue();
		componentsInDishToShow.add(str);
		
		//Extract only the component ID in order to add him to the dish
		String numberOnly= str.replaceAll("[^0-9]", "");		
		componentsInDishList.add(Integer.parseInt(numberOnly));
		String list="";		
		for(String s : componentsInDishToShow) {
			list += s+"\n";
		}
		componentsList.setText(list);			
		
	}
	

	
	public void addCDishesInOrderToList(ActionEvent e) {
		//order can have several dishes
		//show all the components value in the text area
		String str = dishesInOrder.getValue();
		dishesInOrderText.add(str);
		
		//Extract only the component ID in order to add him to the dish
		String numberOnly= str.replaceAll("[^0-9]", "");	
		dishesInOrderList.add(Integer.parseInt(numberOnly));
		String list="";
		for(String s : dishesInOrderText) {
			list += s+"\n";
		}
		dishesInOrderShow.setText(list);
	}

	//TODO FIX!!
	
	
	
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
		dishesInOrderText.removeAll(dishesInOrderText);
		dishesInOrderShow.setText("");
	}
	
	public void clearDeliveriesInOrderList(ActionEvent e) {
//		DeliveriesInOrderList.removeAll(dishesInOrderList);
		deliveriesInOrderShow.setText("");
	}
	
	public void clearComponentsInDishesList(ActionEvent e) {
		componentsInDishToShow.removeAll(componentsInDishToShow);
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


	public void uploadCertificate(ActionEvent e)
	{

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
	public void refreshGui(){     


		
		/**Resetting the Customer**/
		customerId.setText("");
		customerFirst.setText("");
		customerLast.setText("");		
		customerPass.setText("");
		customerPassVerify.setText("");
		customerLactose.setSelected(false);
		customerGluten.setSelected(false);
		
	
		/**Resetting the Delivery Person**/
		delPersonId.setText("");		
		delPersonFName.setText("");
		delPersonLName.setText("");
		
		
		/**Reseting the Cook**/
		cookId.setText("");		
		cookFirstName.setText("");
		cookLastName.setText("");
		cookExpertise.setPromptText("Expertise");
		cookGender.setPromptText("Gender");
		cooksInSys.setPromptText("Current Cook");
		/*TODO cookDate reset*/
		isChef.setSelected(false);;

		
		/**Resetting the Component**/
		componentID.setText("");
		componentName.setText("");
		componentPrice.setText("");
		hasLactose.setSelected(false);
		hasGluten.setSelected(false);

		
		/**Resetting the Dish**/
		dishId.setText("");
		dishName.setText("");
		timeToMake.setText("");
		componentsList.setText("");
		//TODO addComponentToList clear the TextArea componentsList

		
		/**Resetting the Order**/
		orderId.setText("");
		customerForOrderId.setText("");
		dishesInOrderShow.setText("");
//		deliveriesInOrderShow.setText("");
		
		
		/**Resetting the Delivery**/
		deliveryID.setText("");
		dishesInOrderShow.setText("");
		isExpress.setSelected(false);
		isDelivered.setSelected(false);
		ordersListInDelivery.setText("");
		customPostage.setText("");
		
		
		/**Resetting the Delivery Area**/
		delAreaID.setText("");
		delAreaName.setText("");
		delAreaTime.setText("");
		hoodList.setText("");
		oldAreaID.setText("");
		newAreaID.setText("");

		
		/**Resetting the Blacklist**/

		/****************************************************************************/		
		/************************Updating all the ComboBox**************************/
		/****************************************************************************/		

		/**************Load list of dishes in system*********/
		ArrayList<String> dishesDB = new ArrayList<>();
		for(Dish d :  Restaurant.getInstance().getDishes().values()) {
			dishesDB.add("ID: " + d.getId() + " Name: " + d.getDishName());
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
		ordersInDelivery.setItems(ObservableListOrders);

		
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
			delPersonDB.add("ID: "+ dp.getId() + " Name: "+dp.getFirstName() + " " + dp.getLastName());
		}
		ObservableList<String> ObservableListDelPersons = FXCollections.observableArrayList();
		ObservableListDelPersons.addAll(delPersonDB);
		delPersonDelete.setItems(ObservableListDelPersons);
		deliveryPersonInDelivery.setItems(ObservableListDelPersons);
		
		/***************Load list of delivery areas in system*********************/
		ArrayList<String> areasDB = new ArrayList<>();
		ArrayList<String> areasDB2 = new ArrayList<>();
		for(DeliveryArea da :  Restaurant.getInstance().getAreas().values()) {
			areasDB.add(da.getAreaName());
		}
		ObservableList<String> comboAreas = FXCollections.observableArrayList();
		comboAreas.addAll(areasDB);
		delPersonArea.setItems(comboAreas);
		deliveryAreaInDelivery.setItems(comboAreas);

		
		ObservableList<String> comboAreas2 = FXCollections.observableArrayList();
		for(DeliveryArea da :  Restaurant.getInstance().getAreas().values()) {
			areasDB2.add("ID:" + da.getId() + " Name: "+da.getAreaName());
		}
		comboAreas2.addAll(areasDB2);
		deliveryAreasByID.setItems(comboAreas2);
		
		/***************Load list of components in the system***************/
		ArrayList<String> componentsDB = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB.add("ID: "+c.getId() + " Name: " + c.getComponentName());
		}
		ObservableList<String> ObservableListComponents = FXCollections.observableArrayList();
		ObservableListComponents.addAll(componentsDB);
		componentsInDish.setItems(ObservableListComponents);
		
		ArrayList<String> componentsDB2 = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB2.add("ID:" + c.getId() + " Name: " + c.getComponentName());
		}
		ObservableList<String> ObservableListComponents2 = FXCollections.observableArrayList();
		ObservableListComponents2.addAll(componentsDB2);
		componentsDelete.setItems(ObservableListComponents2);
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

		/*********************Load Deliveries********************************/
		ArrayList<String> deliveriesDb = new ArrayList<>();
		
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			deliveriesDb.add(String.valueOf(d));
		}
		ObservableList<String> ObservableListDeliveries=FXCollections.observableArrayList();
		ObservableListDeliveries.addAll(deliveriesDb);
		deliveriesInOrder.setItems(ObservableListDeliveries);
		currentDelivery.setItems(ObservableListDeliveries);

/****************************************************************************/		

	}

}
