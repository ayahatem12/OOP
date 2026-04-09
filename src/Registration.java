
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Registration  {
    public String registrationType;
    public Registration(String registrationType) {
        this.registrationType = registrationType;
    }
    Scanner input = new Scanner(System.in);
    public void insertPatientInfo(){
        Patient p = new Patient();
        boolean checkspace1=true;
        do {
            System.out.println("Enter your First Name");
            String firstname_Patient = input.nextLine();
            if (firstname_Patient.contains(" ")||firstname_Patient.isEmpty()) {
                System.out.println("Enter the first name and second name separately");
                firstname_Patient = input.nextLine();
                checkspace1=true;
            }
            if(!firstname_Patient.contains(" ")){
                p.setFirstname(firstname_Patient);
                checkspace1=false;
            }
        } while (checkspace1);
        boolean checkspace2=true;
        do {
            System.out.println("Enter your Last Name");
            String lastname_Patient = input.nextLine();
            if (lastname_Patient.contains(" ")||lastname_Patient.isEmpty()) {
                System.out.println("Enter the Last Name Only");
                lastname_Patient = input.nextLine();
                checkspace2=true;
            }
            if(!lastname_Patient.contains(" ")){
                p.setLastname(lastname_Patient);
                checkspace2=false;
            }
        } while (checkspace2);
        boolean checkemail=true;
        do {
            System.out.println("Enter your Email like user@ex.com");
            String email_Patient = input.nextLine();
            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if(email_Patient.matches(emailPattern)){
                p.setEmail(email_Patient);
                checkemail=false;
            }
            else if(email_Patient.isEmpty()) {
                System.out.println("Invalid Email");
            }

        }while (checkemail);
        String mobileRegex = "^01(0|1|2|5)[0-9]{8}$";
        while(true){
            try{
                System.out.println("Enter your Mobile Number");
                String mobileno_Patient = input.nextLine();
                if(!mobileno_Patient.matches(mobileRegex)){
                    throw new IllegalArgumentException("Invalid mobile number format.");
                }
                else if(mobileno_Patient.matches(mobileRegex)){
                    p.setMobilenumber(mobileno_Patient);
                }
                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter your Patient History");
        String patienthistory_Patient = input.nextLine();
        p.setPatientHistory(patienthistory_Patient);
        boolean checkgender=true;
        do {
            System.out.println("Enter your Gender Female/Male");
            String gender_Patient=input.nextLine();
            if(gender_Patient.equals("Female")||gender_Patient.equals("female")||gender_Patient.equals("male")||gender_Patient.equals("Male")){
                p.setGender(gender_Patient);
                checkgender=false;
            }
            else {
                System.out.println("Try again");
            }
        }while (checkgender);

        boolean checkbloodtype=true;
        do {
            System.out.println("Enter your Blood Type");
            String bloodtype_Patient = input.nextLine().trim();
            String bloodTypePattern = "^(A|B|AB|O)[+-]?$";
            if (bloodtype_Patient.matches(bloodTypePattern)) {
                p.setBloodType(bloodtype_Patient);

                break;
            } else  {
                System.out.println("Invalid blood type. Please enter a valid blood type (AB+/AB-, A+/A-, B-/B+,O-/O+)");
                p.setBloodType(bloodtype_Patient);
                checkbloodtype=true;
            }

        }while (checkbloodtype);
        boolean checkage=true;
        do {
            System.out.println("Enter your Age");
            if(input.hasNextInt()){
                int age_Patient = input.nextInt();
                p.setAge(age_Patient);
                checkage=false;
            }
            else {
                System.out.println("Invalid type");
                input.next();
            }
        }while (checkage);
        boolean checkweight=true;
        do {
            System.out.println("Enter your Weight");
            if(input.hasNextDouble()){
                Double weight_Patient = input.nextDouble();
                p.setWeight(weight_Patient);
                checkweight=false;
            }
            else if(input.hasNextInt()){
                int weight_patient=input.nextInt();
                p.setWeight(weight_patient);
                checkweight=false;
            }
            else {
                System.out.println("Invalid type");
                input.nextLine();
            }

        }while (checkweight);
        boolean checkheight=true;
        do {
            System.out.println("Enter your Height");
            if(input.hasNextDouble()){
                Double height_Patient = input.nextDouble();
                p.setHeight(height_Patient);
                checkheight=false;
            }
            else if(input.hasNextInt()){
                int height_patient=input.nextInt();
                p.setHeight(height_patient);
                checkheight=false;
            }
            else {
                System.out.println("Invalid type");
                input.nextLine();
            }
        }while (checkheight);
        input.nextLine();
        boolean checkusername=true;
        do {
            System.out.println("Enter your Username");
            boolean isfound=false;
            String username_Patient = input.nextLine();
            p.setUsername(username_Patient);
            boolean checkspace3=true;
            do {
                if (username_Patient.contains(" ")||username_Patient.isEmpty()) {
                    System.out.println("Enter the username only");
                    username_Patient = input.nextLine();
                    checkspace3=true;
                }
                if(!username_Patient.contains(" ")){
                    p.setUsername(username_Patient);
                    checkspace3=false;
                }
            } while (checkspace3);
            for (Patient p1 : Clinic.patients) {
                if (p1.getUsername().equals(p.getUsername())&&!Clinic.patients.isEmpty()) {
                    isfound=true;
                    break;
                }
            }
            if(isfound) {
                System.out.println("You have enter your information before");
            }
            else {
                Clinic.patients.add(p);
                checkusername=false;
            }
        }while (checkusername);
        System.out.println("Enter your Password");
        String password_Patient = input.nextLine();
        p.setPassword(password_Patient);
        Clinic.patients.add(p);

    }


    public void insertReceptionistInfo() {
        Receptionist r = new Receptionist();
        boolean checkspace=true;
        do {
            System.out.println("Enter your First Name");
            String firstname_Receptionist = input.nextLine();
            if (firstname_Receptionist.contains(" ")||firstname_Receptionist.isEmpty()) {
                System.out.println("Enter the first name and second name separately");
                firstname_Receptionist = input.nextLine();
                checkspace=true;
            }
            if(!firstname_Receptionist.contains(" ")){
                r.setFirstname(firstname_Receptionist);
                checkspace=false;
            }
        } while (checkspace);
        boolean checkspace2=true;
        do {
            System.out.println("Enter your Last Name");
            String lastname_Receptionist = input.nextLine();
            if (lastname_Receptionist.contains(" ")||lastname_Receptionist.isEmpty()) {
                System.out.println("Enter the Last Name Only");
                lastname_Receptionist = input.nextLine();
                checkspace2=true;
            }
            if(!lastname_Receptionist.contains(" ")){
                r.setLastname(lastname_Receptionist);
                checkspace2=false;
            }
        } while (checkspace2);
        boolean checkemail=true;
        do {
            System.out.println("Enter your Email like user@ex.com");
            String email_Receptionist = input.nextLine();
            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if(email_Receptionist.matches(emailPattern)){
                r.setEmail(email_Receptionist);
                checkemail=false;
            }
            else {
                System.out.println("Invalid Email");
            }

        }while (checkemail);
        String mobileRegex = "^01(0|1|2|5)[0-9]{8}$";
        while(true){
            try{
                System.out.println("Enter your Mobile Number");
                String mobileno_Receptionist = input.nextLine();
                if(!mobileno_Receptionist.matches(mobileRegex)){
                    throw new IllegalArgumentException("Invalid mobile number format.");
                }
                else if(mobileno_Receptionist.matches(mobileRegex)){
                    r.setMobilenumber(mobileno_Receptionist);
                }
                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        boolean checkage=true;
        do {
            System.out.println("Enter your Age");
            if(input.hasNextInt()){
                int age_Receptionist = input.nextInt();
                r.setAge(age_Receptionist);
                checkage=false;
                input.nextLine();
            }
            else {
                System.out.println("Invalid type");
                input.nextLine();
            }

        }while (checkage);
        boolean checkgender=true;
        do {
            System.out.println("Enter your Gender Female/Male");
            String gender_Receptionist = input.nextLine();
            if (gender_Receptionist.equals("Female") || gender_Receptionist.equals("female") || gender_Receptionist.equals("male") || gender_Receptionist.equals("Male")) {
                r.setGender(gender_Receptionist);
                checkgender = false;
            } else {
                System.out.println("Try again");
            }
        }  while (checkgender);

        boolean checkspace3=true;
        do {
            System.out.println("Enter your Username");
            String username_Receptionist = input.nextLine();
            if (username_Receptionist.contains(" ")||username_Receptionist.isEmpty()) {
                System.out.println("Enter the username only");
                username_Receptionist = input.nextLine();
                checkspace3=true;
            }
            if(!username_Receptionist.contains(" ")){
                r.setUsername(username_Receptionist);
                checkspace3=false;
            }
        } while (checkspace);
        System.out.println("Enter your Password");
        String password_Receptionist = input.nextLine();
        r.setPassword(password_Receptionist);
        Clinic.receptionist=r;
    }
    public void insertDoctorInfo() {

        Doctor d = new Doctor();
        boolean checkspace=true;
        do {
            System.out.println("Enter your First Name");
            String firstname_Doctor = input.nextLine();
            if (firstname_Doctor.contains(" ")||firstname_Doctor.isEmpty()) {
                System.out.println("Enter the first name and second name separately");
                firstname_Doctor = input.nextLine();
                checkspace=true;
            }
            if(!firstname_Doctor.contains(" ")){
                d.setFirstname(firstname_Doctor);
                checkspace=false;
            }
        } while (checkspace);
        boolean checkspace2=true;
        do {
            System.out.println("Enter your Last Name");
            String lastname_Doctor = input.nextLine();
            if (lastname_Doctor.contains(" ")||lastname_Doctor.isEmpty()) {
                System.out.println("Enter the Last Name Only");
                lastname_Doctor = input.nextLine();
                checkspace2=true;
            }
            if(!lastname_Doctor.contains(" ")){
                d.setLastname(lastname_Doctor);
                checkspace2=false;
            }
        } while (checkspace2);
        boolean checkemail=true;
        do {
            System.out.println("Enter your Email like user@ex.com");
            String email_Doctor = input.nextLine();
            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if(email_Doctor.matches(emailPattern)){
                d.setEmail(email_Doctor);
                checkemail=false;
            }
            else {
                System.out.println("Invalid Email");
            }
        }while (checkemail);
        String mobileRegex = "^01(0|1|2|5)[0-9]{8}$";
        while(true){
            try{
                System.out.println("Enter your Mobile Number");
                String mobileno_Doctor = input.nextLine();
                if(!mobileno_Doctor.matches(mobileRegex)){
                    throw new IllegalArgumentException("Invalid mobile number format.");
                }
                else if(mobileno_Doctor.matches(mobileRegex)){
                    d.setMobilenumber(mobileno_Doctor);
                }
                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter your Specialization");
        String specialization_Doctor = input.nextLine();
        d.setSpecialization(specialization_Doctor);
        String ans="yes";
        boolean checkusername=true;
        do {
            System.out.println("Enter your Username");
            boolean isfound=false;
            String username_Doctor = input.nextLine();
            d.setUsername(username_Doctor);
            boolean checkspace3=true;
            do {
                if (username_Doctor.contains(" ")||username_Doctor.isEmpty()) {
                    System.out.println("Enter the username only");
                    username_Doctor = input.nextLine();
                    checkspace3=true;
                }
                if(!username_Doctor.contains(" ")){
                    d.setUsername(username_Doctor);
                    checkspace3=false;
                }
            } while (checkspace3);
            for (Doctor d1: Clinic.doctors) {
                if (d1.getUsername().equals(d.getUsername())&&!Clinic.doctors.isEmpty()) {
                    isfound=true;
                    break;
                }
            }
            if(isfound) {
                System.out.println("You have enter your information before");
            }
            else {
                Clinic.doctors.add(d);
                checkusername=false;
            }
        }while (checkusername);

        System.out.println("Enter your Password");
        String password_Doctor = input.nextLine();
        d.setPassword(password_Doctor);
        do{
            System.out.println("Enter your Available Days and Hours on this day");
            String availableday_Doctor = input.nextLine().toLowerCase();
            int availablehour_Doctor = input.nextInt();
            d.setAvailableDaysandhours(availableday_Doctor,availablehour_Doctor);
            System.out.println("Do you have another day or hour");
            ans=input.nextLine().toLowerCase();

        }while (ans.equals("yes"));
        input.nextLine();
    }
}