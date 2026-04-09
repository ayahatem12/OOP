import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorView {

    Doctor doctor;
    boolean done;
    Scanner input = new Scanner(System.in);
    String answer=new String();
    String day = new String();
    int hour ;
    //private String CheckAnswer(String answer){
//    while(!(answer.equalsIgnoreCase("no"))){
//
//            System.out.println("Invalid Answer, please choose yes or no");
//            answer=input.next().toLowerCase().trim();
//
//    }
//    return answer;
    //}
    private String checkanswer(String answer){
        while(true){
            try{

                if(!answer.equalsIgnoreCase("yes")&&!answer.equalsIgnoreCase("no")){
                    throw new IllegalArgumentException("Invalid answer you should write \"yes\" or \"no\" \n Try again.");
                }
                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                answer=input.next().toLowerCase();
            }
        }
        return answer;
    }

    public DoctorView(Doctor doctor) {
        this.doctor = doctor;

        System.out.println("Doctor View");
        String choice =new String();


        do{
            System.out.println("\nPlease choose one of the following options:");
            System.out.println("1 - Set Your Available Days");
            System.out.println("2 - View Your Available Days");
            System.out.println("3 - Change your Available Days or Hours");
            System.out.println("4 - View Appointments for a Specific Day");
            System.out.println("5 - Get Patient Information");
            System.out.println("6 - Create a Prescription for a Patient");
            System.out.println("7 - Get Receptionist's Contact Information");
            System.out.println("8 - Log Out");
            choice = input.next();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6") && !choice.equals("7")&& !choice.equals("8")) {
                System.out.println("Invalid choice re_nter your choice");
                choice = input.next().trim();
            }
            switch (choice) {
                case "1": {
                    do {
                        System.out.println("Enter your available day (e.g., monday, tuesday,...) and hour (e.g., 10 for 10 AM):");
                        day = input.next().toLowerCase();
                        while (true) {
                            try {
                                hour = input.nextInt();  // Attempt to read an integer
                                break;  // If input is valid, break out of loop
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid integer for the hour.");
                                input.nextLine();  // Clear the buffer to avoid an infinite loop
                            }
                        }
                        done = doctor.setAvailableDaysandhours(day, hour);

                        if (!done) {
                            System.out.println("The change was unsuccessful. Do you want to try again? (yes/no)");
                            answer = input.next();
                            answer=checkanswer(answer);



                        } else if (done) {
                            System.out.println("Are you available on another time? (yes/no):");
                            answer=input.next();
                            answer= checkanswer(answer);

                        }

                    } while (answer.equalsIgnoreCase("yes"));
                    System.out.println("Your available days and hours are added sucessfully!");
                    System.out.println("...............................................................................................................................");
                    break;
                }
                case "2": {

                    if(doctor.DisplayAvailableDaysandhours(true)){
                    }
                    else{
                        System.out.println("You are not available");
                    }


                    System.out.println("...............................................................................................................................");
                    break;
                }
                case "3":{
                    String newday=new String();
                    int newhour;

                    do {

                        System.out.println("Enter the day you want to change (e.g., monday, tuesday,...) and hour (e.g., 10 for 10 AM):");
                        day = input.next().toLowerCase().trim();
                        while (true) {
                            try {
                                hour = input.nextInt();  // Attempt to read an integer
                                break;  // If input is valid, break out of loop
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid integer for the hour.");
                                input.nextLine();  // Clear the buffer to avoid an infinite loop
                            }
                        }

                        System.out.println("Enter the new day you want (e.g., monday, tuesday,...) and hour (e.g., 10 for 10 AM):");
                        newday = input.next().toLowerCase().trim();
                        while (true) {
                            try {
                                newhour = input.nextInt();  // Attempt to read an integer
                                break;  // If input is valid, break out of loop
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid integer for the hour.");
                                input.nextLine();  // Clear the buffer to avoid an infinite loop
                            }
                        }
                        done=doctor.ChangeAvailability(day,hour,newday,newhour);
                        if(done){
                            System.out.println("Do yo want to change another day? (yes/no)");
                            answer=input.next();
                            answer=checkanswer(answer);
                        }
                        else{
                            System.out.println("The process was unsuccessful. Do you want to try again? (yes/no)");
                            answer=input.next();
                            answer=checkanswer(answer);
                        }


                    }while(answer.equalsIgnoreCase("yes"));
                    System.out.println("...............................................................................................................................");

                    break;
                }
                case "4":{
                    System.out.println("Enter the day you want to know the appointments for (e.g., Monday, Tuesday):");
                    day = input.next().toLowerCase().trim();
                    doctor.ShowAllAppointments(day);
                    System.out.println("...............................................................................................................................");
                    break;
                }
                case "5":{
                    do {
                        System.out.println("Enter the Appointment for the Desired Patient");
                        System.out.println("Enter the Day (e.g., monday, tuesday, etc.):");
                        day = input.next().toLowerCase();
                        System.out.println("Enter the Hour (e.g., 10,12):");
                        while (true) {
                            try {
                                hour = input.nextInt();  // Attempt to read an integer
                                break;  // If input is valid, break out of loop
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid integer for the hour.");
                                input.nextLine();  // Clear the buffer to avoid an infinite loop
                            }
                        }
                        done=doctor.GetPatientInfo(day, hour);
                        if(done){
                            break;
                        }
                        else{
                            System.out.println("The change was unsuccessful. Do you want to try again? (yes/no)");
                            answer=input.next();
                            answer= checkanswer(answer);
                        }

                    }while(answer.equalsIgnoreCase("yes"));
                    System.out.println("...............................................................................................................................");

                    break;

                }
                case "6": {
                    String name = new String();
                    String date = new String();
                    String username = new String();
                    do{
                        ArrayList<String> medicines = new ArrayList<>();
                        System.out.println("Enter the Patient`s Name: ");
                        name = input.next().toLowerCase().trim();
                        System.out.println("Enter the Date: ");
                        date = input.next().trim();
                        System.out.println("Enter the Medicine: ");
                        System.out.println("ATTENTION:When You Finish Writing the Medicines Write Finish");
                        do {
                            medicines.add(input.next().trim());
                        } while (!input.next().trim().equalsIgnoreCase("finish"));
                        System.out.println("Enter the Patient`s Username: ");
                        username = input.next().toLowerCase().trim();
                        done = doctor.CreatePrescription(name, date, medicines, username);
                        if (done) {
                            break;
                        } else {
                            System.out.println("The change was unsuccessful. Do you want to try again? (yes/no)");
                            answer=input.next();
                            answer=checkanswer(answer);
                        }

                    }while(answer.equalsIgnoreCase("yes"));
                    System.out.println("...............................................................................................................................");
                    break;




                }
                case "7":{
                    System.out.println("The Receptionist Contact information is: ");
                    doctor.GetReceptionistContactInfo();
                    System.out.println("...............................................................................................................................");
                    break;
                }
            }
            if(choice.equals("8")) {
                System.out.println("LOG OUT");
//            new Loginview();
                break;
            }

        }while(true);

    }
}