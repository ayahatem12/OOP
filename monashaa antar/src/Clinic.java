import java.util.ArrayList;

public class Clinic {
    private String name;
    private String address;
    public static ArrayList<Doctor> doctors = new ArrayList<>();//saved
    public static ArrayList<Patient> patients = new ArrayList<>();
    public static Receptionist receptionist = new Receptionist();
    public static Room room = new Room();

    // Constructor
    public Clinic(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Adders
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Getters

    public static Room getRoom() {
        return room;
    }

    public static Patient getspecificpatient(String username){
        for(Patient p:patients){
            if(p.getUsername().equals(username)){
                return p;
            }
        }
        return null;
    }
    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public static Receptionist getReceptionist() {
        return receptionist;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public static void setDoctors(ArrayList<Doctor> doctors) {
        Clinic.doctors = doctors;
    }

    public static void setRoom(Room room) {
        Clinic.room = room;
    }

    public static void setReceptionist(Receptionist receptionist) {
        Clinic.receptionist = receptionist;
    }

    public static void setPatients(ArrayList<Patient> patients) {
        Clinic.patients = patients;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}







