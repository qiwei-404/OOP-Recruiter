package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.JobPosting;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A GUI that allows an Applicant to see the descriptions to a selected Job Posting.
 */
public class PostingDescriptionGUI extends OurFrame {
    public JPanel PostingDescriptionWindow;
    private JButton backToApplicantMainButton;
    private JButton applyButton;
    private JButton backToSelectionButton;
    private JLabel company;
    private JLabel postedDate;
    private JLabel closed;
    private JLabel description;
    private JLabel Req;
    private JLabel JobNameLabel;
    private JobPosting jobPosting;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     * @param posting The Job Posting to be viewed
     */
    PostingDescriptionGUI(ManagingSystem currentMS, JobPosting posting, String title) {
        super(currentMS, title);
        this.setJobPosting(posting);
        this.setJLabel();

        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });

        // This action listener allows the applicant to create a Submission and apply to a Job Posting
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                PostingDescriptionGUI frame = (PostingDescriptionGUI) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                Applicant currentUser = (Applicant) currentMS.getCurrentUser();
                if (!currentUser.getAllSubmissions().checkSubmitted(jobPosting)) {
                    ApplyGUI applyGUI = new ApplyGUI(currentMS, frame.getJobPosting(), currentUser, "Submission Window");
                    applyGUI.openWindow(new ApplyGUI(currentMS, frame.getJobPosting(), currentUser, "Submission Window").ApplyWindow);
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "You have already applied for this job" +
                            "going back selection.");
                    ViewPostingGUI viewPostingGUI = new ViewPostingGUI(frame.getCurrentManagingSystem(),
                            "View Job Postings");
                    viewPostingGUI.openWindow(new ViewPostingGUI(frame.getCurrentManagingSystem(),
                            "View Job Postings").jobpostField);
                    frame.dispose();
                }
            }
        });

        // This action listener takes an Applicant back to the ViewPostingsGUI selection.
        backToSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ViewPostingGUI viewPostingGUI = new ViewPostingGUI(frame.getCurrentManagingSystem(),
                        "View Job Postings");
                viewPostingGUI.openWindow(new ViewPostingGUI(frame.getCurrentManagingSystem(),
                        "View Job Postings").jobpostField);
                frame.dispose();
            }
        });
    }

    /**
     * Returns the Job Posting that this GUI is describing
     * @return The Job Posting to be described
     */
    public JobPosting getJobPosting() {
        return jobPosting;
    }

    private void setJobPosting(JobPosting jobpost) {
        this.jobPosting = jobpost;
    }

    /**
     * Sets the JLabels to show the Job Posting's details
     */
    private void setJLabel() {
        DateFormat strDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JobNameLabel.setText(this.getJobPosting().getName());
        company.setText(this.getJobPosting().getCompany().getCompanyName());
        postedDate.setText(strDate.format(this.getJobPosting().getPostingActivation().getDatePosted()));
        closed.setText(strDate.format(this.getJobPosting().getPostingActivation().getDateCloses()));
        description.setText(this.getJobPosting().getDescription());
        String documentRequirements = "";
        for (String requirement: this.getJobPosting().getDocumentRequirements()) {
            documentRequirements += requirement + ", ";
        }
        Req.setText(documentRequirements);
    }
}
