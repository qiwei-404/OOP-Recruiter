package GUIs.InterviewerGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Helpers.ListToListOfStrings;
import Helpers.SearchListHelper;
import Jobs.Interviews.Interview;
import Jobs.Interviews.InterviewPerCandidate;
import StartUp.ManagingSystem;
import Users.Interviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MakeRecommendationsGUI extends OurFrame {
    public JPanel MakeRecommendationsWindow;
    private JButton backToMainMenuButton;
    private JButton iHadSuchAButton;
    private JButton thisCandidateIsAButton;
    private JLabel titleLabel;
    private boolean evaluated=false;
    private boolean toRecommend;
    private Interview interview;
    private JList list1;
    private JScrollPane pane;

    MakeRecommendationsGUI(ManagingSystem currentMS, String title, Interview interview) {
        super(currentMS, title);
        this.interview = interview;

        this.list1 = new JList(getInterviewPerCandidates().toArray());
        list1.setVisibleRowCount(5);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pane = new JScrollPane(list1);
        setLayOut();

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("INTERVIEWER", e);
            }
        });


        iHadSuchAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MakeRecommendationsGUI frame = (MakeRecommendationsGUI) SwingUtilities.getRoot((Component) e.getSource());
                if (list1.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must select an applicant!");
                }
                else {
                    if (!frame.evaluated) {
                        recommend(true);
                        updateRecommendations(e);
                        JFrame frame1 = new JFrame();
                        JOptionPane.showMessageDialog(null, "This applicant has been " +
                                "forwarded to next round of interviews");
                        frame.evaluated = true;
                    }
                    else {
                        JFrame frame1 = new JFrame();
                        JOptionPane.showMessageDialog(null, "You have already evaluated this candidate.");
                    }
                }

            }
        });
        thisCandidateIsAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeRecommendationsGUI frame = (MakeRecommendationsGUI) SwingUtilities.getRoot((Component) e.getSource());
                if (list1.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must select an applicant!");
                }
                else {
                    if (!frame.evaluated) {
                        recommend(false);
                        updateRecommendations(e);
                        JFrame frame1 = new JFrame();
                        JOptionPane.showMessageDialog(null, "This applicant has been rejected");
                        frame.evaluated = true;
                    }
                    else {
                        JFrame frame1 = new JFrame();
                        JOptionPane.showMessageDialog(null, "You cannot back track after you made your call.");
                    }
                }

            }
        });
    }

    private void recommend(boolean rec) {
        this.toRecommend = rec;
    }

    private boolean isRecommended() {
        return this.toRecommend;
    }



    private void updateRecommendations(ActionEvent e) {
        Component source = (Component) e.getSource();
        OurFrame frame = (OurFrame) SwingUtilities.getRoot(source);
        Interviewer currentUser = (Interviewer) frame.getCurrentManagingSystem().getCurrentUser();
        InterviewPerCandidate interviewPerCandidate = searchInterviewPerCandidate();
        currentUser.makeRecommendation(interviewPerCandidate, this.isRecommended());
    }

    private void setLayOut() {
        MakeRecommendationsWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //        put constraints on components
        // start with PickInterviewerPanel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        MakeRecommendationsWindow.add(titleLabel, gbc);

        // position of scroll panel pane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        MakeRecommendationsWindow.add(pane, gbc);

        // and so on
        gbc.gridx = 0;
        gbc.gridy = 2;
        MakeRecommendationsWindow.add(iHadSuchAButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        MakeRecommendationsWindow.add(thisCandidateIsAButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        MakeRecommendationsWindow.add(backToMainMenuButton, gbc);

    }

    private ArrayList<String> getInterviewPerCandidates() {
        ListToListOfStrings listToListOfStrings = new ListToListOfStrings();
        return listToListOfStrings.convert(this.interview.getInterviewsPerCandidate());
    }


    private InterviewPerCandidate searchInterviewPerCandidate() {
        SearchListHelper<InterviewPerCandidate> searchListHelper = new SearchListHelper();
        searchListHelper.search((String) list1.getSelectedValue(), interview.getInterviewsPerCandidate());
        return searchListHelper.getObjectFound();
    }

}
