package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PreHomeGerantController {

    @FXML
    private Button addAdminButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button goToBienvenueButton;

    @FXML
    void PreHomeToBienvenue(ActionEvent event)throws IOException {
    	
    	 try {
             SceneSwitcher.switchToScene(event, "Bienvenue.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @FXML
    void PreHomeToDashboard(ActionEvent event) throws IOException {
    	 try {
             SceneSwitcher.switchToScene(event, "Dashbord.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @FXML
    void PreHomeToRegister(ActionEvent event)throws IOException {
    	 try {
             SceneSwitcher.switchToScene(event, "Register.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }  	

    }

}
