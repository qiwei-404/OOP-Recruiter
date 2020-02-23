package GUIs.InterviewerGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Interviews.Interview;
import StartUp.ManagingSystem;
import Users.Interviewer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewInterviewsGUI extends OurFrame {
    public JPanel ViewInterviewsWindow;
    private JButton backToInterviewerMainButton;
    private JList list1;
    private JLabel pickInterviewLabel;


    ViewInterviewsGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        ViewInterviewsWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.list1 = new JList(generateInterviews().toArray());
        JButton evalButton = new JButton("Evaluate This Applicant");

        JScrollPane pane = new JScrollPane(list1);

        backToInterviewerMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("INTERVIEWER", e);
            }
        });

        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        list1.setSelectionModel(m);
        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e.toString());
            }
        });


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ViewInterviewsWindow.add(pickInterviewLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        ViewInterviewsWindow.add(pane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        ViewInterviewsWindow.add(evalButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        ViewInterviewsWindow.add(backToInterviewerMainButton, gbc);


        class SelectInterviewListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int selected[] = list1.getSelectedIndices();

                for (int i: selected) {
                    String element = (String) list1.getModel().getElementAt(
                            i);
                    selectedItem = element;
                }
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                if (selectedItem.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must select an interview.");
                }
                else {
                    MakeRecommendationsGUI makeRecommendationsGUI = new MakeRecommendationsGUI(getCurrentManagingSystem(),
                            "Make Recommendations", getChosenInterview(e));
                    makeRecommendationsGUI.openWindow(new MakeRecommendationsGUI(getCurrentManagingSystem(),
                            "Make Recommendations", getChosenInterview(e)).MakeRecommendationsWindow);
                    frame.dispose();
                }
            }
        }
        evalButton.addActionListener(new SelectInterviewListener());

    }
    private Interview getChosenInterview(ActionEvent e) {
        //returns the interview chosen by the user
        Component component = (Component) e.getSource();
        OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
        return findInterview(selectedItem);
    }

    /**
     * generates an ArrayList of all interviews (stored in their string representations) to pass into JList
     * @return the arraylist of interviews
     */
    private ArrayList<String> generateInterviews() {
        Interviewer currentUser = (Interviewer) getCurrentManagingSystem().getCurrentUser();
        ArrayList<String> listInterviews = new ArrayList<>();
        for (Interview interview : currentUser.getAllInterviews()){
            if (!listInterviews.contains(interview.toString())) {
                listInterviews.add(interview.toString());
            }
        }
        return listInterviews;
    }

    private Interview findInterview (String interviewName) {
        //given the string representation of an interview, returns the interview object
        Interview foundInterview = null;
        Interviewer currentUser = (Interviewer) getCurrentManagingSystem().getCurrentUser();
        for (Interview interview : currentUser.getAllInterviews()) {
            if (interview.toString().equals(interviewName)) {
                foundInterview = interview;
            }
        }
        return foundInterview;
    }
}
