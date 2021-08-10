package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CooksByExpertiseQueryController implements Initializable{
	
	
	
	
	/**********For getRelevantDishes()************/
	public static String givenExp;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*set in the table all the cooks data from database for each of their fields*/
		//TODO FIX BOOLEAN
		cookID.setCellValueFactory(new PropertyValueFactory<Cook, Integer>("id"));
		cookFirstName.setCellValueFactory(new PropertyValueFactory<Cook, String>("firstName"));
		cookLastName.setCellValueFactory(new PropertyValueFactory<Cook, String>("lastName"));
		cookBD.setCellValueFactory(new PropertyValueFactory<Cook, LocalDate>("birthDay"));
		cookGender.setCellValueFactory(new PropertyValueFactory<Cook, Gender>("gender"));
		cookExpertise.setCellValueFactory(new PropertyValueFactory<Cook, Expertise>("expert"));
//		cookIsChef.setCellValueFactory(new PropertyValueFactory<Cook, String>("isChef"));
		cooksTable.setItems(getCooksByExp());
		
	}
	
	private ObservableList<Cook> getCooksByExp() {
		try {
			ObservableList<Cook> cooks=FXCollections.observableArrayList();
			Expertise userSelection = null;
			for(Expertise exp: Expertise.values()) {
				if(givenExp.equals(String.valueOf(exp))){
					userSelection = exp;
				}
			}
			cooks.addAll(Restaurant.getInstance().GetCooksByExpertise(userSelection));
			if(cooks.isEmpty())
				throw new Exception();
			return cooks;
		}
	
		catch(Exception e1) {
			badSound();
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setContentText("Faild to find cooks with this Expertise");
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