package GUIs;

import Jobs.JobPosting;
import StartUp.Dates;
import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PickDateGUI extends OurFrame {
    public JPanel PickDateWindow;
    private JTextField textField1;
    private JButton iMSureThisButton;
    private JButton iForgotWhatDateButton;

    public PickDateGUI(ManagingSystem managingSystem, String title) {
        super(managingSystem, title);
        jobPostingExpire();
        iForgotWhatDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);

                frame.dispose();
            }
        });
        iMSureThisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                OurFrame frame = (OurFrame) SwingUtilities.getRoot(component);
                String todayDate = textField1.getText();
                try {
                    Date today = (new Dates(todayDate, component)).getCurrentDate();
                    Date defaultDate = frame.getCurrentManagingSystem().getCurrentDate();
                    if (defaultDate.compareTo(today) <= 0) {
                        jobPostingExpire();
                        frame.getCurrentManagingSystem().setCurrentDate(today);

                        frame.dispose();
                        JOptionPane.showMessageDialog(null, "You have entered the date correctly.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remember, the arrow of time only moves forward.");
                    }
                } catch (NullPointerException ne) {
                    System.out.println("Wrong format.");
                }
            }
        });
    }

    private void jobPostingExpire() {
        for (JobPosting job: this.getCurrentManagingSystem().getAllJobPostings()) {
            if (job.getPostingActivation().getDateCloses().before(this.getCurrentManagingSystem().getCurrentDate())) {
                job.getPostingActivation().closePosting();
            }
        }
    }
}
