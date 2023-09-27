public class Customer {
    private int socialSecurityNumber;  //Uses as username to log in
    private String firstName;
    private String lastName;
    private String email;
    private String password;    //Uses to log in

    public Customer(int socialSecurityNumber, String firstName, String lastName, String email, String password) {
        this.socialSecurityNumber = socialSecurityNumber; //Uses as username to log in
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;                           //Uses as username to log in

    }
    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
