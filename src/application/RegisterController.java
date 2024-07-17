package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterController {

	   @FXML
	    private Label PopUpFullName;

	    @FXML
	    private Label PopUpPassConfirmRegister;

	    @FXML
	    private Label PopUpPassReg;

	    @FXML
	    private Label PopUpUser;

	    @FXML
	    private Button goToSignInPage;

	    @FXML
	    private PasswordField newConfirmPassField;

	    @FXML
	    private TextField newNameField;

	    @FXML
	    private PasswordField newPassField;

	    @FXML
	    private TextField newUserField;

	    @FXML
	    private AnchorPane registerSceneAnchorPane;

	    @FXML
	    private Button signUpButton;

	    @FXML
	    void add_btn(ActionEvent event) throws SQLException {
	    	Connection conn = ConnectDB.connectDB();

         String FullName = newNameField.getText();
         String Username = newUserField.getText();
         String Password = newPassField.getText();
         String newp=newConfirmPassField.getText();
         if (!FullName.isEmpty() && !Username.isEmpty() && !Password.isEmpty() && !newp.isEmpty()&& Password.equals(newp)  ) {

         String sql="insert into admins (FullName,Username,Password) VALUES('"+FullName+"', '"+Username+"','"+Password+"')";
         Statement statement=conn.createStatement();
         statement.execute(sql);
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText("You succesfully added data to Database!");
         alert.setTitle("SUCCESSFUL");
         alert.showAndWait();
         }
         else if(!newp.isEmpty() && !Password.equals(newp)) {  
        	 Alert alert=new Alert(Alert.AlertType.WARNING);
        	 alert.setHeaderText("Please enter the same password");
             alert.showAndWait();
        	 
         } 
        	 
         else {
        	 
        	 Alert alert=new Alert(Alert.AlertType.ERROR);

        	    alert.setHeaderText("Please fill in all the fields");
                alert.showAndWait();
         
         }
	    }
	 
	    @FXML
		public void switchToScene1(ActionEvent event) throws IOException {
	        try {
	             SceneSwitcher.switchToScene(event, "PreHomeGerant.fxml");
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		}
		 
	
}
