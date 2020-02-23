package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI that allows a Coordinator to see the details of an applicant from a given submission.
 */
public class ApplicantDetailGUI extends OurFrame {
    public JPanel ApplicantDetailWindow;
    private JLabel applicantUserName;
    private JLabel applicantPhoneNumber;
    private JLabel jobPostingTitle;
    private JButton returnToViewApplicantsButton;
    private JButton viewApplicationButton;
    private Submission selectedSubmission;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     * @param selectedSubmission the submission that belongs to the applicant
     */
    ApplicantDetailGUI(ManagingSystem currentMS, Submission selectedSubmission, String title) {
        super(currentMS, title);
        this.selectedSubmission = selectedSubmission;
        this.setJLabelValues();
        this.setJButtons();
    }

    /**
     * Sets the buttons to show the Coordinators options
     */
    private void setJButtons() {
        viewApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                ApplicationGUI applicationGUI = new ApplicationGUI(
                        currentMS, selectedSubmission, "See Application");
                applicationGUI.openWindow(new ApplicationGUI(
                        currentMS, selectedSubmission, "See Application").ApplicationWindow);
                frame.dispose();
            }

        });

        returnToViewApplicantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                ManagePostingGUI managePostingGUI = new ManagePostingGUI(currentMS,
                        "Manage Job Posting", selectedSubmission.getJobPosting());
                managePostingGUI.openWindow(new ManagePostingGUI(currentMS,
                        "Manage Job Posting", selectedSubmission.getJobPosting()).ManagePostingWindow);
                frame.dispose();
            }
        });
    }

    /**
     * Sets the JPanel values to show the details of the applicant
     */
    private void setJLabelValues() {
        this.applicantUserName.setText(this.selectedSubmission.getApplicant().getUsername());
        this.applicantUserName.validate();

        this.applicantPhoneNumber.setText(this.selectedSubmission.getApplicant().getPhoneNumber());
        this.applicantPhoneNumber.validate();

        this.jobPostingTitle.setText(this.selectedSubmission.getJobPosting().toString());
        this.jobPostingTitle.validate();
    }
}
