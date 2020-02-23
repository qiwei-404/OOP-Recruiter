package GUIs.ApplicantGUI;
import GUIs.OurFrame;
import GUIs.PickDateGUI;
import GUIs.StartUpGUIs.StartUpGUI;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A GUI that allows an Applicant to see their options. It uses buttons to allow them to pick what they would like to do.
 */
public class ApplicantMainGUI extends OurFrame {
    public JPanel ApplicantMainWindow;
    private JButton viewJobPostingsButton;
    private JButton viewSubmissionsButton;
    private JButton logOutButton;
    private JButton changeTodaysDateButton;
    private JButton addDocumentButton;
    private JButton viewDocumentButton;
    private JLabel currentDateLabel;
    private JButton addRefereeButton;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    public ApplicantMainGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        this.setJLabels(currentMS);

        //Takes the Applicant to the ViewJobPostingGUI
        viewJobPostingsButton.addActionListener(new ActionListener() {
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

        //Takes the Applicant to the ViewSubmissionsGUI
        viewSubmissionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ViewSubmissionsGUI viewSubmissionsGUI = new ViewSubmissionsGUI(frame.getCurrentManagingSystem(),
                        "View Submissions");
                viewSubmissionsGUI.openWindow(new ViewSubmissionsGUI(frame.getCurrentManagingSystem(),
                        "View Submissions").ViewSubmissionsWindow);
                frame.dispose();
            }
        });

        //Logs the current Applicant out
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to log out it will just call StartUpGUIs
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                StartUpGUI startUpGUI = new StartUpGUI(frame.getCurrentManagingSystem(), "Start Up");
                startUpGUI.openWindow(new StartUpGUI(frame.getCurrentManagingSystem(), "Start Up").StartUpScreen);
                frame.dispose();
            }
        });

        //Changes Today's Date
        changeTodaysDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ApplicantMainGUI applicantMainGUI = new ApplicantMainGUI(frame.getCurrentManagingSystem(),
                        "Applicant Main Menu");
                applicantMainGUI.openWindow(new ApplicantMainGUI(frame.getCurrentManagingSystem(),
                        "Applicant Main Menu").ApplicantMainWindow);
                PickDateGUI pickDateGUI = new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?");
                pickDateGUI.openWindow(new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?").PickDateWindow);
                setJLabels(frame.getCurrentManagingSystem());
                frame.dispose();
            }
        });

        //Takes the Applicant to the AddDocGUI
        addDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                AddDocGUI addDocGUI = new AddDocGUI(frame.getCurrentManagingSystem(), "Add Document");
                addDocGUI.openWindow(new AddDocGUI(frame.getCurrentManagingSystem(),
                         "Add Document").AddDocField);
                frame.dispose();
            }
        });

        //Takes the Applicant to the ViewDocGUI
        viewDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ViewDocGUI viewDocGUI = new ViewDocGUI(frame.getCurrentManagingSystem(), "View Document");
                viewDocGUI.openWindow(new ViewDocGUI(frame.getCurrentManagingSystem(),
                        "View Document").viewDocField);
                frame.dispose();
            }
        });

        //Takes the Applicant to the AddRefereeGUI
        addRefereeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                AddRefereeGUI addRefereeGUI = new AddRefereeGUI(frame.getCurrentManagingSystem(), "Pick a Referee");
                addRefereeGUI.openWindow(new AddRefereeGUI(frame.getCurrentManagingSystem(),
                        "Pick a Referee").AddRefereeWindow);
                frame.dispose();
            }
        });
    }

    /**
     * Sets the JLabels to show the Applicant's account details
     * @param currentMS the current ManagingSystem that the Applicant is part of
     */
    private void setJLabels(ManagingSystem currentMS) { ;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.currentDateLabel.setText("Your account was created on " + dateFormat.format(((Applicant) currentMS.getCurrentUser()).getDateCreated()));
        this.currentDateLabel.validate();
    }
}
