package Users;

import Jobs.CheckDocumentRequirements;
import Jobs.Document;
import Jobs.Interviews.Interview;
import Jobs.JobPosting;
import Jobs.SubmissionList;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;

import java.util.ArrayList;
import java.util.Date;

/**
 * Users.Applicant is a Users.User who applies for the jobs that Human resources user posts.
 * Users.Applicant is initialized with ID password.
 * Run only with one managing system (current Managing System "Overlord")
 * Applicants keep track of all their interviews and Submissions.
 */
public class Applicant extends User {


    private ArrayList<Document> docs = new ArrayList<>();
    private String phoneNumber;
    private SubmissionList allSubmissions = new SubmissionList();
    private ArrayList<Interview> allInterviews = new ArrayList<Interview>();
    private Date DateCreated;


    /**
     * Creates a new Applicant and adds them as part of the ManagingSystem.
     * @param username A String given during Sign Up
     * @param password A String given during Sign Up
     * @param phoneNumber A String given during Sign Up
     * @param currentMS A ManagingSystem object which the Users.HR user is created within.
     */
    public Applicant(String username, String password, String phoneNumber, ManagingSystem currentMS) {
        // Initialize needed information
        super(username, password, currentMS);
        this.userType = "Users.Applicant";
        // Add phone number too
        this.setPhoneNumber(phoneNumber);
        this.setDateCreated(currentMS.getCurrentDate());
    }

    public SubmissionList getAllSubmissions() {
        return this.allSubmissions;
    }
    public Date getDateCreated() {
        return this.DateCreated;
    }
    private void setDateCreated(Date DateCreated) {
        this.DateCreated = DateCreated;
    }
    public ArrayList<Interview> getAllInterviews() {
        return allInterviews;
    }
    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public ArrayList<Document> getDocs() { return docs;}
    void addDoc(Document document) {
        this.docs.add(document);
    }



    /**
     * Allows the applicant to apply to Jobs, by adding a Jobs.Submission to a Job Posting
     *
     * @param posting     The Job Posting that the submission is to be added to
     * @param requiredDocuments Documents to submit
     * @param currentDate The date of the application
     * returns a message regarding submission
     */
    public String submitToJobPosting(JobPosting posting, ArrayList<Document> requiredDocuments, Date currentDate) {
        if (posting.getPostingActivation().isActive()) {
            CheckDocumentRequirements checkDocumentRequirements = new CheckDocumentRequirements(posting.getDocumentRequirements());
            boolean documentTypeCorrect = checkDocumentRequirements.checkDocumentRequirements(requiredDocuments);
            if (documentTypeCorrect) {
                Submission newSubmission = new Submission(this, requiredDocuments, posting, currentDate);
                posting.addSubmission(newSubmission);
                this.getAllSubmissions().getListSub().add(newSubmission);
                this.linkSubmissionToDocuments(newSubmission, requiredDocuments);
                return "Yay! You have successfully made a submission.";
            }
            else {
                return checkDocumentRequirements.getCheckedStatus();
            }
        }
        return "We are not accepting applications for this posting any more. Please do not waste our time.";
    }

    /**
     *adds the given documents to the submission provided
     *@param documents the documents that are added to the submission
     * @param submission the submission that the documents are added to
     */
    private void linkSubmissionToDocuments(Submission submission, ArrayList<Document> documents) {
            for (Document document: documents) {
                document.submittedTo(submission);
            }
        }
}
