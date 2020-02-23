package Jobs;

import Jobs.Interviews.Interview;
import Jobs.Submissions.Submission;
import StartUp.ManagingSystem;
import Users.Applicant;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a Job Posting within the Managing System. It is identified by the name. It keeps track of all the
 * requirements, as well as the current applicants, the applicants that have been rejected, all the submissions, and all
 * the interviews.
 */
public class JobPosting {
    private String name;
    private String description;
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<Applicant> rejected = new ArrayList<>();
    private ArrayList<Submission> allSubmissions = new ArrayList<>();
    private ManagingSystem managingSystem;
    private Company company;
    private Date datePosted;
    private ArrayList<String> interviewTypesPerRound;
    private TagInterpreter tagInterpreter = new TagInterpreter(",");
    private ArrayList<String> documentRequirements;
    private String branch = "";
    private PostingActivation postingActivation;
    private ArrayList<Interview> groupInterviews = new ArrayList<>();

    /**
     * Each instance of JobPosting should only have one branch. If multiple branches share the same JobPosting, it creates
     * identical instances of JobPosting with different value of branch.
     * TODO: add text input field that takes in tags (, separated)
     */
    public JobPosting(String name, Date datePosted, Date dateCloses, int vacancies, ArrayList<String> documentRequirements,
                      ArrayList<String> interviewTypesPerRound, Company company, ManagingSystem overlord) {
        this.name = name;
        this.documentRequirements = documentRequirements;
        this.interviewTypesPerRound = interviewTypesPerRound;
        this.company = company;
        this.datePosted = datePosted;
        this.managingSystem = overlord;
        this.managingSystem.addJobPosting(this);
        this.postingActivation = new PostingActivation(dateCloses, datePosted, vacancies);
        company.addJobPosting(this);
    }

    /**
    returns a copy of this JobPosting (to be called in the GUI to generate multiple copies of on jobPosting at different branches)
     */
    public JobPosting getCopy() {
        return new JobPosting(name, postingActivation.getDatePosted(), postingActivation.getDateCloses(),
                postingActivation.getNumOfVacancies(), documentRequirements, interviewTypesPerRound, company, managingSystem);
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public String getName() {return this.name; }
    public String getDescription() {
        return description;
    }

    public String getBranch() {
        return branch;
    }

    /**
     * This is called in CreateJobPostingGUI. It should take a String each time a branch is added in the for loop.
     * @param branch the branch this JobPosting belongs to
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }


    public ArrayList<String> getInterviewTypesPerRound() {
        return interviewTypesPerRound;
    }

    TagInterpreter getTagInterpreter() { return this.tagInterpreter; }

    public void updateTagInterpreter(String jobTagsInput) { this.tagInterpreter.setTagsFromString(jobTagsInput); }

    public ArrayList<String> getDocumentRequirements() {
        return this.documentRequirements;
    }

    private void addApplicant(Applicant applicant) {
        this.applicants.add(applicant);
    }

    /**
     * @return a list of all applicants
     */
    public ArrayList<Applicant> getApplicants() {
        return this.applicants;
    }

    /**
     * removes applicant from remaining list of eligible applicants
     * @param applicant Applicant to be removed
     */
    public void removeApplicant(Applicant applicant) {
        this.applicants.remove(applicant);
    }


    public void addRejected(Applicant applicant){
        //adds applicant to rejected
        if (!this.rejected.contains(applicant)){
            this.rejected.add(applicant);
            for (Submission submission: applicant.getAllSubmissions().getListSub()) {
                if (submission.getJobPosting() == this) {
                    submission.updateStatus("reject");
                }
            }
        }
    }

    public ArrayList<Submission> getAllSubmissions() {
        return allSubmissions;
    }

    public boolean addSubmission(Submission submission) {
        if (!this.allSubmissions.contains(submission)) {
            this.allSubmissions.add(submission);
            this.addApplicant(submission.getApplicant());
            return true;
        }
        return false;
    }

    public ManagingSystem getManagingSystem() {
        return this.managingSystem;
    }

    public void setManagingSystem(ManagingSystem managingSystem) {
        this.managingSystem = managingSystem;
    }



    @Override
    public String toString() {
        String string = this.name + " posted by " + this.company + " at " + this.branch + " on " + this.datePosted;
        return string;
    }

    public PostingActivation getPostingActivation() {
        return this.postingActivation;
    }

    /**
     * Method to get all group interviews related to this JobPosting
     * called in matchInterviews
     * @return
     */
    public ArrayList<Interview> getGroupInterviews() {
        return groupInterviews;
    }

    public void addGroupInterview(Interview interview) {
        groupInterviews.add(interview);
    }
}


