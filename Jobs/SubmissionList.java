package Jobs;

import Helpers.SearchListHelper;
import Jobs.Submissions.Submission;

import java.util.ArrayList;

public class SubmissionList {

    private ArrayList<Submission> listSub;


    public SubmissionList(){
        listSub = new ArrayList<>();
    }





    public ArrayList<Submission> getListSub() {
        return listSub;
    }




    /**
     * Allows applicant to remove any of their submissions, based on the name
     * This method will erase every stored submission that has same name
     * Precondition: sub is in listSub
     * @param name: the name of Submission
     */
    public void withdrawSubmission(String name) {
        Submission sub = this.viewSubmission(name);
        sub.updateStatus("withdraw");
    }


    /**
     * Allows applicant to get a submission from the name
     * found should always be true!
     * @param name A String that represents the name of the wanted submission
     * @return A Jobs.Submission that's name matches the input String
     */
    private Submission viewSubmission(String name) {
        SearchListHelper<Submission> searchListHelper = new SearchListHelper<>();
        searchListHelper.search(name, listSub);
        return searchListHelper.getObjectFound();
    }


    /**
     * Checks to see if a submission has been successfully added to a job posting
     * @param posting The job posting to check
     * @return A boolean of the status of the application.
     */
    public boolean checkSubmitted(JobPosting posting) {
        boolean submitted = false;
        for (int i = 0; i < this.getListSub().size(); i++) {
            submitted = (submitted | this.getListSub().get(i).getJobPosting().equals(posting));
        }
        return submitted;
    }





}
