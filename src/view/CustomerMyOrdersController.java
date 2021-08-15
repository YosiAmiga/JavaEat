package view;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;

public class CustomerMyOrdersController implements Initializable {
	//the customer currently logged in
	static Customer customer;
	
	@FXML
	private ComboBox<String> starters;
	
	@FXML
	private ComboBox<String> MainDishes;	

	@FXML
	private ComboBox<String> desserts;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/**************Load list of dishes in system*********/
		ArrayList<String> startersDB = new ArrayList<>();
		ArrayList<String> mainDishesDB = new ArrayList<>();
		ArrayList<String> dessertsDB = new ArrayList<>();

		for(Dish d :  Restaurant.getInstance().getDishes().values()) {
			if(d.getType().toString().equals("Starter")) {
				startersDB.add(d.getDishName());
			}
			if(d.getType().toString().equals("Main")) {
				mainDishesDB.add(d.getDishName());
			}
			
			if(d.getType().toString().equals("Dessert")) {
				dessertsDB.add(d.getDishName());
			}
			
		}

	
		ObservableList<String> ObservableListStarters = FXCollections.observableArrayList();
		ObservableList<String> ObservableListMainDishes = FXCollections.observableArrayList();
		ObservableList<String> ObservableListDesserts = FXCollections.observableArrayList();

		ObservableListStarters.addAll(startersDB);
		starters.setItems(ObservableListStarters);
		
		
		ObservableListMainDishes.addAll(mainDishesDB);
		MainDishes.setItems(ObservableListMainDishes);

		ObservableListDesserts.addAll(dessertsDB);
		desserts.setItems(ObservableListDesserts);
		
		
		
	}
	
	

}
