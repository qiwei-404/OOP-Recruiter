package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Helpers.SearchListHelper;
import Jobs.Submissions.Submission;
import StartUp.Dates;
import StartUp.ManagingSystem;
import Users.Coordinator;
import Users.Interviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Sets Interviews for a all the Submissions for a given Job Posting
 */
public class SetInterviewGUI extends OurFrame {
    public JPanel SetInterviewWindow;
    private JButton backToCoordinatorMainButton;
    private JTextField textField1;
    private JList list1;
    private JLabel pickDateLabel;
    private JTextField textField2;
    private JLabel PickInterviewersLabel;
    private JLabel InterviewTypeLabel;
    private JButton setUpInterviewButton;
    private JScrollPane pane;
    private Date interviewDate=null;

    SetInterviewGUI(ManagingSystem currentMS, String title, ArrayList<Submission> submissions) {
        super(currentMS, title);

        this.list1 = new JList(generateInterviewers().toArray());
        list1.setVisibleRowCount(5);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setUpInterviewButton = new JButton("Set Up Interview");
        this.pane = new JScrollPane(list1);

        backToCoordinatorMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("COORDINATOR", e);
            }
        });

        class SelectInterviewerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                Coordinator currentUser = (Coordinator) getCurrentManagingSystem().getCurrentUser();
                try {
                    Component component = (Component) e.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);

                    if (list1.getSelectedValuesList().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "You must select an interviewer.");
                    }
                    else {
                        getInterviewDate(e);
                        ArrayList<Interviewer> interviewers = findInterviewers((ArrayList<String>) list1.getSelectedValuesList());
                        if (interviewDate != null) {
                            String message = currentUser.matchInterviews(interviewers, submissions, interviewDate, textField2.getText());
                            JOptionPane.showMessageDialog(null, message);
                            CoordinatorMainGUI coordinatorMainGUI = new CoordinatorMainGUI(currentMS, "Coordinator Main Menu");
                            coordinatorMainGUI.openWindow(new CoordinatorMainGUI(currentMS, "Coordinator Main Menu").CoordinatorMainWindow);
                            frame.dispose();
                        }

                    }
                }
                catch (NullPointerException ne) {
                }
            }
        }
        setUpInterviewButton.addActionListener(new SelectInterviewerListener());
        setLayOut();
    }

    private void setLayOut() {
        SetInterviewWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //        put constraints on components
        // start with PickInterviewerPanel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        SetInterviewWindow.add(PickInterviewersLabel, gbc);

        // position of scroll panel pane
        gbc.gridx = 1;
        gbc.gridy = 0;
        SetInterviewWindow.add(pane, gbc);

        // and so on
        gbc.gridx = 0;
        gbc.gridy = 1;
        SetInterviewWindow.add(pickDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        SetInterviewWindow.add(textField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        SetInterviewWindow.add(setUpInterviewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        SetInterviewWindow.add(InterviewTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        SetInterviewWindow.add(textField2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        SetInterviewWindow.add(backToCoordinatorMainButton);
    }

    private void getInterviewDate(ActionEvent e) {
        Component component = (Component) e.getSource();
        try {
            Date date = new Dates(textField1.getText(), component).getCurrentDate();
            if (date.after(getCurrentManagingSystem().getCurrentDate())) {
                this.interviewDate = date;
            }
            else {
                JOptionPane.showMessageDialog(null, "Remember, the arrow of time only moves forward.");
            }
        }
        catch (NullPointerException ne) {
            System.out.println("Wrong format.");
        }
    }

    private ArrayList<String> generateInterviewers() {
        ArrayList<String> listInterviewers = new ArrayList<String>();
        for (Interviewer interviewer : this.getCurrentManagingSystem().getAllInterviewers()){
            if (interviewer.getCompany() == ((Coordinator) getCurrentManagingSystem().getCurrentUser()).getCompany()) {
                listInterviewers.add(interviewer.toString());
            }
        }
        return listInterviewers;
    }

    /**
     * Helper to convert list of selected items to list of Documents.
     * Precondition: The selected items are in the ManagingSystem.allInterviewers
     * @param interviewerNames the name of the interviewer
     * @return An arrayList of Interviewers
     */
    private ArrayList<Interviewer> findInterviewers(ArrayList<String> interviewerNames) {
        ArrayList<Interviewer> interviewers = new ArrayList<>();
        for (String interviewerName: interviewerNames) {
            SearchListHelper<Interviewer> searchListHelper = new SearchListHelper<>();
            searchListHelper.search(interviewerName, getCurrentManagingSystem().getAllInterviewers());
            interviewers.add(searchListHelper.getObjectFound());
        }
        return interviewers;
    }
}



