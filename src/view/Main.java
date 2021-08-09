package view;
	
import Model.Restaurant;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	//the file to save all the data
	private static final String Input = "Rest.ser";
	
	public static void main(String[] args) {
		//load the data from the Rest.ser database
		Restaurant.load(Input);
		
		//save the data in Rest.ser database
		Restaurant.save(Input);
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("fxmlFolder\\JavaEatMain.fxml"));
			Scene scene = new Scene(root,1060,700);
			scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
			//TODO fix icon 
//			primaryStage.getIcons().add(NameOfIcon);
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaEat Restaurant");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
