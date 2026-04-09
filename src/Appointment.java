public class Appointment {
    private String day;
    private int hour;
    private String doctor_username;
    private String patient_username;

    public Appointment() {
    }

    public Appointment(String day, int hour, String doctor_username, String patient_username) {
        this.day = day;
        this.hour = hour;
        this.doctor_username=doctor_username;
        this.patient_username=patient_username;
    }

    public Appointment(String day, int hour) {
        this.day = day;
        this.hour=hour;
    }


    public String getPatient_username() {
        return patient_username;
    }

    public void setPatient_username(String patient_username) {
        this.patient_username = patient_username;
    }

    public String getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getdoctor_username(){return doctor_username;}

    public void setDay(String day) {
        this.day = day;
    }

    public void setDoctor_username(String doctor_username) {
        this.doctor_username = doctor_username;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return hour == that.hour && day.equals(that.day);
    }


}