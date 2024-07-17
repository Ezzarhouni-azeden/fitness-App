package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class GerantVerificationController {

	
	    @FXML
	    private Label loginPopUp;

	    @FXML
	    private Button toBienvenue;

	    @FXML
	    private PasswordField txtPassword;

	    @FXML
	    private TextField txtUsername;

	    @FXML
	    private Button verificationGerantButton;

	    @FXML
	    void gerantVerifToBienvenue(ActionEvent event) throws IOException {
	    	
	    	try {
	             SceneSwitcher.switchToScene(event, "Bienvenue.fxml");
	         } catch (IOException e) {
	             e.printStackTrace();
	         } 	
	    }

	    @FXML
	    void verificationGerant(ActionEvent event) throws IOException {
	    	
	    	if(txtUsername.getText().equals("gerant") &&txtPassword.getText().equals("gerant1")   )
	    	{
	    		 try {
		             SceneSwitcher.switchToScene(event, "PreHomeGerant.fxml");
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
	    	}
	    	else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");

	    	}		

	    }

	}

	
	
	
	
	
	
	
	
	
	
	