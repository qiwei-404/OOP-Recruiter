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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A GUI that allows an Applicant to the list of all their Documents.
 */
public class ViewDocGUI extends OurFrame {
    private JButton backToApplicantMainButton;
    public JPanel viewDocField;
    private JList listDocs;
    private JLabel titleLabel;
    private JScrollPane pane;

    /**
     * Creates a new GUI frame
     * @param currentMS the current ManagingSystem that the Applicant is part of
     * @param title A String for the title of this window
     */
    ViewDocGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        Applicant currentUser = (Applicant) currentMS.getCurrentUser();

        this.listDocs = new JList(stringListDocs(currentUser.getDocs()).toArray());
        this.pane = new JScrollPane(this.listDocs);
        this.setLayOut();
        // Click only works when the field is not empty
        if (!currentUser.getDocs().isEmpty()) {
            this.doubleClickListener(this.listDocs);
        }

        //  The action listener that lists for when the button to go back to the main menu is clicked.
        backToApplicantMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("APPLICANT", e);
            }
        });
    }

    /**
     * Returns a String representation of the list of their documents
     * @param docList An ArrayList of Documents
     * @return A String of the Users.ApplicantGUI's documents
     */
    private ArrayList<String> stringListDocs(ArrayList<Document> docList) {
        ArrayList<String> stringDocList = new ArrayList<>();
        for (int i = 0; i < docList.size(); i++) {
            if (!docList.isEmpty()) {
                Document currentDocument = docList.get(i);
                stringDocList.add(currentDocument.toString());
            }
        }
        return stringDocList;
    }

    /**
     * Apply double click button listener to the given list
     * @param docList An ArrayList of Documents
     */
    private void doubleClickListener(JList docList) {
        docList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent click) {
                JList docList = (JList) click.getSource();
                if (click.getClickCount() >= 2) {
                    // When clicked more than doulbe,direct to description Document GUI
                    int index = docList.locationToIndex(click.getPoint());
                    Component component = (Component) click.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                    ManagingSystem frameCurrentMS = frame.getCurrentManagingSystem();
                    Applicant user = (Applicant) frameCurrentMS.getCurrentUser();
                    Document selectedDoc = user.getDocs().get(index);
                    ViewDesDocumentGUI viewDesDocumentGUI = new ViewDesDocumentGUI(frameCurrentMS,
                            "View Document Description", selectedDoc);
                    viewDesDocumentGUI.openWindow(new ViewDesDocumentGUI(
                            frameCurrentMS, "View Document Description", selectedDoc).viewDocsDescField);
                    frame.dispose();
                }
            }
        });
    }

    /**
     * Sets the layout to show the Document's details
     */
        private void setLayOut() {
            viewDocField.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //        put constraints on components
            // start with PickInterviewerPanel
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            viewDocField.add(titleLabel, gbc);

            // position of scroll panel pane
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            viewDocField.add(pane, gbc);

            // and so on
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            viewDocField.add(backToApplicantMainButton, gbc);
        }
    }
