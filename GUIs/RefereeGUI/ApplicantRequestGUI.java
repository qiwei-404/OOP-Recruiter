package GUIs.RefereeGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Document;
import StartUp.ManagingSystem;
import Users.Applicant;
import Users.Referee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantRequestGUI extends OurFrame {
    private JButton addReferenceForThisButton;
    private JButton backToApplicantSelectionButton;
    private JButton backToMainMenuButton;
    public JPanel ApplicantRequestWindow;
    private JLabel ApplicantName;

    ApplicantRequestGUI(ManagingSystem currentMS, Applicant applicant, String title) {
        super(currentMS, title);
        this.ApplicantName.setText(applicant.getUsername());

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("REFEREE", e);
            }
        });
        backToApplicantSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ViewRequestedReferencesGUI viewRequestedReferencesGUI = new ViewRequestedReferencesGUI(frame.getCurrentManagingSystem(), "Applicants Requesting References");
                viewRequestedReferencesGUI.openWindow(new ViewRequestedReferencesGUI(frame.getCurrentManagingSystem(),
                        "Applicants Requesting References").ViewRequestedReferencesWindow);
                frame.dispose();
            }
        });
        addReferenceForThisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                Referee currentUser = (Referee) currentMS.getCurrentUser();

                // add the reference from Referee to Applicant
                if (currentUser.getReferences().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have nothing to add!");
                }
                else {
                    Document reference = currentUser.getReferences().get(0);
                    applicant.getDocs().add(reference);

                    // return to main menu
                    RefereeMainGUI refereeMainGUI = new RefereeMainGUI(currentMS, "Users.RefereeGUI main");
                    refereeMainGUI.openWindow(new RefereeMainGUI(currentMS, "Users.RefereeGUI main").RefereeMainWindow);
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Thank you for submitting the reference! You will be blessed!");
                }

            }
        });
    }
}
