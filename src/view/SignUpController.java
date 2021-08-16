package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.EmptyComboBoxException;
import Exceptions.IllegalCustomerException;
import Exceptions.IllegelInputException;
import Exceptions.IllegelPasswordException;
import Exceptions.PasswordMismatchException;
import Exceptions.SimilarIDInSystemException;
import Model.Restaurant;
import Utils.Gender;
import Utils.Neighberhood;
import controller.PrimaryController;
import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SignUpController implements Initializable {
	private static final String Input = "Rest.ser";
	
	/*The controller to add and remove everything from the GUI to the database*/
	PrimaryController control=new PrimaryController();
	@FXML
	private ImageView customerProfile;
	@FXML
	private Button customerAddPic;
	/**************************************Customer Page*****************************************/
	@FXML
	private AnchorPane mainPane;
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
	@FXML
	private Button getBack;
	@FXML
	private Button addCustomer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		/******************Load Neighborhood enum****************/
		ArrayList<String> neighberhoodsDB=new ArrayList<String>();
		for(Neighberhood n : Neighberhood.values()) {

			neighberhoodsDB.add(String.valueOf(n));
		}

		ObservableList<String> ObservableListNeighborhoods=FXCollections.observableArrayList();
		ObservableListNeighborhoods.addAll(neighberhoodsDB);
		customerHoodCombo.setItems(ObservableListNeighborhoods);
		
		/******************Load genders enum****************/
		ArrayList<String> gendersDB=new ArrayList<String>();
		for(Gender g : Gender.values()) {

			gendersDB.add(String.valueOf(g));
		}		
		ObservableList<String> observableListGenders=FXCollections.observableArrayList();
		observableListGenders.addAll(gendersDB);
		customerGenderCombo.setItems(observableListGenders);
		
	}
	


	/**************Add a customer*************/
	//working
	public void addCustomer(ActionEvent e){
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
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
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
			if(control.addCustomerFromGUI(id,firstName, LastName, localDate, selectedG, password, passwordVerify, selectedN, lactose, gluten)) {
				successAdded(section, "Success");
				Restaurant.save(Input);				
			}
			//if could not add customer
			else {
				fail(section,"Could not add customer to system!");
			}
//			Customer custToAdd = new Customer(firstName,LastName,localDate,selectedG,selectedN,lactose,gluten);
//			if(Restaurant.getInstance().addCustomer(custToAdd)) {
//			}

			System.out.println(Restaurant.getInstance().getCustomers());
			refreshGui();

			//pop up with success
			//exception-Customer adding failed,Customer already exists/illegal input
		}
		catch(EmptyComboBoxException e1) {
			failSelection("Gender/Neighborhood",e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(PasswordMismatchException e1) {
			fail(section, e1.toString());
		}
		catch(IllegelPasswordException e1) {
			fail(section,e1.toString());
		}
		catch(IllegalCustomerException e1) {
			fail(section,e1.toString());
		}
		catch(SimilarIDInSystemException e1) {
			fail(section,e1.toString());
		}
//		catch(NegativeNumberNotPriceException e1) {
//			badSound();
//			fail(a, e1.toString());
//		}
//		catch(ObjectExistException e1) {
//			badSound();
//			fail(a, "Person"+e1.toString());
//		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
	}
	
	
	/***************a method to save a photo*******************/
	public void uploadFile(ActionEvent e)
	{
		FileChooser fc=new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png") );
		File file= fc.showOpenDialog(null);


		if(file!=null)
		{
//			labelForFile.setFont(new Font(20));
//			labelForFile.setText("Uploaded successfully");	

			successUpload();
			File toFile = new File(file.getName());

			try {

				java.nio.file.Files.move( 
						file.toPath(), 
						toFile.toPath() ,StandardCopyOption.REPLACE_EXISTING);
				//get the file name using input stream
				InputStream stream = new FileInputStream(file.getName());
				//create a new image and use the uploaded file
			    Image image = new Image(stream);
			    //set the image in the image view
			    customerProfile.setImage(image);
			    
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
//		else
//		{
//			labelForFile.setFont(new Font(20));
//			labelForFile.setText("Add photo to procceed");		
//		}
	}
	
	public void refreshGui(){}
	/*log out from the system and back to the JavaEat main page*/
	//works
	public void goBack(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to return to the main page");
		al.setTitle("Return");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			goodSound();
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

	public void successUpload() {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Successfully uploaded photo!");
		al.setHeaderText("Upload");
		al.setTitle("Photo");
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
	
	public void successAdded(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
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
	
	/*sounds methods */
	public void successSound() {
		Sounds s = new Sounds();
		try {
			s.addSound();
		}catch(Exception e2) {
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
	
	public void goodSound() {
		Sounds s = new Sounds();
		try {
			s.successSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}	
