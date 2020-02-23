package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Helpers.ListToListOfStrings;
import Jobs.JobPosting;
import Jobs.JobPostingFilter;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A GUI that allows an Applicant to the list of all Job Postings.
 */
public class ViewPostingGUI extends OurFrame {
    public JPanel jobpostField;
    private JButton backToApplicantMainButton;
    private JList jobList;
    private JLabel titleLabel;
    private JButton filteredViewButton;
    private JTextField filterViewTextField;
    private JScrollPane pane;
    private boolean filteredSearch;
    private JobPostingFilter jobPostingFilter;


    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    ViewPostingGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        filteredSearch = false;
        jobPostingFilter = new JobPostingFilter();

        ListToListOfStrings listToListOfStrings = new ListToListOfStrings();
        ArrayList<String> jobPostingsString = listToListOfStrings.convert(currentMS.getAllJobPostings());
        this.jobList = new JList(jobPostingsString.toArray());

        this.pane = new JScrollPane(this.jobList);
        this.doubleClickListener(this.jobList);

        this.setList();
        this.setLayOut();

        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });

        // allows the applicant to filter through jobs positings
        filteredViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                jobPostingFilter.reset();
                jobPostingFilter.updateTagInterpreter(filterViewTextField.getText());
                jobPostingFilter.matchPostingList(currentMS.getAllJobPostings());
                if (!filterViewTextField.getText().isEmpty()) {
                    filteredSearch = true;
                    if (jobPostingFilter.getMatchingPostingsNames().isEmpty()) {
                        JOptionPane.showMessageDialog(null,"No job postings match your search.");
                    }
                }
                else {
                    filteredSearch = false;
                }
                setList();
            }
        });
    }

    /**
     * Apply double click button listener to the given list
     * @param postList An ArrayList of Jobpost
     */
    private void doubleClickListener(JList postList) {
        postList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent click) {
                JList postList = (JList) click.getSource();
                if (click.getClickCount() >= 2) {
                    // When clicked more than doulbe,direct to description Document GUI
                    int index = postList.locationToIndex(click.getPoint());
                    Component component = (Component) click.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                    ManagingSystem frameCurrentMS = frame.getCurrentManagingSystem();
                    Applicant user = (Applicant) frameCurrentMS.getCurrentUser();
                    JobPosting selectedPost = frameCurrentMS.getAllJobPostings().get(index);
                    if (filteredSearch) {
                        selectedPost = jobPostingFilter.getMatchingPostings().get(index);
                    }
                    PostingDescriptionGUI postingDescriptionGUIGUI = new PostingDescriptionGUI(
                            frame.getCurrentManagingSystem(), selectedPost, "View Posting Description");
                    postingDescriptionGUIGUI.openWindow(
                            new PostingDescriptionGUI(frame.getCurrentManagingSystem(), selectedPost,
                                    "View Posting Description").PostingDescriptionWindow);
                    frame.dispose();
                }
            }
        });
    }

    private void setLayOut() {
        jobpostField.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //        put constraints on components
        // start with PickInterviewerPanel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jobpostField.add(titleLabel, gbc);

        // position of scroll panel pane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        jobpostField.add(pane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        jobpostField.add(filteredViewButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        jobpostField.add(filterViewTextField, gbc);

        // and so on
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        jobpostField.add(backToApplicantMainButton, gbc);
    }

    private void setList() {
        ListToListOfStrings listToListOfStrings = new ListToListOfStrings();
        ArrayList<String> jobPostingsString = listToListOfStrings.convert(getCurrentManagingSystem().getAllJobPostings());
        if (filteredSearch) {
            jobPostingsString = listToListOfStrings.convert(jobPostingFilter.getMatchingPostings());
        }
        this.jobList.setListData(jobPostingsString.toArray());
    }
}
