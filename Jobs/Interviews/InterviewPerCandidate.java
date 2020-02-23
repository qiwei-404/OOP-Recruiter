package Jobs.Interviews;

import Jobs.Submissions.Submission;
import Users.Applicant;
import Users.Interviewer;

import java.util.ArrayList;

public class InterviewPerCandidate {
    private String name;
    private Submission submission;
    private ArrayList<Interviewer> interviewers;
    private boolean successful=false;

    /**
     * InterviewPerCandidate constructor should only be directly called in Interview
     * @param submission
     * @param interviewers
     */
    InterviewPerCandidate(Submission submission, ArrayList<Interviewer> interviewers, String interviewType) {
        this.submission = submission;
        submission.updateStatus(interviewType);
        this.interviewers = interviewers;
        this.name = "Interview for " + this.submission.getApplicant().toString() + " with ";
        for (Interviewer interviewer: interviewers) {
            this.name += interviewer.toString();
        }
    }



    public void evaluateCandidate(boolean successful) {
        this.successful = successful;
        if (successful) {
            submission.updateStatus("recommend");

        }
        else {
            submission.updateStatus("failInterview");
            submission.getJobPosting().removeApplicant(submission.getApplicant());
            submission.getJobPosting().addRejected(submission.getApplicant());
        }
    }

    boolean isSuccessful() {
        return this.successful;
    }

    Applicant getApplicant() {
        return submission.getApplicant();
    }

    ArrayList<Interviewer> getInterviewers() {
        return interviewers;
    }

    Submission getSubmission() {
        return submission;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
