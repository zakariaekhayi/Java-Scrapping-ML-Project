package auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpFrame extends JFrame {
    private JTextField firstNameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private JFormattedTextField birthdayField;

    public SignUpFrame() {
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Birthday (yyyy-mm-dd):"));
        birthdayField = new JFormattedTextField();
        panel.add(birthdayField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this::handleSignUp);

        add(panel, BorderLayout.CENTER);
        add(signUpButton, BorderLayout.SOUTH);
    }

    private void handleSignUp(ActionEvent e) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthday = birthdayField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        UserDatabase.addUser(new user(firstName, lastName, birthday, email, password));
        JOptionPane.showMessageDialog(this, "Sign-up successful!");
        new LoginFrame().setVisible(true);
        dispose();
    }
}
