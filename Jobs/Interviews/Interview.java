package Jobs.Interviews;

import Jobs.JobPosting;
import Jobs.Submissions.Submission;
import Users.Interviewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents an Jobs.Interview that has been made by the Users.CoordinatorGUI, assigning an Users.ApplicantGUI with an Users.InterviewerGUI. It also
 * specifies the type of interview, phone verse in-person, the date of the interview and the job posting it is for. The
 * name of the Jobs.Interview is given by a concatenation of the Users.ApplicantGUI name, Job Posting name and the date.
 */
public class Interview {
    private Date dateOfInterview;
    /**
     * A String of Applicants' names
     * Useful in toString()
     */
    private String applicantNames="";
    /**
     * interviewersRecruited is useful to get a collection of Interviewer names (without duplicates) for toString()
     */
    private ArrayList<Interviewer> interviewersRecruited = new ArrayList<>();
    private ArrayList<InterviewPerCandidate> interviewsPerCandidate=new ArrayList<>();

    /**
     * Precondition: interviewType is the correct interview type for this round.
     */
    public Interview(ArrayList<Interviewer> interviewers,
                     String interviewType, Date dateOfInterview, ArrayList<Submission> submissions) {
        this.dateOfInterview = dateOfInterview;
        for (Submission submission: submissions) {
            addCandidate(submission, interviewers, interviewType);
        }
        storeInterviewInPosting(this);
    }


    /**
     * private helper: store group interviews in JobPosting
     * @param interview
     */
    private void storeInterviewInPosting(Interview interview) {
        JobPosting jobPosting = interview.getJobPosting();
        if (interview.getInterviewType().equals("group interview")) {
            if (!jobPosting.getGroupInterviews().contains(interview)) {
                jobPosting.addGroupInterview(interview);
            }
        }

    }

    /**
     * Helper method
     * Updates applicantNames
     * Directly called only in addCandidate()
     * @param interviewPerCandidate
     */
    private void addApplicantNames(InterviewPerCandidate interviewPerCandidate) {
        this.applicantNames += interviewPerCandidate.getApplicant().toString();
    }

    /**
     * Helper method
     * Updates interviewersRecruited
     * Directly called only in addCandidate().
     */
    private void recruitInterviewers(InterviewPerCandidate interviewPerCandidate) {
        this.interviewersRecruited.addAll(interviewPerCandidate.getInterviewers());
    }

    /**
     * This method gets called in two occasions: 1) The constructor of Interview. 2. A new applicant or interviewers are
     * added to an existing interview (precondition: this interview is a group interview or a take home test)
     * @param submission
     * @param interviewers
     * @param interviewType
     */
    private void addCandidate(Submission submission, ArrayList<Interviewer> interviewers, String interviewType) {
        InterviewPerCandidate interviewPerCandidate = new InterviewPerCandidate(submission, interviewers, interviewType);
        this.interviewsPerCandidate.add(interviewPerCandidate);
        addApplicantNames(interviewPerCandidate);
        recruitInterviewers(interviewPerCandidate);
        submission.addInterview(this);
        submission.getApplicant().getAllInterviews().add(this);
        for (Interviewer interviewer: interviewers) {
            interviewer.addInterview(this);
        }
    }

    public ArrayList<InterviewPerCandidate> getInterviewsPerCandidate() {
        return this.interviewsPerCandidate;
    }





    private String getInterviewType() {
        return interviewsPerCandidate.get(0).getSubmission().getInterviewRound().getInterviewType();
    }



    public JobPosting getJobPosting() {
        return interviewsPerCandidate.get(0).getSubmission().getJobPosting();
    }



    /**
     * Convert an ArrayList of Interviewer to a SINGLE String.
     * Useful in toString()
     * @param interviewers
     * @return
     */
    private String interviewersToString(ArrayList<Interviewer> interviewers) {
        String interviewersString = "";
        for (Interviewer interviewer: interviewers) {
            interviewersString += interviewer.toString() + ", ";
        }
        return interviewersString;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        return "Interview on " + df.format(dateOfInterview) + " for candidate(s) " + applicantNames +
                " with interviewer(s) " + interviewersToString(interviewersRecruited);
    }


}
