package GUIs.RefereeGUI;

import GUIs.OurFrame;
import GUIs.PickDateGUI;
import GUIs.StartUpGUIs.StartUpGUI;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RefereeMainGUI extends OurFrame{

    public JPanel RefereeMainWindow;
    private JButton checkRequestedForReferencesButton;
    private JButton logoutButton;
    private JButton uploadDocumentButton;
    private JButton changeTodaySDateButton;

    public RefereeMainGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to log out it will just call StartUpGUIs
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                StartUpGUI startUpGUI = new StartUpGUI(frame.getCurrentManagingSystem(), "Start Up");
                startUpGUI.openWindow(new StartUpGUI(frame.getCurrentManagingSystem(), "Start Up").StartUpScreen);
                frame.dispose();
            }
        });
        changeTodaySDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                RefereeMainGUI refereeMainGUI = new RefereeMainGUI(frame.getCurrentManagingSystem(), "Referee Main Menu");
                refereeMainGUI.openWindow(new RefereeMainGUI(frame.getCurrentManagingSystem(), "Referee Main Menu").RefereeMainWindow);
                PickDateGUI pickDateGUI = new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?");
                pickDateGUI.openWindow(new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?").PickDateWindow);
                frame.dispose();
            }
        });
        uploadDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Component component = (Component) e.getSource();
                    OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                    AddRefGUI addRefGUI = new AddRefGUI(frame.getCurrentManagingSystem(), "Add Reference");
                    addRefGUI.openWindow(new AddRefGUI(frame.getCurrentManagingSystem(),
                            "Add Reference").AddRefWindow);
                    frame.dispose();
            }
        });
        checkRequestedForReferencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ViewRequestedReferencesGUI viewRequestedReferencesGUI = new ViewRequestedReferencesGUI(frame.getCurrentManagingSystem(), "Applicants Requesting References");
                viewRequestedReferencesGUI.openWindow(new ViewRequestedReferencesGUI(frame.getCurrentManagingSystem(),
                        "Applicants Requesting References").ViewRequestedReferencesWindow);
                frame.dispose();
            }
        });
    }
}
