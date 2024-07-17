package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.sql.*;

public class GoFit extends Application {
	@Override
	public void start(Stage primaryStage) {
		  primaryStage.setTitle("gym: Go-Fit");
		try {
			 // Load the main scene from the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
            primaryStage.setMinHeight(550);
            primaryStage.setMinWidth(700);
         
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		launch(args);
		

	

	}	
}
