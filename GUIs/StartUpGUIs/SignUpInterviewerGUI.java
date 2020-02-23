package GUIs.StartUpGUIs;

import GUIs.InterviewerGUI.InterviewerMainGUI;
import GUIs.OurFrame;
import GUIs.PickDateGUI;
import StartUp.Login;
import StartUp.ManagingSystem;
import StartUp.SignUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpInterviewerGUI extends OurFrame {
    public JPanel SignUpInterviewerWindow;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JButton signUpButton;
    private JButton actuallyCanILogButton;

    SignUpInterviewerGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUp currentSignUp = new SignUp(currentMS);
                Login intervieweerLogin = new Login(currentMS);
                String password = new String(passwordField1.getPassword());
                if (textField1.getText().isEmpty() || password.isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                }
                else {
                    currentSignUp.createNewInterviewer(textField1.getText(), password, textField2.getText());
                    if (currentSignUp.isSuccessful()) {
                        intervieweerLogin.login(textField1.getText(), password);
                        InterviewerMainGUI interviewerMainGUI = new InterviewerMainGUI(currentMS, "Interviewer Main Menu");
                        interviewerMainGUI.openWindow(new InterviewerMainGUI(currentMS, "Interviewer Main Menu").InterviewerMainWindow);
                        PickDateGUI pickDateGUI = new PickDateGUI(currentMS, "What date is it today?");
                        pickDateGUI.openWindow(new PickDateGUI(currentMS, "What date is it today?").PickDateWindow);
                        frame.dispose();
                        // Pop up a success message
                        JOptionPane.showMessageDialog(null, "Welcome " + textField1.getText() +
                                "! Now you can chat with random strangers about money and college degrees. Enjoy it!");
                    } else {
                        // Pop up a failure message.
                        JOptionPane.showMessageDialog(null, "This username " + textField1.getText() +
                                " already exists in the system. " + " Please choose a different username or log in instead.");
                    }
                }
            }
        });
        actuallyCanILogButton.addActionListener(new ActionListener() {
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
