package Jobs;

import java.util.ArrayList;

/**
 * The JobPostingFilter class serves as a container for:
 * (1) inputted tags that the user may search for in ViewPostingGUI.
 * (2) the JobPosting objects whose TagInterpreter.getTags()
 * value contains all the inputted tags.
 * This allows the user to filter through job postings.
 */
public class JobPostingFilter {
    private TagInterpreter searchedTags;
    private ArrayList<JobPosting> matchingPostings;

    public JobPostingFilter() {
        this.setVariableValues();
    }

    /**
     * Set new values for all private variables
     */
    private void setVariableValues() {
        this.searchedTags = new TagInterpreter(",");
        this.matchingPostings = new ArrayList<>();
    }

    /**
     * Reset values of private variables
     */
    public void reset() {
        this.setVariableValues();
    }




    /**
     * @param jobTagsInput A String object to be interpreted by TagInterpreter this.searchedTags
     */
    public void updateTagInterpreter(String jobTagsInput) { this.searchedTags.setTagsFromString(jobTagsInput); }

    /**
     * Adds a given JobPosting object to this.matchingPostings iff
     * its TagInterpreter.getTags() value is a subset of this.searchedTags
     * @param jobPosting The JobPosting object to check
     */
    private void matchPosting(JobPosting jobPosting) {
        if (jobPosting.getTagInterpreter().getTags().containsAll(this.searchedTags.getTags())) {
            this.matchingPostings.add(jobPosting);
        }
    }

    /**
     * Given an ArrayList of JobPostings, iterate over each
     * JobPosting and add it to this.matchingPostings iff
     * its TagInterpreter.getTags() value is a subset of this.searchedTags
     * @param jobPostings An ArrayList of JobPosting objects to check
     */
    public void matchPostingList(ArrayList<JobPosting> jobPostings) {
        for (JobPosting jobPosting : jobPostings) {
            this.matchPosting(jobPosting);
        }
    }

    public ArrayList<JobPosting> getMatchingPostings() {
        return this.matchingPostings;
    }

    /**
     * @return Return a String containing the names of all matching
     * postings, where each name is separated by a new line
     */
    public String getMatchingPostingsNames() {
        String matchingPostingsNames = "";
        int numbering = 1;
        if (!this.matchingPostings.isEmpty()) {
            for (JobPosting matchingPosting : this.matchingPostings) {
                if (!matchingPosting.getPostingActivation().isClosed()) {
                    matchingPostingsNames += numbering + ". " + matchingPosting.getName() + " \n ";
                    numbering++;
                }
            }
        }
        else {
            return "";
        }
        return matchingPostingsNames;
    }

}