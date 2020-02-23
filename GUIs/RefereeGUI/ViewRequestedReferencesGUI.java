package GUIs.RefereeGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Helpers.ListToListOfStrings;
import Helpers.SearchListHelper;
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

public class ViewRequestedReferencesGUI extends OurFrame {
    private JList list1;
    public JPanel ViewRequestedReferencesWindow;
    private JButton backToRefereeMainButton;
    private JLabel listAllApplicants;

    private JButton viewRequestButton;
    private JLabel titleLabel;
    private JScrollPane pane;


    ViewRequestedReferencesGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        this.list1 = new JList(generateApplicants().toArray());
        list1.setVisibleRowCount(5);
        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        list1.setSelectionModel(m);
        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e.toString());
            }
        });
        viewRequestButton = new JButton("View this Applicant");
        this.pane = new JScrollPane(list1);
        pane.setVisible(true);

        backToRefereeMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("REFEREE", e);
            }
        });

        class SelectRequestListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int selected[] = list1.getSelectedIndices();

                for (int i = 0; i < selected.length; i++) {
                    String element = (String) list1.getModel().getElementAt(
                            selected[i]);
                    selectedItem = element;
                }

                try {
                    SearchListHelper<Applicant> searchListHelper = new SearchListHelper<>();
                    Referee referee = (Referee) currentMS.getCurrentUser();
                    Component component = (Component) e.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                    if (!list1.isSelectionEmpty()) {
                        searchListHelper.search((String) list1.getSelectedValue(), referee.getApplicants());
                        Applicant applicant = searchListHelper.getObjectFound();
                        ApplicantRequestGUI applicantRequestGUI = new ApplicantRequestGUI(currentMS, applicant, "Refer this Applicant");
                        applicantRequestGUI.openWindow(new ApplicantRequestGUI(currentMS, applicant, "Refer this Applicant").ApplicantRequestWindow);
                        frame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Make a selection! Quick!");
                    }
                }
                catch (NullPointerException ne) {

                }
            }
        }
        viewRequestButton.addActionListener(new SelectRequestListener());
        setLayOut();


    }

    private void setLayOut() {
        ViewRequestedReferencesWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ViewRequestedReferencesWindow.add(titleLabel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        ViewRequestedReferencesWindow.add(listAllApplicants, gbc);

        // and so on
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        ViewRequestedReferencesWindow.add(pane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        ViewRequestedReferencesWindow.add(viewRequestButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        ViewRequestedReferencesWindow.add(backToRefereeMainButton, gbc);


    }

    private ArrayList<String> generateApplicants() {
        Referee referee = (Referee) getCurrentManagingSystem().getCurrentUser();
        ArrayList<Applicant> applicants = referee.getApplicants();
        ListToListOfStrings<Applicant> listToListOfStrings = new ListToListOfStrings<>();
        return listToListOfStrings.convert(applicants);
    }
}