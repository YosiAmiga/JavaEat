package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Order;
import Model.Restaurant;
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

public class AIQueryController implements Initializable{
	private static final String Input = "Rest.ser";
	
	public static int givenDeliveryID;
	public static DeliveryPerson givenDeliveryPerson;
	public static DeliveryArea givenDeliveryArea;
	public static TreeSet<Order> givenTree;
	
	/**********For getRelevantDishes()************/

	@FXML
	private TableView<Delivery> aiTable;
	@FXML
	private TableColumn<Delivery, Integer> deliveryID;
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
		deliveryDate.setCellValueFactory(new PropertyValueFactory<Delivery, LocalDate>("deliveredDate"));
		aiTable.setItems(getDeliveriesByPerson());
	}
	
	
	
	public ObservableList<Delivery> getDeliveriesByPerson() {
		try {
			ObservableList<Delivery> deliveriesByAI=FXCollections.observableArrayList();
			TreeSet<Delivery> deliveryToSaveInDB = Restaurant.getInstance().createAIMacine(givenDeliveryID, givenDeliveryPerson, givenDeliveryArea, givenTree);
			deliveriesByAI.addAll(deliveryToSaveInDB);
			if(deliveriesByAI.isEmpty()) {
				throw new Exception();				
			}
			else {
				//add the deliveries that the A.I created to the database of deliveries
				for(Delivery d : deliveryToSaveInDB) {
					if(!Restaurant.getInstance().getDeliveries().containsKey(d.getId())) {
						Restaurant.getInstance().getDeliveries().put(d.getId(), d);						
					}
				}
				succsesDelivery();
				Restaurant.save(Input);
			}
			return deliveriesByAI;
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
	
	
	public void succsesDelivery() {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Deliveries created!");
		al.setHeaderText("A.I created delivery");
		al.setTitle("Delivery");
		al.setResizable(false);
		al.showAndWait();
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

}