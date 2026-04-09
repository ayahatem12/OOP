import java.util.Scanner;
public class Mainview {

    Scanner input = new Scanner(System.in);
    public Mainview() {

        System.out.println("                                                                   (Welcome to our clinic system) ");
        while (true){
            System.out.println("( 1 ) for Login \n( 2 ) for Register\n( 3 ) Exit And Save !");
            String choice =input.nextLine();
            if(choice.equals("1")) {
                new Loginview();
            }
            else if(choice.equals("2")) {
                registerView registerView=new registerView();
                registerView.setType();
            }
            else if(choice.equals("3")) {
                return;
            }
            else {
                System.out.println("Invalid choice re_nter your choice");
            }
        }

    }
}
