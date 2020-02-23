package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Helpers.ListToListOfStrings;
import Helpers.SearchListHelper;
import Jobs.JobPosting;
import StartUp.ManagingSystem;
import Users.Coordinator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI that allows a Coordinator to see a list of all Job postings from their company.
 */
public class CompanyPostingsGUI extends OurFrame {
    public JPanel CompanyPostingsWindow;
    private JList list1;


    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    CompanyPostingsGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        CompanyPostingsWindow.setLayout(new BorderLayout());
        // company is not set for users
        ArrayList<JobPosting> allPostings = ((Coordinator) this.getCurrentManagingSystem().getCurrentUser())
                .getCompany().getAllJobPostings();
        ListToListOfStrings listToListOfStrings = new ListToListOfStrings();
        ArrayList<String> allPostingNames = listToListOfStrings.convert(allPostings);
        this.list1 = new JList(allPostingNames.toArray());
        JButton button = new JButton("Select");
        JScrollPane pane = new JScrollPane(list1);

        JButton backButton = new JButton("Back to coordinator main menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("COORDINATOR", e);
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

        CompanyPostingsWindow.add(pane, BorderLayout.NORTH);
        CompanyPostingsWindow.add(button, BorderLayout.SOUTH);
        CompanyPostingsWindow.add(backButton, BorderLayout.WEST);

        class SelectPostingListener implements ActionListener {
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
                    JOptionPane.showMessageDialog(null,
                            "Which job posting do you want to select?");
                }
                else {
                    SearchListHelper<JobPosting> searchListHelper = new SearchListHelper<>();
                    searchListHelper.search(selectedItem, allPostings);
                    JobPosting jobPosting = searchListHelper.getObjectFound();
                    ManagePostingGUI managePostingGUI = new ManagePostingGUI(currentMS,
                            "Manage Job Posting", jobPosting);
                    managePostingGUI.openWindow(new ManagePostingGUI(currentMS,
                            "Manage Job Posting", jobPosting).ManagePostingWindow);
                    frame.dispose();
                }
            }
        }
        button.addActionListener(new SelectPostingListener());
    }
}
