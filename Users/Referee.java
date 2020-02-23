package Users;

import Jobs.Document;
import StartUp.ManagingSystem;

import java.util.ArrayList;

/**
 * Represents a Referee, which is a User that can add references to a chosen Applicant. They are identified by their
 * username, password, and phone number. They keep track of all their reference Documents and chosen Applicants.
 */
public class Referee extends User {

    private ArrayList<Document> references = new ArrayList<>();
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private String phoneNumber;

    /**
     * Creates a new Referee with a given username, password and phone number, within a given ManagingSystem.
     * @param username A string given during sign up.
     * @param password A string given during sign up.
     * @param phoneNumber A string given during sign up.
     * @param currentMS A ManagingSystem object which the Users.HR user is created within.
     */
    public Referee(String username, String password, String phoneNumber, ManagingSystem currentMS) {
        super(username, password, currentMS);
        this.userType = "Users.Referee";
        this.phoneNumber = phoneNumber;
        this.currentMS.addReferees(this);
    }

    /**
     * Returns the list of references that the Referee has made.
     * @return An ArrayList of Documents that are the reference Documents for the Referee.
     */
    public ArrayList<Document> getReferences() {
        return references;
    }



    /**
     * Only called in submitReference()
     * Returns the list of Applicants that the have requested references from the Referee.
     * @return An ArrayList of Applicants that have been added to the Referee.
     */
    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public void addReference(Document reference) {
        this.getReferences().add(reference);
    }

    /**
     * Only called in submitReference()
     * Adds a given Applicant to the Referee's list of Applicants
     * @param applicant the Applicant to be added.
     */
    public void addApplicant(Applicant applicant) {
        this.getApplicants().add(applicant);
    }

    /**
     * Submit the reference to the Applicant.docs
     * @param reference
     * @param applicant
     */
    public void submitReference(Document reference, Applicant applicant) {
        addReference(reference);
        addApplicant(applicant);
        applicant.addDoc(reference);
    }
}
