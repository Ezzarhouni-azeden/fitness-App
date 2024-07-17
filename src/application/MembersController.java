package application;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Models.MembersModel;

import java.util.logging.Level;
import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

public class MembersController implements Initializable {

   
    @FXML
    private Button btnHome;

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
    private TextField txtAmount;

    @FXML
    private TextField txtFulname;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TextField txtPhone;

    @FXML
    private DatePicker EDate;

    @FXML
    private DatePicker SDate;

    @FXML
    private TextField txtGender;

    @FXML
    private TableView<MembersModel> table;

    @FXML
    private TableColumn<MembersModel, Integer> col_id;

    @FXML
    private TableColumn<MembersModel, String> col_Fulname;

    @FXML
    private TableColumn<MembersModel, String> col_Gender;

    @FXML
    private TableColumn<MembersModel, String> col_Payment;

    @FXML
    private TableColumn<MembersModel, String> col_Phone;

    @FXML
    private TableColumn<MembersModel, Date> col_EndDate;

    @FXML
    private TableColumn<MembersModel, Date> col_StartDate;
    
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        try {
            SceneSwitcher.switchToScene(event, "Dashbord.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        try {
            SceneSwitcher.switchToScene(event, "Login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void switchToScene3(ActionEvent event) throws IOException {
        try {
            SceneSwitcher.switchToScene(event, "Register.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<MembersModel>listM;
    int index= -1;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    ObservableList<MembersModel> membersSearchModelObservableList = FXCollections.observableArrayList();

//la methode pour chercher
    public void initializeLogic() {

        String dynamiqueSearchQuery = "select IdMember,FullName,Gender,Telephone,StartDate,EndDate,Payment from members;";

        try {
            membersSearchModelObservableList.clear();

            conn = ConnectDB.connectDB();

            pst = conn.prepareStatement(dynamiqueSearchQuery);

            rs = pst.executeQuery();


            while (rs.next()) {

                Integer resultId = rs.getInt("IdMember");
                String resultFullname = rs.getString("FullName");
                String resultGender = rs.getString("Gender");
                String resultPhone = rs.getString("Telephone");
                Date resultStartDate = rs.getDate("StartDate");
                Date resultEndDate = rs.getDate("EndDate");
                String resultPayment = rs.getString("Payment");

                membersSearchModelObservableList.add(new MembersModel(resultId, resultFullname, resultGender, resultPhone, resultStartDate, resultEndDate, resultPayment));

            }
           


            col_id.setCellValueFactory(new PropertyValueFactory<>("IdMember"));
            col_Fulname.setCellValueFactory(new PropertyValueFactory<>("FullName"));
            col_Gender.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
            col_Phone.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            col_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
            col_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
            col_Payment.setCellValueFactory(new PropertyValueFactory<>("Payment"));

            // t3mar dial table view

            table.setItems(membersSearchModelObservableList);


            FilteredList<MembersModel> filterMySearch = new FilteredList<>(membersSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterMySearch.setPredicate(members -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String keywordToSearch = newValue.toLowerCase();

                    if (((Integer) members.getIdMember()).toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if ((members).getFullName().toLowerCase().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getGender().toLowerCase().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if ((members).getTelephone().toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getStartDate().toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getEndDate().toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getPayment().toLowerCase().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else return false;


                });
            });

            SortedList<MembersModel> sortedData = new SortedList<>(filterMySearch);

            sortedData.comparatorProperty().bind(table.comparatorProperty());

            table.setItems(sortedData);


        } catch (Exception e) {
            Logger.getLogger(MembersController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    	//l operation de recherche dans la table
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

          String dynamiqueSearchQuery = "select IdMember,FullName,Gender,Telephone,StartDate,EndDate,Payment from members;";
          try {
            conn = ConnectDB.connectDB();
            pst = conn.prepareStatement(dynamiqueSearchQuery);
            rs = pst.executeQuery();
            while (rs.next()) {
                Integer resultId = rs.getInt("IdMember");
                String resultFullname = rs.getString("FullName");
                String resultGender = rs.getString("Gender");
                String resultPhone = rs.getString("Telephone");
                Date resultStartDate = rs.getDate("StartDate");
                Date resultEndDate = rs.getDate("EndDate");
                String resultPayment = rs.getString("Payment");

             membersSearchModelObservableList.add(new MembersModel(resultId, resultFullname, resultGender, resultPhone, resultStartDate, resultEndDate, resultPayment));
            }
            //PropertyValueFactory is the new ClientSearchModel fields
            col_id.setCellValueFactory(new PropertyValueFactory<>("IdMember"));
            col_Fulname.setCellValueFactory(new PropertyValueFactory<>("FullName"));
            col_Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            col_Phone.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
            col_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
            col_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
            col_Payment.setCellValueFactory(new PropertyValueFactory<>("Payment"));
            // remplissage du table view
            table.setItems(membersSearchModelObservableList);
            FilteredList<MembersModel> filterMySearch = new FilteredList<>(membersSearchModelObservableList, b -> true);
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterMySearch.setPredicate(members -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String keywordToSearch = newValue.toLowerCase();
                    if (((Integer) members.getIdMember()).toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getFullName().toLowerCase().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getGender().toLowerCase().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getTelephone().toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getStartDate().toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getEndDate().toString().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else if (members.getPayment().toLowerCase().indexOf(keywordToSearch) > -1) {
                        return true;
                    } else return false;

                });
            });

            SortedList<MembersModel> sortedData = new SortedList<>(filterMySearch);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        } catch (SQLException e) {
            Logger.getLogger(MembersController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
         updateTable(null);
    }

//la methode qui ajoute un membre
    public void add() {

        conn=ConnectDB.connectDB();
        String FullName = txtFulname.getText();
        String Gender = txtGender.getText();
        String Phone = txtPhone.getText();
        String Payment=txtAmount.getText();
        if (!FullName.isEmpty() && !Gender.isEmpty() && !Phone.isEmpty() && !(SDate.getValue()==null) && !(EDate.getValue()==null) && !Payment.isEmpty()  ) {

            String sql="insert into members (FullName,Gender,Telephone,StartDate,EndDate,Payment)values(?,?,?,?,?,?)";
            try {
                pst=conn.prepareStatement(sql);
                pst.setString(1, txtFulname.getText());
                pst.setString(2, txtGender.getText());
                pst.setString(3, txtPhone.getText());
                pst.setString(4, String.valueOf(SDate.getValue()) );
                pst.setString(5, String.valueOf(EDate.getValue()));
                pst.setString(6, txtAmount.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Member added successfully");
                 updateTable(null);

            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
            }
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please fill in all the fields");
            alert.showAndWait();
        }
    }

    @FXML
    void getSelected(MouseEvent event) {
        index=table.getSelectionModel().getSelectedIndex();
        if (index<= -1) {
            return;
        }

        txtFulname.setText(col_Fulname.getCellData(index).toString());
        txtGender.setText(col_Gender.getCellData(index).toString());
        txtPhone.setText(col_Phone.getCellData(index).toString());
        Object md = table.getSelectionModel().getSelectedItem();
        SDate.setValue(LocalDate.parse(String.valueOf(((MembersModel) md).getStartDate())));
        EDate.setValue(LocalDate.parse(String.valueOf(((MembersModel) md).getEndDate())));
        txtAmount.setText(col_Payment.getCellData(index).toString());

    }
    public void edit() {
        try {

            conn=ConnectDB.connectDB();
            String   value1=col_id.getCellData(index).toString();
            String value2=txtFulname.getText();
            String value3=txtGender.getText();
            String value4=txtPhone.getText();
            LocalDate value5=SDate.getValue();
            LocalDate value6=EDate.getValue();
            String value7=txtAmount.getText();
            String sql="update members set FullName= '"+value2+"',Gender='"+value3+"',Telephone= '"+value4+"',StartDate= '"+value5+"',EndDate= '"+value6+"',Payment= '"+value7+" 'where IdMember= '"+value1+"' ";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Member updated");
           updateTable(null);
        }
        catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);

            alert.setHeaderText("select Member to update");
            alert.showAndWait();

        }
    }

    public void delete() {
        conn=ConnectDB.connectDB();
        String sql="delete from members where IdMember = ?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,col_id.getCellData(index).toString());


            pst.execute();
            JOptionPane.showMessageDialog(null, " Member Deleted");
           updateTable(null);



        }

        catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);

            alert.setHeaderText("select Member to delete");
            alert.showAndWait();
        }
    }


    public void updateTable(MouseEvent event) {

         initializeLogic();

    }









}