package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Jobs.Document;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A GUI that allows a Coordinator to see the details from a given submission.
 */
public class ApplicationGUI extends OurFrame {
    public JPanel ApplicationWindow;
    private JLabel submissionNameHeader;
    private JLabel applicantName;
    private JLabel applicationDate;
    private JLabel applicationStatus;
    private JButton returnToApplicantDetailButton;
    private Submission currentSubmission;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     * @param selectedSubmission the submission that belongs to the applicant
     */
    ApplicationGUI(ManagingSystem currentMS, Submission selectedSubmission, String title) {
        super(currentMS, title);
        this.currentSubmission = selectedSubmission;

        this.setJLabelValues();
        this.setJButtons();
        setLayOut();
    }

    private ArrayList<JLabel> addDocsToPanel() {
        ArrayList<JLabel> labels = new ArrayList<>();
        for (Document document: currentSubmission.getSubmittedDocuments()) {
            JLabel docDisplay = new JLabel();
            docDisplay.setText(document.getNumbering() + document.getType() + "\n" + document.getContent());
            docDisplay.validate();
            labels.add(docDisplay);
        }
        return labels;
    }

    /**
     * Sets the JPanel values to show the details of the submission
     */
    private void setJLabelValues() {
        this.submissionNameHeader.setText(this.currentSubmission.toString());
        this.submissionNameHeader.validate();

        this.applicantName.setText("Applicant Name: " + this.currentSubmission.getApplicant().getUsername());
        this.applicantName.validate();

        this.applicationStatus.setText("Application Status: " + this.currentSubmission.getStatus());
        this.applicationStatus.validate();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = dateFormat.format(this.currentSubmission.getSubmissionDate());
        this.applicationDate.setText("Submission Date: " + strDate);
        this.applicationDate.validate();
    }

    /**
     * Sets the buttons to show the Coordinators options
     */
    private void setJButtons() {
        returnToApplicantDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                ApplicantDetailGUI applicantDetailGUI = new ApplicantDetailGUI(
                        currentMS, currentSubmission, "Applicant Details");
                applicantDetailGUI.openWindow(new ApplicantDetailGUI(
                        currentMS, currentSubmission, "Applicant Details").ApplicantDetailWindow);
                frame.dispose();
            }
        });
    }

    private void setLayOut() {
        ApplicationWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        ApplicationWindow.add(submissionNameHeader, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;

        ApplicationWindow.add(applicationStatus, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;

        ApplicationWindow.add(applicantName, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;

        ApplicationWindow.add(applicationDate, gbc);

        for (JLabel label: addDocsToPanel()) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx += 2;
            gbc.gridy += 2;
            gbc.gridwidth = 2;
            ApplicationWindow.add(label, gbc);
        }
    }
}
