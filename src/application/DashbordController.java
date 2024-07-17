package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashbordController implements Initializable {

    @FXML
    private Label Admin;

    @FXML
    private Button Coach;

    @FXML
    private Label Dsh_Ncoachs;

    @FXML
    private Label Dsh_Nmembers;

    @FXML
    private Label Dsh_income;

    @FXML
    private AreaChart<?, ?> Dsh_incomeData;

    @FXML
    private Button Members;

    @FXML
    private Label Welcome;

    @FXML
    private Button btnLogout;

    Connection conn=null; 
    ResultSet rs=null;
    PreparedStatement pst=null;
   
    @FXML
	public void switchToScene1(ActionEvent event) throws IOException {
    	 try {
             SceneSwitcher.switchToScene(event, "Members.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }
	  }
    
    @FXML
   	public void switchToScene2(ActionEvent event) throws IOException {
   		 try {
             SceneSwitcher.switchToScene(event, "Bienvenue.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }
   	  }
    @FXML
   	public void switchToScene3(ActionEvent event) throws IOException {
   		 
   		try {
            SceneSwitcher.switchToScene(event, "Coach.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
   	  }
    @FXML
   	public void switchToScene4(ActionEvent event) throws IOException {
       	 try {
                SceneSwitcher.switchToScene(event, "Registration.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
   	  }
   public void dashNMembers() {
   	 conn = ConnectDB.connectDB();
   	String sql="select count(IdMember)from members ";
   	int nm=0;
   	try {
   		 pst = conn.prepareStatement(sql);
    	rs=pst.executeQuery();	
    	if(rs.next()) {
    		nm=rs.getInt("count(IdMember)");
    	}
    	Dsh_Nmembers.setText(String.valueOf(nm));
    	} catch (Exception e) {
    		e.printStackTrace();
	}
}
   public void dashNCoach() {
	   	 conn = ConnectDB.connectDB();
	   	String sql="select count(IdCoach)from coach ";
	   	int nm=0;
	   	try {
	   		 pst = conn.prepareStatement(sql);
	    	rs=pst.executeQuery();	
	    	if(rs.next()) {
	    		nm=rs.getInt("count(IdCoach)");
	    	}
	    	Dsh_Ncoachs.setText(String.valueOf(nm));
	    	} catch (Exception e) {
	    		e.printStackTrace();
		}
	}
   public void dashIncome() {
	   	 conn = ConnectDB.connectDB();
	   	String sql="select sum(Payment)from members ";
	   	int nm=0;
	   	try {
	   		 pst = conn.prepareStatement(sql);
	    	rs=pst.executeQuery();	
	    	if(rs.next()) {
	    		nm=rs.getInt("sum(Payment)");
	    	}
	    	Dsh_income.setText(String.valueOf(nm)+"Dh");
	    	} catch (Exception e) {
	    		e.printStackTrace();
		}
	}
   @SuppressWarnings({ "unchecked", "rawtypes" })
public void dashChart() {
	   Dsh_incomeData.getData().clear();
	   	 conn = ConnectDB.connectDB();
	   	String sql="select StartDate, sum(Payment) from members group by StartDate";
	   	//Dsh_incomeData.setTitle("Line Chart Sample");
	   	Dsh_incomeData.setStyle("-fx-alternative-row-fill-visible: true;");

	   	XYChart.Series chart=new XYChart.Series();
	   	try {
	   		 pst = conn.prepareStatement(sql);
	    	rs=pst.executeQuery();	
	    	while(rs.next()) {
	    		double paymentValue = Double.parseDouble(rs.getString(2));
             chart.getData().add(new XYChart.Data(String.valueOf(rs.getDate(1)),paymentValue));	    
             }
	    	Dsh_incomeData.getData().add(chart);
	    	} catch (Exception e) {
	    		e.printStackTrace();
		}
	}
   @Override
  	public void initialize(URL url, ResourceBundle rb) {
	   dashNMembers();
	   dashNCoach();
	   dashIncome();
	   dashChart();
        }
     
   
   
   

   
   
   
   
}
