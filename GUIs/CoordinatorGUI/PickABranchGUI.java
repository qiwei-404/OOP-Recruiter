package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import StartUp.ManagingSystem;
import Users.Coordinator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI that allows a Coordinator to pick a which branch they are part of from their company.
 */
public class PickABranchGUI extends OurFrame {
    private JButton addBranchButton;
    private JButton backToCoordinatorMainButton;
    private JButton donTSeeYourButton;
    public JPanel PickABranchWindow;
    private JLabel titleLabel;
    private JList list1;
    private JScrollPane pane;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    PickABranchGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        Coordinator thisCoordinator = (Coordinator) currentMS.getCurrentUser();
        this.list1 = new JList(thisCoordinator.getCompany().getBranches().toArray());
        list1.setVisibleRowCount(5);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.pane = new JScrollPane(list1);
        setLayOut();

        donTSeeYourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem frameCurrentMS = frame.getCurrentManagingSystem();
                CreateBranchGUI createBranchGUI = new CreateBranchGUI(frameCurrentMS,
                        "Create A Branch For Your Company");
                createBranchGUI.openWindow(new CreateBranchGUI(frameCurrentMS,
                        "Create A Branch For Your Company").createBranchWindow);
                frame.dispose();
            }
        });

        backToCoordinatorMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("COORDINATOR", e);
            }
        });

        addBranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                if (list1.getSelectedValuesList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Don't waste my time if you aren't " +
                            "selecting a branch.");
                }
                else {
                    for (String branch: (ArrayList<String>) list1.getSelectedValuesList()) {
                        thisCoordinator.addBranch(branch);

                    }
                    JOptionPane.showMessageDialog(null, "Yayyy! You've added the branches you " +
                            "are coordinating!");
                }
            }
        });
    }

    private void setLayOut() {
        PickABranchWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        PickABranchWindow.add(titleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        PickABranchWindow.add(pane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        PickABranchWindow.add(addBranchButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        PickABranchWindow.add(donTSeeYourButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        PickABranchWindow.add(backToCoordinatorMainButton, gbc);
    }
}
