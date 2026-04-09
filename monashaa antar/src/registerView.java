import java.util.Scanner;

public class registerView {
    public boolean isvaid=true;
    public registerView() {}
    public void setType(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("Enter the type of registration");
            System.out.println("Patient/Doctor/Receptionist");
            String type=input.next();
            if(type.equals("Patient")||type.equals("patient")){
                Registration r=new Registration(type);
                r.insertPatientInfo();
                isvaid=false;
            }
            else if(type.equals("Doctor")||type.equals("doctor")){
                Registration r2=new Registration(type);
                r2.insertDoctorInfo();
                isvaid=false;
            }
            else if(type.equals("Receptionist")||type.equals("receptionist")){
                Registration r3=new Registration(type);
                r3.insertReceptionistInfo();
                isvaid=false;
            }
            else {
                System.out.println("Please try again");
                isvaid=true;
            }
        }while (isvaid);
    }
}