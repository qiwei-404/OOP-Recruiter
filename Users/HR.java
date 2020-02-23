package Users;

import Helpers.CaseInsensitiveMatch;
import Jobs.Company;
import StartUp.ManagingSystem;

import java.util.ArrayList;


/**
 * Represents a Users.User that is specified as being part of Human Resources. This is an abstract class where HR must
 * be either an Interviewer or a Coordinator. They are identified by their username and password combination, which is
 * created when they sign up and is used for log in. They are associated with only one Jobs.Company within one
 * managingSystem.
 */
public abstract class HR extends User {

    private Company company;
    private ArrayList<String> branches;

    /**
     * Creates a new Users.HR user using their username, password, company and managingSystem.
     * @param username A string given during sign up.
     * @param password A string given during sign up.
     * @param company A Jobs.Company object that is assigned to the Users.HR user during sign up based on which company
     *                the user is associated with.
     * @param currentMS A ManagingSystem object which the Users.HR user is created within.
     */
    public HR(String username, String password, Company company,
              ManagingSystem currentMS) {
        super(username, password, currentMS);
        this.company = company;
        this.branches = new ArrayList<>();
    }

    /**
     * Sets the company for the HR User
     * @param company A Company that the HR User is part of
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Returns the Company for the HR User
     * @return A Company that the HR User is part of
     */
    public Company getCompany() {
        return this.company;
    }

    /**
     * Returns the branches for the HR User
     * @return Branches ArrayList</String> that the HR User is associated with
     */

    public ArrayList<String> getBranches() {
        return branches;
    }

    /**
     * Sets the branches for the HR User
     * This method checks if the branches already exists in the HR and Company branches.
     * @param branch The branches that the HR User is associated with
     */
    public void addBranch(String branch) {

        if (!checkExistingBranch(branch, this.branches)) {
            this.branches.add(branch);
        }
        if (!checkExistingBranch(branch, this.company.getBranches())) {
            company.addBranch(branch);
        }
    }

    /**
     *checks to see if the entered branch is in the list of existing branches (called in above method)
     * @param branch branch to check for duplicates
     * @param existingBranches list of branches to check from
     */
    private boolean checkExistingBranch(String branch, ArrayList<String> existingBranches) {
        boolean branchExists = false;
        for (String branchName: existingBranches) {
            CaseInsensitiveMatch caseInsensitiveMatch = new CaseInsensitiveMatch();
            branchExists = (branchExists | caseInsensitiveMatch.isPatternMatching(branch, branchName));
        }
        return branchExists;
    }


}
