import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
public class PatientView {
    Scanner input = new Scanner(System.in);
    String answer;
    double answerr;
    Appointment app;
    boolean ans1= true , ans2, ans3, loggedOut=false , found;
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    String mobileRegex = "^01(0|1|2|5)[0-9]{8}$";
    public PatientView(Patient patient) {
        do {
            ans3 = false;
            System.out.println("choose a service to execute : \n 1-Show the avaliable appointments for a certain doctor? \n 2-Show all the avaliavle appointments for this week \n 3-Show the price list?\n 4-Reserve an appointment?\n 5-Cancel my last reserved appointment? \n 6-Check all my appointments?\n 7-Show my prescription?\n 8-Change my saved info(Email-mobile number-weight-height)?\n 9-Log out");
            System.out.println("-------------------------------");
            answer = input.nextLine();
            switch (answer) {
                case "1":
                    System.out.println("Do you want to search by: \n 1-Doctor's name \n 2-Doctor's mobile number");
                    System.out.println("-------------------------------");
                    do {
                        answer = input.nextLine();
                        if (answer.equals("1")) {
                            System.out.println("Please enter doctor's username");
                            do{
                                answer = input.nextLine();
                                found=patient.SearchForDoctor(answer, "0");
                            }while(found);

                            ans1 = true;

                        } else if (answer.equals("2")) {
                            System.out.println("Please enter doctor's mobile number");
                            do{
                                answer = input.nextLine();
                                patient.SearchForDoctor("0", answer);
                            }while(found);
                            ans1 = true;
                        } else {
                            System.out.println("Invalid choice! try again");
                            ans1 = false;
                        }
                    } while (!ans1);
                    break;
                case "2":
                    patient.ShowAvailableAppointments();
                    break;
                case "3":
                    patient.show_pricess();

                    break;
                case "4":
                    patient.add_to_my_Appointments();
                    break;
                case "5":
                    try {
                        app = patient.my_app.get(patient.my_app.size() - 1);
                        patient.cancel_my_Appointments(app);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You don't have an appointment reserved to cancel");
                    }
                    System.out.println("Do you want to reserve an appointment? write \"yes\" or \"no\".");
                    while(true){
                        try{
                            answer = input.nextLine();
                            if(!answer.equalsIgnoreCase("yes")&&!answer.equalsIgnoreCase("no")){
                                throw new IllegalArgumentException("Invalid answer you should write \"yes\" or \"no\" \n Try again.");
                            }
                            else if (answer.equalsIgnoreCase("yes")) {
                                patient.add_to_my_Appointments();
                            }
                            break;
                        }catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "6":
                    patient.Showmyappo();
                    break;
                case "7":
                    try {
                        patient.DisplayMyPpt();
                    } catch (NullPointerException e) {
                        System.out.println("You don't have any saved prescription");
                    }
                    break;
                case "8":
                    do {
                        ans1 = false;
                        System.out.println("choose one :\n 1-email \n 2-mobile number \n 3-weight \n 4-height");
                        do {
                            ans2 = false;
                            answer = input.nextLine();
                            switch (answer) {
                                case "1":
                                    System.out.println("Enter your updated email");
                                    while(true){
                                        try{
                                            answer = input.nextLine();
                                            patient.ChangeEmail(answer);
                                            if(!answer.matches(emailRegex)){
                                                throw new IllegalArgumentException("Invalid email format. Please enter a valid email.");
                                            }
                                            break;
                                        }catch(IllegalArgumentException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case "2":
                                    System.out.println("Enter your updated mobile number");
                                    while(true){
                                        try{
                                            answer = input.nextLine();
                                            patient.ChangeEmail(answer);

                                            if(!answer.matches(mobileRegex)){
                                                throw new IllegalArgumentException("Invalid mobile number format. Please enter a valid mobile number.");
                                            }
                                            break;
                                        }catch(IllegalArgumentException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case "3":
                                    System.out.println("Enter your updated weight");
                                    while (true) {
                                        try {
                                            answerr = input.nextDouble();
                                            input.nextLine();
                                            patient.ChangeWeight(answerr);
                                            break;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! You should enter your weight in numerical form \n Try again.");
                                            input.nextLine();
                                        }
                                    }
                                    break;
                                case "4":
                                    System.out.println("Enter your updated height");
                                    while (true) {
                                        try {
                                            answerr = input.nextDouble();
                                            input.nextLine();
                                            patient.ChangeHeight(answerr);
                                            break;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! You should enter your height in numerical form \n Try again.");
                                            input.nextLine();
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice! You should choose a number from 1-4\nTry again.");
                                    ans2 = true;
                            }
                        } while (ans2);
                        System.out.println("Do you want to change another info? write yes or no.");
                        while(true){
                            try{
                                answer = input.nextLine();
                                if(!answer.equalsIgnoreCase("yes")&&!answer.equalsIgnoreCase("no")){
                                    throw new IllegalArgumentException("Invalid answer you should write \"yes\" or \"no\" \n Try again.");
                                }
                                else if (answer.equalsIgnoreCase("yes")) {
                                    ans1 = true;
                                }
                                break;
                            }catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        }
                    } while (ans1);
                    break;
                case "9":
                    System.out.println("You're logged out successfully!");
                    loggedOut=true;
//                    input.close();
                    break;
                default:
                    System.out.println("Invalid choice! try again");
                    ans3 = true;
            }
            if (ans3) {
                continue;
            }
            if(loggedOut){
                break;
            }
            System.out.println("Do you want to use another service? write \"yes\" or \"no\".");
            while(true){
                try{
                    answer = input.nextLine();
                    if(!answer.equalsIgnoreCase("yes")&&!answer.equalsIgnoreCase("no")){
                        throw new IllegalArgumentException("Invalid answer you should write \"yes\" or \"no\" \n Try again.");
                    }
                    else if (answer.equalsIgnoreCase("yes")) {
                        ans3 = true;
                    }
                    else {
                        ans3=false;
                        System.out.println("Do you want to log out? write \"yes\" or \"no\"");
                        while(true){
                            try{
                                answer = input.nextLine();
                                if(!answer.equalsIgnoreCase("yes")&&!answer.equalsIgnoreCase("no")){
                                    throw new IllegalArgumentException("Invalid answer you should write \"yes\" or \"no\" \n Try again.");
                                }
                                else if (answer.equalsIgnoreCase("yes")) {
                                    System.out.println("You're logged out successfully!");

                                }
                                else {
                                    ans3=true;
                                }
                                break;
                            }catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    break;
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        } while (ans3);
    }
}