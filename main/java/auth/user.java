package auth;

public class user {
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String password;

    public user(String firstName, String lastName, String birthday, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
