package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
  


public class LoginController {

  @FXML
     private Button toBienvenuePage;
  @FXML
  	 private Label myLabel;
  @FXML
  	 private Label loginPopUp;
  @FXML
  	 private TextField txtUsername;
  @FXML
  	 private TextField txtPassword;
  @FXML
  	 private Button btn; 	
  @FXML

	public void loginCode(ActionEvent event) throws IOException {
	    String username = txtUsername.getText();
	    String password = txtPassword.getText();
	    String verificationQuery = "SELECT COUNT(1) FROM salledefitness.admins WHERE Username = ? AND Password = ?";
	    
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/salledefitness", "root", "Azd123@");
	         PreparedStatement statement = con.prepareStatement(verificationQuery)) {
	        
	        statement.setString(1, username);
	        statement.setString(2, password);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next() && resultSet.getInt(1) == 1) {
	            	  loginPopUp.setText("Welcome!");

	                switchToScene2(event);
	            } else {
	                JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
	            }
	        }
	    } catch (SQLException e) {
	       
	        e.printStackTrace();
	    }
	}

	public void switchToScene2(ActionEvent event) throws IOException {
		  try {
	             SceneSwitcher.switchToScene(event, "Dashbord.fxml");
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	  }
	
	public void switchToScene3(ActionEvent event) throws IOException {
        try {
            SceneSwitcher.switchToScene(event, "Bienvenue.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	




}

