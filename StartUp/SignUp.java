package StartUp;

import Jobs.Company;
import Users.*;

import java.util.ArrayList;

/**
 * Facilitates the creation of new users by adding new credentials to the credential text files. Assumes that the
 * credentials entered do not already exist.
 */
public class SignUp {
    private DirectorySystem directorySystem;
    private TextFileSystem textFileSystem;
    private String textFileName;
    private ManagingSystem currentMS;
    private User newUser;
    private boolean signUpSuccessful=false;

    /**
     * Creates a new SignUp system for the current managingSystem.
     * @param currentMS The current ManagingSystem
     */
    public SignUp(ManagingSystem currentMS) {
        this.directorySystem = new DirectorySystem();
        this.textFileSystem = new TextFileSystem();
        this.currentMS = currentMS;
        this.setUpApplicantsDirectoryAndTextFile();
        this.setUpRefereeDirectoryAndTextFile();
    }

    /**
     * Makes a new Applicant 
     * @param username the applicant's username
     * @param password the applicant's password
     * @param phoneNumber the applicant's phone number
     */
    public void createNewApplicant(String username, String password, String phoneNumber) {
        //Save credentials to the all-encompassing applicants folder by default
        this.textFileName = "ApplicantCredentials";
        String newData = username + "/" + password + "/" + phoneNumber;
        //Instantiating this.newUser to add in createNewUser.
        this.newUser = new Applicant(username, password, phoneNumber, this.currentMS);
        this.createNewUser(username, newData, "applicants");
    }

    /**
     * Makes a new Referee 
     * @param username the referee's username
     * @param password the referee's password
     * @param phoneNumber the referee's phone number
     */
    public void createNewReferee(String username, String password, String phoneNumber) {
        //Save credentials to the all-encompassing applicants folder by default
        this.textFileName = "RefereeCredentials";
        String newData = username + "/" + password + "/" + phoneNumber;
        //Instantiating this.newUser to add in createNewUser.
        this.newUser = new Referee(username, password, phoneNumber, this.currentMS);
        this.createNewUser(username, newData, "referees");
    }

    /**
     * Makes a new Coordinator 
     * @param username the coordinator's username
     * @param password the coordinator's password
     * @param companyName the company that the coordinator is part of
     */
    public void createNewCoordinator(String username, String password, String companyName) {
        Company companyToUpdate = this.searchForCompanyWithMS(companyName);
        this.newUser = new Coordinator(username, password, companyToUpdate, this.currentMS);
        this.createNewHR(username, password, "Users.CoordinatorGUI", companyName);
    }

    /**
     * Makes a new Interviewer 
     * @param username the interviewer's username
     * @param password the interviewer's password
     * @param companyName the company that the interviewer is part of
     */
    public void createNewInterviewer(String username, String password, String companyName) {
        Company companyToUpdate = this.searchForCompanyWithMS(companyName);
        this.newUser = new Interviewer(username, password, companyToUpdate, this.currentMS);
        this.createNewHR(username, password, "Users.InterviewerGUI", companyName);
    }

    /**
     * Searches the ManagingSystem for the Company with the given name
     * @param companyName String of the name to be looked for
     * @return the Company that uses that name
     */
    private Company searchForCompanyWithMS(String companyName) {
        ArrayList<Company> allCompanies = this.currentMS.getAllCompanies();
        for (Company company : allCompanies) {
            String companyNameToCheck = company.getCompanyName();
            if (companyNameToCheck.equals(companyName)) {
                return company;
            }
        }
        // Create a new Jobs.Company in the system if companyName doesn't exist yet.
        Company newCompany = new Company(companyName, this.currentMS);
        //Create a new company folder in files directory
        this.directorySystem.createNewDirectory(companyName);
        //Create a new HRCredentials.txt file in new company folder
        this.textFileSystem.createNewTextFile("HRCredentials", companyName);
        return newCompany;
    }

    /**
     * Check if the user has successfully signed up.
     * @return boolean true if they are successful
     */
    public boolean isSuccessful() {
        return this.signUpSuccessful;
    }

    private void createNewHR(String username, String password, String HRType, String companyName) {
        this.textFileName = "HRCredentials";
        String newData = username + "/" + password + "/" + HRType;
        this.createNewUser(username, newData, companyName);
    }

    private void createNewUser(String username, String newData, String companyName) {
        if (!(this.textFileSystem.stringInTextFile(username, 0))) {
            this.textFileSystem.updateFileData(this.textFileName, companyName, newData);
            this.currentMS.addUser(this.newUser);
            this.signUpSuccessful = true;
        }
        else {
            System.out.print("\nThe username '" + username + "' is already taken...");
            System.out.print("\nYour information will only be saved if LOGIN STATUS is successful.");
        }
    }

    /**
     * Create a new 'applicant' folder (iff does not exist) and
     * create a new 'ApplicantCredentials.txt file (iff does not exist)*/
    private void setUpApplicantsDirectoryAndTextFile() {
        this.directorySystem.createNewDirectory("applicants");
        this.textFileSystem.createNewTextFile("ApplicantCredentials", "applicants");
    }

    /**
     * Create a new 'referees' folder (iff does not exist) and
     * create a new 'RefereeCredentials.txt' file (iff does not exist)*/
    private void setUpRefereeDirectoryAndTextFile() {
        this.directorySystem.createNewDirectory("referees");
        this.textFileSystem.createNewTextFile("RefereeCredentials", "referees");
    }
}