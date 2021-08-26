package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Sounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import Model.Customer;


public class CustomerMainPageController implements Initializable  {
		//the customer currently logged in
		static Customer customer;

		
	 	@FXML
	    private StackPane mainPane;

	    @FXML
	    private ToggleButton newOrder;

	    @FXML
	    private ToggleButton query;
	    
	    @FXML
	    private ToggleButton settings;
	    
	    @FXML
		private ToggleButton logout;

	    @FXML
	    private ToggleButton exit;

	    @FXML
	    private AnchorPane rootPane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    newOrder.setSelected(false);
	    query.setSelected(false);
	    settings.setSelected(false);
	    logout.setSelected(false);
	    exit.setSelected(false);
	    successLogin(customer.getFirstName() + " " + customer.getLastName(),"Welcome to JavaEat!");
	    
	}
	
	public void loadNewOrder(ActionEvent e)
	{
		try {
			if(newOrder.isSelected())
			{
				CustomerMyOrdersController.customer = customer;
				query.setSelected(false);
				TabPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\customerMyOrders.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	
	public void loadQueries(ActionEvent e)
	{
		try {
			if(query.isSelected())
			{
				CustomerQueryController.customer = customer;
				newOrder.setSelected(false);
				TabPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\CustomerQueryPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	public void loadSettings(ActionEvent e)
	{
		try {
			if(settings.isSelected())
			{
				CustomerSettingsController.customer = customer;
				newOrder.setSelected(false);
				AnchorPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\CustomerUpdateSettings.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	
	public void logOutSys(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to log out of the system?");
		al.setTitle("Log out");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			exitSound();
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("fxmlFolder\\JavaEatMain.fxml"));
				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
				mainPane.getChildren().removeAll(mainPane.getChildren());
				mainPane.getChildren().add(pane);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void exitProgrem(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to exit the program?");
		al.setTitle("Exit Program");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK) {
			exitSound();
			System.exit(0);
		}
	}

	
	public void successLogin(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" logged in successfuly!");
		al.setHeaderText(header);
		al.setTitle("Welcome");
		al.setResizable(false);
		al.showAndWait();
	}
	


	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		CustomerMainPageController.customer = customer;
	}

	
	public void exitSound() {
		Sounds s = new Sounds();
		try {
			s.exitSound();
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
