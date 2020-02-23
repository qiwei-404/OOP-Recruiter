package GUIs.StartUpGUIs;

import GUIs.CoordinatorGUI.CoordinatorMainGUI;
import GUIs.OurFrame;
import GUIs.PickDateGUI;
import StartUp.Login;
import StartUp.ManagingSystem;
import StartUp.SignUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpCoordinatorGUI extends OurFrame {
    public JPanel SignUpCoordinatorWindow;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JButton signUpButton;
    private JButton iHaveAnAccountButton;

    SignUpCoordinatorGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUp currentSignUp = new SignUp(currentMS);
                Login coordinatorLogin = new Login(currentMS);
                String password = new String(passwordField1.getPassword());
                if (textField1.getText().isEmpty() || password.isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                }
                else {
                    currentSignUp.createNewCoordinator(textField1.getText(), password, textField2.getText());
                    if (currentSignUp.isSuccessful()) {
                        coordinatorLogin.login(textField1.getText(), password);
                        CoordinatorMainGUI coordinatorMainGUI = new CoordinatorMainGUI(currentMS, "Coordinator Main Menu");
                        coordinatorMainGUI.openWindow(new CoordinatorMainGUI(currentMS, "Coordinator Main Menu").CoordinatorMainWindow);
                        PickDateGUI pickDateGUI = new PickDateGUI(currentMS, "What date is it today?");
                        pickDateGUI.openWindow(new PickDateGUI(currentMS, "What date is it today?").PickDateWindow);
                        frame.dispose();
                        // Pop up a success message
                        JOptionPane.showMessageDialog(null, "Welcome " + textField1.getText() +
                                "! Now you have the power to fire and hire. Please use it wisely.");
                    } else {
                        // Pop up a failure message.
                        JOptionPane.showMessageDialog(null, "This username " + textField1.getText() +
                                " already exists in the system. " + " Please choose a different username or log in instead.");
                    }
                }
            }
        });
        iHaveAnAccountButton.addActionListener(new ActionListener() {
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
