package Models;



public class CoachModel extends PersonneModel {

        private int IdCoach;
        private String TelephoneSecours,Adresse;

    public CoachModel(int IdCoach, String FullName, String Telephone,String TelephoneSecours,String Adresse) {
            super(FullName, Telephone);
            this.TelephoneSecours=TelephoneSecours;
            this.Adresse=Adresse;
        }

    public int getIdCoach() {
        return IdCoach;
    }

    public void setIdCoach(int IdCoach) {
        this.IdCoach = IdCoach;
    }

   
    public String getTelephoneSecours() {
        return TelephoneSecours;
    }

    public void setTelephoneSecours(String TelephoneSecours) {
        this.TelephoneSecours = TelephoneSecours;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }
}