package GUIs.StartUpGUIs;

import GUIs.OurFrame;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpHRGUI extends OurFrame {
    public JPanel SignUpHRWindow;
    private JButton coordinatorButton;
    private JButton interviewerButton;
    private JButton whatIsHRButton;

    SignUpHRGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        coordinatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUpCoordinatorGUI signUpCoordinatorGUI = new SignUpCoordinatorGUI(currentMS, "Coordinator Sign Up");
                signUpCoordinatorGUI.openWindow(new SignUpCoordinatorGUI(currentMS, "Coordinator Sign Up").SignUpCoordinatorWindow);
                frame.dispose();
            }
        });
        interviewerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUpInterviewerGUI signUpInterviewerGUI = new SignUpInterviewerGUI(currentMS, "Interviewer Sign Up");
                signUpInterviewerGUI.openWindow(new SignUpInterviewerGUI(currentMS, "Interviewer Sign Up").SignUpInterviewerWindow);
                frame.dispose();
            }
        });
        whatIsHRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                StartUpGUI startUpGUI = new StartUpGUI(currentMS, "Hello world");
                // Call GUIs.OurFrame.openWindow() to make our new window visible
                startUpGUI.openWindow(new StartUpGUI(currentMS, "Hello world").StartUpScreen);
                JOptionPane.showMessageDialog(null, "If you are wondering what is HR, you are not HR.");
                frame.dispose();
            }
        });
    }
}
