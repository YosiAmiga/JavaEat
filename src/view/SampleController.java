package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


import controller.Sounds;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Model.*;


public class SampleController implements Initializable{

	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private TextField username;

	@FXML
	private Button login;
	
	@FXML
	private PasswordField password;

	@FXML
	private Button forgot;

	@FXML
	private Button backPage;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void login(ActionEvent e) throws IOException {
		try {
			Person userType;
			
			/*if the user input for both username and password is manager, then login as an manager*/
			//works, only let you get into manager page if you enter correctly 
			if(username.getText().equals("m") && password.getText().equals("m")) {
				userType = Manager.getInstance();
				goodSound();
				StackPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\ManagerMainPage.fxml"));
				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
				mainPane.getChildren().removeAll(mainPane.getChildren());
				mainPane.getChildren().add(pane);
			}
			/*if the user enter any username (his id) or any password, create this user and check him in the database*/
//			else {
//				userType = loginCheck(Integer.parseInt(username.getText()),password.getText());
//			}
			if(username.getText().equals("User") && password.getText().equals("User")) {
				goodSound();
				StackPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\CustomerMainPage.fxml"));
				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
				mainPane.getChildren().removeAll(mainPane.getChildren());
				mainPane.getChildren().add(pane);
			}
//			
//			if(userType instanceof Customer) {
//				//move to customer xml
//				goodSound();
//				CustomerMainPageController.setCustomer(Restaurant.getInstance().getCustomers().get(Integer.parseInt(username.getText())));
//				StackPane pane=FXMLLoader.load(getClass().getResource("ManagerMainPage.fxml"));
//				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
//				mainPane.getChildren().removeAll(mainPane.getChildren());
//				mainPane.getChildren().add(pane);
//			}
			
			
		}catch(Exception ex) {
			System.err.println("error");
		}
	}
	
	/*a method to check the login of a user if he is in the database*/
	/**
	 * 
	 * @param id - the id of the given customer
	 * @param password - the full name of the given customer -> first name + last name, combined
	 * @return the customer itself
	 */
	public Customer loginCheck(int id, String password) {
		//take the given 
		Customer cust = Restaurant.getInstance().getRealCustomer(id);
		System.out.println(cust);
//		if(cust == null) {
//			//throw new exception of illegal user
//		}
//		
		if(cust.getPassword().equals(password)) {
			return cust;
		}
		//TODO else{ new exception of illegal password
		return null;
	}

	public void ForgotPassword(ActionEvent e)
	{
		goodSound();
//		try {
//			FXMLLoader loader=new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
//			Parent root1=(Parent)loader.load();
//			Stage stage=new Stage();
//			stage.setTitle("Forgot Password");
//			stage.setScene(new Scene(root1));
//			ForgotPasswordController.stage=stage;
//			stage.show();
//			
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}
	
	

//    @FXML
//    void nextPage(ActionEvent e) {
//    	Employee emp = new Employee();
//    	emp.name = "Jon Snow";
//        emp.address = "Draganston, Winterfall";
//        emp.number = 1;
//        
//    	Employee emp2 = new Employee();
//    	emp2.name = "Moshe Moshe";
//        emp2.address = "Draganston, Winterfall";
//        emp2.number = 2;
//        
//        ArrayList<Employee> EmpArray = new ArrayList<>();
//        EmpArray.add(emp);
//        EmpArray.add(emp2);
//        try {
//           FileOutputStream fileOut = new FileOutputStream("employee.ser");//name of the folder we create
//           ObjectOutputStream out = new ObjectOutputStream(fileOut);
//           for(Employee temp : EmpArray) {
//        	   out.writeObject(temp);
//        	   
//           }
//           out.close();
//           fileOut.close();
//           System.out.printf("Serialized data is saved in employee.ser");
//
//        } catch (IOException i) {
//           i.printStackTrace();
//        }
//     }
//    @FXML
//    void DeserializeDemo(ActionEvent e) {
//    	Employee emp = null;
//        try {
//           FileInputStream fileIn = new FileInputStream("employee.ser");
//           ObjectInputStream in = new ObjectInputStream(fileIn);
//           emp = (Employee) in.readObject();
//           in.close();
//           fileIn.close();
//        } catch (IOException i) {
//           i.printStackTrace();
//           return;
//        } catch (ClassNotFoundException c) {
//           System.out.println("Employee class not found");
//           c.printStackTrace();
//           return;
//        }
//        
//        System.out.println("Deserialized Employee...");
//        System.out.println("Name: " + emp.name);
//        System.out.println("Address: " + emp.address);
//        System.out.println("Number: " + emp.number);
//     }
    
    
//    @FXML
//    void backPage(ActionEvent e) {
//    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
//    }
    //spin the circle

    
//    public void Login(ActionEvent e) throws IOException
//    {
//    	try {
//    		Person user;
//    		/*if the given user name and password were "admin" then login to the system as an admin*/
//    		if(username.getText().equals("admin") && password.getText().equals("admin"))
//    		{
//    			user = Manager.getInstance();
//    		}
//    		else
//    			user=loginSystem(Long.parseLong(username.getText()),password.getText());
//    		if(user instanceof Customer)
//    		{
//    			goodSound();
//    			CustomerMainPageController.setCustomer(Restaurant.getInstance().getCustomers().get(Long.parseLong(username.getText())));
//    			StackPane pane=FXMLLoader.load(getClass().getResource("CustomerMainPage.fxml"));
//    			pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
//    			mainPane.getChildren().removeAll(mainPane.getChildren());
//    			mainPane.getChildren().add(pane);
//    		}
//    		else if(user instanceof Guide)
//    		{
//    			goodSound();
//    			GuideMainPageController.setGuide(Shared.getInstance().getGuides().get(Long.parseLong(id.getText())));
//    			StackPane pane=FXMLLoader.load(getClass().getResource("GuideMainPage.fxml"));
//    			pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
//    			mainPane.getChildren().removeAll(mainPane.getChildren());
//    			mainPane.getChildren().add(pane);
//    		}
//    		else
//    		{
//    			goodSound();
//    			StackPane pane=FXMLLoader.load(getClass().getResource("AdminMainPage.fxml"));
//    			pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
//    			mainPane.getChildren().removeAll(mainPane.getChildren());
//    			mainPane.getChildren().add(pane);
//    		}
//    	} 
//    	catch (IllegelPasswordException e1) {
//    		badSound();
//    		Alert al = new Alert(Alert.AlertType.ERROR);
//    		al.setContentText("Please try again");
//    		al.setHeaderText("Wrong Password");
//    		al.setTitle("Error");
//    		al.setResizable(false);
//    		al.showAndWait();
//    	}
//    	catch (NumberFormatException e1) {
//    		badSound();
//    		Alert al = new Alert(Alert.AlertType.ERROR);
//    		al.setContentText("Please try again");
//    		al.setHeaderText("Wrong User Name");
//    		al.setTitle("Error");
//    		al.setResizable(false);
//    		al.showAndWait();
//    	}
//    	catch (IllegelUserNameException e1) {
//    		badSound();
//    		Alert al = new Alert(Alert.AlertType.ERROR);
//    		al.setContentText("Please try again");
//    		al.setHeaderText("Wrong User Name");
//    		al.setTitle("Error");
//    		al.setResizable(false);
//    		al.showAndWait();
//    	}
//    	catch (Exception e1) {
//    		e1.printStackTrace();
//    	}
//    	
//    }
//    
//    
//    /**
//     * method to login to the system
//     * @param id of person
//     * @param password of person
//     */
//    public Person loginSystem(long id, String password) throws Exception{
//    	Person user = Restaurant.getInstance().getUserConfirmation().get(id);
//    	if(user == null)
//    		throw new IllegelUserNameException();
//    	
//    	if(user.getPassword().equals(password))
//    	{
//    		return user;
//    	}
//    	else
//    		throw new IllegelPasswordException();
//    }
//    
    
    
    
    
    
    
    
    
    
    
/*sounds methods */
	
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