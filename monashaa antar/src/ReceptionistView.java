import java.util.Scanner;

import java.util.Scanner;

public class ReceptionistView {

    Receptionist receptionist;

    public ReceptionistView(Receptionist receptionist) {
        this.receptionist = receptionist;
        Scanner input = new Scanner(System.in);
        int c;

        System.out.println("Receptionist View");

        do {
            System.out.println("Please choose an option:");
            System.out.println("1 - Update Patient Details");
            System.out.println("2 - Update Receptionist Details");
            System.out.println("3 - LOG OUT");
            c = input.nextInt();

            if (c < 1 || c > 3) {
                System.out.println("Invalid choice. Please enter a valid option (1, 2, or 3).");
            }
        } while (c < 1 || c > 3);


        switch (c) {
            case 1:
                receptionist.UpdatePatientDetails();
                break;

            case 2:
                receptionist.UpdateReceptionistDetails();
                break;

            case 3:
                System.out.println("LOG OUT");
                break;
        }
    }
}