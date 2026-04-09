import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
      Clinic c =  FileHandler.loadClinicData();
      Mainview mm = new Mainview();
      FileHandler.saveClinicData(c);
    }
}