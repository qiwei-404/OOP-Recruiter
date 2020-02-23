package Users;

import Jobs.Company;
import Jobs.Interviews.Interview;
import Jobs.Interviews.InterviewPerCandidate;
import StartUp.ManagingSystem;

import java.util.ArrayList;

/**
 * Represents an Users.HR Users.User that is specified as being an Users.Interviewer. They are identified by their username and
 * password combination, which is created when they sign up and is used for log in. They are associated with only one
 * Jobs.Company within one managingSystem. They also have a list of all Interviews and a list of all the Applicants that they
 * are interviewing.
 */
public class Interviewer extends HR {

    private ArrayList<Interview> allInterviews;

    /**
     * Creates a new Interviewer in the given ManagingSystem using the given username, password and company. This is an
     * Interviewer that does not already have a list of interviews or interviewees.
     * @param username A string given during sign up.
     * @param password A string given during sign up.
     * @param company A Jobs.Company object that is assigned to the Users.HR user during sign up based on which company
     *                the user is associated with.
     * @param managingSystem A ManagingSystem object which the Users.HR user is created within.
     */
    public Interviewer(String username, String password, Company company,
                       ManagingSystem managingSystem){
        super(username, password, company, managingSystem);
        this.userType = "Users.Interviewer";
        this.allInterviews = new ArrayList<> ();
        this.getCurrentMS().addInterviewers(this);
    }



    /**
     * This method adds an Interview to the Interviewer's list of Interviews
     * @param interview the Interview to be added
     */
    public void addInterview(Interview interview) {
        if (!this.allInterviews.contains(interview)) {
            this.allInterviews.add(interview);
        }
    }

    /**
     * This method returns the Interviewer's list of all Interviews.
     * @return An ArraryList of Interviews for the Interviewer
     */
    public ArrayList<Interview> getAllInterviews() {
        return this.allInterviews;
    }

    /**
     * Allows the Users.Interviewer to make a recommendation after an Jobs.Interview about the Users.ApplicantGUI.
     * @param interviewPerCandidate The InterviewPerCandidate that the interviewer is making the decision about.
     * @param success Whether the applicant was successful.
     */
    public void makeRecommendation(InterviewPerCandidate interviewPerCandidate, boolean success){
        //updates isSuccessful of some Jobs.Interview
        //Updates rejected in some Jobs.JobPosting
        //Update applicants in some Jobs.JobPosting
        interviewPerCandidate.evaluateCandidate(success);
    }


}
