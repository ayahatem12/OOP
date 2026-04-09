import java.util.ArrayList;

public class Prescription {
    private String patient_name;
    private String date;
    private ArrayList<String> medicins = new ArrayList<>();
    public int contact_num =11111;


    public ArrayList<String> getMedicins() {
        return medicins;
    }


    public void setMedicins(ArrayList<String> medicins) {
        this.medicins = medicins;
    }


    public int getContact_num() {
        return contact_num;
    }

    public String getPatient_name() {
        return this.patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setContact_num(int contact_num) {
        this.contact_num = contact_num;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public void getDetails() {
        System.out.println("Prescription for: " + this.getPatient_name());
        System.out.println("Date: " + this.getDate());
        System.out.println();
        System.out.println("Medicines: " );
        System.out.println(contact_num);
        System.out.println("------------------------------------------");
    }

}
