package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Component;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import Utils.DishType;
import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelevantDishesQueryController implements Initializable{
	
	
	
	
	/**********For getRelevantDishes()************/
	public static int givenCustomerID;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*set in the table all the dishes data from database for each of their fields*/
		dishID.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
		dishName.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		dishType.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		dishComponents.setCellValueFactory(new PropertyValueFactory<Dish, ArrayList<Component>>("componenets"));
		dishTime.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("timeToMake"));
		dishesTable.setItems(getRelevantDishes());
	}
	
	private ObservableList<Dish> getRelevantDishes() {
		try {
			ObservableList<Dish> relDishes=FXCollections.observableArrayList();
			Customer customerToQuery = Restaurant.getInstance().getRealCustomer(givenCustomerID);
			relDishes.addAll(Restaurant.getInstance().getReleventDishList(customerToQuery));
			if(relDishes.isEmpty())
				throw new Exception();
			return relDishes;
		}
		catch(Exception e1) {
			badSound();
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setContentText("Faild to find dishes");
			al.setHeaderText(e1.toString());
			al.setTitle("Database");
			al.setResizable(false);
			al.showAndWait();
			return null;
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
