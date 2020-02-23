package Jobs.Submissions;

import Jobs.Interviews.Interview;
import Jobs.Interviews.InterviewRound;

import java.util.ArrayList;

/**
 * Keeps track of status of the Submission
 */
public class SubmissionStatus {

    private String status = "Your application is submitted and under review.";
    private InterviewRound currentInterviewRound = new InterviewRound();
    private ArrayList<Interview> interviews = new ArrayList<>();
    private boolean isActive=true;

    SubmissionStatus() {}

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    InterviewRound getCurrentInterviewRound() {
        return this.currentInterviewRound;
    }

    void proceedToNextRound(String interviewType) {
        this.currentInterviewRound.proceedToNextRound(interviewType);
        this.status = "Round " + Integer.toString(currentInterviewRound.getRoundNumber()) + ": " +
                currentInterviewRound.getInterviewType() + " is scheduled.";
    }

    void clearCurrentRound() {
        this.status = "Round " + Integer.toString(currentInterviewRound.getRoundNumber()) + ": " +
                currentInterviewRound.getInterviewType() + " is successful.";
    }

    void withdraw() {
        this.status = "We are sorry that you withdrew your application. We always welcome you to apply again.";
        isActive = false;
    }

    void failCurrentRound() {
        this.status = "Unfortunately you did not make it through round " + Integer.toString(currentInterviewRound.getRoundNumber()) + ": " +
                currentInterviewRound.getInterviewType() + ". We welcome you to apply again in the future.";
        isActive = false;
    }

    void rejectedByCoordinator() {
        this.status = "Unfortunately the pool is very competitive, " +
                "so we cannot give you the job. We welcome you to apply in the future.";
        isActive = false;
    }

    void acceptedToJob() {
        this.status = "Congratulations! You got the job!";
    }

    void addInterview(Interview interview) {
        interviews.add(interview);
    }

    public ArrayList<Interview> getInterviews() {
        return this.interviews;
    }

    boolean submissionIsActive() {
        return isActive;
    }


}
