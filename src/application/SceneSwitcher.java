package application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

    public static void switchToScene(javafx.event.ActionEvent event, String fxmlFile) throws IOException {
        // Charge le fichier FXML spécifié
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlFile));
        // Récupère le stage actuel à partir de l'événement
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Crée une nouvelle scène avec le root chargé
        Scene scene = new Scene(root);
        // Change la scène du stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
	
	
	
	
	
	
	
	

