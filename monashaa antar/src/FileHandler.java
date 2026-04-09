import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandler {
    private static final String CLINIC_FILE_PATH = "D:\\code\\java\\monashaa antar\\monashaa antar\\Clinic.csv";

    // Save entire clinic data to a single CSV file
    public static void saveClinicData(Clinic clinic) throws FileNotFoundException {
        if (clinic == null) {
            throw new IllegalArgumentException("Clinic object cannot be null");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(CLINIC_FILE_PATH))) {
            // Write Clinic Information
            if (clinic.getName() != null && clinic.getAddress() != null) {
                writer.println("CLINIC_INFO," + clinic.getName() + "," + clinic.getAddress());
            } else {
                throw new IllegalStateException("Clinic name or address cannot be null");
            }

            // Write Receptionist
            Receptionist receptionist = clinic.getReceptionist();
            if (receptionist != null) {
                writer.println("RECEPTIONIST," +
                        validateField(receptionist.getUsername()) + "," +
                        validateField(receptionist.getPassword()) + "," +
                        validateField(receptionist.getFirstname()) + "," +
                        validateField(receptionist.getLastname()) + "," +
                        validateField(receptionist.getEmail()) + "," +
                        validateField(receptionist.getMobilenumber()));
            }

            // Write Doctors
            if (clinic.getDoctors() != null) {
                for (Doctor doctor : clinic.getDoctors()) {
                    if (doctor != null) {
                        writer.println("DOCTOR," +
                                validateField(doctor.getUsername()) + "," +
                                validateField(doctor.getPassword()) + "," +
                                validateField(doctor.getFirstname()) + "," +
                                validateField(doctor.getLastname()) + "," +
                                validateField(doctor.getEmail()) + "," +
                                validateField(doctor.getMobilenumber()));

                        // Write Doctor's Available Days and Hours
                        if (doctor.getAvailableDaysandhours() != null) {
                            for (Appointment appointment : doctor.getAvailableDaysandhours()) {
                                if (appointment != null) {
                                    writer.println("DOCTOR_AVAILABLE," +
                                            validateField(appointment.getDay()) + "," +
                                            appointment.getHour());
                                }
                            }
                        }

                        // Write Doctor's Patients
                        if (doctor.getMyPatients() != null) {
                            for (Patient patient : doctor.getMyPatients()) {
                                if (patient != null) {
                                    writePatientDetails(writer, patient);
                                }
                            }
                        }
                    }
                }
            }

            // Write ALL Patients
            writer.println("CLINIC_PATIENTS_START");
            if (clinic.getPatients() != null) {
                for (Patient patient : clinic.getPatients()) {
                    if (patient != null) {
                        writePatientDetails(writer, patient);
                    }
                }
            }
            writer.println("CLINIC_PATIENTS_END");

            // Write Room Appointments
            writer.println("ROOM_APPOINTMENTS_START");
            if (Clinic.room != null && Clinic.room.getAppointments() != null) {
                for (Appointment app : Clinic.room.getAppointments()) {
                    if (app != null) {
                        writer.println("ROOM_APPOINTMENT," +
                                validateField(app.getDay()) + "," +
                                app.getHour() + "," +
                                validateField(app.getdoctor_username()) + "," +
                                validateField(app.getPatient_username()));
                    }
                }
            }
            writer.println("ROOM_APPOINTMENTS_END");

            System.out.println("Clinic data saved successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Could not find or create file at path: " + CLINIC_FILE_PATH);
            throw e;
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred while saving clinic data");
            throw new RuntimeException("Failed to save clinic data", e);
        }
    }

    // Helper method to write patient details
    private static void writePatientDetails(PrintWriter writer, Patient patient) {
        if (patient == null) return;

        try {
            writer.println("PATIENT," +
                    validateField(patient.getUsername()) + "," +
                    validateField(patient.getPassword()) + "," +
                    validateField(patient.getFirstname()) + "," +
                    validateField(patient.getLastname()) + "," +
                    validateField(patient.getEmail()) + "," +
                    validateField(patient.getMobilenumber()) + "," +
                    validateField(patient.getPatientHistory()) + "," +
                    validateField(patient.getGender()) + "," +
                    validateField(patient.getBloodType()) + "," +
                    patient.getAge() + "," +
                    patient.getWeight() + "," +
                    patient.getHeight());

            // Write Patient's Appointments
            writer.println("PATIENT_APPOINTMENTS_START");
            if (patient.getMy_app() != null) {
                for (Appointment app : patient.getMy_app()) {
                    if (app != null) {
                        writer.println("APPOINTMENT," +
                                validateField(app.getDay()) + "," +
                                app.getHour() + "," +
                                validateField(app.getdoctor_username()) + "," +
                                validateField(app.getPatient_username()));
                    }
                }
            }
            writer.println("PATIENT_APPOINTMENTS_END");

            // Write Patient's Prescription
            Prescription prescription = patient.getMy_prescription();
            if (prescription != null) {
                writer.println("PRESCRIPTION," +
                        validateField(prescription.getPatient_name()) + "," +
                        validateField(prescription.getDate()) + "," +
                        validateField(String.join("|", prescription.getMedicins())) + "," +
                        prescription.getContact_num());
            }
        } catch (Exception e) {
            System.err.println("Error writing patient details for: " + patient.getUsername());
            e.printStackTrace();
        }
    }

    // Load entire clinic data from CSV file
    public static Clinic loadClinicData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CLINIC_FILE_PATH))) {
            Clinic clinic = null;
            String nextLine = reader.readLine();

            // Check if file is empty
            if (nextLine == null) {
                System.out.println("Creating new clinic with default values as file is empty");
                return createDefaultClinic();
            }

            Doctor currentDoctor = null;
            Patient currentPatient = null;
            boolean inClinicPatients = false;
            boolean inRoomAppointments = false;

            do {
                try {
                    String[] fields = nextLine.split(",");
                    if (fields.length == 0) continue;

                    switch (fields[0]) {
                        case "CLINIC_INFO":
                            if (fields.length < 3) {
                                throw new IllegalStateException("Invalid clinic info format");
                            }
                            clinic = new Clinic(fields[1], fields[2]);
                            break;

                        case "RECEPTIONIST":
                            if (fields.length < 7 || clinic == null) continue;
                            processReceptionist(clinic, fields);
                            break;

                        case "DOCTOR":
                            if (fields.length < 7 || clinic == null) continue;
                            currentDoctor = processDoctor(clinic, fields);
                            currentPatient = null;
                            break;

                        case "PATIENT":
                            if (fields.length < 13) continue;
                            currentPatient = processPatient(clinic, currentDoctor, fields, inClinicPatients);
                            break;

                        case "APPOINTMENT":
                            if (fields.length < 5 || currentPatient == null) continue;
                            processAppointment(currentPatient, fields);
                            break;

                        case "PRESCRIPTION":
                            if (fields.length < 5 || currentPatient == null) continue;
                            processPrescription(currentPatient, fields);
                            break;

                        case "CLINIC_PATIENTS_START":
                            inClinicPatients = true;
                            break;

                        case "CLINIC_PATIENTS_END":
                            inClinicPatients = false;
                            break;

                        case "ROOM_APPOINTMENTS_START":
                            if (clinic != null) {
                                clinic.getRoom().getAppointments().clear();
                                inRoomAppointments = true;
                            }
                            break;

                        case "ROOM_APPOINTMENT":
                            if (inRoomAppointments && clinic != null && fields.length >= 5) {
                                processRoomAppointment(clinic, fields);
                            }
                            break;

                        case "ROOM_APPOINTMENTS_END":
                            inRoomAppointments = false;
                            break;

                        case "DOCTOR_AVAILABLE":
                            if (currentDoctor != null && fields.length >= 3) {
                                processDoctorAvailability(currentDoctor, fields);
                            }
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Warning: Invalid number format in line: " + nextLine);
                    continue;
                } catch (IllegalArgumentException e) {
                    System.err.println("Warning: " + e.getMessage() + " in line: " + nextLine);
                    continue;
                } catch (Exception e) {
                    System.err.println("Warning: Error processing line: " + nextLine);
                    e.printStackTrace();
                    continue;
                }
            } while ((nextLine = reader.readLine()) != null);

            // Validate final clinic object
            if (clinic == null) {
                System.out.println("No valid clinic data found. Creating default clinic.");
                return createDefaultClinic();
            }

            if (clinic.getReceptionist() == null) {
                System.out.println("No receptionist found. Adding default receptionist.");
                addDefaultReceptionist(clinic);
            }

            System.out.println("Clinic data loaded successfully!");
            return clinic;

        } catch (FileNotFoundException e) {
            System.out.println("Clinic data file not found. Creating new clinic with default values");
            return createDefaultClinic();
        } catch (IOException e) {
            System.out.println("Error reading clinic data. Creating new clinic with default values");
            return createDefaultClinic();
        }
    }

    private static void processReceptionist(Clinic clinic, String[] fields) {
        Receptionist receptionist = new Receptionist();
        receptionist.setUsername(fields[1]);
        receptionist.setPassword(fields[2]);
        receptionist.setFirstname(fields[3]);
        receptionist.setLastname(fields[4]);
        receptionist.setEmail(fields[5]);
        receptionist.setMobilenumber(fields[6]);
        clinic.setReceptionist(receptionist);
    }

    private static Doctor processDoctor(Clinic clinic, String[] fields) {
        Doctor doctor = new Doctor();
        doctor.setUsername(fields[1]);
        doctor.setPassword(fields[2]);
        doctor.setFirstname(fields[3]);
        doctor.setLastname(fields[4]);
        doctor.setEmail(fields[5]);
        doctor.setMobilenumber(fields[6]);
        clinic.addDoctor(doctor);
        return doctor;
    }

    private static Patient processPatient(Clinic clinic, Doctor currentDoctor, String[] fields, boolean inClinicPatients) {
        Patient patient = new Patient();
        patient.setUsername(fields[1]);
        patient.setPassword(fields[2]);
        patient.setFirstname(fields[3]);
        patient.setLastname(fields[4]);
        patient.setEmail(fields[5]);
        patient.setMobilenumber(fields[6]);
        patient.setPatientHistory(fields[7]);
        patient.setGender(fields[8]);
        patient.setBloodType(fields[9]);
        patient.setAge(Integer.parseInt(fields[10]));
        patient.setWeight(Double.parseDouble(fields[11]));
        patient.setHeight(Double.parseDouble(fields[12]));

        if (inClinicPatients) {
            clinic.addPatient(patient);
        } else if (currentDoctor != null) {
            currentDoctor.add_patient(patient);
        }
        return patient;
    }

    private static void processAppointment(Patient patient, String[] fields) {
        Appointment appointment = new Appointment();
        appointment.setDay(fields[1]);
        appointment.setHour(Integer.parseInt(fields[2]));
        appointment.setDoctor_username(fields[3]);
        appointment.setPatient_username(fields[4]);
        patient.getMy_app().add(appointment);
    }

    private static void processPrescription(Patient patient, String[] fields) {
        Prescription prescription = new Prescription();
        prescription.setPatient_name(fields[1]);
        prescription.setDate(fields[2]);
        String[] medicines = fields[3].split("\\|");
        prescription.setMedicins(new ArrayList<>(Arrays.asList(medicines)));
        prescription.setContact_num(Integer.parseInt(fields[4]));
        patient.setMy_Prescription(prescription);
    }

    private static void processRoomAppointment(Clinic clinic, String[] fields) {
        Appointment roomAppointment = new Appointment();
        roomAppointment.setDay(fields[1]);
        roomAppointment.setHour(Integer.parseInt(fields[2]));
        roomAppointment.setDoctor_username(fields[3]);
        roomAppointment.setPatient_username(fields[4]);
        clinic.getRoom().getAppointments().add(roomAppointment);
    }

    private static void processDoctorAvailability(Doctor doctor, String[] fields) {
        Appointment availableAppointment = new Appointment();
        availableAppointment.setDay(fields[1]);
        availableAppointment.setHour(Integer.parseInt(fields[2]));
        doctor.getAvailableDaysandhours().add(availableAppointment);
    }

    private static String validateField(String field) {
        return field != null ? field : "";
    }

    private static Clinic createDefaultClinic() {
        Clinic clinic = new Clinic("New Clinic", "Default Address");
        addDefaultReceptionist(clinic);
        return clinic;
    }

    private static void addDefaultReceptionist(Clinic clinic) {
        Receptionist defaultReceptionist = new Receptionist();
        defaultReceptionist.setUsername("admin");
        defaultReceptionist.setPassword("admin");
        defaultReceptionist.setFirstname("Admin");
        defaultReceptionist.setLastname("User");
        defaultReceptionist.setEmail("admin@clinic.com");
        defaultReceptionist.setMobilenumber("0000000000");
        clinic.setReceptionist(defaultReceptionist);
    }
}