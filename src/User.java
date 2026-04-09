import java.util.Scanner;
public abstract class User {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String mobilenumber;

    public User(String username, String password, String firstname, String lastname, String email, String mobilenumber) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilenumber = mobilenumber;
    }
    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }


    @Override
    public String toString(){
        return "User Information:" +
                "\nUser Name:"+username+
                "\nPassword:"+password+
                "\nFirst Name:"+firstname+
                "\nLast Name:"+lastname+
                "\nEmail:"+email+
                "\nMobile Number:"+mobilenumber;
    }
}
