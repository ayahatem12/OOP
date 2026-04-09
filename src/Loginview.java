import java.util.Scanner;

public class Loginview {
    public Loginview() {
        Scanner input = new Scanner(System.in);
        System.out.println( "Loginview");
        System.out.println("choose login");
        System.out.println("1-Doctor");
        System.out.println("2-Patient");
        System.out.println("3-Receptionist");
        String choice =new String();
        choice = input.nextLine();
        boolean loginfail = false;
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.println("Invalid choice");
            choice = input.nextLine();
        }
        if(choice.equals("1")) {
            Login login = new Login();
            Doctor loggedin = login.Doctorlogin();
            if(loggedin!=null) {
                DoctorView doctorView=new DoctorView(loggedin);
            }
            else {
                loginfail = true;
                System.out.println("Login Failed");

            }
        }
        else if(choice.equals("2")) {
            Login login = new Login();
            Patient loggedin = login.Patientlogin();
            if(loggedin!=null) {
                 PatientView patientview=new PatientView(loggedin);
            }
            else {
                loginfail = true;
                System.out.println("Login Failed");
            }
        }
        else if(choice.equals("3")) {
            Login login = new Login();
            Receptionist loggedin= login.Reciptionstlogin();
            if(loggedin!=null) {
            ReceptionistView receptionistview=new ReceptionistView(loggedin);
            }
            else {
                loginfail = true;
                System.out.println("Login Failed");
            }
        }
        if(loginfail) {
           Mainview mainview = new Mainview();
        }
    }

}
