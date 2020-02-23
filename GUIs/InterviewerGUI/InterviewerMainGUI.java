package GUIs.InterviewerGUI;

import GUIs.OurFrame;
import GUIs.PickDateGUI;
import GUIs.StartUpGUIs.StartUpGUI;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterviewerMainGUI extends OurFrame {
    public JPanel InterviewerMainWindow;
    private JButton viewInterviewsButton;
    private JButton logOutButton;
    private JButton changeTodaysDateButton;

    public InterviewerMainGUI(ManagingSystem currentMS, String title) {
        super(currentMS, title);

        viewInterviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                // ViewInterviewsGUI needs a ManagingSystem attribute
                ViewInterviewsGUI viewInterviewsGUI = new ViewInterviewsGUI(frame.getCurrentManagingSystem(),
                        "View Interviews");
                viewInterviewsGUI.openWindow(new ViewInterviewsGUI(frame.getCurrentManagingSystem(),
                        "View Interviews").ViewInterviewsWindow);
                frame.dispose();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
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
        changeTodaysDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                InterviewerMainGUI interviewerMainGUI = new InterviewerMainGUI(frame.getCurrentManagingSystem(),
                        "Interviewer Main Menu");
                interviewerMainGUI.openWindow(new InterviewerMainGUI(frame.getCurrentManagingSystem(),
                        "Interviewer Main Menu").InterviewerMainWindow);
                PickDateGUI pickDateGUI = new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?");
                pickDateGUI.openWindow(new PickDateGUI(frame.getCurrentManagingSystem(),
                        "What date is it today?").PickDateWindow);
                frame.dispose();
            }
        });
    }
}
