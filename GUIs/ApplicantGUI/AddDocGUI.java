package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Document;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI that allows an Applicant to add their resume or cover letter. It uses text fields to get the name and content
 * for the new document and then creates it after the add button is clicked.
 */
public class AddDocGUI extends OurFrame {
    private JButton backToApplicantMainButton;
    private JButton addButton;
    private JTextField contentComesHereTextField;
    private JTextField nameComesHereTextField;
    public JPanel AddDocField;
    private JTextField typeTextBox;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    AddDocGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        // The action listener that lists for when the button to add a document is clicked.
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                if (!typeTextBox.getText().isEmpty() && !contentComesHereTextField.getText().isEmpty()
                        && !nameComesHereTextField.getText().isEmpty()) {
                        Document document = new Document(nameComesHereTextField.getText(),
                                contentComesHereTextField.getText(), (Applicant) currentMS.getCurrentUser());
                        document.setType(typeTextBox.getText());
                        ((Applicant) currentMS.getCurrentUser()).getDocs().add(document);
                        ManagingSystem frameCurrentMS = frame.getCurrentManagingSystem();
                        ApplicantMainGUI applicantMainGUI = new ApplicantMainGUI(frameCurrentMS,
                                "Users.ApplicantGUI main");
                        applicantMainGUI.openWindow(new ApplicantMainGUI(
                                frameCurrentMS, "Users.ApplicantGUI main").ApplicantMainWindow);
                        frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please fill in all the info section");
                }
            }
        });

        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });
    }
}
