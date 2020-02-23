package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI that allows an Applicant to the list of all their Submissions.
 */
public class ViewSubmissionsGUI extends OurFrame {
    public JPanel ViewSubmissionsWindow;
    private JList listOfSubmissions;
    private JButton backToApplicantMainButton;

    private JLabel titleLabel;
    private JLabel pickSubLabel;
    private Applicant applicant;


    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    ViewSubmissionsGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        this.applicant = (Applicant) currentMS.getCurrentUser();
        ViewSubmissionsWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setJButtons();

        // Getting the names (class String) of each submission
        // in this.applicant.getAllSubmissions()
        ArrayList<Submission> submissionsOfApplicant = this.applicant.getAllSubmissions().getListSub();
        ArrayList<String> allSubmissionNames = new ArrayList<>();
        for (Submission submission : submissionsOfApplicant) {
            allSubmissionNames.add(submission.getName());
        }

        this.listOfSubmissions = new JList(allSubmissionNames.toArray());

        /**DEBUGGING SYSTEM OUT PRINT LN BELOW
         * this.applicant.getAllSubmissions doesn't
         * seem to be updating dynamically - Becky**/
        //System.out.println(allSubmissionNames.toString());

        JButton button = new JButton("Select");
        JScrollPane pane = new JScrollPane(this.listOfSubmissions);

        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        listOfSubmissions.setSelectionModel(m);
        listOfSubmissions.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) { System.out.println(e.toString()); }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ViewSubmissionsWindow.add(titleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        ViewSubmissionsWindow.add(pickSubLabel, gbc);

        // position of scroll panel pane
        gbc.gridx = 2;
        gbc.gridy = 1;
        ViewSubmissionsWindow.add(pane, gbc);

        // and so on
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        ViewSubmissionsWindow.add(button, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        ViewSubmissionsWindow.add(backToApplicantMainButton, gbc);

        class SelectPostingListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int selected[] = listOfSubmissions.getSelectedIndices();

                for (int i: selected) {
                    String element = (String) listOfSubmissions.getModel().getElementAt(
                            i);
                    selectedItem = element;
                }
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                if (selectedItem.isEmpty()) {
                    JOptionPane.showMessageDialog(component, "Which job posting do you want to select?");
                }
                else {
                    for (Submission submission : submissionsOfApplicant) {
                        if (submission.getName().equals(selectedItem)) {
                            SubmissionDetailsGUI submissionDetailsGUI = new SubmissionDetailsGUI(currentMS, submission, "Submission Details");
                            submissionDetailsGUI.openWindow(new SubmissionDetailsGUI(currentMS, submission, "Submission Details").SubmissionDetailsWindow);
                            frame.dispose();
                        }
                    }

                }
            }
        }
        button.addActionListener(new SelectPostingListener());
    }

    /**
     * Sets the JButtons to show the appliant's options
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
}
