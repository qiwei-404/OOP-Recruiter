package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Document;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A GUI that allows an Applicant to the details of their submission.
 */
public class SubmissionDetailsGUI extends OurFrame {
    public JPanel SubmissionDetailsWindow;
    private JButton backToApplicantMainButton;
    private JLabel name;

    private JLabel status;
    private JLabel dateofSubmissions;
    private JLabel docsLabel;

    private Submission currSubmission;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     * @param currSubmission The Submission to be viewed
     */
    SubmissionDetailsGUI(ManagingSystem currentMS, Submission currSubmission, String title) {
        super(currentMS, title);
        this.currSubmission = currSubmission;
        this.setLabelValues();
        this.setJButtons();
    }

    /**
     * Sets the JButtons to show the Applicant's options
     */
    private void setJButtons(){
        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });
    }

    /**
     * Sets the JLabels to show the Submission's details
     */
    private void setLabelValues(){
        this.name.setText(this.currSubmission.getName());
        this.name.validate();

        this.docsLabel.setText("Documents Submitted: " + "\n" + getAllDocContents());
        this.docsLabel.validate();

        this.status.setText("Submission Status: " + this.currSubmission.getStatus());
        status.validate();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = dateFormat.format(this.currSubmission.getSubmissionDate());
        this.dateofSubmissions.setText("Submission Date" + strDate);
        dateofSubmissions.validate();
    }

    private String getAllDocContents() {
        String docContents = "";
        for (Document document: this.currSubmission.getSubmittedDocuments()) {
            docContents += document.getNumbering() + ": \n" + document.getContent() + "\n";
        }
        return docContents;
    }
}
