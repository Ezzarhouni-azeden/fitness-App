package Models;

import java.sql.Date;

public class MembersModel extends PersonneModel{

  private String Gender,Payment;
  private Date StartDate,EndDate;
  private int IdMember;
  
  
  
    public  MembersModel(int IdMember,String FullName, String Telephone ,String Gender,Date StartDate,Date EndDate,String Payment) {
		
        super(FullName, Telephone);
    	this.IdMember=IdMember;
		this.Gender=Gender;
		this.StartDate=StartDate;
		this.EndDate=EndDate;
		this.Payment=Payment;
		}
	
	public int getIdMember() {
	return IdMember;
	}
     public void setId(int IdMember) {
	this.IdMember = IdMember;
     }
    

    public String getGender() {
		return Gender;
	}

    public void setGender(String Gender) {
		this.Gender = Gender;
	}

    public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date StartDate) {
		this.StartDate = StartDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date EndDate) {
		this.EndDate = EndDate;
	}
    public String getPayment() {
		return Payment;
	}

    public void setPayment(String Payment) {
    	this.Payment = Payment;
	}

	
	
	   
	}