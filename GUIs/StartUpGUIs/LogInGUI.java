package GUIs.StartUpGUIs;

import GUIs.ApplicantGUI.ApplicantMainGUI;
import GUIs.CoordinatorGUI.CoordinatorMainGUI;
import GUIs.InterviewerGUI.InterviewerMainGUI;
import GUIs.OurFrame;
import GUIs.PickDateGUI;
import GUIs.RefereeGUI.RefereeMainGUI;
import Jobs.Submissions.Submission;
import StartUp.Login;
import StartUp.ManagingSystem;
import Users.Applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInGUI extends OurFrame {
    public JPanel LogInWindow;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton donTHaveAnButton;

    LogInGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                ManagingSystem currentMS = frame.getCurrentManagingSystem();
                String password = new String(passwordField1.getPassword());
                Login currentLogin = new Login(currentMS);
                currentLogin.login(textField1.getText(), password);
                if (currentLogin.getUserLoggedIn()) {
                    // Using switch since there are many specific cases.
                    switch (currentLogin.getUserType()) {
                        case "Users.ApplicantGUI":
                            ApplicantMainGUI applicantMainGUI = new ApplicantMainGUI(currentMS, "Applicant Main Menu");
                            applicantMainGUI.openWindow(new ApplicantMainGUI(currentMS, "Applicant Main Menu").ApplicantMainWindow);
                            // Pop up a success message
                            JOptionPane.showMessageDialog(null, "Welcome back " + textField1.getText() +
                                    "! Please keep exploring millions of opportunities we provide.");
                            submissionNotification(e);
                            break;
                        case "Users.CoordinatorGUI":
                            CoordinatorMainGUI coordinatorMainGUI = new CoordinatorMainGUI(currentMS, "Coordinator Main Menu");
                            coordinatorMainGUI.openWindow(new CoordinatorMainGUI(currentMS, "Coordinator Main Menu").CoordinatorMainWindow);
                            // Pop up a success message
                            JOptionPane.showMessageDialog(null, "Welcome back " + textField1.getText() +
                                    "! It seems that you really enjoy hiring and firing. Keep it up!");
                            break;
                        case "Users.InterviewerGUI":
                            InterviewerMainGUI interviewerMainGUI = new InterviewerMainGUI(currentMS, "Interviewer Main Menu");
                            interviewerMainGUI.openWindow(new InterviewerMainGUI(currentMS, "Interviewer Main Menu").InterviewerMainWindow);
                            // Pop up a success message
                            JOptionPane.showMessageDialog(null, "Great news, " + textField1.getText() +
                                    "! You are meeting even more strangers. Are you excited?");
                            break;
                        case "Users.RefereeGUI":
                            RefereeMainGUI refereeMainGUI = new RefereeMainGUI(currentMS, "Referee Main Menu");
                            refereeMainGUI.openWindow(new RefereeMainGUI(currentMS, "Referee Main Menu").RefereeMainWindow);
                            // Pop up a success message
                            JOptionPane.showMessageDialog(null, "Welcome back " + textField1.getText() +
                                    "! Ready to submit more testimonies? We know you are! Woo!");
                            break;
                    }
                    PickDateGUI pickDateGUI = new PickDateGUI(currentMS, "What date is it today?");
                    pickDateGUI.openWindow(new PickDateGUI(currentMS, "What date is it today?").PickDateWindow);
                    frame.dispose();
                }
                else {
                    // If log in is unsuccessful.
                    JOptionPane.showMessageDialog(null, "Your username and password do not match our " +
                            "records. Please try again or sign up if you do not have an account yet.");
                }
            }
        });
        donTHaveAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a reference to the Sign Up button.
                Component component = (Component) e.getSource();
                // Create a reference to the current window.
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                // Create a new frame
                SignUpGUI signUpGUI = new SignUpGUI(frame.getCurrentManagingSystem(), "User Sign Up");
                // Call the openWindow helper in GUIs.OurFrame class.
                signUpGUI.openWindow(new SignUpGUI(frame.getCurrentManagingSystem(), "User Sign Up").SignUpWindow);
                // Close the current window once we're done.
                frame.dispose();
            }
        });
    }

    private void submissionNotification(ActionEvent e) {
        Applicant currentApplicant = (Applicant) this.getCurrentManagingSystem().getCurrentUser();
        ArrayList<Submission> submissions = currentApplicant.getAllSubmissions().getListSub();
        if (!submissions.isEmpty()) {
            String message = submissions.get(submissions.size()-1).getStatus();
            JOptionPane.showMessageDialog(null, message);
        }
    }
}
