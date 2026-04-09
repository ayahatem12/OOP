import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandler {
    private static final String CLINIC_FILE_PATH = "Clinic.csv";

    // Save entire clinic data to a single CSV file
    public static void saveClinicData(Clinic clinic) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CLINIC_FILE_PATH))) {
            // Write Clinic Information
            writer.println("CLINIC_INFO," + clinic.getName() + "," + clinic.getAddress());

            // Write Receptionist
            Receptionist receptionist = clinic.getReceptionist();
            writer.println("RECEPTIONIST," +
                    receptionist.getUsername() + "," +
                    receptionist.getPassword() + "," +
                    receptionist.getFirstname() + "," +
                    receptionist.getLastname() + "," +
                    receptionist.getEmail() + "," +
                    receptionist.getMobilenumber());

            // Write Doctors
            for (Doctor doctor : clinic.getDoctors()) {
                writer.println("DOCTOR," +
                        doctor.getUsername() + "," +
                        doctor.getPassword() + "," +
                        doctor.getFirstname() + "," +
                        doctor.getLastname() + "," +
                        doctor.getEmail() + "," +
                        doctor.getMobilenumber());

                // Write Doctor's Available Days and Hours
                for (Appointment appointment : doctor.getAvailableDaysandhours()) {
                    writer.println("DOCTOR_AVAILABLE," +
                            appointment.getDay() + "," +
                            appointment.getHour());
                }

                // Write Doctor's Patients
                for (Patient patient : doctor.getMyPatients()) {
                    writePatientDetails(writer, patient);
                }
            }

            // Write ALL Patients in the Clinic (including those not associated with a specific doctor)
            writer.println("CLINIC_PATIENTS_START");
            for (Patient patient : clinic.getPatients()) {
                // Check if this patient is already written (to avoid duplicates)
                writePatientDetails(writer, patient);
            }
            writer.println("CLINIC_PATIENTS_END");

            // Write Room Appointments
            writer.println("ROOM_APPOINTMENTS_START");
            for (Appointment app : Clinic.room.getAppointments()) {
                writer.println("ROOM_APPOINTMENT," +
                        app.getDay() + "," +
                        app.getHour() + "," +
                        app.getdoctor_username() + "," +
                        app.getPatient_username());
            }
            writer.println("ROOM_APPOINTMENTS_END");

            System.out.println("Clinic data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to write patient details
    private static void writePatientDetails(PrintWriter writer, Patient patient) {
        writer.println("PATIENT," +
                patient.getUsername() + "," +
                patient.getPassword() + "," +
                patient.getFirstname() + "," +
                patient.getLastname() + "," +
                patient.getEmail() + "," +
                patient.getMobilenumber() + "," +
                patient.getPatientHistory() + "," +
                patient.getGender() + "," +
                patient.getBloodType() + "," +
                patient.getAge() + "," +
                patient.getWeight() + "," +
                patient.getHeight());

        // Write Patient's Appointments
        writer.println("PATIENT_APPOINTMENTS_START");
        for (Appointment app : patient.getMy_app()) {
            writer.println("APPOINTMENT," +
                    app.getDay() + "," +
                    app.getHour() + "," +
                    app.getdoctor_username() + "," +
                    app.getPatient_username());
        }
        writer.println("PATIENT_APPOINTMENTS_END");

        // Write Patient's Prescription if exists
        Prescription prescription = patient.getMy_prescription();
        if (prescription != null) {
            writer.println("PRESCRIPTION," +
                    prescription.getPatient_name() + "," +
                    prescription.getDate() + "," +
                    String.join("|", prescription.getMedicins()) + "," +
                    prescription.getContact_num());
        }
    }

    // Load entire clinic data from CSV file
    public static Clinic loadClinicData() {
        Clinic clinic = null;
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new FileReader(CLINIC_FILE_PATH))) {
            String nextLine;
            Doctor currentDoctor = null;
            Patient currentPatient = null;
            boolean inClinicPatients = false;
            boolean inRoomAppointments = false;

            while ((nextLine = reader.readLine()) != null) {
                String[] fields = nextLine.split(",");
                switch (fields[0]) {
                    case "CLINIC_INFO":
                        clinic = new Clinic(fields[1], fields[2]);
                        break;

                    case "RECEPTIONIST":
                        Receptionist receptionist = new Receptionist();
                        receptionist.setUsername(fields[1]);
                        receptionist.setPassword(fields[2]);
                        receptionist.setFirstname(fields[3]);
                        receptionist.setLastname(fields[4]);
                        receptionist.setEmail(fields[5]);
                        receptionist.setMobilenumber(fields[6]);
                        clinic.setReceptionist(receptionist);
                        break;

                    case "DOCTOR":
                        currentDoctor = new Doctor();
                        currentDoctor.setUsername(fields[1]);
                        currentDoctor.setPassword(fields[2]);
                        currentDoctor.setFirstname(fields[3]);
                        currentDoctor.setLastname(fields[4]);
                        currentDoctor.setEmail(fields[5]);
                        currentDoctor.setMobilenumber(fields[6]);
                        clinic.addDoctor(currentDoctor);
                        currentPatient = null;
                        break;

                    case "PATIENT":
                        currentPatient = new Patient();
                        currentPatient.setUsername(fields[1]);
                        currentPatient.setPassword(fields[2]);
                        currentPatient.setFirstname(fields[3]);
                        currentPatient.setLastname(fields[4]);
                        currentPatient.setEmail(fields[5]);
                        currentPatient.setMobilenumber(fields[6]);
                        currentPatient.setPatientHistory(fields[7]);
                        currentPatient.setGender(fields[8]);
                        currentPatient.setBloodType(fields[9]);
                        currentPatient.setAge(Integer.parseInt(fields[10]));
                        currentPatient.setWeight(Double.parseDouble(fields[11]));
                        currentPatient.setHeight(Double.parseDouble(fields[12]));

                        // Add patient to doctor or clinic list
                        if (inClinicPatients) {
                            clinic.addPatient(currentPatient);
                        } else if (currentDoctor != null) {
                            currentDoctor.add_patient(currentPatient);
                        }
                        break;

                    case "PATIENT_APPOINTMENTS_START":
                        if (currentPatient != null) {
                            currentPatient.getMy_app().clear();
                        }
                        break;

                    case "APPOINTMENT":
                        Appointment appointment = new Appointment();
                        appointment.setDay(fields[1]);
                        appointment.setHour(Integer.parseInt(fields[2]));
                        appointment.setDoctor_username(fields[3]);
                        appointment.setPatient_username(fields[4]);

                        if (currentPatient != null) {
                            currentPatient.getMy_app().add(appointment);
                        }
                        break;

                    case "PATIENT_APPOINTMENTS_END":
                        break;

                    case "PRESCRIPTION":
                        if (currentPatient != null) {
                            Prescription prescription = new Prescription();
                            prescription.setPatient_name(fields[1]);
                            prescription.setDate(fields[2]);
                            String[] medicines = fields[3].split("\\|");
                            prescription.setMedicins(new ArrayList<>(Arrays.asList(medicines)));
                            prescription.setContact_num(Integer.parseInt(fields[4]));
                            currentPatient.setMy_Prescription(prescription);
                        }
                        break;

                    case "CLINIC_PATIENTS_START":
                        inClinicPatients = true;
                        break;

                    case "CLINIC_PATIENTS_END":
                        inClinicPatients = false;
                        break;

                    case "ROOM_APPOINTMENTS_START":
                        clinic.getRoom().getAppointments().clear();
                        inRoomAppointments = true;
                        break;

                    case "ROOM_APPOINTMENT":
                        if (inRoomAppointments) {
                            Appointment roomAppointment = new Appointment();
                            roomAppointment.setDay(fields[1]);
                            roomAppointment.setHour(Integer.parseInt(fields[2]));
                            roomAppointment.setDoctor_username(fields[3]);
                            roomAppointment.setPatient_username(fields[4]);
                            clinic.getRoom().getAppointments().add(roomAppointment);
                        }
                        break;

                    case "ROOM_APPOINTMENTS_END":
                        inRoomAppointments = false;
                        break;

                    // Loading Doctor's Available Days and Hours
                    case "DOCTOR_AVAILABLE":
                        if (currentDoctor != null) {
                            Appointment availableAppointment = new Appointment();
                            availableAppointment.setDay(fields[1]);
                            availableAppointment.setHour(Integer.parseInt(fields[2]));
                            currentDoctor.getAvailableDaysandhours().add(availableAppointment);
                        }
                        break;
                }
            }

            System.out.println("Clinic data loaded successfully!");
            return clinic;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
