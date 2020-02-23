package GUIs.CoordinatorGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import StartUp.ManagingSystem;
import Users.Coordinator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI that allows a Coordinator to create a new branch if theirs does not yet exist.
 */
public class CreateBranchGUI extends OurFrame {
    private JTextField branchNameTextField;
    private JButton addBranchButton;
    private JButton backToCoordinatorMainButton;
    public JPanel createBranchWindow;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    CreateBranchGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        // the button that creates the new branch
        addBranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String branch = branchNameTextField.getText();
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();

                Coordinator currentUser = (Coordinator) currentMS.getCurrentUser();
                currentUser.addBranch(branch);
                PickABranchGUI pickABranchGUI = new PickABranchGUI(currentMS,
                        "Pick a Branch");
                pickABranchGUI.openWindow(new PickABranchGUI(currentMS,
                        "Pick a Branch").PickABranchWindow);
                frame.dispose();
                JOptionPane.showMessageDialog(null, "You have successfully added this branch "
                        + branch);
            }
        });

        // button to take them back to the coordinator's main menu
        backToCoordinatorMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("COORDINATOR", e);
            }
        });
    }
}
