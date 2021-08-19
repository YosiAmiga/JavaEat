package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import Model.RegularDelivery;
import Model.Restaurant;
import Utils.DishType;
import controller.Sounds;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GetDeliveriesByPersonQueryController implements Initializable{
	
	
	
	public static int givenDeliveryPersonID;
	public static int givenMonth;
	
	/**********For getRelevantDishes()************/

	@FXML
	private TableView<Delivery> deliveryTable;
	@FXML
	private TableColumn<Delivery, Integer> deliveryID;
//	@FXML
//	private TableColumn<RegularDelivery, TreeSet<Order>> regDeliveryOrders;
	@FXML
	private TableColumn<Delivery, DeliveryPerson> deliveryDelPerson;
	@FXML
	private TableColumn<Delivery, DeliveryArea> deliveryDelArea;
	@FXML
	private TableColumn<Delivery, String> deliveryIsDelivered;
	@FXML
	private TableColumn<Delivery, LocalDate> deliveryDate;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		/*set in the table all the regular deliveries data from database for each of their fields*/
		deliveryID.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("id"));
		deliveryDelPerson.setCellValueFactory(new PropertyValueFactory<Delivery, DeliveryPerson>("deliveryPerson"));
		deliveryDelArea.setCellValueFactory(new PropertyValueFactory<Delivery, DeliveryArea>("area"));
		deliveryIsDelivered.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isDelivered())));
//		regDeliveryOrders.setCellValueFactory(new PropertyValueFactory<RegularDelivery, TreeSet<Order>>("orders"));
		deliveryDate.setCellValueFactory(new PropertyValueFactory<Delivery, LocalDate>("deliveredDate"));
		deliveryTable.setItems(getDeliveriesByPerson());
	}
	
	public ObservableList<Delivery> getDeliveriesByPerson() {
		try {
			ObservableList<Delivery> deliveriesByPerson=FXCollections.observableArrayList();
			DeliveryPerson delPersonQuery = Restaurant.getInstance().getRealDeliveryPerson(givenDeliveryPersonID);
			
			deliveriesByPerson.addAll(Restaurant.getInstance().getDeliveriesByPerson(delPersonQuery,givenMonth));
			if(deliveriesByPerson.isEmpty())
				throw new Exception();
			return deliveriesByPerson;
		}
		catch(Exception e1) {
			badSound();
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setContentText("Faild to find deliveries");
			al.setHeaderText("No deliveries in this month by this delivery person");
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