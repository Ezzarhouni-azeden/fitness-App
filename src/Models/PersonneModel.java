package Models;


public abstract class PersonneModel {
	static int cmpt=0;
    protected  String FullName;
    protected String Telephone;
    protected int idPersonne;


    public PersonneModel(String fullName, String Telephone) {
    	this.idPersonne=cmpt;
        this.FullName = fullName;
        this.Telephone = Telephone;
        cmpt++;
    }

  
    public String getFullName() {
        return FullName;
    }

    public int getIdPersonne() {
		return idPersonne;
	}


	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}


	public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

}