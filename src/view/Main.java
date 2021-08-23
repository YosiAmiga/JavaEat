package view;
	
import java.io.File;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import Model.Restaurant;
import controller.Sounds;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.fxml.FXML;
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
			programOnSound();
			Parent root = FXMLLoader.load(getClass().getResource("fxmlFolder\\JavaEatMain.fxml"));
			Scene scene = new Scene(root,1080,750);
			scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
			//TODO fix icon 
//			primaryStage.getIcons().add(new Image("file:icon.ico"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaEat Restaurant");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void programOnSound() {
		Sounds s = new Sounds();
		try {
			s.programOnSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
//	private File file;
//	private Media media;
//	private MediaPlayer mediaPlayer;
//	@FXML
//	private MediaView video;
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		file= new File ("C:\\Users\\Nitza\\Desktop\\temp.mp4");
//		media = new Media (file.toURI().toString());
//		mediaPlayer= new MediaPlayer(media);
//		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
//		video.setMediaPlayer(mediaPlayer);
//		video.setFitHeight(500);
//		video.setFitWidth(500);
//		mediaPlayer.setMute(true);
//		mediaPlayer.play();
//		
//		
//	}

	
}
