package application;

	
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

	public class BienvenueController {

	    @FXML
	    private Button buttonToAdminLogin;

	    @FXML
	    private Button buttonToGerantLogin;

	    //les methodes pour changer les pages en click
	    @FXML
	    void bienvenueToAdminLogin(ActionEvent event) throws IOException {
	   
	    	 try {
	             SceneSwitcher.switchToScene(event, "Login.fxml");
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	    }

	    @FXML
	    void bienvenueToGerantLogin(ActionEvent event)throws IOException {

	    	 try {
	             SceneSwitcher.switchToScene(event, "GerantVerification.fxml");
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	    	
	    }

	}

	


