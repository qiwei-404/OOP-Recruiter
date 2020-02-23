package StartUp;

import Jobs.Company;
import Users.*;

import java.util.ArrayList;

/**
 * Allows for users to log into the system by updating the current managing system
 */
public class Login {
    private DirectorySystem directorySystem;
    private TextFileSystem textFileSystem;
    private boolean userIsLoggedIn;
    private boolean userIsHR=false;
    private boolean userIsReferee=false;
    private String userType="";
    private ManagingSystem currentMS;
    private String credentialFileToCheck="";
    private Company companyToUpdate;

    public Login(ManagingSystem currentMS) {
        this.currentMS = currentMS;
        this.defineNonMSVariables();
    }

    private void defineNonMSVariables() {
        this.directorySystem = new DirectorySystem();
        this.textFileSystem = new TextFileSystem();
        this.userIsLoggedIn = false;
    }

    public boolean getUserLoggedIn() {
        return this.userIsLoggedIn;
    }

    public boolean getUserIsHR() { return this.userIsHR; }

    public String getUserType() { return this.userType; }


     public void login(String username, String password) {
        //Login method for both Users.ApplicantGUI and Users.HR (i.e., Users.CoordinatorGUI, Users.InterviewerGUI)
        this.defineNonMSVariables();
        ArrayList<String> allDirectories = this.directorySystem.getDirectoryNames();
        for (String directory : allDirectories) {
            this.setCredentialFilesToCheck(directory);
            this.setCompanyToUpdate(directory);
            if (!this.userIsLoggedIn) {
                this.userIsReferee = (directory.equals("referees"));
                this.userIsHR = ((!(directory.equals("applicants"))) && !this.userIsReferee);
            }
            ArrayList<String[]> users = textFileSystem.getFileData(
                    this.credentialFileToCheck, directory);
            this.checkCredentials(username, password, users);
        }
    }

    private void setCredentialFilesToCheck(String directory) {
        if (directory.equals("applicants")) {
            this.credentialFileToCheck = "ApplicantCredentials";
        }
        else if (directory.equals("referees")) {
            this.credentialFileToCheck = "RefereeCredentials";
        }
        else {
            this.credentialFileToCheck = "HRCredentials";
        }
    }

    /**
     * checks if user credentials match
     * @param username username to check
     * @param password password to check
     * @param users list of users to check from
     */
    private void checkCredentials(String username, String password, ArrayList<String[]> users) {
        for (String[] user: users) {
            if (user[0].equals(username)) {
                if (user[1].equals(password)) {
                    System.out.println("\nLOGIN STATUS: Successful!");
                    this.userIsLoggedIn = true;
                    this.setUser(user);
                }
            }
        }
    }

// Source code does not match byte code
    public void setUser(String[] user) {
        //defining local variables for ease of reading
        String username = user[0];

        this.setUserType(user);
        this.addUserToCurrentMS(user);
        this.currentMS.updateCurrentUser(username);
    }

    private void setUserType(String[] user) {
        if (this.userIsHR) {
            this.userType = user[2];
        }
        else {
            if (this.userIsReferee) {
                this.userType = "Users.RefereeGUI";
            }
            else {
                this.userType = "Users.ApplicantGUI";
            }
        }
    }

    private void addUserToCurrentMS(String[] user) {
        //defining local variables for ease of reading
        String username = user[0];
        String password = user[1];
        User newUser;

        // could use a switch statement?
        if (this.userType.equals("Users.ApplicantGUI")) {
            String phone_number = user[2];
            newUser = new Applicant(username, password, phone_number, this.currentMS);
        }
        else if (this.userType.equals("Users.CoordinatorGUI")) {
            newUser = new Coordinator(username, password, this.companyToUpdate, this.currentMS);
        }
        else if (this.userType.equals("Users.InterviewerGUI")) {
            newUser = new Interviewer(username, password, this.companyToUpdate, this.currentMS);
        }
        else if (this.userType.equals("Users.RefereeGUI")) {
            String phone_number = user[2];
            newUser = new Referee(username, password, phone_number, this.currentMS);
        }
        else {
            System.out.println("No valid user type assigned.");
            newUser = null;
        }

        boolean added = this.currentMS.addUser(newUser);
    }

    private void setCompanyToUpdate(String companyName) {
        ArrayList<Company> allCompanies = this.currentMS.getAllCompanies();
        boolean companyInMS = false;
        for (Company company : allCompanies) {
            String companyNameToCheck = company.getCompanyName();
            if (companyNameToCheck.equals(companyName)) {
                this.companyToUpdate = company;
                companyInMS = true;
            }
        }
        if (!companyInMS) {
            this.companyToUpdate = new Company(companyName, this.currentMS);
        }
    }

    public void logout() {
        this.userIsLoggedIn = false;
    }
}