package Jobs.Submissions;

import Jobs.Document;
import Jobs.Interviews.Interview;
import Jobs.Interviews.InterviewRound;
import Jobs.JobPosting;
import Users.Applicant;

import java.util.ArrayList;
import java.util.Date;

public class Submission {
    private String name;
    //a concatenation, i.e., (Users.ApplicantGUI.name + Jobs.Company.name + Jobs.JobPosting.name)
    private Applicant applicant;
    // Formatted in order of CV (index 0), cover letter (index 1), references index (2) and then additional documents
    private ArrayList<Document> submittedDocuments;
    private JobPosting jobPosting;
    private SubmissionStatus status = new SubmissionStatus();
    private Date submissionDate;

    /**
     * Submission is only instantiated when requiredDocuments are correct!
     * @param applicant
     * @param documentsToSubmit
     * @param jobPosting
     * @param submissionDate
     */
    public Submission(Applicant applicant, ArrayList<Document> documentsToSubmit, JobPosting jobPosting, Date submissionDate) {
        this.name = applicant.getUsername() + "'s submission to " + jobPosting.getName() +
                " of the company " + jobPosting.getCompany().getCompanyName();
        this.applicant = applicant;
        this.jobPosting = jobPosting;
        this.submissionDate = submissionDate;
        this.submittedDocuments = documentsToSubmit;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Applicant getApplicant() {
        return this.applicant;
    }
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    public ArrayList<Document> getSubmittedDocuments() {
        return this.submittedDocuments;
    }
    public JobPosting getJobPosting() {
        return this.jobPosting;
    }
    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public String getStatus() {
        return this.status.getStatus();
    }

    /**
     * This method takes a string called statusMarker, and depending on the value of statusMarker, it calls the
     * appropriate method in SubmissionStatus.
     * Do remember to use the correct statusMarker listed here!
     * statusMarkers are fixed except if you want to call proceedToNextRound. In that case, use the interviewType as
     * the statusMarker.
     */
    public void updateStatus(String statusMarker) {
        switch (statusMarker) {
            case "withdraw":
                this.status.withdraw();
                break;
            case "recommend":
                this.status.clearCurrentRound();
                break;
            case "failInterview":
                this.status.failCurrentRound();
                break;
            case "reject":
                this.status.rejectedByCoordinator();
                break;
            case "accept":
                this.status.acceptedToJob();
            default:
                this.status.proceedToNextRound(statusMarker);
                break;
        }
    }

    public void addInterview(Interview interview) {
        status.addInterview(interview);
    }

    public ArrayList<Interview> getScheduledInterviews() {
        return status.getInterviews();
    }

    public Date getSubmissionDate() {
        return this.submissionDate;
    }

    public InterviewRound getInterviewRound() {
        return this.status.getCurrentInterviewRound();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public boolean submissionIsActive() {
        return status.submissionIsActive();
    }
}
