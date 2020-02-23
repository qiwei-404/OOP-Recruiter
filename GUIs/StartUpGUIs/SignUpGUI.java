package GUIs.StartUpGUIs;

import GUIs.OurFrame;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends OurFrame {
    public JPanel SignUpWindow;
    private JButton applicantButton;
    private JButton HRButton;
    private JButton refereeButton;

    SignUpGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        applicantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUpApplicantGUI signUpApplicantGUI = new SignUpApplicantGUI(currentMS, "Applicant Sign Up");
                signUpApplicantGUI.openWindow(new SignUpApplicantGUI(currentMS, "Applicant Sign Up").SignUpApplicantWindow);
                frame.dispose();
            }
        });
        HRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUpHRGUI signUpHRGUI = new SignUpHRGUI(currentMS, "HR Sign Up");
                signUpHRGUI.openWindow(new SignUpHRGUI(currentMS, "HR Sign Up").SignUpHRWindow);
                frame.dispose();
            }
        });
        refereeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                SignUpRefereeGUI signUpRefereeGUI = new SignUpRefereeGUI(currentMS, "Referee Sign Up");
                signUpRefereeGUI.openWindow(new SignUpRefereeGUI(currentMS, "Referee Sign Up").SignUpRefereeWindow);
                frame.dispose();
            }
        });
    }
}
