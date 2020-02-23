package StartUp;

import Jobs.Company;
import Jobs.JobPosting;
import Users.Interviewer;
import Users.Referee;
import Users.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A system in order to manage and keep track of all instances of different classes when using the program. It is
 * responsible for keeping track of the full lists of Applicants, Users, Job Postings, Interviewers, and Companies
 * within the program. It also keeps track of who the current user logged into the system is.
 */
public class ManagingSystem {

    private ArrayList<JobPosting> allJobPostings;
    private ArrayList<Referee> allReferees;
    private HashMap<String, User> allUsers;
    private ArrayList<Interviewer> allInterviewers;
    private ArrayList<Company> allCompanies;
    private User currentUser;
    private Date currentDate;

    /**
     * Constructs a new Managing System and sets all the lists to new empty lists.
     */
    public ManagingSystem() {
        this.allJobPostings = new ArrayList<>();
        this.allInterviewers = new ArrayList<>();
        this.allReferees = new ArrayList<>();
        this.allCompanies = new ArrayList<>();
        this.allUsers = new HashMap<>();
        this.currentDate = Calendar.getInstance().getTime();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    ArrayList<Company> getAllCompanies() {
        return this.allCompanies;
    }

    public ArrayList<Interviewer> getAllInterviewers() {
        return this.allInterviewers;
    }

    public ArrayList<JobPosting> getAllJobPostings() {
        return this.allJobPostings;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public ArrayList<Referee> getAllReferees() {
        return allReferees;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Adds a single Job Posting to the list off all Job postings within the ManagingSystem.
     * @param newJob A Jobs.JobPosting object that is the new Job Posting to be added into the managing system.
     */
    public void addJobPosting(JobPosting newJob) {
        if (!this.allJobPostings.contains(newJob)) {
            this.allJobPostings.add(newJob);
        }
    }

    /**
     * Adds a single Users.InterviewerGUI to the list off all Interviewers within the ManagingSystem.
     * @param newInterviewer An Users.InterviewerGUI object that is the new Users.InterviewerGUI to be added into the managing system.
     */
    public void addInterviewers(Interviewer newInterviewer) {
        if (!this.allInterviewers.contains(newInterviewer)) {
            this.allInterviewers.add(newInterviewer);
        }
    }

    /**
     * Adds a single Users.RefereeGUI to the list off all Referees within the ManagingSystem.
     * @param newReferee An Users.RefereeGUI object that is the new Users.RefereeGUI to be added into the managing system.
     */
    public void addReferees(Referee newReferee) {
        if (!this.allReferees.contains(newReferee)) {
            this.allReferees.add(newReferee);
        }
    }

    /**
     * Adds a single Jobs.Company to the list off all Companies within the ManagingSystem.
     * @param newCompany A Jobs.Company object that is the new Jobs.Company to be added into the managing system.
     */
    public void addCompany(Company newCompany) {
        if (!this.allCompanies.contains(newCompany)) {
            this.allCompanies.add(newCompany);
        }
    }

    /**
     * Adds a single Users.User to the HashMap of all Users within the ManagingSystem, where the username is the key and the
     * Users.User is the value.
     * return true if the user is added, and false if the user already exists.
     * @param newUser An Users.User object that is the new Users.User to be added into the managing system.
     *
     */
    boolean addUser(User newUser) {
        // check if this Users.User already exists
        if (!this.allUsers.containsValue(newUser)) {
            // Make sure there is no duplicate username
            if (!this.allUsers.containsKey(newUser.username)) {
                this.allUsers.put(newUser.username, newUser);
                return true;
            }
        }
        return false;
    }

    /**
     * This method updates the current user to be which ever Users.User matches the given username.
     * @param username A String that identifies which Users.User.
     */
    void updateCurrentUser(String username) {
        if (this.allUsers.containsKey(username)) {
            this.setCurrentUser(this.allUsers.get(username));
        }
    }
}
