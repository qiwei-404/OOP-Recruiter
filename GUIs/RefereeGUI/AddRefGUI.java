package GUIs.RefereeGUI;

import GUIs.OurFrame;
import Helpers.BackToMainMenuFactory;
import Jobs.Document;
import StartUp.ManagingSystem;
import Users.Referee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRefGUI extends OurFrame {
    public JPanel AddRefWindow;
    private JTextField nameGoesHereTextField;
    private JTextField contentGoesHereTextField;
    private JButton addDocumentButton;
    private JButton backToRefereeMainButton;

    AddRefGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);
        addDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();

                Document letterDocument = new Document(nameGoesHereTextField.getText(),
                        contentGoesHereTextField.getText(), (Referee) currentMS.getCurrentUser());
                letterDocument.setType("Reference Letter");
                ((Referee) currentMS.getCurrentUser()).getReferences().add(letterDocument);
                ManagingSystem frameCurrentMS = frame.getCurrentManagingSystem();
                RefereeMainGUI refereeMainGUI = new RefereeMainGUI(frameCurrentMS, "Referee Main Menu");
                refereeMainGUI.openWindow(new RefereeMainGUI(frameCurrentMS,
                        "Referee Main Menu").RefereeMainWindow);
                frame.dispose();
                JOptionPane.showMessageDialog(null, "You have added a reference! Now you can help people get their dream jobs!");
            }
        });
        backToRefereeMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackToMainMenuFactory main = new BackToMainMenuFactory();
                main.getMainMenu("REFEREE", e);
            }
        });
    }
}
