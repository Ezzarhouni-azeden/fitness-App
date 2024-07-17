package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegistrationController {
	
	@FXML
    private Button back;

	
	 @FXML
	    public void RegistrationToDashbord(ActionEvent event) throws IOException {
	        try {
	            SceneSwitcher.switchToScene(event, "Dashbord.fxml");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	
	
}
