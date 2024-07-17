
package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Models.CoachModel;
import Models.MembersModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConnectDB {
 Connection conn=null;

 
//etablir une connection avec la base de donnee
public static  Connection connectDB()  {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); // Setup the connection with the DB
		Connection  conn  = DriverManager.getConnection("jdbc:mysql://localhost/salledefitness", "root","Azd123@");  
		  return conn;

	} catch (Exception e) {
//affichage de tableau si la connection a echouee
JOptionPane.showMessageDialog(null, e)	;
return null;
	}
}

//methode getDatamembers qui nous permet de retirer les donnees des membres de la BD
public static ObservableList<MembersModel>getDatamembers() {
	Connection conn= connectDB();	
	ObservableList<MembersModel>list=FXCollections.observableArrayList();
	try {
		//ps=requette sql
		PreparedStatement ps=conn.prepareStatement("select * from members");
		//rs=retour de la requette
		ResultSet rs=ps.executeQuery();
        //iteration sur la BD
		while (rs.next()) {
            int id = rs.getInt("IdMember");
            String fullname = rs.getString("FullName");
            String gender = rs.getString("Gender");
            String phone = rs.getString("Telephone");
            Date startDate = rs.getDate("StartDate");
            Date endDate = rs.getDate("EndDate");
            String payment = rs.getString("Payment");
        	//creation d'objet CoachModel avec ce que contient la BD
            list.add(new MembersModel(id, fullname, gender, phone, startDate, endDate, payment));  
        }
	} catch (Exception e) {
	}
	return list;
}


//methode getDatamembers qui nous permet de retirer les donnees des coachs de la BD
public static ObservableList<CoachModel> getDataCoach() {
	//etablir une connection avec BD
    Connection conn= connectDB();
    //collection observable list qui contient les coachs
    ObservableList<CoachModel> list= FXCollections.observableArrayList();
    try {
		//ps=requette sql
        PreparedStatement ps=conn.prepareStatement("select * from coach");
		//rs=retour de la requette
        ResultSet rs=ps.executeQuery();
        //iteration sur la BD
        while(rs.next()) {
        	//creation d'objet CoachModel avec ce que contient la BD
            list.add(new CoachModel(rs.getInt("IdCoach"),rs.getString("FullName"),rs.getString("Telephone"),rs.getString("TelephoneSecours"),rs.getString("Adresse")));
        }
    } catch (Exception e) {
    }
    return list;

}
}
