package Users;

import Helpers.CheckAllSubmissionsActive;
import Helpers.GenerateListToReject;
import Jobs.CheckInterviewRequirements;
import Jobs.Company;
import Jobs.Interviews.Interview;
import Jobs.JobPosting;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;

import java.util.ArrayList;
import java.util.Date;

public class Coordinator extends HR {

    /**
     * Default Constructor
     */
    public Coordinator(String username, String password, Company company,
                       ManagingSystem managingSystem) {
        super(username, password, company, managingSystem);
        this.userType = "Users.CoordinatorGUI";
    }



    /**
     * Allows Coordinator to accept applicants, regardless of the Interviews they completed.
     * Check the number of accepted is smaller than the number of vacancies, and all submissions are active.
     * @param jobPosting The Job Posting to get the list of Applicants
     */
    public String nominee(JobPosting jobPosting, ArrayList<Submission> submissionsToAccept) {
        //successfulApplicant comes from GUI
        if (submissionsToAccept.size() <= jobPosting.getPostingActivation().getNumOfVacancies() -
                jobPosting.getPostingActivation().getNumOfAccepted()) {
            CheckAllSubmissionsActive checkAllSubmissionsActive = new CheckAllSubmissionsActive(submissionsToAccept);
            if (checkAllSubmissionsActive.checkActive()) {
                for (Submission submission : submissionsToAccept) {
                    submission.updateStatus("accept");
                    jobPosting.getPostingActivation().addAccepted();
                }
                if (jobPosting.getPostingActivation().isFilled()) {
                    GenerateListToReject generateListToReject = new GenerateListToReject();
                    ArrayList<Submission> toReject = generateListToReject.listToReject(jobPosting.getAllSubmissions(),
                            submissionsToAccept);
                    for (Submission submission : toReject) {
                        submission.updateStatus("reject");
                    }
                }
                return "You have successfully accepted the selected applicants.";

            }
            return "Some submissions are no longer active. Please select again.";



        }
        return "You cannot accept more applicants than the number of vacancies";
    }





    /**
     * This method checks if the input interview type is correct for the appropriate round or the maximum number of
     * interviews has been reached.

     */
    public String matchInterviews(ArrayList<Interviewer> interviewers,
                                ArrayList<Submission> submissions, Date date, String interviewType) {


        boolean interviewTypeCorrect = true;
        CheckAllSubmissionsActive checkAllSubmissionsActive = new CheckAllSubmissionsActive(submissions);
        if (checkAllSubmissionsActive.checkActive()) {
            for (Submission submission : submissions) {
                CheckInterviewRequirements checkInterviewRequirements = new CheckInterviewRequirements(submission.getJobPosting().
                        getInterviewTypesPerRound());
                if (!checkInterviewRequirements.checkInterviewRequirements(submission.getScheduledInterviews(), interviewType)) {
                    interviewTypeCorrect = false;

                    System.out.println(checkInterviewRequirements.checkInterviewRequirements(submission.getScheduledInterviews(), interviewType));
                }
            }
            if (interviewTypeCorrect) {
                new Interview(interviewers, interviewType, date, submissions);
                return "You have successfully set up an interview.";
            }
            return "The interview type you entered is not correct or the maximum number of interviews has been reached";
        }
        return "Some submissions are no longer active. Please select again.";
    }






}