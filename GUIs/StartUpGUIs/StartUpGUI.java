package GUIs.StartUpGUIs;

// You must import those packages.
import GUIs.OurFrame;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// You must manually add "extends JFrame" to use the JFrame features.
public class StartUpGUI extends OurFrame {
    // JPanel needs to be accessed.
    public JPanel StartUpScreen;
    private JButton signUpButton;
    private JButton logInButton;

    public StartUpGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);

        // To create ActionListener, you must right click on the GUI component in StartUpGUIs.form, and then go
        // to Create Listener >Action Listener. Once you do that, this method will be automatically called.
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a reference to the Sign Up button.
                Component component = (Component) e.getSource();
                // Create a reference to the current window.
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                // Create a new frame
                SignUpGUI signUpGUI = new SignUpGUI(frame.getCurrentManagingSystem(), "User Sign Up");
                // Call the openWindow helper in GUIs.OurFrame class.
                signUpGUI.openWindow(new SignUpGUI(frame.getCurrentManagingSystem(), "User Sign Up").SignUpWindow);

                // Close the current window once we're done.
                frame.dispose();
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // The purpose of the following 5 lines is explained in MainPhase1.java.
                // Create a reference to the Sign Up button.
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
