package Jobs;

import Jobs.Submissions.Submission;
import Users.Applicant;
import Users.Referee;
import Users.User;

import java.util.ArrayList;

public class Document {
    /**
     * Users.ApplicantGUI is a Users.User who applies for the jobs that Human resources user posts.
     * Users.ApplicantGUI is initialized with ID password.
     * Run only with one managing system (current Managing System "Overlord")
     */
    private String numbering;
    private String content;
    private String type;
    private User usedBy;
    private ArrayList<Submission> allSubmissions = new ArrayList<Submission>();

    public Document(String num, String content, Applicant used) {
        // Initialize needed information
        this.setNumbering(num);
        this.setContent(content);
        this.setUsedBy(used);
    }

    public Document(String num, String content, Referee used) {
        // Initialize needed information
        this.setNumbering(num);
        this.setContent(content);
        this.setUsedBy(used);
    }

    public String getNumbering() {
        return numbering;
    }
    private void setNumbering(String numbering) {
        this.numbering = numbering;
    }
    public String getContent() {
        return content;
    }
    private void setContent(String content) {
        this.content = content;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    private void setUsedBy(User usedBy) {
        this.usedBy = usedBy;
    }
    public ArrayList<Submission> getAllSubmissions() {
        return allSubmissions;
    }


    public void submittedTo(Submission sub) {
        if (!this.allSubmissions.contains(sub)) {
            this.allSubmissions.add(sub);
        }
    }

    /**
     * A method that prints the type of Document. Helpful in CheckDocumentRequirements
     * @return
     */
    @Override
    public String toString() {
        return this.getType();
    }
}