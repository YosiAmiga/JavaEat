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
	/*the controller to add and remove everything*/
	PrimaryController control=new PrimaryController();
	@FXML
	private TextField locationCountry;

	@FXML
	private TextField LocationCity;

	@FXML
	private Button addLocation;



	@FXML
	private TextField customerEmail;







	@FXML
	private TextField guideId;

	@FXML
	private TextField guideFirst;

	@FXML
	private TextField guideLast;

	@FXML
	private TextField guideEmail;

	@FXML
	private TextField guideCountry;

	@FXML
	private TextField guideCity;

	@FXML
	private TextField guideStreet;

	@FXML
	private TextField guideHouse;

	@FXML
	private TextField guidePhone;

	@FXML
	private DatePicker guideBirth;

	@FXML
	private Button addGuide;

	@FXML
	private TextField guidePass;

	@FXML
	private TextField guidePassVerify;

	@FXML
	private TextField guideQuestion;

	@FXML
	private TextArea guideListOfExpDestinations;

	@FXML
	private Button guideAddtoList;

	@FXML
	private Button guideClearList;

	@FXML
	private DatePicker startDate;

	@FXML
	private ComboBox<String> guideDestinations;

	@FXML
	private Button addHotel;

	@FXML
	private TextField hotelStars;

	@FXML
	private TextField hotelId;

	@FXML
	private TextField hotelName;

	@FXML
	private TextField hotelRooms;

	@FXML
	private TextField hotelPeoplePerRoom;

	@FXML
	private TextField hotelPrice;

	@FXML
	private CheckBox hotelPool;

	@FXML
	private CheckBox HotelBreakfast;

	@FXML
	private ComboBox<String> hotelCountry;
	/**************************************Delivery Area Page****************************************/
//	public DeliveryArea(String areaName, HashSet<Neighberhood> neighberhoods, int deliverTime) {
//		super();
//		this.id = idCounter++;
//		this.areaName = areaName;
//		this.neighberhoods = neighberhoods;
//		this.deliverTime = deliverTime;
//		delPersons = new HashSet<>();
//		delivers = new HashSet<>();
//	}
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
	
	/**************************************Delivery Person Page****************************************/
//	public DeliveryPerson(int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
//			DeliveryArea area)
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
	private TextField delComponentID;
	
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
	/*******************************************************************************/

	@FXML
	private ComboBox<String> hotelCity;

	@FXML
	private Button addMotel;

	@FXML
	private TextField motelId;

	@FXML
	private TextField motelName;

	@FXML
	private TextField motelNumOfRooms;

	@FXML
	private TextField motelPeoplePerRoom;

	@FXML
	private TextField motelPricePerPerson;

	@FXML
	private CheckBox motelWIFI;

	@FXML
	private CheckBox motelKitchen;

	@FXML
	private ComboBox<String> motelCountry;

	@FXML
	private ComboBox<String> motelCity;

	@FXML
	private Button addHostel;

	@FXML
	private TextField hostelId;

	@FXML
	private TextField hostelName;

	@FXML
	private TextField hostelNumOfRooms;

	@FXML
	private TextField hostelNumOfPeople;

	@FXML
	private TextField hostelPricePerPerson;

	@FXML
	private ComboBox<String> hostelType;

	@FXML
	private ComboBox<String> hostelCountry;

	@FXML
	private ComboBox<String> hostelCity;

	@FXML
	private   TextField tripId;

	@FXML
	private ComboBox<Long> tripGuideId;

	@FXML
	private TextField tripPrice;

	@FXML
	private DatePicker tripDate;
	@FXML
	private TextField tripPeople;

	@FXML
	private Button addTrip;

	@FXML
	private TextArea tripDescription;

	@FXML
	private ComboBox<String> tripCountry;

	@FXML
	private ComboBox<String> tripCity;

	@FXML
	private TextField packageName;

	@FXML
	private TextField packageNumOfPeople;

	@FXML
	private TextField packagePrice;

	@FXML
	private ComboBox<String> packageFlights;

	@FXML
	private ComboBox<String> packageAccommodations;

	@FXML
	private ComboBox<String> packageGroupTrip;


	@FXML
	private Button packageAddFlights;

	@FXML
	private Button packageClearFlights;

	@FXML
	private Button packageaddAccommodations;

	@FXML
	private Button packageClearAccommodations;

	@FXML
	private Button packageAddTrips;

	@FXML
	private Button PackageClearTrips;

	@FXML
	private TextArea packageFlightListView;

	@FXML
	private TextArea packageAccommodationListView;

	@FXML
	private TextArea packagetTripsListView;

	@FXML
	private Button addPackage;
//
//	@FXML
//	private ComboBox<Long> guideExpId;



//	@FXML
//	private ComboBox<String> guideExpCountry;
//
//	@FXML
//	private ComboBox<String> guideExpCity;

//	@FXML
//	private Button expCertificate;
//
//	@FXML
//	private Label expCertificateLabel;
	@FXML
	private TextField flightNum;

	@FXML
	private TextField flightSeats;

	@FXML
	private TextField flightPrice;

	@FXML
	private DatePicker flightDate;

	@FXML
	private Button addCustomer1;

	@FXML
	private ComboBox<String> flightFromCountry;

	@FXML
	private ComboBox<String> flightFromCity;

	@FXML
	private ComboBox<String> flightToCountry;

	@FXML
	private ComboBox<String> flightToCity;

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		/***************list of delivery persons to delete*********/
		ArrayList<String> delPersonDB = new ArrayList<>();
		for(DeliveryPerson dp :  Restaurant.getInstance().getDeliveryPersons().values()) {
			delPersonDB.add(dp.getFirstName() + " " + dp.getLastName());
		}
		ObservableList<String> comboDelPersons = FXCollections.observableArrayList();
		comboDelPersons.addAll(delPersonDB);
		delPersonDelete.setItems(comboDelPersons);
		

		/***************list of delivery areas*********************/
		ArrayList<String> areasDB = new ArrayList<>();
		for(DeliveryArea da :  Restaurant.getInstance().getAreas().values()) {
			areasDB.add("Area ID: " + da.getId()+ " Name: " +da.getAreaName());
		}
		ObservableList<String> comboAreas = FXCollections.observableArrayList();
		comboAreas.addAll(areasDB);
		delPersonArea.setItems(comboAreas);
		deliveryAreasByID.setItems(comboAreas);
		
		/***************list of components to delete***************/
		ArrayList<String> componentsDB = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB.add("ID: " + c.getId() + " Name: " + c.getComponentName() + " Price: " + c.getPrice());
		}
		ObservableList<String> comboComponents = FXCollections.observableArrayList();
		comboComponents.addAll(componentsDB);
		componentsDelete.setItems(comboComponents);
		
		
		
		/***************list of customers to delete***************/
		ArrayList<String> customerDB = new ArrayList<>();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			customerDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> comboCustomers = FXCollections.observableArrayList();
		comboCustomers.addAll(customerDB);
		customerDelete.setItems(comboCustomers);
		/************WORKS*********/
		ArrayList<String> neighberhoodsDB=new ArrayList<String>();
		for(Neighberhood n : Neighberhood.values()) {

			neighberhoodsDB.add(String.valueOf(n));
		}

		ObservableList<String> comboNeighborhoods=FXCollections.observableArrayList();
		comboNeighborhoods.addAll(neighberhoodsDB);
		customerHoodCombo.setItems(comboNeighborhoods);
		delAreaHoods.setItems(comboNeighborhoods);
		/************WORKS*********/
		ArrayList<String> gendersDB=new ArrayList<String>();
		for(Gender g : Gender.values()) {

			gendersDB.add(String.valueOf(g));
		}

		ObservableList<String> comboGenders=FXCollections.observableArrayList();
		comboGenders.addAll(gendersDB);
		customerGenderCombo.setItems(comboGenders);
		genderCombo.setItems(comboGenders);
		/**********WORKS************/
		ArrayList<String> vehicleDB = new ArrayList<>();
		
		for(Vehicle v : Vehicle.values()) {
			vehicleDB.add(String.valueOf(v));
		}
		ObservableList<String> comboVehicles=FXCollections.observableArrayList();
		comboVehicles.addAll(vehicleDB);
		delPersonVehicle.setItems(comboVehicles);
		/*********************/
		
//		expCertificateLabel.setFont(new Font(20));
//		expCertificateLabel.setText("Add Destination Certificate to procceed");	
		ObservableList<String> Hostellist=FXCollections.observableArrayList("Short","Long");
		hostelType.setItems(Hostellist);

		ObservableList<String> list=FXCollections.observableArrayList();
		refreshGui();
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
	
	/**************replace a Delivery Area*********/
	//TODO Finish method + in PrimaryController
	public void replaceDeliveryArea(ActionEvent e) {
		String section = "Delivery Area";
		try {
			int idOldArea = Integer.parseInt(oldAreaID.getText());
			int idNewArea = Integer.parseInt(newAreaID.getText());

			if(control.replaceDeliveryAreaGUI(idOldArea, idNewArea)){
				successSound();
				successRemove(section, "Success");
				Restaurant.save(Input);	
			}
			else {
				fail(section, "Could not replace old area with new one!");
			}
		}catch(Exception e1) {
			System.out.println("hey in del area");
		}
		
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
				successSound();
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
		
	}

	/**************Remove a Delivery Person*******/
	public void removeDeliveryPerson(ActionEvent e) {
		String section = "Delivery Person";
		try {
			int id = Integer.parseInt(deleteDelPersonID.getText());
			if(control.removeDeliveryPersonGUI(id)) {
				successSound();
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
				successSound();
				successAdded(section,"Success");
				System.out.println(Restaurant.getInstance().getDeliveryPersons().values());
				Restaurant.save(Input);								
			}
			else {
				fail(section,"This id already exists in the customer database!");
			}
								
		}catch(Exception e1) {
			e1.printStackTrace();
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
			badSound();
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
				successSound();
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
				successSound();
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
			badSound();
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
//			public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
//					Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
//				super(idCounter++, firstName, lastName, birthDay, gender);
//				this.neighberhood = neighberhood;
//				this.isSensitiveToLactose = isSensitiveToLactose;
//				this.isSensitiveToGluten = isSensitiveToGluten;
//			}
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
				successSound();
				successAdded(section, "Success");
				Restaurant.save(Input);				
			}
			//if could not add customer
			else {
				badSound();
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

		if(!GuideExp.contains(guideDestinations.getValue()))
			GuideExp.add(guideDestinations.getValue());
		String list="";
		for(String s:GuideExp)
			list+=s+"\n";

		guideListOfExpDestinations.setText(list);




	}
	public void ClearExpList(ActionEvent e)
	{
		GuideExp.removeAll(GuideExp);
		guideListOfExpDestinations.setText("");

	}

	//working
	public void addFlight(ActionEvent e)
	{
//		String a = "Flight";
//		try {
//			String flightNumber=flightNum.getText();
//			int numberOfSeats=Integer.parseInt(flightSeats.getText());
//			String fromCountry=flightFromCountry.getValue();
//			String fromCity=flightFromCity.getValue();
//			String toCountry=flightToCountry.getValue();
//			String toCity=flightToCity.getValue();
//			double price=Double.parseDouble(flightPrice.getText());
//			LocalDate localDateStart =flightDate.getValue();
//			Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
//			Date date = Date.from(instantStart);
//
//
//			control.addFlight(flightNumber, numberOfSeats, fromCountry, fromCity, toCountry, toCity, price, date);
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			//pop up with success
//			//exception-Flight adding failed,flight already exists/illegal input
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, a+e1.toString());
//		}
//		catch(NegativNumberException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(IllegelCountryException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch(FlightToSameLocationException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(LocationNotExistException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}
	//working
	public void addHotel(ActionEvent e)
	{
//		String a = "Hotel";
//		try {
//			long id=Long.parseLong(hotelId.getText());
//			String name=hotelName.getText();
//			int roomNums=Integer.parseInt(hotelRooms.getText());
//			int peoplePerRoom=Integer.parseInt(hotelPeoplePerRoom.getText());
//			double pricePerPerson=Double.parseDouble(hotelPrice.getText());
//			String country=hotelCountry.getValue();
//			String city=hotelCity.getValue();
//			double stars=Double.parseDouble(hotelStars.getText());
//			boolean breakfast=HotelBreakfast.isSelected();
//			boolean pool=hotelPool.isSelected();
//
//			control.addHotel(id, name, roomNums, peoplePerRoom, pricePerPerson, country, city, stars, breakfast, pool);
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			refreshGui();
//
//			//pop up with success
//			//exception-Hotel adding failed,Hotel already exists/illegal input
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, "Acommodation"+e1.toString());
//		}
//		catch(NegativNumberException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch(IllegelCountryException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(LocationNotExistException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}

	//working
	public void addMotel(ActionEvent e)
	{
//		String a = "Motel";
//		try {
//			long id=Long.parseLong(motelId.getText());
//			String name=motelName.getText();
//			int roomNums=Integer.parseInt(motelNumOfRooms.getText());
//			int peoplePerRoom=Integer.parseInt(motelPeoplePerRoom.getText());
//			double pricePerPerson=Double.parseDouble(motelPricePerPerson.getText());
//			String country=motelCountry.getValue();
//			String city=motelCity.getValue();
//			boolean kitchen=motelKitchen.isSelected();
//			boolean wifi=motelWIFI.isSelected();
//
//			control.addMotel(id, name, roomNums, peoplePerRoom, pricePerPerson, country, city, kitchen, wifi);
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			refreshGui();
//
//			//pop up with success
//			//exception-Hotel adding failed,Hotel already exists/illegal input
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, "Acommodation"+e1.toString());
//		}
//		catch(NegativNumberException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch(IllegelCountryException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(LocationNotExistException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}


	//working
	public void addHostel(ActionEvent e)
	{
//		String a = "Hostel";
//		try {
//			long id=Long.parseLong(hostelId.getText());
//			String name=hostelName.getText();
//			int roomNums=Integer.parseInt(hostelNumOfRooms.getText());
//			int peoplePerRoom=Integer.parseInt(hostelNumOfPeople.getText());
//			double pricePerPerson=Double.parseDouble(hostelPricePerPerson.getText());
//			String country=hostelCountry.getValue();
//			String city=hostelCity.getValue();
//			int i=0;
//			if(hostelType.getValue().equals("Long"))
//				i=1;
//
//			control.addHostel(id, name, roomNums, peoplePerRoom, pricePerPerson, country, city, i);
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			refreshGui();
//
//			//pop up with success
//			//exception-Hotel adding failed,Hotel already exists/illegal input
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, "Acommodation"+e1.toString());
//		}
//		catch(NegativNumberException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(IllegelCountryException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch(LocationNotExistException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}


	//working
	public void addGroupTrip(ActionEvent e)
	{
//		String a = "Group Trip";
//		try {
//			int idtrip=Integer.parseInt(tripId.getText());
//			long guideId=tripGuideId.getValue();
//			String description=tripDescription.getText();
//			String country=tripCountry.getValue();
//			String city=tripCity.getValue();
//			double price=Double.parseDouble(tripPrice.getText());
//			int maxPeople=Integer.parseInt(tripPeople.getText());
//			LocalDate localDateStart =tripDate.getValue();
//			Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
//			Date tripDate = Date.from(instantStart);
//
//			control.addGroupTrip(idtrip, guideId, description, tripDate, country, city, price, maxPeople);
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			refreshGui();
//
//			//pop up with success
//			//exception-trip adding failed, already exists/illegal input	
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, a+e1.toString());
//		}
//
//		catch(GuideNotExist e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativNumberException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch(IllegelCountryException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(LocationNotExistException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}
	//working
	public void addGuideExp(ActionEvent e)
	{
//		String a = "Guide Experience";
//		try {
//			String country=guideExpCountry.getValue();
//			String city=guideExpCity.getValue();
//			Long id=guideExpId.getValue();
//			control.addExperiencedDestinationsforGuide(id, country, city);
//			if(!expCertificateLabel.getText().equals("Destination Certificate Was Added!"))
//				throw new GuideCertificationException();
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			refreshGui();
//
//
//			//pop up with success
//			//exception-exp adding failed, already exists/illegal input	
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch(GuideNotExist e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(IllegelCountryException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(GuideCertificationException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(GuideExperienceMismatchException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch(LocationNotExistException e1) {
//			badSound();
//			fail(a,e1.toString());
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
	}


	public void addPackage(ActionEvent e)
	{
//		String a = "Package";
//		try {
//			int numberOfPeople=Integer.parseInt(packageNumOfPeople.getText());
//			double price=Double.parseDouble(packagePrice.getText());
//			String name=packageName.getText();
//			String[] codesTicket=new String[packageFlightList.size()+packageAccommodationList.size()+packageTripList.size()];
//			int ticketStart=Shared.getInstance().getCount();
//			for(int i=0;i<codesTicket.length;i++)
//			{
//				codesTicket[i]=String.valueOf(ticketStart++);
//				Shared.getInstance().boostCount();
//			}
//
//
//			control.addTravelPackage(name, numberOfPeople, price, codesTicket,packageFlightList.toArray(new String[packageFlightList.size()]), packageAccommodationList.toArray(new Long[packageAccommodationList.size()]),packageTripList.toArray(new Integer[packageTripList.size()]));
//			goodSound();
//			success(a, "Success");
//			Shared.save(Input);
//			packageFlightList.removeAll(packageFlightList);
//			packageAccommodationList.removeAll(packageAccommodationList);
//			packageTripList.removeAll(packageTripList);
//			refreshGui();
//
//			//pop up with success
//			//exception-trip adding failed, already exists/illegal input	
//		}
//		catch(IllegelInputException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, a+e1.toString());
//		}
//		catch(NegativNumberException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			fail(a, "Wrong Input!");
//		}
//		catch (Exception e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
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

		if(!packageFlightList.contains(packageFlights.getValue()))
			packageFlightList.add(packageFlights.getValue());
		String list="";
		for(String s:packageFlightList)
			list+=s+"\n";

		packageFlightListView.setText(list);



	}
	public void ClearFlightList(ActionEvent e)
	{
		packageFlightList.removeAll(packageFlightList);
		packageFlightListView.setText("");

	}

	public void addToAccommodationFlightList(ActionEvent e)
	{
		Long a=Long.parseLong(packageAccommodations.getValue());
		if(!packageAccommodationList.contains(a))
			packageAccommodationList.add(a);
		String list="";
		for(Long s:packageAccommodationList)
			list+=s+"\n";

		packageAccommodationListView.setText(list);



	}
	public void ClearAccommodationList(ActionEvent e)
	{
		packageAccommodationList.removeAll(packageAccommodationList);
		packageAccommodationListView.setText("");

	}

	public void addToPackageTripList(ActionEvent e)
	{

		if(!packageTripList.contains(Integer.parseInt(packageGroupTrip.getValue())))
			packageTripList.add(Integer.parseInt(packageGroupTrip.getValue()));
		String list="";
		for(Integer s:packageTripList)
			list+=s+"\n";

		packagetTripsListView.setText(list);


	}
	public void ClearTripList(ActionEvent e)
	{
		packageTripList.removeAll(packageTripList);
		packagetTripsListView.setText("");

	}

	public void showCitiesHotel(ActionEvent e)
	{
//		ObservableList<String> list=FXCollections.observableArrayList();
//
//		String country=hotelCountry.getValue();
//		ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//		for(Destination d:dests)
//		{
//			list.add(d.getCity());
//		}
//		hotelCity.setItems(list);
	}
	public void showCitiesHostel(ActionEvent e)
	{
//		{
//			ObservableList<String> list=FXCollections.observableArrayList();
//
//			String country=hostelCountry.getValue();
//			ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(d.getCity());
//			}
//			hostelCity.setItems(list);
//		}
	}
	public void showCitiesMotel(ActionEvent e)
	{
//		{
//			ObservableList<String> list=FXCollections.observableArrayList();
//
//			String country=motelCountry.getValue();
//			ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(d.getCity());
//			}
//			motelCity.setItems(list);
//		}
	}

	public void showCitiesTrip(ActionEvent e)
	{
//		{
//			ObservableList<String> list=FXCollections.observableArrayList();
//
//			String country=tripCountry.getValue();
//			ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(d.getCity());
//			}
//			tripCity.setItems(list);
//		}
	}
	public void showCitiesExperiencedDestinations(ActionEvent e)
	{
//		{
//			ObservableList<String> list=FXCollections.observableArrayList();
//
//			String country=guideExpCountry.getValue();
//			ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//			if(dests!=null)
//			{
//				for(Destination d:dests)
//				{
//					list.add(d.getCity());
//				}
//			}
//			guideExpCity.setItems(list);
//
//		}
	}
	public void showFromCitiesFlight(ActionEvent e)
	{
//		{
//			ObservableList<String> list=FXCollections.observableArrayList();
//
//			String country=flightFromCountry.getValue();
//			ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(d.getCity());
//			}
//			flightFromCity.setItems(list);
//		}
	}
	public void showToCitiesFlight(ActionEvent e)
	{
//		{
//			ObservableList<String> list=FXCollections.observableArrayList();
//
//			String country=flightToCountry.getValue();
//			ArrayList<Destination>dests =Shared.getInstance().getDestinations().get(country);
//			for(Destination d:dests)
//			{
//				list.add(d.getCity());
//			}
//			flightToCity.setItems(list);
//		}
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
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Removed Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
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

	public void refreshGui()
	{     
		/**Resetting the Delivery Person**/
		delPersonId.setText("");		
		delPersonFName.setText("");
		delPersonLName.setText("");
		deleteDelPersonID.setText("");
		/**Resetting the Component**/

		componentID.setText("");
		componentName.setText("");
		componentPrice.setText("");
		hasLactose.setSelected(false);
		hasGluten.setSelected(false);
		delComponentID.setText("");

		/**Resetting the Customer**/
		customerId.setText("");
		customerFirst.setText("");
		customerLast.setText("");		
		customerPass.setText("");
		customerPassVerify.setText("");
		customerLactose.setSelected(false);
		customerGluten.setSelected(false);
		delCustomerID.setText("");
		
		
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
//		/************WORKS*********/
//		ArrayList<String> gendersDB=new ArrayList<String>();
//		for(Gender g : Gender.values()) {
//			genders.add(g);
//			gendersDB.add(String.valueOf(g));
//		}
//
//		ObservableList<String> comboGenders=FXCollections.observableArrayList();
//		comboGenders.addAll(gendersDB);
//		customerGenderCombo.setItems(comboGenders);
//		/*********************/
		customerGluten.setSelected(false);
		customerLactose.setSelected(false);
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
