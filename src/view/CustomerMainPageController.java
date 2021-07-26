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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import Model.Customer;


public class CustomerMainPageController implements Initializable  {
	 static Customer customer;
	 @FXML
	    private StackPane mainPane;

	    @FXML
	    private ToggleButton newOrder;

	    @FXML
	    private ToggleButton cancel;

	    @FXML
	    private ToggleButton orders;
	    @FXML
		private ToggleButton log;

	    @FXML
	    private ToggleButton exit;

	    @FXML
	    private AnchorPane rootPane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public void loadNewOrder(ActionEvent e)
	{
		try {
			if(newOrder.isSelected())
			{
//				CustomerNewOrderPageController.setCustomer(customer);
				cancel.setSelected(false);
				orders.setSelected(false);
				TabPane pane=FXMLLoader.load(getClass().getResource("CustomerNewOrderPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void loadCancelOrder(ActionEvent e)
	{
		try {
			if(cancel.isSelected())
			{
//				CustomerCancelPageController.setCustomer(customer);
				newOrder.setSelected(false);
				orders.setSelected(false);
				AnchorPane pane=FXMLLoader.load(getClass().getResource("CustomerCancelPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public void loadOrders(ActionEvent e)
	{
		try {
			if(orders.isSelected())
			{
//				customerOrdersPageController.setCustomer(customer);
				newOrder.setSelected(false);
				cancel.setSelected(false);
				TableView pane=FXMLLoader.load(getClass().getResource("CustomerOrdersPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	public void exitProgrem(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to exit the program?");
		al.setTitle("Exit Program");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK) {
			goodSound();
			System.exit(0);
		}
	}

	
	
	
	public static Customer getCustomer() {
		return customer;
	}
	public static void setCustomer(Customer customer) {
		CustomerMainPageController.customer = customer;
	}

	
	public void logOutSys(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to log out of the system?");
		al.setTitle("Log out");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			goodSound();
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("JavaEatMain.fxml"));
				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
				mainPane.getChildren().removeAll(mainPane.getChildren());
				mainPane.getChildren().add(pane);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
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
