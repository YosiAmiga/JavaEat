package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeSet;

import view.RelevantDishesQueryController;
import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Model.*;
import Utils.DishType;
import Utils.Expertise;

public class ManagerQueryController implements Initializable{
	private static final String Input = "JavaEat.ser";
	
	/***************ReleventDishList() Query*****************/
	@FXML
	private TextField customerIDForRelDish;
	@FXML
	private Button searchForCustomer;
	@FXML
	private ComboBox<String> customersInSystem;
	@FXML
	private AnchorPane relDishesWrapper;
	
	/**************getPopularComponents() Query*************/
	@FXML
	private TableView<Component> getPopularComponentsTable;
	@FXML
	private TableColumn<Component, Integer> componentID;
	@FXML
	private TableColumn<Component, String> componentName;
	@FXML
	private TableColumn<Component, String> componentLactose;
	@FXML
	private TableColumn<Component, String> componentGluten;
	@FXML
	private TableColumn<Component, Double> componentPrice;

	/**************getProfitRelation() Query*************/
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


	/**************GetCooksByExpertise() Query*************/
	@FXML
	private Button searchForCook;
	@FXML
	private ComboBox<String> cookExpertise;
	@FXML
	private AnchorPane cookByExpWrapper;
	
	/*******************************************************/


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*Show the data for query: getReleventDishList()*/
		/***************Load list of customers in the system***************/
		ArrayList<String> customerDB = new ArrayList<>();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			customerDB.add("ID: " + c.getId() + " Name: " + c.getFirstName()+ " " +c.getLastName());
		}
		ObservableList<String> ObservableListCustomers = FXCollections.observableArrayList();
		ObservableListCustomers.addAll(customerDB);
		customersInSystem.setItems(ObservableListCustomers);
		
		/*Show the data for query: getPopularComponents()*/
		componentID.setCellValueFactory(new PropertyValueFactory<Component, Integer>("id"));
		componentName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		componentLactose.setCellValueFactory(new PropertyValueFactory<Component, String>("hasLactose"));
		componentGluten.setCellValueFactory(new PropertyValueFactory<Component, String>("hasGluten"));
		componentPrice.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
		getPopularComponentsTable.setItems(getPopularComp());

		
		/*Show the data for query: getProfitRelation()*/
		dishID.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
		dishName.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		dishType.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		dishTime.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("timeToMake"));
		dishesTable.setItems(getProfitRel());
		
		
		
		/******************Load Expertise enum****************/
		ArrayList<String> cookExpertiseAL = new ArrayList<>();
		for(Expertise exp: Expertise.values()){
			cookExpertiseAL.add(String.valueOf(exp));
		}
		ObservableList<String> ObservableListExpertise=FXCollections.observableArrayList();
		ObservableListExpertise.addAll(cookExpertiseAL);
		cookExpertise.setItems(ObservableListExpertise);
		
		//populating popular destinations table
		
//		popularDestCountry.setCellValueFactory(new PropertyValueFactory<Destination, String>("Country"));
//		popularDestCity.setCellValueFactory(new PropertyValueFactory<Destination, String>("City"));
//		PopularDestinationsTable.setItems(getDestItems());
//		//populating WIFI table
//		wifiId.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
//		wifiFirst.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
//		wifiLat.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
//		wifiBirth.setCellValueFactory(new PropertyValueFactory<Person, Date>("birthDate"));
//		wifiEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
//		wifiAddress.setCellValueFactory(new PropertyValueFactory<Person, Address>("address"));
//		wifiTable.setItems(getWifiItems());
//		//populating Orders Table
//		ordersID.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Integer>("id"));
//		ordersOwner.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("owner"));
//		ordersQuantity.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Integer>("quantity"));
//		ordersPrice.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Double>("Price"));
//		ordersTable.setItems(getOrderItems());
//		//populating FlightXOR comboBoxes
//		ObservableList<String> flights=FXCollections.observableArrayList();
//		ArrayList<String> flightNumber=new ArrayList<String>();
//		for(String f:Shared.getInstance().getFlights().keySet())
//			flightNumber.add(f);
//		flights.addAll(flightNumber);
//		xorFlightOne.setItems(flights);
//		xorFlightTwo.setItems(flights);


	}
	
	/*a method to use the GUI to call the query*/
	public void getCustomerToQueryFromGUI(ActionEvent e){
		RelevantDishesQueryController.givenCustomerID = Integer.parseInt(customerIDForRelDish.getText());
		TableView<Dish> pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("fxmlFolder\\RelevantDishTable.fxml"));
			pane.setPrefSize(relDishesWrapper.getWidth(), relDishesWrapper.getHeight());
			relDishesWrapper.getChildren().removeAll(relDishesWrapper.getChildren());
			relDishesWrapper.getChildren().add(pane);
		}

		catch (IOException e1) {

			e1.printStackTrace();
		}

	}
	
	/*a method to use the GUI to call the query*/
	public void getExpertiesToQueryFromGUI(ActionEvent e){
		CooksByExpertiseQueryController.givenExp = cookExpertise.getValue();
		TableView<Dish> pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("fxmlFolder\\CooksByExpTable.fxml"));
			pane.setPrefSize(cookByExpWrapper.getWidth(), cookByExpWrapper.getHeight());
			cookByExpWrapper.getChildren().removeAll(cookByExpWrapper.getChildren());
			cookByExpWrapper.getChildren().add(pane);
		}

		catch (IOException e1) {

			e1.printStackTrace();
		}

	}
	
	
//
//
//	private ObservableList<OrdersForTable> getOrderItems() {
//		ObservableList<OrdersForTable> orders=FXCollections.observableArrayList();
//		ArrayList<Order> query=new ArrayList<Order>(control.getAllOrdersSortededByTotalCost());
//		ArrayList<OrdersForTable> convertedForTable=new ArrayList<OrdersForTable>();
//		for(Order o:query)
//			convertedForTable.add(new OrdersForTable(o));
//		orders.addAll(convertedForTable);
//		return orders;
//	}
//
//
//
//
//	public void getAccommodationAsc(ActionEvent e)
//	{
//		goodSound();
//		AccommodationTableController.ascending=true;
//		TableView<TravelPackage> pane;
//		try {
//			pane = FXMLLoader.load(getClass().getResource("AccommodationTable.fxml"));
//			pane.setPrefSize(accommodationTableWrapper.getWidth(), accommodationTableWrapper.getHeight());
//			accommodationTableWrapper.getChildren().removeAll(accommodationTableWrapper.getChildren());
//			accommodationTableWrapper.getChildren().add(pane);
//		}
//
//		catch (IOException e1) {
//
//			e1.printStackTrace();
//		}
//
//
//	}
//	public void getAccommodationDsc(ActionEvent e)
//	{
//		goodSound();
//		AccommodationTableController.ascending=false;
//		TableView<TravelPackage> pane;
//		try {
//			pane = FXMLLoader.load(getClass().getResource("AccommodationTable.fxml"));
//			pane.setPrefSize(accommodationTableWrapper.getWidth(), accommodationTableWrapper.getHeight());
//			accommodationTableWrapper.getChildren().removeAll(accommodationTableWrapper.getChildren());
//			accommodationTableWrapper.getChildren().add(pane);
//		}
//
//		catch (IOException e1) {
//
//			e1.printStackTrace();
//		}
//
//	}
//
//
//	private ObservableList<Person> getWifiItems() {
//		ObservableList<Person> wifi=FXCollections.observableArrayList();
//		ArrayList<Person> query=new ArrayList<Person>();
//		query.addAll(control.getAllCustomersWhoOrdered_A_MotelWithWiFiService());
//		wifi.addAll(query);
//		return wifi;
//	}
//
	/****Returns the data of the given queries from the system****/
	//for getPopularComponents()
	private ObservableList<Component> getPopularComp(){
		ObservableList<Component> popularComponents = FXCollections.observableArrayList();
		List<Component> query = new ArrayList<Component>(Restaurant.getInstance().getPopularComponents());
		popularComponents.addAll(query);
		
		return popularComponents;		
	}
	
	private ObservableList<Dish> getProfitRel(){
		ObservableList<Dish> mpDish = FXCollections.observableArrayList();
		ArrayList<Dish> query = new ArrayList<Dish> (Restaurant.getInstance().getProfitRelation());
		mpDish.addAll(query);
		
		return mpDish;		
	}
	
	
//	private ObservableList<Destination> getDestItems() {
//		ObservableList<Destination> popular=FXCollections.observableArrayList();
//		ArrayList<Destination> query=(ArrayList<Destination>) control.getMostPopularDestinations();
//		popular.addAll(query);
//
//
//		return popular;
//	}
//
//
//	public void getPackagesInRange(ActionEvent e)
//	{
//		try {
//			double min=Double.parseDouble(packagesMinPrice.getText());
//			double max=Double.parseDouble(packagesMaxPrice.getText());
//			ArrayList<TravelPackage> query=new ArrayList<TravelPackage> (control.getAllPackagesInRange(min, max));
//			PackageTableController.tableInput=query;
//			TableView<TravelPackage> pane;
//			pane = FXMLLoader.load(getClass().getResource("PackagesTable.fxml"));
//			pane.setPrefSize(packagesTableWrapper.getWidth(), packagesTableWrapper.getHeight());
//			packagesTableWrapper.getChildren().removeAll(packagesTableWrapper.getChildren());
//			packagesTableWrapper.getChildren().add(pane);
//		}
//
//		catch (IOException e1) {
//
//			e1.printStackTrace();
//		}
//		catch(NumberFormatException e1) {
//			badSound();
//			Alert al = new Alert(Alert.AlertType.ERROR);
//			al.setContentText("Please type numbers");
//			al.setHeaderText("Input Error");
//			al.setTitle("Database");
//			al.setResizable(false);
//			al.showAndWait();
//		}
//
//	}

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
}
