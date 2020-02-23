package GUIs.StartUpGUIs;

import GUIs.ApplicantGUI.ApplicantMainGUI;
import GUIs.OurFrame;
import GUIs.PickDateGUI;
import StartUp.Login;
import StartUp.ManagingSystem;
import StartUp.SignUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpApplicantGUI extends OurFrame {
    public JPanel SignUpApplicantWindow;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton signUpButton;
    private JTextField textField2;
    private JButton alreadyHaveAnAccountButton;

    SignUpApplicantGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUp currentSignUp = new SignUp(currentMS);
                Login applicantLogin = new Login(currentMS);
                String password = new String(passwordField1.getPassword());
                if (textField1.getText().isEmpty() || password.isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                }
                else {
                    currentSignUp.createNewApplicant(textField1.getText(), password, textField2.getText());
                    if (currentSignUp.isSuccessful()) {
                        applicantLogin.login(textField1.getText(), password);
                        ApplicantMainGUI applicantMainGUI = new ApplicantMainGUI(currentMS, "Applicant Main Menu");
                        applicantMainGUI.openWindow(new ApplicantMainGUI(currentMS, "Applicant Main Menu").ApplicantMainWindow);
                        PickDateGUI pickDateGUI = new PickDateGUI(currentMS, "What date is it today?");
                        pickDateGUI.openWindow(new PickDateGUI(currentMS, "What date is it today?").PickDateWindow);
                        frame.dispose();
                        // Pop up a success message
                        JOptionPane.showMessageDialog(null, "Welcome " + textField1.getText() +
                                "! You are now entitled to millions of opportunities we provide.");
                    } else {
                        // Pop up a failure message.
                        JOptionPane.showMessageDialog(null, "This username " + textField1.getText() +
                                " already exists in the system. " + " Please choose a different username or log in instead.");
                    }
                }
            }
        });
        alreadyHaveAnAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                // Create a reference to the current window.
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                LogInGUI logInGUI = new LogInGUI(frame.getCurrentManagingSystem(), "User Log In");
                logInGUI.openWindow(new LogInGUI(frame.getCurrentManagingSystem(), "User Log In").LogInWindow);
                // Close the current window once we're done.
                frame.dispose();
            }
        });
    }
}
