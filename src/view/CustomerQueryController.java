package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.EmptyComboBoxException;
import Model.Component;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import Utils.Expertise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CustomerQueryController implements Initializable{
	private static final String Input = "JavaEat.ser";
	//the customer currently logged in
	static Customer customer;
	/*************** .1. ReleventDishList() Query*****************/

	@FXML
	private Button searchForCustomer;
	@FXML
	private Label customerInfo;
	@FXML
	private AnchorPane relDishesWrapper;
	
	/*************** .3. GetCooksByExpertise() Query*****************/

	@FXML
	private Button searchForCook;
	@FXML
	private ComboBox<String> cookExpertise;
	@FXML
	private AnchorPane cookByExpWrapper;
	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		customerInfo.setText("Customer ID: " + customer.getId() + " Full Name: " + customer.getFirstName() + " " +customer.getLastName() );
		
		/******************Load Expertise enum****************/
		ArrayList<String> cookExpertiseAL = new ArrayList<>();
		for(Expertise exp: Expertise.values()){
			cookExpertiseAL.add(String.valueOf(exp));
		}
		ObservableList<String> ObservableListExpertise=FXCollections.observableArrayList();
		ObservableListExpertise.addAll(cookExpertiseAL);
		cookExpertise.setItems(ObservableListExpertise);
		
		/*Show the data for query: getPopularComponents()*/
		componentID.setCellValueFactory(new PropertyValueFactory<Component, Integer>("id"));
		componentName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		componentLactose.setCellValueFactory(new PropertyValueFactory<Component, String>("hasLactose"));
		componentGluten.setCellValueFactory(new PropertyValueFactory<Component, String>("hasGluten"));
		componentPrice.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
		getPopularComponentsTable.setItems(getPopularComp());
	}
	
	/*************** .1. ReleventDishList() Query*****************/
	/*a method to use the GUI to call the query*/
	public void getCustomerToQueryFromGUI(ActionEvent e){
		try {
			RelevantDishesQueryController.givenCustomerID = customer.getId();
			
			TableView<Dish> pane;
			pane = FXMLLoader.load(getClass().getResource("fxmlFolder\\RelevantDishTable.fxml"));
			pane.setPrefSize(relDishesWrapper.getWidth(), relDishesWrapper.getHeight());
			relDishesWrapper.getChildren().removeAll(relDishesWrapper.getChildren());
			relDishesWrapper.getChildren().add(pane);
		}
		catch (IOException e1) {
			e1.printStackTrace();
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
	
	/*************** .7. getPopularComponents() Query*****************/

	/****Returns the data of the given queries from the system****/
	//for getPopularComponents()
	private ObservableList<Component> getPopularComp(){
		ObservableList<Component> popularComponents = FXCollections.observableArrayList();
		List<Component> query = new ArrayList<Component>(Restaurant.getInstance().getPopularComponents());
		popularComponents.addAll(query);
		
		return popularComponents;		
	}

}
