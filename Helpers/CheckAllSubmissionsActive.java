package Helpers;

import Jobs.Submissions.Submission;

import java.util.ArrayList;

public class CheckAllSubmissionsActive {
    private ArrayList<Submission> submissions;

    public CheckAllSubmissionsActive(ArrayList<Submission> submissions) {
        this.submissions = submissions;
    }

    public boolean checkActive() {
        for (Submission submission: submissions) {
            if (!submission.submissionIsActive()) {
                return false;
            }
        }
        return true;
    }

}
