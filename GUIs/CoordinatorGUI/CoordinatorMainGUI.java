package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import GUIs.PickDateGUI;
import GUIs.StartUpGUIs.StartUpGUI;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI that allows a Coordinator to see a all their options in the main menu.
 */
public class CoordinatorMainGUI extends OurFrame {
    public JPanel CoordinatorMainWindow;
    private JButton createAJobPostingButton;
    private JButton seeCompanyJobPostingsButton;
    private JButton logOutButton;
    private JButton changeTodaysDateButton;
    private JButton pickABranchButton;

    public CoordinatorMainGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        // take the coordinator to where they can create a job posting
        createAJobPostingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                CreateJobPostingGUI createJobPostingGUI = new CreateJobPostingGUI(frame.getCurrentManagingSystem(),
                        "Create a Job Posting");
                createJobPostingGUI.openWindow(new CreateJobPostingGUI(frame.getCurrentManagingSystem(),
                        "Create a Job Posting").CreateJobPostingWindow);
                frame.dispose();
            }
        });

        // takes them to where they can see their company postings
        seeCompanyJobPostingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                // CompanyPostingsGUI needs to have the ManagingSystem as an Attribute
                CompanyPostingsGUI companyPostingsGUI = new CompanyPostingsGUI(frame.getCurrentManagingSystem(),
                        "Company Job Postings");
                companyPostingsGUI.openWindow(new CompanyPostingsGUI(frame.getCurrentManagingSystem(),
                        "Company Job Postings").CompanyPostingsWindow);
                frame.dispose();
            }
        });

        // logs the coordinator out of the system
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to log out it will just call StartUpGUIs
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                StartUpGUI startUpGUI = new StartUpGUI(frame.getCurrentManagingSystem(),
                        "Start Up");
                startUpGUI.openWindow(new StartUpGUI(frame.getCurrentManagingSystem(),
                        "Start Up").StartUpScreen);
                frame.dispose();
            }
        });

        // allows them to update today's date
        changeTodaysDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // probably should make this a pop up dialog if possible?
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                CoordinatorMainGUI coordinatorMainGUI = new CoordinatorMainGUI(frame.getCurrentManagingSystem(),
                        "Coordinator Main Menu");
                coordinatorMainGUI.openWindow(new CoordinatorMainGUI(frame.getCurrentManagingSystem(),
                        "Coordinator Main Menu").CoordinatorMainWindow);
                PickDateGUI pickDateGUI = new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?");
                pickDateGUI.openWindow(new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?").PickDateWindow);
                frame.dispose();
            }
        });

        // takes them to where it allows them to choose which branch they are part of
        pickABranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                PickABranchGUI pickABranchGUI = new PickABranchGUI(frame.getCurrentManagingSystem(),
                        "Pick a Branch");
                pickABranchGUI.openWindow(new PickABranchGUI(frame.getCurrentManagingSystem(),
                        "Pick a Branch").PickABranchWindow);
                frame.dispose();
            }
        });
    }
}
