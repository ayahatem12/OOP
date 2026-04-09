import java.util.ArrayList;
public class Room {
    public static ArrayList<Appointment> appointments=new ArrayList<>();

    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public boolean addAppointment(Appointment a){
        boolean available = false;
        if(a.getHour()<1 || a.getHour()>12){
            System.out.println("Invalid hour. Please provide an hour between 1 and 12..");
            return false;
        }

        if(appointments.contains(a)) {
            System.out.println("Sorry!! Appointment already exists on ");
            return false;
        }
        else {
            int i=0;
            for(;i<Clinic.doctors.size();i++){
                if(Clinic.doctors.get(i).availableDaysandhours.contains(a)){
                    available = true;
                    break;
                }
            }
            if(available){
                if(Clinic.doctors.get(i).getUsername().equals(a.getdoctor_username())){
                    appointments.add(a);
                    System.out.println("Appointment added: " + a.getDay() + " at " + a.getHour() + ":00.");
                  // Clinic.doctors.get(i).availableDaysandhours.remove(a);
                    for(Patient p:Clinic.patients){
                        if(p.getUsername().equals(a.getPatient_username())){
                            Clinic.doctors.get(i).add_patient(p);
                            break;
                        }
                    }
                    return true;
                }
                else{
                    System.out.println("Doctor: " + a.getdoctor_username() + "is not available in this appointment");
                    return false;
                }
            }
            else{
                System.out.println("This appointment not available in our appointments");
                return false;
            }
        }
    }
    public boolean cancelAppointment(Appointment a) {
        boolean canceled = false;
        if(appointments.contains(a)){
            appointments.remove(a);
            canceled=true;
            for(Doctor d :Clinic.doctors){
                if(d.getUsername().equals(a.getdoctor_username())){
                    for (Patient p:Clinic.patients){
                        if(p.getUsername().equals(a.getPatient_username())){
                            d.myPatients.remove(p);
                            p.my_app.remove(p.my_app.get(p.my_app.size() - 1));
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return canceled;
    }


    public void showEmptyAppointments(String day){
        boolean found = false;
        System.out.println("Available appointments on " + day + ":");
        for (int hour = 1; hour < 13; hour++) {
            boolean isOccupied = false;
            for (Appointment appt : appointments) {
                if (appt.getDay().equalsIgnoreCase(day) && appt.getHour() == hour) {
                    isOccupied = true;
                    break;
                }
            }
            if (!isOccupied) {
                System.out.println(day + " at " + hour + ":00 is available.");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available appointment slots on " + day + ".");
        }
    }
}