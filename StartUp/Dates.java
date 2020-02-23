package StartUp;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Takes a Simple Date Format and a component to then parse a String into a Date
 */
public class Dates {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private Date currentDate;

    public Dates(String d, Component component) {
        try {
            currentDate = sdf.parse(d);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Told ya! You must enter the date in yyyy/mm/dd format.");
        }
    }

    public Date getCurrentDate() {
        return currentDate;
    }
}
