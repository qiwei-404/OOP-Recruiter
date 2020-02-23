import GUIs.StartUpGUIs.StartUpGUI;
import StartUp.ManagingSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("Opening the best hiring platform in CSC207");
        ManagingSystem Overlord = new ManagingSystem();
        // Create a new StartUpGUIs. Note the default window is empty when a GUI is created.
        // Pass in current managing system and window title in GUI constructor
        StartUpGUI startUpGUI = new StartUpGUI(Overlord, "Hello world");
        // Call GUIs.OurFrame.openWindow() to make our new window visible
        startUpGUI.openWindow(new StartUpGUI(Overlord, "Hello world").StartUpScreen);
    }
}
