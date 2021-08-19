package view;



import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Exceptions.EmptyComboBoxException;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import Model.*;

public class ManagerAddPageController implements Initializable {
	private static final String Input = "Rest.ser";
	
	/*The controller to add and remove everything from the GUI to the database*/
	PrimaryController control=new PrimaryController();
	
	///***************////////
	@FXML
	private ImageView customerProfile;
	@FXML
	private Button customerAddPic;



	////*************//////////
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
	private Button fillDataCust;
	@FXML
	private Button updateCustomer;
	
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
	private Button fiilDataDelPerson;
	@FXML
	private Button updateDelPerson;
	

	
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
	
	@FXML
	private Button fillDataCook;
	@FXML
	private Button updateCook;

	
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
	private Button componentFillData;
	
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
	
	@FXML
	private Button fillDataDish;
	@FXML
	private Button updateDish;
	
	/**************************************Order Page*****************************************/
	
	@FXML
	private TextField orderId;

	
	@FXML
	private ComboBox<String> customersForOrder;
	
	@FXML
	private ComboBox<String> currentOrders;
	
	@FXML
	private ComboBox<String> dishesInOrder;// multiple choice 
	

	
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
	
	@FXML
	private Button fillDataOrder;
	@FXML
	private Button updateOrder;
	
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

	@FXML
	private Button fillDataDeliveryArea;
	@FXML
	private Button updateDeliveryArea;

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
			dishesDB.add("Dish ID: " + d.getId() + " Dish Name: " + d.getDishName());
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
			cooksDB.add("Cook ID: " + c.getId() + " Cook Name: " + c.getFirstName()+ " " +c.getLastName());
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
		ArrayList<String> customerNotInBLDB = new ArrayList<>();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			if(!Restaurant.getInstance().getBlackList().contains(c)) {
				customerNotInBLDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
			}
			customerDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> ObservableListCustomers = FXCollections.observableArrayList();
		ObservableListCustomers.addAll(customerDB);
		customerDelete.setItems(ObservableListCustomers);
		customersForOrder.setItems(ObservableListCustomers);
		
		ObservableList<String> ObservableListCustomersBL = FXCollections.observableArrayList();
		ObservableListCustomersBL.addAll(customerNotInBLDB);
		customerList.setItems(ObservableListCustomersBL);

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
//		deliveriesInOrder.setItems(ObservableListDeliveries);
		currentDelivery.setItems(ObservableListDeliveries);
	}
	
	/**************************************Methods*****************************************/
	/**************************************Customer Methods*****************************************/

	/*************Fill data for update************/
	public void fillDataCustomer(ActionEvent e) {
		try {
			if(customerId.getText().isBlank()) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(customerId.getText());
			
			Customer temp = Restaurant.getInstance().getRealCustomer(id);
			
			customerFirst.setText(temp.getFirstName());
			customerLast.setText(temp.getLastName());
			customerBirth.setValue(temp.getBirthDay());
			customerPass.setText(String.valueOf(temp.getPassword()));
			customerPassVerify.setText(String.valueOf(temp.getPassword()));
			customerGenderCombo.setValue(String.valueOf(temp.getGender()));
			customerHoodCombo.setValue(String.valueOf(temp.getNeighberhood()));
			customerGluten.setSelected(temp.isSensitiveToGluten());
			customerLactose.setSelected(temp.isSensitiveToLactose());
			
		}
		catch(IllegelInputException e1) {
			fail("No update ", e1.toString());
		}
	}
	
	/**************Update a Customer*************/
	public void updateCustomer(ActionEvent e) {
		String section = "Customer";
		try {
			//check if the combo box were selected
			if(customerGenderCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(customerHoodCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(customerBirth.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
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

			//if no new id, use the same id to be updated as the "new id"
			if(control.updateCustomerGUI(id,firstName, LastName, localDate, selectedG, password, passwordVerify, selectedN, lactose, gluten)) {
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
	
	/**************Delete a customer*************/
	public void removeCustomer(ActionEvent e) {
		String section = "Customer";
		try {
			if(customerDelete.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			//Save the data from the current customer combo box
			String str = customerDelete.getValue();
			//Extract only the customer ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);

			if(control.removeCustomerGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			//if the id is not in the customer database, we can't delete it
			else {
				fail(section,"This id does not exists in the customer database!");
			}
			System.out.println(Restaurant.getInstance().getCustomers());
			
		}		
		catch(EmptyComboBoxException e1) {
			failSelection("Customer to delete",e1.toString());
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		refreshGui();
	}

	/**************Add a customer*************/
	//working
	public void addCustomer(ActionEvent e){
		String section = "Customer";
		try {
			//check if the combo box were selected
			if(customerGenderCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(customerHoodCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(customerBirth.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
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
		catch(EmptyComboBoxException e1) {
			failSelection("Gender/Neighborhood",e1.toString());
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
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			badSound();
			fail(section, e1.toString());
		}
	}
	
	/**************************************Delivery Person Methods****************************************/

	/*************Fill data for update************/
	public void fillDataDeliveryPerson(ActionEvent e) {
		try {
			if(delPersonId.getText().isBlank()) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(delPersonId.getText());
			
			DeliveryPerson temp = Restaurant.getInstance().getRealDeliveryPerson(id);
			
			delPersonFName.setText(temp.getFirstName());
			delPersonLName.setText(temp.getLastName());
			delPersonBirth.setValue(temp.getBirthDay());
			genderCombo.setValue(String.valueOf(temp.getGender()));
			delPersonVehicle.setValue(String.valueOf(temp.getVehicle()));
			delPersonArea.setValue(String.valueOf(temp.getArea()));

			
		}
		catch(IllegelInputException e1) {
			fail("No id to fill data ", e1.toString());
		}
	}
	/**************Update a Delivery Person*************/
	public void updateDeliveryPerson(ActionEvent e) {
		String section = "Delivery Person";
		try {
			if(genderCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(delPersonVehicle.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(delPersonArea.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(delPersonBirth.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
			//if no new id, use the same id to be updated as the "new id"
			if(control.updateDeliveryPersonGUI(id, fName, lName, localDate, selectedG, selectedV,dArea)) {
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
		successUpdate(section, "Success");
		Restaurant.save(Input);
		refreshGui();
	}
	/**************Remove a Delivery Person*******/
	public void removeDeliveryPerson(ActionEvent e) {
		String section = "Delivery Person";
		try {
			if(delPersonDelete.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
			
		}
		catch(EmptyComboBoxException e1) {
			failSelection("Delivery Person to delete",e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**************Add a Delivery Person**********/
	public void addDeliveryPerson(ActionEvent e) {

		String section = "Delivery Person";
		try {
			if(genderCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(delPersonVehicle.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(delPersonArea.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(delPersonBirth.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
								
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshGui();
	}
	
	
	/**************************************Cook Methods****************************************/
	/*************Fill data for update************/
	public void fillDataCook(ActionEvent e) {
		try {
			if(cookId.getText().isBlank()) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(cookId.getText());
			
			Cook temp = Restaurant.getInstance().getRealCook(id);
			
			cookFirstName.setText(temp.getFirstName());
			cookLastName.setText(temp.getLastName());
			cookDate.setValue(temp.getBirthDay());
			cookExpertise.setValue(String.valueOf(temp.getExpert()));
			cookGender.setValue(String.valueOf(temp.getGender()));
			isChef.setSelected(temp.isChef());

			
		}
		catch(IllegelInputException e1) {
			fail("No id to fill data", e1.toString());
		}
	}
	
	/**************Update a Customer*************/
	public void updateCook(ActionEvent e) {
		String section = "Cook";
		try {
			if(cookDate.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(cookGender.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(cookExpertise.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
			boolean isCookChef=isChef.isSelected();// create an option to choose if chef or not
			
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

			//if no new id, use the same id to be updated as the "new id"
			if(control.updateCookGUI(id,firstName, LastName, localDate, selectedG,selectedN, isCookChef)) {
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
	
	
	/**************Remove a Cook*************/
	public void removeCook(ActionEvent e) {
		String section = "Cook";
		try {
			if(cooksInSys.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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
			if(cookDate.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(cookGender.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(cookExpertise.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
			boolean isCookChef=isChef.isSelected();// create an option to choose if chef or not
			
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

			if(control.addCookFromGUI(id,firstName, LastName, localDate, selectedG,selectedN, isCookChef)) {
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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

	/*************Fill data for update************/
	public void fillData(ActionEvent e) {
		try {
			if(componentID.getText() == null) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(componentID.getText());
			
			Component temp = Restaurant.getInstance().getRealComponent(id);
			
			componentName.setText(temp.getComponentName());
			hasLactose.setSelected(temp.isHasLactose());
			hasGluten.setSelected(temp.isHasGluten());
			componentPrice.setText(String.valueOf(temp.getPrice()));
			
		}
		catch(IllegelInputException e1) {
			fail("No update ", e1.toString());
		}
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
			if(componentsDelete.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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
	/*************Fill data for update************/
	public void fillDataDish(ActionEvent e) {
		try {
			if(dishId.getText().isBlank()) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(dishId.getText());
			
			Dish temp = Restaurant.getInstance().getRealDish(id);
			dishName.setText(temp.getDishName());
			timeToMake.setText(String.valueOf(temp.getTimeToMake()));
			TypeOfTheDish.setValue(String.valueOf(temp.getType()));
//			
//			componentsInDishList.removeAll(componentsInDishList);
//			componentsInDishToShow.removeAll(componentsInDishToShow);

			for(Component c : temp.getComponenets()) {
				
				String str = "ID: "+c.getId() + " Name: " + c.getComponentName();
				componentsInDishToShow.add(str);
//				String numberOnly= str.replaceAll("[^0-9]", "");		
//				componentsInDishList.add(Integer.parseInt(numberOnly));
			}

			String list="";		
			for(String s : componentsInDishToShow) {
				list += s+"\n";
			}
			componentsList.setText(list);	

			
		}
		catch(IllegelInputException e1) {
			fail("No id to fill data ", e1.toString());
		}
	}
	
	/**************Update a Dish*************/
	public void updateDish(ActionEvent e) {
		String section = "Dish";
		try {
			if(TypeOfTheDish.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(componentsInDishList.isEmpty()) {
				throw new NoComponentsExceptions();
			}
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


			if(control.updateDishGUI(id, dName,selectedD, componentsInDishList, toMake)) {
				successUpdate(section, "Success");
				Restaurant.save(Input);
			}
		}
		catch(NoComponentsExceptions e1) {
			failSelection(section,e1.toString());
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			failUpdate(section, e1.toString());
		}
		refreshGui();
	}
	
	
	/*************Remove a Dish**********/
	public void removeDish(ActionEvent e) {
		String section = "Dish";
		try {
			if(dishIDToDelete.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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
			if(TypeOfTheDish.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(componentsInDish.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
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
		refreshGui();
	}
	/**************************************Order Methods****************************************/
	
	/*************Fill data for update************/
	public void fillDataOrder(ActionEvent e) {
		String section = "Order";

		try {
			if(orderId.getText().isBlank()) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(orderId.getText());
			
			Order temp =  Restaurant.getInstance().getRealOrder(id);
			int customerID= temp.getCustomer().getId();
			Customer customerOrder = Restaurant.getInstance().getRealCustomer(customerID);		
			customersForOrder.setValue("ID: " + customerOrder.getId() + " Name: " + customerOrder.getFirstName()+ " " +customerOrder.getLastName());

			ArrayList<String> dishesInOrder = new ArrayList<>();
			for(Dish d : temp.getDishes()) {
				String str = "ID: " + d.getId() + " Name: " + d.getDishName();
				dishesInOrder.add(str);
			}
			String list="";		
			for(String s : dishesInOrder) {
				list += s+"\n";
			}
			dishesInOrderShow.setText(list);
			
			
		}
		catch(IllegelInputException e1) {
			fail("No id to fill data ", e1.toString());
		}
	}
	
	/**************Update a Order*************/
	public void updateOrder(ActionEvent e) {
		String section = "Order";
		try {
			if(customersForOrder.getValue() == null) {
				throw new EmptyComboBoxException();
			}

			int id=Integer.parseInt(orderId.getText());// get id			
			
			String custID = customersForOrder.getValue();
			//Extract only the Order ID in order to remove him
			String numberOnlyCustomer= custID.replaceAll("[^0-9]", "");	
			int custForOrder=Integer.parseInt(numberOnlyCustomer);//get the customer's id after viewing the combo box	
			
			if(control.updateOrderGUI(id,custForOrder, dishesInOrderList)) {
				successUpdate(section, "Success");
				Restaurant.save(Input);
			}
		}

		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			failUpdate(section, e1.toString());
		}
		refreshGui();
	}
	
	/**************Add an Order*************/
	public void addOrder(ActionEvent e)
	{
		String section = "Order";
		try {
			if(customersForOrder.getValue() == null) {
				throw new EmptyComboBoxException();
			}

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
			for(Order or : Restaurant.getInstance().getOrders().values()) {
				System.out.println(or.getDelivery());
			}
			refreshGui();

		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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
		refreshGui();
	}


	/**************************************Delivery Methods****************************************/
	public void removeDelivery(ActionEvent e) {
		String section = "Delivery";
		try {
			if(delIDToRemove.getText() == null) {
				throw new EmptyTextFieldException();
			}
			int id = Integer.parseInt(delIDToRemove.getText());
			if(control.removeDeliveryFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);	
			}
			
		}
		catch(EmptyTextFieldException e1) {
			fail(section, e1.toString());
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
			if(deliveryPersonInDelivery.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(deliveryAreaInDelivery.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(deliveryDate.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String delPerId = deliveryPersonInDelivery.getValue();
			//Extract only the delivery person ID 
			String numberOnlyDelPer= delPerId.replaceAll("[^0-9]", "");	
			
			int dpID = Integer.parseInt(numberOnlyDelPer);
			
			String dArea = deliveryAreaInDelivery.getValue();	
			
			LocalDate delDate = deliveryDate.getValue();
			boolean isSent = isDelivered.isSelected();
			boolean isEXP = isExpress.isSelected();
			//if the combo box of express delivery is selected, then use the express delivery method
			if(isEXP) {

				//if no new postage was entered, use default 5.0
				if(customPostage.getText() != null) {				
					if(control.addDeliveryFromGUI(id, dpID, dArea, isSent, delDate, isEXP, 5, ordersListToDelivery)) {
						successAdded(section, "Success");
						Restaurant.save(Input);	
					}
					else {
						fail(section,"This id already exists in the deliveries database!");
					}
					
				}
				else {	
					double custPost = Double.parseDouble(customPostage.getText());
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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
	
	/*************Fill data for update************/
	public void fillDataDeliveryArea(ActionEvent e) {
		String section = "Delivery Area";

		try {
			if(delAreaID.getText().isBlank()) {
				throw new IllegelInputException();
			}
			int id = Integer.parseInt(delAreaID.getText());
			
			DeliveryArea temp = Restaurant.getInstance().getRealDeliveryArea(id);
			delAreaName.setText(temp.getAreaName());
			delAreaTime.setText(String.valueOf(temp.getDeliverTime()));
			hoodsInDeliveryArea.removeAll(hoodsInDeliveryArea);
			for(Neighberhood n : temp.getNeighberhoods()) {
				hoodsInDeliveryArea.add(String.valueOf(n));
			}
			String list="";
			for(String s : hoodsInDeliveryArea) {
				list += s+"\n";
			}
			hoodList.setText(list);

			
		}
		catch(IllegelInputException e1) {
			fail("No id to fill data ", e1.toString());
		}
	}
	
	/**************Update a Delivery Area*************/
	//TODO FIX!!
	public void updateDeliveryArea(ActionEvent e) {
		String section = "Delivery Area";
		try {
			int id = Integer.parseInt(delAreaID.getText());
			String aName = delAreaName.getText();
			int deliveryTime = Integer.parseInt(delAreaTime.getText());
			
			if(control.updateDeliveryAreaGUI(id, aName, hoodsInDeliveryArea, deliveryTime)) {
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
		successUpdate(section, "Success");
		Restaurant.save(Input);
		refreshGui();
	}
	
	/**************Add a Delivery Area************/
	public void addDeliveryArea(ActionEvent e) {

		String section = "Delivery Area";
		try {
			int id = Integer.parseInt(delAreaID.getText());
			String aName = delAreaName.getText();
			int deliveryTime = Integer.parseInt(delAreaTime.getText());
			
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
			e1.printStackTrace();
		}
		refreshGui();
	}
	/**************************************Blacklist Methods****************************************/
	

	/*************Add to Blacklist*******/
	public void addCustomerToBlacklist(ActionEvent e) {
		String section = "Customer";
		try {
			if(customerList.getValue() == null) {
				throw new EmptyComboBoxException();
			}

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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
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
		String section = "Order";
		try {
			if(ordersInDelivery.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
	}

	/**
	 * a method to show all the components in the dish
	 */
	public void addComponentToList(ActionEvent e) {
		//dish can have several components
		String section = "Component";
		try {
			
			//show all the components value in the text area
			if(componentsInDish.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		
	}
	

	/**
	 * a method to show all the dishes in the order
	 */
	public void addCDishesInOrderToList(ActionEvent e) {
		String section = "Dishes";
		//order can have several dishes
		try {
			
			//show all the components value in the text area
			if(dishesInOrder.getValue() == null) {
				throw new EmptyComboBoxException();
			}
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
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
	}


	
	

	/**
	 * a method to clear the list of dishes in the list view
	 * @param e 
	 */
	public void clearDishesInOrderList(ActionEvent e) {
		dishesInOrderText.removeAll(dishesInOrderText);
		dishesInOrderList.removeAll(dishesInOrderList);
		dishesInOrderShow.setText("");
	}
	
	public void clearDeliveriesInOrderList(ActionEvent e) {
		DeliveriesInOrderList.removeAll(DeliveriesInOrderList);
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
		String section = "Neighborhood";
		try {
			
			if(delAreaHoods.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
			if(!hoodsInDeliveryArea.contains(delAreaHoods.getValue())) {
				hoodsInDeliveryArea.add(delAreaHoods.getValue());
			}
			String list="";
			for(String s : hoodsInDeliveryArea) {
				list += s+"\n";
			}
			hoodList.setText(list);
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
	}
	/**
	 * a method to clear the list of neighborhoods in the list view
	 * @param e
	 */
	public void clearHoodsInArea(ActionEvent e) {
		hoodsInDeliveryArea.removeAll(hoodsInDeliveryArea);
		hoodList.setText("");
	}


	/***************a method to save a photo*******************/
	public void uploadFile(ActionEvent e)
	{
		FileChooser fc=new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png") );
		File file= fc.showOpenDialog(null);


		if(file!=null)
		{


			successUpload();
			File toFile = new File(file.getName());

			try {

				java.nio.file.Files.move( 
						file.toPath(), 
						toFile.toPath() ,StandardCopyOption.REPLACE_EXISTING);
				//get the file name using input stream
				InputStream stream = new FileInputStream(file.getName());
				//create a new image and use the uploaded file
			    Image image = new Image(stream);
			    //set the image in the image view
			    customerProfile.setImage(image);

				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}

	}
	
//	@Override
//    public void handle(ActionEvent t) {
//        FileChooser fileChooser = new FileChooser();
//        
//        //Set extension filter
//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//         
//        //Show open file dialog
//        File file = fileChooser.showOpenDialog(null);
//                  
//        try {
//        } catch (IOException ex) {
//            Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

	public void successUpload() {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Successfully uploaded photo!");
		al.setHeaderText("Upload");
		al.setTitle("Photo");
		al.setResizable(false);
		al.showAndWait();
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

		
		/**Resetting the Order**/
		orderId.setText("");
		dishesInOrderShow.setText("");
//		deliveryIDToOrder.setText("");
		
		
		/**Resetting the Delivery**/
		deliveryID.setText("");
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
		ArrayList<String> customerNotInBLDB = new ArrayList<>();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			if(!Restaurant.getInstance().getBlackList().contains(c)) {
				customerNotInBLDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
			}
			customerDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> ObservableListCustomers = FXCollections.observableArrayList();
		ObservableListCustomers.addAll(customerDB);
		customerDelete.setItems(ObservableListCustomers);
		customersForOrder.setItems(ObservableListCustomers);
		
		ObservableList<String> ObservableListCustomersBL = FXCollections.observableArrayList();
		ObservableListCustomersBL.addAll(customerNotInBLDB);
		customerList.setItems(ObservableListCustomersBL);

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
//		deliveriesInOrder.setItems(ObservableListDeliveries);
		currentDelivery.setItems(ObservableListDeliveries);

/****************************************************************************/		

	}

}
