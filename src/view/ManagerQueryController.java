package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.EmptyComboBoxException;
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
import javafx.scene.control.Label;
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
	
	/*************** .1. ReleventDishList() Query*****************/

	@FXML
	private Button searchForCustomer;
	@FXML
	private ComboBox<String> customersInSystem;
	@FXML
	private AnchorPane relDishesWrapper;
	
	/***************TODO .2. deliver() Query*****************/
	@FXML
	private ComboBox<String> deliveriesInSystem;
	@FXML
	private TextField deliveryID;
	@FXML
	private Button deliverDelivery;

	/*************** .3. GetCooksByExpertise() Query*****************/

	@FXML
	private Button searchForCook;
	@FXML
	private ComboBox<String> cookExpertise;
	@FXML
	private AnchorPane cookByExpWrapper;
	
	/***************TODO .4. getDeliveriesByPerson() Query*****************/
	
	
	
	/*************** .5. getNumberOfDeliveriesPerType() Query*****************/

	@FXML
	private Label regularCount;
	@FXML
	private Label expressCount;
	
	/***************.6. revenueFromExpressDeliveries() Query*****************/
	@FXML
	private Label expressRevenue;
	
	/*************** .7. getPopularComponents() Query*****************/
	
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

	/*************** .8. getProfitRelation() Query*****************/

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

	/***************TODO .9. createAIMacine() Query*****************/


	/*******************************************************/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ArrayList<String> deliveriesDb = new ArrayList<>();
		
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			deliveriesDb.add(String.valueOf(d));
		}
		ObservableList<String> ObservableListDeliveries=FXCollections.observableArrayList();
		ObservableListDeliveries.addAll(deliveriesDb);
		deliveriesInSystem.setItems(ObservableListDeliveries);

		
		expressRevenue.setText(String.valueOf(Restaurant.getInstance().revenueFromExpressDeliveries()));
		
		regularCount.setText(String.valueOf(deliveryCounter("Regular delivery")));
		expressCount.setText(String.valueOf(deliveryCounter("Express delivery")));
		
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

		Restaurant.save(Input);
	}
	
	/*************** .1. ReleventDishList() Query*****************/
	/*a method to use the GUI to call the query*/
	public void getCustomerToQueryFromGUI(ActionEvent e){
		try {
			if(customersInSystem.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = customersInSystem.getValue();
			
			//Extract only the component ID in order to add him to the dish
			String numberOnly= str.replaceAll("[^0-9]", "");	
			RelevantDishesQueryController.givenCustomerID = Integer.parseInt(numberOnly);
			
			TableView<Dish> pane;
			pane = FXMLLoader.load(getClass().getResource("fxmlFolder\\RelevantDishTable.fxml"));
			pane.setPrefSize(relDishesWrapper.getWidth(), relDishesWrapper.getHeight());
			relDishesWrapper.getChildren().removeAll(relDishesWrapper.getChildren());
			relDishesWrapper.getChildren().add(pane);
		}
		catch(EmptyComboBoxException e1) {
			failSelection("Customer to select",e1.toString());
		}
		catch (IOException e1) {

			e1.printStackTrace();
		}

	}
	
	/*************** .2. deliver() Query*****************/
	public void deliverTheDelivery(ActionEvent e) {
		try {
			if(deliveryID.getText() == null) {
				throw new NumberFormatException();
			}
			int delID = Integer.parseInt(deliveryID.getText());
			Delivery d = Restaurant.getInstance().getRealDelivery(delID);
			Restaurant.getInstance().deliver(d);
		}
		catch(NumberFormatException e1) {
			fail("Delivery ID", "Wrong Input!");
		}
	}
	
	/*************** .3. GetCooksByExpertise() Query*****************/

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
	
	/*************** .5. getNumberOfDeliveriesPerType() Query*****************/

	//for getNumberOfDeliveriesPerType()
	private int deliveryCounter(String deliveryType) {
		HashMap<String, Integer> copyQuery = new HashMap<>(Restaurant.getInstance().getNumberOfDeliveriesPerType());
		int counter = 0;
		for(String s : copyQuery.keySet()) {
			if(s.equals(deliveryType)) {
				counter = copyQuery.get(s);
			}
		}
		return counter;
	}
	
	/*************** .7. getPopularComponents() Query*****************/

	/****Returns the data of the given queries from the system****/
	//for getPopularComponents()
	private ObservableList<Component> getPopularComp(){
		ObservableList<Component> popularComponents = FXCollections.observableArrayList();
		List<Component> query = new ArrayList<Component>(Restaurant.getInstance().getPopularComponents());
		popularComponents.addAll(query);
		
		return popularComponents;		
	}
	
	/*************** .8. getProfitRelation() Query*****************/

	//for getProfitRelation()
	private ObservableList<Dish> getProfitRel(){
		ObservableList<Dish> mpDish = FXCollections.observableArrayList();
		ArrayList<Dish> query = new ArrayList<Dish> (Restaurant.getInstance().getProfitRelation());
		mpDish.addAll(query);
		
		return mpDish;		
	}
	


	
	public void succsesDeliver() {
		goodSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Deliverey has reached customer!");
		al.setHeaderText("Deliver");
		al.setTitle("Delivery");
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
	
	public void failSelection(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to select : " + content);
		al.setHeaderText(header);
		al.setTitle("ComboBox");
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
}
