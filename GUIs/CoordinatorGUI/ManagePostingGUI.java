package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Helpers.ListToListOfStrings;
import Helpers.SearchListHelper;
import Jobs.JobPosting;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;
import Users.Coordinator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI that allows a Coordinator to manage an existing job Posting.
 */
public class ManagePostingGUI extends OurFrame {
    public JPanel ManagePostingWindow;
    private JButton backToCoordinatorMainButton;
    private JCheckBox selectThisApplicantAsCheckBox;
    private JCheckBox setUpAnInterviewCheckBox;
    private JList list1;
    private JCheckBox viewSubmissionDetailsOfCheckBox;
    private JLabel SelectApplicantLabel;
    private JobPosting jobPosting;

    private JButton selectButton;
    private JScrollPane pane;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    ManagePostingGUI(ManagingSystem currentMS, String title, JobPosting jobPosting) {
        super(currentMS, title);
        this.jobPosting = jobPosting;

        ListToListOfStrings listToListOfStrings = new ListToListOfStrings();
        ArrayList<String> submissions = listToListOfStrings.convert(jobPosting.getAllSubmissions());
        this.list1 = new JList(submissions.toArray());
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectButton = new JButton("Select");
        pane = new JScrollPane(list1);

        backToCoordinatorMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("COORDINATOR", e);

            }
        });

        class SelectApplicantListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                if (list1.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Which applicant do you want to select?");
                }
                else if (checkOnlyOneSelected()) {
                    ArrayList<Submission> submissions = findSubmissions((ArrayList<String>) list1.getSelectedValuesList());
                    if (setUpAnInterviewCheckBox.isSelected()) {

                        SetInterviewGUI setInterviewGUI = new SetInterviewGUI(getCurrentManagingSystem(),
                                "Set Interview", submissions);
                        setInterviewGUI.openWindow(new SetInterviewGUI(getCurrentManagingSystem(),
                                "Set Interview", submissions).SetInterviewWindow);
                        frame.dispose();
                    }
                    else if (selectThisApplicantAsCheckBox.isSelected()) {
                        Coordinator currentUser = (Coordinator) getCurrentManagingSystem().getCurrentUser();
                        String message = currentUser.nominee(getJobPosting(e), submissions);
                        JOptionPane.showMessageDialog(null, message);
                        CompanyPostingsGUI companyPostingsGUI = new CompanyPostingsGUI(frame.getCurrentManagingSystem(),
                                "Company Job Postings");
                        companyPostingsGUI.openWindow(new CompanyPostingsGUI(frame.getCurrentManagingSystem(),
                                "Company Job Postings").CompanyPostingsWindow);
                        frame.dispose();
                    } else if (viewSubmissionDetailsOfCheckBox.isSelected()) {
                        if (submissions.size() == 1) {
                            ApplicantDetailGUI applicantDetailGUI = new ApplicantDetailGUI(currentMS,
                                    submissions.get(0), "Applicant Details");
                            applicantDetailGUI.openWindow(new ApplicantDetailGUI(currentMS,
                                    submissions.get(0), "Applicant Details").ApplicantDetailWindow);
                            frame.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Please make only one selection.");
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "You can only check one box. Make up your mind!");
                }
            }
        }
        selectButton.addActionListener(new SelectApplicantListener());
        setLayOut();
    }

    private JobPosting getJobPosting(ActionEvent e) {
        Component component = (Component) e.getSource();
        OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
        return this.jobPosting;
    }


    private ArrayList<Submission> findSubmissions(ArrayList<String> submissionNames) {
        //given the string representation of an applicant, returns the applicant object
        ArrayList<Submission> submissions = new ArrayList<>();
        for (String name: submissionNames) {
            SearchListHelper<Submission> searchListHelper = new SearchListHelper<>();
            searchListHelper.search(name, jobPosting.getAllSubmissions());
            submissions.add(searchListHelper.getObjectFound());
        }
        return submissions;
    }

    private void setLayOut() {
        ManagePostingWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(SelectApplicantLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(pane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(selectThisApplicantAsCheckBox, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(viewSubmissionDetailsOfCheckBox, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(setUpAnInterviewCheckBox, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(selectButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        ManagePostingWindow.add(backToCoordinatorMainButton, gbc);
    }

    private boolean checkOnlyOneSelected() {
        if ((selectThisApplicantAsCheckBox.isSelected() && !setUpAnInterviewCheckBox.isSelected()
                && !viewSubmissionDetailsOfCheckBox.isSelected()) || (!selectThisApplicantAsCheckBox.isSelected()
                && setUpAnInterviewCheckBox.isSelected() && !viewSubmissionDetailsOfCheckBox.isSelected()) ||
                (!selectThisApplicantAsCheckBox.isSelected() && !setUpAnInterviewCheckBox.isSelected() &&
                        viewSubmissionDetailsOfCheckBox.isSelected())) {
            return true;
        }
        return false;
    }
}



