import java.util.Scanner;

public class Login {
    Scanner input = new Scanner(System.in);
    public Doctor Doctorlogin() {
        String username;
        String password;

        while (true) { // Allow multiple login attempts
            System.out.println("Doctor Login");

            // Get username input
            System.out.println("Enter username: ");
            username = input.nextLine().trim();
            while (username.isEmpty() || username.contains(" ")) {
                System.out.println("Invalid username. Please enter a valid username.");
                username = input.nextLine().trim();
            }

            // Get password input
            System.out.println("Enter password: ");
            password = input.nextLine();

            // Check credentials
            for (Doctor doctor : Clinic.doctors) {
                if (doctor.getUsername().equals(username) && doctor.getPassword().equals(password)) {
                    System.out.println("Login Successful");
                    return doctor; // Return the logged-in doctor directly
                }
            }

            // If login fails
            System.out.println("Login Failed");
            System.out.println("Do you want to login again? (y/n)");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break; // Exit the loop if the user doesn't want to retry
            }
        }

        return null; // Return null if login fails and the user chooses not to retry
    }

    public Patient Patientlogin() {
        String username;
        String password;
        boolean isPatientFound = false;

        while (true) {
            isPatientFound = false;
            System.out.println("Patient Login");

            // Get username input
            System.out.println("Enter username: ");
            username = input.nextLine();
            while (username.isEmpty() || username.contains(" ")) {
                System.out.println("Invalid username. Please enter a valid username.");
                username = input.nextLine();
            }

            // Get password input
            System.out.println("Enter password: ");
            password = input.nextLine();

            // Check credentials
            for (Patient patient : Clinic.patients) {
                if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                    isPatientFound = true;
                    System.out.println("Login Successful");
                    return patient;  // Return the logged-in patient
                }
            }

            // If login fails
            System.out.println("Login Failed");
            System.out.println("Do you want to login again? (y/n)");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break;  // Exit the loop and return null
            }
        }

        return null;  // If login fails and user chooses not to retry
    }

    public Receptionist Reciptionstlogin() {
        while (true) {
            System.out.println("Receptionist Login");

            // Get username input
            System.out.println("Enter username: ");
            String username = input.nextLine().trim();
            while (username.isEmpty() || username.contains(" ")) {
                System.out.println("Invalid username. Please enter a valid username.");
                username = input.nextLine().trim();
            }

            // Get password input
            System.out.println("Enter password: ");
            String password = input.nextLine();

            // Check credentials
            if (username.equals(Clinic.receptionist.getUsername()) &&
                    password.equals(Clinic.receptionist.getPassword())) {
                System.out.println("Login Successful");
                return Clinic.receptionist;
            }

            // If login fails
            System.out.println("Login Failed");
            System.out.println("Do you want to login again? (y/n)");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break; // Exit the loop if the user chooses not to retry
            }
        }
        return null;
    }

}