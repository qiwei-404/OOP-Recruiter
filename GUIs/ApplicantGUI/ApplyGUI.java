package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Document;
import Jobs.JobPosting;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI that allows an Applicant to apply to a job posting. It uses the Applicant's documents to create a submission
 * and checks to see if they match the requirements of the Job Posting.
 */
public class ApplyGUI extends OurFrame {
    public JPanel ApplyWindow;
    private JLabel selectLabel;
    private JButton backToApplicantMainButton;
    private JobPosting jobPosting;
    private JList documentsList;

    private JButton applyButton = new JButton("I can't wait to submit my application!");
    private JScrollPane pane;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     * @param posting The Job Posting that the Applicant wants to apply too
     * @param applicant The Applicant that is applying
     */
    ApplyGUI(ManagingSystem currentMS, JobPosting posting, Applicant applicant, String title) {
        super(currentMS, title);
        this.jobPosting = posting;


        this.documentsList = new JList(applicant.getDocs().toArray());
        documentsList.setVisibleRowCount(5);
        documentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pane = new JScrollPane(documentsList);
        setLayout();
        // Allows for the Applicant to select the Documents by implementing ActionListeners.
        class SelectDocumentsListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (!documentsList.getSelectedValuesList().isEmpty()) {
                    ArrayList<Document> documents = (ArrayList<Document>)documentsList.getSelectedValuesList();
                    //ArrayList<Document> documents = findDocuments((ArrayList<String>) documentsList.getSelectedValuesList());
                    String message = applicant.submitToJobPosting(jobPosting, documents, currentMS.getCurrentDate());
                    Component component = (Component) e.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                    ViewPostingGUI viewPostingGUI = new ViewPostingGUI(frame.getCurrentManagingSystem(),
                            "View Job Postings");
                    viewPostingGUI.openWindow(new ViewPostingGUI(frame.getCurrentManagingSystem(),
                            "View Job Postings").jobpostField);
                    JOptionPane.showMessageDialog(null, message);
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must select documents to apply!");
                }

            }
        }
        applyButton.addActionListener(new SelectDocumentsListener());

        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });
    }


    /**
     * Helper method to set the layout of the Frame
     */
    private void setLayout() {
        ApplyWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ApplyWindow.add(selectLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        ApplyWindow.add(pane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        ApplyWindow.add(applyButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        ApplyWindow.add(backToApplicantMainButton, gbc);
    }
}
