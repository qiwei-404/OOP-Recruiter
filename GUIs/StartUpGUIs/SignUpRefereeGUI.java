package GUIs.StartUpGUIs;

import GUIs.OurFrame;
import GUIs.PickDateGUI;
import GUIs.RefereeGUI.RefereeMainGUI;
import StartUp.Login;
import StartUp.ManagingSystem;
import StartUp.SignUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpRefereeGUI extends OurFrame {
    public JPanel SignUpRefereeWindow;
    private JTextField textField1;
    private JButton alreadyHaveAnAccountButton;
    private JButton signUpButton;
    private JPasswordField passwordField1;
    private JTextField textField2;

    SignUpRefereeGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        alreadyHaveAnAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                LogInGUI logInGUI = new LogInGUI(frame.getCurrentManagingSystem(), "User Log In");
                logInGUI.openWindow(new LogInGUI(frame.getCurrentManagingSystem(), "User Log In").LogInWindow);
                frame.dispose();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUp currentSignUp = new SignUp(currentMS);
                Login refereeLogin = new Login(currentMS);
                String password = new String(passwordField1.getPassword());
                if (textField1.getText().isEmpty() || password.isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                }
                else {
                    currentSignUp.createNewReferee(textField1.getText(), password, textField2.getText());
                    if (currentSignUp.isSuccessful()) {
                        refereeLogin.login(textField1.getText(), password);
                        RefereeMainGUI refereeMainGUI = new RefereeMainGUI(currentMS, "Referee Main Menu");
                        refereeMainGUI.openWindow(new RefereeMainGUI(currentMS, "Referee Main Menu").RefereeMainWindow);
                        PickDateGUI pickDateGUI = new PickDateGUI(currentMS, "What date is it today?");
                        pickDateGUI.openWindow(new PickDateGUI(currentMS, "What date is it today?").PickDateWindow);
                        frame.dispose();
                        // Pop up a success message
                        JOptionPane.showMessageDialog(null, "Welcome " + textField1.getText() +
                                "! You are so popular! Lots of people are waiting for your references.");
                    } else {
                        // Pop up a failure message.
                        JOptionPane.showMessageDialog(null, "This username " + textField1.getText() +
                                " already exists in the system. " + " Please choose a different username or log in instead.");
                    }
                }
            }

        });
    }
}
