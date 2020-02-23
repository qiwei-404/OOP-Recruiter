package GUIs;

import StartUp.ManagingSystem;

import javax.swing.*;
import java.awt.*;


// A class for window operations.
public class OurFrame extends JFrame {
    public ManagingSystem managingSystem;
    public String selectedItem="";

    public OurFrame(ManagingSystem managingSystem, String title) {
        // The title is what you see on the top bar of the window
        super(title);
        // assigns ManagingSystem
        this.managingSystem = managingSystem;
    }

    public void setManagingSystem(ManagingSystem managingSystem) {
        this.managingSystem = managingSystem;
    }

    public ManagingSystem getCurrentManagingSystem() {
        // a getter for the current ManagingSystem.
        return this.managingSystem;
    }

    public void openWindow(JPanel panel) {
        // We can call this method to open any window.
        this.setContentPane(panel);
        this.setDefaultCloseOperation(OurFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Jobtopia");
        this.setVisible(true);
//        this.setSize(800, 800);
//        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setSize(width * 4 / 5, height * 4 / 5);
        this.setLocationRelativeTo(null);
    }

    void setSelectedItem(String item) {this.selectedItem = item;}

    String getSelectedItem() {
        return this.selectedItem;
    }

}
