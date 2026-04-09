import java.util.InputMismatchException;
import java.util.Scanner;

public class Receptionist extends User {

    private int age;
    private String gender;

    public Receptionist() {
        super("Admin", "Admin", "Admin", "Admin", "Admin@Admin.com", "010");
        this.age = 0;
        this.gender = "male";
    }

    public Receptionist(String username, String password, String firstname, String lastname, String email, String mobilenumber, int age, String gender) {
        super(username, password, firstname, lastname, email, mobilenumber);
        this.age = age;
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void UpdateReceptionistDetails() {
        Scanner input = new Scanner(System.in);
        String continueUpdating;

        do {
            try {
                System.out.println("What would you like to update for the receptionist?");
                System.out.println("1. Email");
                System.out.println("2. Phone Number");
                System.out.println("Enter your choice (1 or 2):");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the new email:");
                        String newEmail = input.nextLine();
                        Clinic.receptionist.setEmail(newEmail);
                        System.out.println("Email updated successfully for Receptionist: " +
                                Clinic.receptionist.getFirstname() + " " + Clinic.receptionist.getLastname());
                        break;

                    case 2:
                        System.out.println("Your old phone number is:");
                        System.out.println(Clinic.receptionist.getMobilenumber());

                        System.out.println("Enter the new phone number:");
                        String newPhoneNumber = input.nextLine();
                        Clinic.receptionist.setMobilenumber(newPhoneNumber);
                        System.out.println("Phone number updated successfully for Receptionist: " +
                                Clinic.receptionist.getFirstname() + " " + Clinic.receptionist.getLastname());
                        break;


                }

            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

            System.out.println("Do you want to continue updating? (yes/no):");
            continueUpdating = input.nextLine().trim().toLowerCase();
        } while (continueUpdating.equals("yes"));
    }

    public void UpdatePatientDetails() {
        Scanner input = new Scanner(System.in);
        String continueUpdating;

        do {
            try {
                Patient targetPatient = null;


                do {
                    System.out.println("Enter the username of the patient to update details:");
                    String patientUsername = input.nextLine();

                    for (Patient p : Clinic.patients) {
                        if (p.getUsername().equals(patientUsername)) {
                            targetPatient = p;
                            break;
                        }
                    }

                    if (targetPatient == null) {
                        System.out.println("Patient with username " + patientUsername + " not found. Please try again.");
                    }
                } while (targetPatient == null);

                int choice;
                do {
                    System.out.println("What would you like to update for the patient?");
                    System.out.println("1. Email");
                    System.out.println("2. Mobile Number");
                    System.out.println("3. Weight");
                    System.out.println("4. Height");
                    System.out.println("5. Appointments");
                    System.out.println("Enter your choice (1-5):");


                    choice = input.nextInt();
                    input.nextLine();

                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please select a valid option (1-5).");
                    }
                } while (choice < 1 || choice > 5);


                switch (choice) {
                    case 1:
                        System.out.println("Enter the new email:");
                        String newEmail = input.nextLine();
                        targetPatient.ChangeEmail(newEmail);
                        System.out.println("Email updated successfully for Patient: " +
                                targetPatient.getFirstname() + " " + targetPatient.getLastname());
                        break;

                    case 2:
                        System.out.println("Enter the new mobile number:");
                        String newMobileNumber = input.nextLine();
                        targetPatient.ChangeMobilenNo(newMobileNumber);
                        System.out.println("Mobile number updated successfully for Patient: " +
                                targetPatient.getFirstname() + " " + targetPatient.getLastname());
                        break;

                    case 3:
                        System.out.println("Enter the new weight:");
                        double newWeight = input.nextDouble();
                        targetPatient.ChangeWeight(newWeight);
                        System.out.println("Weight updated successfully for Patient: " +
                                targetPatient.getFirstname() + " " + targetPatient.getLastname());
                        break;

                    case 4:
                        System.out.println("Enter the new height:");
                        double newHeight = input.nextDouble();
                        targetPatient.ChangeHeight(newHeight);
                        System.out.println("Height updated successfully for Patient: " +
                                targetPatient.getFirstname() + " " + targetPatient.getLastname());
                        break;

                    case 5:
                        System.out.println("What would you like to do with appointments?");
                        System.out.println("1. Add Appointment");
                        System.out.println("2. Cancel Appointment");
                        int appointmentChoice;

                        do {

                            appointmentChoice = input.nextInt();
                            input.nextLine();

                            if (appointmentChoice < 1 || appointmentChoice > 2) {
                                System.out.println("Invalid choice for appointments. Please select 1 or 2.");
                            }
                        } while (appointmentChoice < 1 || appointmentChoice > 2);

                        switch (appointmentChoice) {
                            case 1:
                                if (Clinic.room != null) {
                                    targetPatient.add_to_my_Appointments();
                                } else {
                                    System.out.println("Room not found.");
                                }
                                break;

                            case 2:

                                System.out.println("Enter day you want:");
                                String day = input.nextLine();
                                System.out.println("Enter hour you want:");
                                int hour = input.nextInt();
                                input.nextLine();
                                System.out.println("Enter your username:");
                                String patientUserName = input.nextLine();
                                System.out.println("Our Doctors Usernames:");
                                for (Doctor d : Clinic.doctors){
                                    System.out.println(d.getUsername());
                                }
                                System.out.println("Enter your doctor username from the list:");
                                String doctorUserName = input.nextLine();

                                if (hour < 1 || hour > 12) {
                                    System.out.println("Invalid hour. Please provide an hour between 1 and 12.");
                                    break;
                                }

                                Appointment appointment1 = new Appointment(day, hour, doctorUserName, patientUserName);

                                if (Clinic.room != null) {
                                    targetPatient.cancel_my_Appointments(appointment1);
                                } else {
                                    System.out.println("Room not found.");
                                }
                                break;
                        }
                        break;
                }

            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

            System.out.println("Do you want to continue updating? (yes/no):");
            continueUpdating = input.nextLine().trim().toLowerCase();
        } while (continueUpdating.equals("yes"));
    }


}