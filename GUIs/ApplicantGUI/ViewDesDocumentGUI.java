package GUIs.ApplicantGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Document;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI that allows an Applicant to the details of their Documents.
 */
public class ViewDesDocumentGUI extends OurFrame {
    public JPanel viewDocsDescField;
    private JButton backToApplicationMainButton;
    private JLabel nameInfo;
    private JLabel contentInfo;
    private JLabel submittedInfo;
    private JButton backToViewDocumentsButton;
    private JLabel typeLabel;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     * @param docs The Document to be viewed
     */
    ViewDesDocumentGUI(ManagingSystem currentMS, String title, Document docs) {
        super(currentMS, title);
        this.setLabel(docs);
        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicationMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });

        //  The action listener that lists for when the button to go back to view the list of Documents is clicked.
        backToViewDocumentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ViewDocGUI viewDocGUI = new ViewDocGUI(frame.getCurrentManagingSystem(), "View Document");
                viewDocGUI.openWindow(new ViewDocGUI(frame.getCurrentManagingSystem(),
                        "View Document").viewDocField);
                frame.dispose();
            }
        });
    }

    /**
     * Sets the JLabels to show the Document's details
     */
    private void setLabel(Document docs){
        this.nameInfo.setText(docs.getNumbering());
        this.contentInfo.setText(docs.getContent());
        this.submittedInfo.setText(docs.getAllSubmissions().toString());
        this.typeLabel.setText(docs.getType());
    }
}
