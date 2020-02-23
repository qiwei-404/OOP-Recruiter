package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Company;
import Jobs.JobPosting;
import StartUp.Dates;
import StartUp.ManagingSystem;
import Users.Coordinator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * A GUI that allows a Coordinator to create a new job Posting.
 */
public class CreateJobPostingGUI extends OurFrame {
    public JPanel CreateJobPostingWindow;
    private JTextField jobName;
    private JTextField dateCloses;
    private JButton publishPostingButton;
    private JButton backToMainMenuButton;
    private JTextField docReqInput;
    private JTextField interviewTypesInput;
    private JTextField vacancies;
    private JList branchesList;
    private JTextField jobTags;
    private JLabel EnterNameLabel;
    private JLabel EnterCloseDateLabel;
    private JLabel EnterDocsLabel;
    private JLabel PickBranchesLabel;
    private JLabel DateFormatLabel;
    private JLabel TitleLabel;
    private JLabel InterviewTypeLabel;
    private JLabel NumVacanciesLabel;
    private JLabel EnterTagLabel;
    private JLabel EachTagLabel;
    private JLabel DocInstructionLabel;
    private JLabel IntInstructionLabel;
    private Company company;
    private Date closingDate;
    private JScrollPane pane;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    CreateJobPostingGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        this.branchesList = new JList(generateBranches().toArray());
        branchesList.setVisibleRowCount(5);
        branchesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.pane = new JScrollPane(branchesList);
        setLayOut();

        publishPostingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Job is made
                String cDate = dateCloses.getText();
                Component component = (Component) e.getSource();

                //get closing date
                Date defaultDate = ((CreateJobPostingGUI)
                        SwingUtilities.getRoot(component)).getCurrentManagingSystem().getCurrentDate();
                try {
                    Dates inputDate = new Dates(cDate, component);
                    Date nDate = inputDate.getCurrentDate();
                    if (defaultDate.compareTo(nDate) <= 0) {
                        ((CreateJobPostingGUI) SwingUtilities.getRoot(component)).closingDate = nDate;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Remember, the arrow of time only moves forward.");
                    }
                } catch (NullPointerException ne) {
                    System.out.println("The date format is wrong.");
                }

                // find company -> should be the same company as the Coordinator is associated with
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                Coordinator currentUser = (Coordinator) frame.getCurrentManagingSystem().getCurrentUser();
                ((CreateJobPostingGUI) SwingUtilities.getRoot((Component) e.getSource())).company =
                        currentUser.getCompany();

                // Sanity check
                if (jobName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the name of the position");
                } else if (docReqInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter document requirements.");
                } else if (interviewTypesInput.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please set the interview types.");
                }
                else if (((CreateJobPostingGUI) SwingUtilities.getRoot((Component) e.getSource())).closingDate != null){
                    // create new Jobs.JobPosting
                    //for branch in ListOfBranches
                    JobPosting newJob = new JobPosting(jobName.getText(),
                            ((CreateJobPostingGUI) SwingUtilities.getRoot((Component) e.getSource())).
                                    getCurrentManagingSystem().getCurrentDate(),
                            ((CreateJobPostingGUI) SwingUtilities.getRoot((Component) e.getSource())).closingDate,
                            Integer.parseInt(vacancies.getText()),
                            stringToArray(docReqInput.getText()),
                            stringToArray(interviewTypesInput.getText()),
                            ((CreateJobPostingGUI) SwingUtilities.getRoot((Component) e.getSource())).company,
                            ((CreateJobPostingGUI) SwingUtilities.getRoot((Component) e.getSource())).
                                    getCurrentManagingSystem());
                    newJob.updateTagInterpreter(jobTags.getText());

                    if (!branchesList.getSelectedValuesList().isEmpty()) {
                        ArrayList<String> branches = (ArrayList<String>) branchesList.getSelectedValuesList();
                        newJob.setBranch(branches.get(0));
                        if (!jobTags.getText().isEmpty()) {
                            for (int i = 1; i < branches.size(); i++){
                                JobPosting postingAtBranch = newJob.getCopy();
                                postingAtBranch.setBranch(branches.get(i));
                                postingAtBranch.updateTagInterpreter(jobTags.getText());
                            }
                        } else {
                            for (int i = 1; i < branches.size(); i++){
                                JobPosting postingAtBranch = newJob.getCopy();
                                postingAtBranch.setBranch(branches.get(i));

                            }
                        }

                    }


                    JOptionPane.showMessageDialog(null,"Job Posting has been created");
                    BackToMainMenuFactory main = new BackToMainMenuFactory();
                    main.getMainMenu("COORDINATOR", e);
                }
            }
        });

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("COORDINATOR", e);
            }
        });

        branchesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
    }

    /**
     * Given a , separated string listing all the requirements, returns ArrayList</String>
     * @param textInput
     * @return
     */
    private ArrayList<String> stringToArray(String textInput) {
        return new ArrayList<String>(Arrays.asList(textInput.split(",")));
    }

    /**
    generates a list of branches that the coordinator is associated with to be passed into the JList
     */
    private ArrayList<String> generateBranches() {
        ArrayList<String> branches = new ArrayList<String>();
        Coordinator currentUser = (Coordinator) this.getCurrentManagingSystem().getCurrentUser();
        branches.addAll(currentUser.getBranches());
        return branches;
    }

    private void setLayOut() {
        CreateJobPostingWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(backToMainMenuButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(TitleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(EnterNameLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(jobName, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(EnterCloseDateLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(DateFormatLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        CreateJobPostingWindow.add(dateCloses, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(EnterDocsLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(DocInstructionLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        CreateJobPostingWindow.add(docReqInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(PickBranchesLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(pane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(InterviewTypeLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(IntInstructionLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        CreateJobPostingWindow.add(interviewTypesInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(NumVacanciesLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(vacancies, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(EnterTagLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        CreateJobPostingWindow.add(jobTags, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 22;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(EachTagLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 24;
        gbc.gridwidth = 2;
        CreateJobPostingWindow.add(publishPostingButton, gbc);

    }
}
