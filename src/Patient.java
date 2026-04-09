import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Patient extends User {
    Scanner input =new Scanner(System.in);
    private String username , password , FirstName, LastName, Email , MobileNo ,PatientHistory, gender, BloodType;
    private int age;
    private double  weight, height;
    public ArrayList<Appointment> my_app= new ArrayList<>();
    private Prescription my_prescription;
    boolean found;
    int hour =-1;

    public Patient( String username, String password, String FirstName, String LastName, String Email, String MobileNo ,String PatientHistory, int age, String gender, String BloodType, double weight, double height){
        super(username,  password,  FirstName,  LastName,  Email, MobileNo);

        setPatientHistory(PatientHistory);
        setAge(age);
        setGender(gender);
        setBloodType(BloodType);
        setWeight(weight);
        setHeight(height);

    }
    public Patient(){
        super();
        Patient p=new Patient(super.getUsername(), super.getPassword(), super.getFirstname(), super.getLastname(), super.getEmail(), super.getMobilenumber(), getPatientHistory(), getAge(), getGender(), getBloodType(), getWeight(), getHeight());
    }

    public void setPatientHistory(String PatientHistory){
        this.PatientHistory=PatientHistory;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public void setBloodType(String BloodType){
        this.BloodType=BloodType;
    }
    public void setWeight(double weight){
        this.weight=weight;
    }
    public void setHeight(double height){
        this.height=height;
    }

    public String getPatientHistory(){
        return PatientHistory;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public String getBloodType(){
        return BloodType;
    }
    public double getWeight(){
        return weight;
    }
    public double getHeight(){
        return height;
    }
    public void setMy_Prescription(Prescription prescription){
        this.my_prescription=prescription;
    }
    public Prescription getMy_prescription(){
        return my_prescription;
    }

    public Scanner getInput() {
        return input;
    }


    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }


    public String getMobileNo() {
        return MobileNo;
    }

    public ArrayList<Appointment> getMy_app() {
        return my_app;
    }

    public boolean isFound() {
        return found;
    }

    public int getHour() {
        return hour;
    }

    public void getpatient(){
        System.out.println("Patient's name is "+getFirstname()+" "+getLastname());
        System.out.println("Patient's age is "+getAge());
        System.out.println("Patient's gender is "+getGender());
        System.out.println("Patient's blood type is "+getBloodType());
        System.out.println("Patient's weight is "+getWeight());
        System.out.println("Patient's height is "+getHeight());
        System.out.println("Patient's history is "+getPatientHistory());
    }

    public void ChangeEmail(String Email){
        super.setEmail(Email);
    }
    public void ChangeMobilenNo(String MobileNo){
        super.setMobilenumber(MobileNo);
    }
    public void ChangeWeight(double weight){
        this.setWeight(weight);
    }
    public void ChangeHeight(double height){
        this.setHeight(height);
    }

    public void show_pricess(){
        System.out.println("Teeth Cleaning costs: 200.0");
        System.out.println( "Cavity Filling costs: 300.0");
        System.out.println("Tooth Extraction costs: 500.0");
        System.out.println( "Tooth Extraction costs: 500.0");
        System.out.println("Root Canal Treatment costs:1500.0");
        System.out.println( "Dental X-Ray costs: 150.0");
        System.out.println("Braces Consultation costs: 250.0");
        System.out.println("Teeth Whitening costs: 1000.0 ");
    }
    public boolean ShowAvailableAppointments(){
        boolean dis=false;
        for(Doctor doctor:Clinic.doctors) {
            dis=doctor.DisplayAvailableDaysandhours(false);
        }
        if(!dis){
            System.out.println("there is no avaliable appointment");
        }
        return dis;
    }
    public boolean SearchForDoctor(String name, String MobileNo){
        boolean NotFound=true;
        try{
            for(Doctor doctor : Clinic.doctors){
                if(doctor.getUsername().equalsIgnoreCase(name)||doctor.getMobilenumber().equalsIgnoreCase(MobileNo)){
                    doctor.DisplayAvailableDaysandhours(false);
                    NotFound =false;
                    break;
                }
            }
        }catch(NullPointerException e){
            System.out.println("Invalid Info! \n Try again...");

        }
        return NotFound;
    }
    public void add_to_my_Appointments(){
        do{
            if(!ShowAvailableAppointments()){
                System.out.println("No appointments are avaliable to reserve");
                break;
            };
            System.out.println("Enter day you want:");
            String day = input.nextLine();
            while(true){
                try{
                    System.out.println("Enter hour you want:");
                    while(true){
                        hour = input.nextInt();
                        if(hour<1 || hour>12){
                            System.out.println("Invalid hour. Please provide an hour between 1 and 12..");
                        }else break;
                    }
                    input.nextLine();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Invalid input you should enter a number! try again.");
                    input.nextLine();
                }
            }
            System.out.println("Enter your doctor username from the list:");
            String doctorUserName = input.nextLine();

            Appointment appointment=new Appointment(day,hour,doctorUserName,this.getUsername());
            if( Clinic.room.addAppointment(appointment)) {
                my_app.add(appointment);
                found=true;
            }else {
                found=false;
            }
        }while(!found);
    }
    public void Showmyappo(){
        for(Appointment app:my_app){
            System.out.println(app.getDay() + " "+ "at " + app.getHour() + ":00");
        }
        if(my_app.isEmpty()){
            System.out.println("You don't have any reserved appointments");
        }
    }
    public void cancel_my_Appointments( Appointment appointment) {
        if(Clinic.room.cancelAppointment(appointment)){
            System.out.println("Appointment canceled successfully");
        }
        else
            System.out.println("Appointment cancellation Failed...");
    }
    public void DisplayMyPpt(){
        System.out.println("Name: "+ getMy_prescription().getPatient_name());
        System.out.println("Date: "+ getMy_prescription().getDate());
        System.out.println("Medicins: "+ getMy_prescription().getMedicins());
    }
}