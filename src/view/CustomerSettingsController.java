package view;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import Exceptions.EmptyComboBoxException;
import Exceptions.IllegelInputException;
import Exceptions.PasswordMismatchException;
import Model.Customer;
import Model.Restaurant;
import Utils.Gender;
import Utils.Neighberhood;
import controller.PrimaryController;
import controller.Sounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CustomerSettingsController implements Initializable  {
	private static final String Input = "Rest.ser";
	PrimaryController control=new PrimaryController();
	//the customer currently logged in
	static Customer customer;

	@FXML
	private TextField customerId;
	@FXML
	private TextField customerFirst;
	@FXML
	private TextField customerLast;
	@FXML
	private DatePicker customerBirth;
	@FXML
	private TextField customerPass;
	@FXML
	private TextField customerPassVerify;
	@FXML
	private ComboBox<String> customerGenderCombo;
	@FXML
	private ComboBox<String> customerHoodCombo;
	@FXML
	private CheckBox customerGluten;
	@FXML
	private CheckBox customerLactose;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		customerId.setText(String.valueOf(customer.getId()));
		customerFirst.setText(customer.getFirstName());
		customerLast.setText(customer.getLastName());
		customerBirth.setValue(customer.getBirthDay());
		customerPass.setText(String.valueOf(customer.getPassword()));
		customerPassVerify.setText(String.valueOf(customer.getPassword()));
		customerGenderCombo.setValue(String.valueOf(customer.getGender()));
		customerHoodCombo.setValue(String.valueOf(customer.getNeighberhood()));
		customerGluten.setSelected(customer.isSensitiveToGluten());
		customerLactose.setSelected(customer.isSensitiveToLactose());		
	}
	
	/**************Update a Customer*************/
	public void updateCustomer(ActionEvent e) {
		String section = "Customer";
		try {
			//check if the combo box were selected
			if(customerGenderCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(customerHoodCombo.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			if(customerBirth.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
			int id=Integer.parseInt(customerId.getText());
			String password=customerPass.getText();
			String passwordVerify=customerPassVerify.getText();
			if(!password.equals(passwordVerify)) {
				throw new PasswordMismatchException();
			}
			String firstName=customerFirst.getText();
			String LastName=customerLast.getText();
			LocalDate localDate =customerBirth.getValue();
			String gender = customerGenderCombo.getValue();

			Gender selectedG = null;
			String neighberhood = customerHoodCombo.getValue();
			
			Neighberhood selectedN = null;
			
			for(Gender g : Gender.values()) {
				if(g.toString().equals(gender)) {
					selectedG = g;
				}
			}
			for(Neighberhood n : Neighberhood.values()) {
				if(n.toString().equals(neighberhood)) {
					selectedN = n;
				}
			}
			boolean lactose = customerLactose.isSelected();
			boolean gluten = customerGluten.isSelected();

			//if no new id, use the same id to be updated as the "new id"
			if(control.updateCustomerGUI(id,firstName, LastName, localDate, selectedG, password, passwordVerify, selectedN, lactose, gluten)) {
				successUpdate(section, "Success");
				Restaurant.save(Input);
			}
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			failUpdate(section, e1.toString());
		}
		refreshGui();
	}
	
	private void refreshGui() {
		customerId.setText(String.valueOf(customer.getId()));
		customerFirst.setText(customer.getFirstName());
		customerLast.setText(customer.getLastName());
		customerBirth.setValue(customer.getBirthDay());
		customerPass.setText(String.valueOf(customer.getPassword()));
		customerPassVerify.setText(String.valueOf(customer.getPassword()));
		customerGenderCombo.setValue(String.valueOf(customer.getGender()));
		customerHoodCombo.setValue(String.valueOf(customer.getNeighberhood()));
		customerGluten.setSelected(customer.isSensitiveToGluten());
		customerLactose.setSelected(customer.isSensitiveToLactose());	
		
	}

	public void successUpdate(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Updated Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
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
	
	public void failUpdate(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
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
	
	public void successSound() {
		Sounds s = new Sounds();
		try {
			s.addSound();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}

}
