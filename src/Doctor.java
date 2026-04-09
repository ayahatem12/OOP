import  java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends User {
    Scanner input = new Scanner(System.in);
    //class attributes
    private String Specialization;
    ArrayList<Appointment> availableDaysandhours = new ArrayList<>();  // Array of ArrayLists for each day
    public ArrayList<Patient> myPatients = new ArrayList<>();
    private String availableDay;
    private int availableHour;

    private boolean exceptions_handling(String day,int hour){
        if(!(day.equalsIgnoreCase("Sunday")||day.equalsIgnoreCase("Monday")||day.equalsIgnoreCase("Tuesday")||day.equalsIgnoreCase("Wednsday")||day.equalsIgnoreCase("Thursday")||day.equalsIgnoreCase("Friday")||day.equalsIgnoreCase("Saturday"))){
            System.out.println("Invalid day. Please enter a valid day (e.g., monday, tuesday):");
            return false;
        }
        else if(hour<1 || hour>12){
            System.out.println("Invalid hour. Please enter an hour between 1 and 12..");
            return false;
        }
        else{
            return true;
        }

    }
    //class constructor,getters and setters
    public Doctor() {
        super(null, null, null, null, null, null);
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public boolean setAvailableDaysandhours(String day, int hour) {

        boolean added = false;
        if(exceptions_handling(day,hour)) {
            for (Doctor doctor : Clinic.doctors) {
                for (Appointment availability : doctor.availableDaysandhours) {
                    //checking if there exists another doctor available at this time
                    if (day.equalsIgnoreCase(availability.getDay()) && hour == availability.getHour()) {
                        if (!getUsername().equals(doctor.getUsername())) {
                            System.out.println("Another doctor is already available at this time. Please choose another day. ");
                        } else {
                            System.out.println("You are already available at this time ");
                        }
                        return added;
                    }
                }
            }


            Appointment available = new Appointment(day, hour);
            availableDaysandhours.add(available);
            added = true;
            System.out.println("Your availability has been successfully set for "+day +" at "+hour);
            return added;
        }
        else {
            return added;
        }
    }

    public boolean ChangeAvailability(String changedDay, int changedHour, String newDay, int newHour) {
        if(exceptions_handling(changedDay,changedHour)&&exceptions_handling(newDay,newHour)) {

            if(availableDaysandhours==null) {
                System.out.print("Error: Please add your available days and hours before attempting to change.");
                return false;
            }

            for (Appointment available : availableDaysandhours) {
                if (changedDay.equals(available.getDay()) && changedHour == available.getHour()) {
                    boolean set = setAvailableDaysandhours(newDay, newHour);
                    if (set) {
                        availableDaysandhours.remove(available);

                        System.out.println("Your availability has been updated successfully.");
                        return set;
                    }
                    else {return false;}
                }
            }

            System.out.println("No availability found for the specified time. You are not available at this time.");
            return false;
        }
        else
        {return  false;}
    }

    public boolean DisplayAvailableDaysandhours(Boolean doc) {
        boolean found=false;
        for (Appointment available : availableDaysandhours) {
            boolean isAvailable = true;

            for (Patient patient : myPatients) {
                for (Appointment app : patient.my_app) {
                    if (available.getDay().equalsIgnoreCase(app.getDay()) && available.getHour() == app.getHour()) {
                        isAvailable = false;
                        break;
                    }
                }
                if (!isAvailable) {
                    break;
                }
            }

            if (isAvailable ) {
                if(doc){
                    System.out.println("Your Available Days Are:");
                }
                found=true;
                System.out.println("Day       Hour    UserName");
                System.out.print(available.getDay() + "\t");
                System.out.print(available.getHour() + "\t\t");
                System.out.println(getUsername());
            }
            System.out.println("---------------------------------------------------------");
        }

        return found;

    }

    public void add_patient(Patient p){
        myPatients.add(p);
    }

    //Method to show all appointments for a given day

    public void ShowAllAppointments(String day){
        boolean found=false;
        if(exceptions_handling(day,1)){

            for(Patient patient:myPatients){
                for (Appointment appointment: patient.my_app) {
                    if (appointment.getDay().equals(day)) {
                        System.out.println("Appointments on " + day + ":");
                        for(Appointment availability:availableDaysandhours) {
                            if (appointment.getHour() == availability.getHour()) {
                                System.out.println(appointment.getHour());
                                found = true;
                                break;
                            }
                        }
                    }
                }}
            if (found==false){
                System.out.println("No appointments found for " + day + "!");
            }
        }
        else{
            found=false;
        }
    }

    public boolean GetPatientInfo(String day,int hour){
        if(exceptions_handling(day,hour)) {
            boolean found = false;
            for (Patient pat : myPatients) {
                for (Appointment app : pat.my_app) {
                    if (app.getHour() == hour && app.getDay().equalsIgnoreCase(day)) {
                        System.out.println("The Patient information is: ");
                        pat.getpatient();
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No Appointments found for that time!");
            }
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean CreatePrescription(String patientsName, String date, ArrayList<String> medicines, String patientsUserName){
        Boolean added=false;

        for(Patient patient:myPatients){
            if(patient.getUsername().equals(patientsUserName)) {
                Prescription prescription=new Prescription();
                prescription.setPatient_name(patientsName);
                prescription.setDate(date);
                prescription.setMedicins(medicines);
                patient.setMy_Prescription(prescription);
                added=true;
                return added;
            }
        }

        System.out.println("The username is incorrect!");
        return added;
    }

    public void GetReceptionistContactInfo(){
        System.out.println(Clinic.receptionist.getFirstname()+" "+Clinic.receptionist.getLastname());
        System.out.println(Clinic.receptionist.getEmail());
        System.out.println(Clinic.receptionist.getMobilenumber());
    }

    //getters

    public String getSpecialization() {
        return Specialization;
    }

    public ArrayList<Appointment> getAvailableDaysandhours() {
        return availableDaysandhours;
    }

    public ArrayList<Patient> getMyPatients() {
        return myPatients;
    }

    public String getAvailableDay() {
        return availableDay;
    }

    public int getAvailableHour() {
        return availableHour;
    }

    //End of class
}