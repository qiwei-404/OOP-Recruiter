package Helpers;

import GUIs.ApplicantGUI.ApplicantMainGUI;
import GUIs.CoordinatorGUI.CoordinatorMainGUI;
import GUIs.InterviewerGUI.InterviewerMainGUI;
import GUIs.OurFrame;
import GUIs.RefereeGUI.RefereeMainGUI;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * A Factory class to direct users back to their main menus GUIs
 */
public class BackToMainMenuFactory {

    /**
     * This calls the correct helper methods depending on what the current user is.
     * @param menuType a String representation of the type of user
     * @param e an ActionEvent of clicking on the button
     */
    public void getMainMenu(String menuType, ActionEvent e) {
        if (menuType.equalsIgnoreCase("COORDINATOR")) {
            coordinatorMain(e);
        } else if (menuType.equalsIgnoreCase("APPLICANT")) {
            applicantMain(e);
        } else if (menuType.equalsIgnoreCase("INTERVIEWER")) {
            interviewerMain(e);
        } else if (menuType.equalsIgnoreCase("REFEREE")) {
            refereeMain(e);
        }
    }

    /**
     * Returns the Coordinator back to their main menu
     * @param e an ActionEvent of clicking on the button
     */
    private void coordinatorMain(ActionEvent e) {
        Component component = (Component) e.getSource();
        OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
        ManagingSystem frameCurrentMS = frame.getCurrentManagingSystem();
        CoordinatorMainGUI coordinatorMainGUI = new CoordinatorMainGUI(frameCurrentMS, "Coordinator Main Menu");
        coordinatorMainGUI.openWindow(new CoordinatorMainGUI(frameCurrentMS, "Coordinator Main Menu").CoordinatorMainWindow);
        frame.dispose();
    }

    /**
     * Returns the Applicant back to their main menu
     * @param e an ActionEvent of clicking on the button
     */
    private void applicantMain(ActionEvent e) {
        Component previous = (Component) e.getSource();
        OurFrame newCurrent = (OurFrame) SwingUtilities.getRoot(previous);
        ManagingSystem currentMS = newCurrent.getCurrentManagingSystem();
        ApplicantMainGUI applicantMainGUI = new ApplicantMainGUI(currentMS, "Applicant Main Menu");
        applicantMainGUI.openWindow(new ApplicantMainGUI(currentMS, "Applicant Main Menu").ApplicantMainWindow);
        newCurrent.dispose();
    }

    /**
     * Returns the Interviewer back to their main menu
     * @param e an ActionEvent of clicking on the button
     */
    private void interviewerMain(ActionEvent e) {
        Component previous = (Component) e.getSource();
        OurFrame newCurrent = (OurFrame) SwingUtilities.getRoot(previous);
        ManagingSystem currentMS = newCurrent.getCurrentManagingSystem();
        InterviewerMainGUI interviewerMainGUI = new InterviewerMainGUI(currentMS, "Applicant Main Menu");
        interviewerMainGUI.openWindow(new InterviewerMainGUI(currentMS, "Applicant Main Menu").InterviewerMainWindow);
        newCurrent.dispose();
    }

    /**
     * Returns the Referee back to their main menu
     * @param e an ActionEvent of clicking on the button
     */
    private void refereeMain(ActionEvent e) {
        Component previous = (Component) e.getSource();
        OurFrame newCurrent = (OurFrame) SwingUtilities.getRoot(previous);
        ManagingSystem currentMS = newCurrent.getCurrentManagingSystem();
        RefereeMainGUI refereeMainGUI = new RefereeMainGUI(currentMS, "Applicant Main Menu");
        refereeMainGUI.openWindow(new RefereeMainGUI(currentMS, "Applicant Main Menu").RefereeMainWindow);
        newCurrent.dispose();
    }
}
