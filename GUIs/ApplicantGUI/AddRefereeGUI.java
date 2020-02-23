package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import StartUp.ManagingSystem;
import Users.Applicant;
import Users.Referee;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI that allows an Applicant to request a reference document from a specified Refereee. It creates a Jlist for the
 * Applicant to select the Referee from.
 */
public class AddRefereeGUI extends OurFrame {
    public JPanel AddRefereeWindow;
    private JPanel PickRefereePanel;
    private JList list1;
    private JButton backToApplicantMainButton;


    /**
     * Creates a new GUI Frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    public AddRefereeGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        AddRefereeWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.list1 = new JList(generateReferees().toArray());
        JButton button = new JButton("Pick Referee");
        JScrollPane pane = new JScrollPane(list1);

        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });

        // creates an interactive list
        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        list1.setSelectionModel(m);
        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e.toString());
            }
        });

//      put constraints on components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        AddRefereeWindow.add(PickRefereePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        AddRefereeWindow.add(pane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        AddRefereeWindow.add(button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        AddRefereeWindow.add(backToApplicantMainButton);

        // Allows for the Applicant to select the Referee by implementing ActionListeners.
        class SelectRefereeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int selected[] = list1.getSelectedIndices();

                for (int i: selected) {
                    String element = (String) list1.getModel().getElementAt(
                            i);
                    selectedItem = element;
                }
                Applicant currentUser = (Applicant) getCurrentManagingSystem().getCurrentUser();
                try {
                    Component component = (Component) e.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                    // checks to see if something is selected in the list
                    if (selectedItem.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "You must select an referee.");
                    }
                    else {
                        // addes the current Applicant to the Referee's list of applicants
                        getChosenReferee(e).addApplicant(currentUser);
                        // takes user back to main menu
                        JOptionPane.showMessageDialog(null, "Great job! Now you can just sit back and wait for the reference.");
                        BackToMainMenuFactory main = new BackToMainMenuFactory();
                        main.getMainMenu("APPLICANT", e);
                        frame.dispose();
                    }
                }
                catch (NullPointerException ne) {

                }
            }
        }
        button.addActionListener(new SelectRefereeListener());
    }

    /**
     * Returns the Referee chosen by the Applicant
     * @param e The action Event of clicking
     * @return A Referee that was clicked
     */
    private Referee getChosenReferee(ActionEvent e) {
        //returns the referee chosen by the user
        return findReferee(selectedItem);
    }

    /**
     * Generates a list of Referees in the current ManagingSystem
     * @return An ArraryList of the Referees names
     */
    private ArrayList<String> generateReferees() {
        ArrayList<String> listReferees = new ArrayList<>();
        for (Referee referee : this.getCurrentManagingSystem().getAllReferees()){
            if (!listReferees.contains(referee.toString())) {
                listReferees.add(referee.toString());
            }
        }
        return listReferees;
    }

    /**
     * Finds a Referee withing the managingSystem from their name
     * @param refereeName A String of the Referee's name
     * @return A Referee that's name matches the string
     */
    private Referee findReferee(String refereeName) {
        Referee foundReferee = null;
        for (Referee referee : getCurrentManagingSystem().getAllReferees()) {
            if (referee.toString().equals(refereeName)) {
                foundReferee = referee;
            }
        }
        return foundReferee;
    }
}
