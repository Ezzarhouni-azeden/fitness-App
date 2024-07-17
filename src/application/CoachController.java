package application;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Models.CoachModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CoachController implements Initializable {

    @FXML
    private Button buttonHome;

    @FXML
    private Button logOutButton;
  
    @FXML
    private TextField adresseTextField;

    @FXML
    private Button refrech;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button myLabel;

    @FXML
    private AnchorPane coachPageAnchorPane;

    @FXML
    private TextField fullnameTextField;

    @FXML
    private TextField telSecoursTextField;
    @FXML
    private TextField telTextField;
    @FXML
    private TableView<CoachModel> coachTableView;

    @FXML
    private TableColumn<CoachModel, Integer> col_idCoach;
    
    @FXML
    private TableColumn<CoachModel, String> col_fullname;

    @FXML
    private TableColumn<CoachModel, String> col_adresse;

    @FXML
    private TableColumn<CoachModel,String > col_tel;

    @FXML
    private TableColumn<CoachModel,String > col_telSecours;


 
    ObservableList<CoachModel> listeCoach;
    int index= -1;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;

    
  @FXML
    public void coachToHome(ActionEvent event) throws IOException {
        try {
            SceneSwitcher.switchToScene(event, "Dashbord.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
  @FXML
     public void coachToLogin (ActionEvent event) throws IOException {
        try {
            SceneSwitcher.switchToScene(event, "Login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add() {

        conn=ConnectDB.connectDB();
        String  FullName= fullnameTextField.getText();
        String Telephone = telTextField.getText();
        String TelephoneSecours = telSecoursTextField.getText();
        String Adresse = adresseTextField.getText();
        if (!FullName.isEmpty() && !Telephone.isEmpty() && !TelephoneSecours.isEmpty() && !Adresse.isEmpty()) {
           String sql= "insert into coach (FullName,Telephone,TelephoneSecours,Adresse) values ('" + fullnameTextField.getText() + "','" + telTextField.getText() + "','" + telSecoursTextField.getText() + "','"+adresseTextField.getText()+"');";
            try {
                pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Member added successfully.");
                updateTable(null);

            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            }
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please fill in all the fields");
            alert.showAndWait();
        }
    }

   @FXML
    public void getSelected(MouseEvent event) {
        index= coachTableView.getSelectionModel().getSelectedIndex();
        if (index<= -1) {
            return;
        }
        
        fullnameTextField.setText(col_fullname.getCellData(index).toString());
        telTextField.setText(col_tel.getCellData(index).toString());
        telSecoursTextField.setText(col_telSecours.getCellData(index).toString());
        adresseTextField.setText(col_adresse.getCellData(index).toString());
    }
   
    public void edit() {
        try {

            conn= ConnectDB.connectDB();
            String value1= col_idCoach.getCellData(index).toString();
            String value2= fullnameTextField.getText();
            String value3= telTextField.getText();
            String value4= telSecoursTextField.getText();
            String value5= adresseTextField.getText();
            String sql="update coach set FullName= '"+value2+"',Telephone='"+value3+"',TelephoneSecours= '"+value4+"',Adresse= '"+value5+" 'where IdCoach= '"+value1+"' ";
            pst=conn.prepareStatement(sql);
            pst.execute();
           JOptionPane.showMessageDialog(null, "update");
            updateTable(null);
        } catch (Exception e) {
           Alert alert=new Alert(Alert.AlertType.WARNING);

    	    alert.setHeaderText("select Coach to update");
          alert.showAndWait();        }
    }

    public void delete() {
        conn=ConnectDB.connectDB();
        String sql="delete from coach where IdCoach =?;";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1, col_idCoach.getCellData(index).toString());
            pst.execute();
           JOptionPane.showMessageDialog(null, "Coach Deleted");
            updateTable(null);
        } 
        catch (Exception e) {
        	 Alert alert=new Alert(Alert.AlertType.WARNING);
       	     alert.setHeaderText("select Coach to delete");
             alert.showAndWait();
        }
    }

    public void updateTable(ActionEvent event) {
        col_idCoach.setCellValueFactory(new PropertyValueFactory<CoachModel, Integer>("IdCoach"));
        col_fullname.setCellValueFactory(new PropertyValueFactory<CoachModel, String>("FullName"));
        col_tel.setCellValueFactory(new PropertyValueFactory<CoachModel, String>("Telephone"));
        col_telSecours.setCellValueFactory(new PropertyValueFactory<CoachModel, String>("TelephoneSecours"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<CoachModel, String>("Adresse"));

        listeCoach =ConnectDB.getDataCoach();
        coachTableView.setItems(listeCoach);
    }

  @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateTable(null);
    }


}