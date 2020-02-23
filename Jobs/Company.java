package Jobs;

import StartUp.ManagingSystem;

import java.util.ArrayList;

/**
 * A object to represent company's within the Managing System. They have a String attribute for their name, an attribute
 * for which managing system it is part of, and a list of all the Job postings for that company.
 */
public class Company {
    private String companyName;
    private ManagingSystem currentMS;
    private ArrayList<JobPosting> allJobPostings = new ArrayList<>();
    private ArrayList<String> branches = new ArrayList<>();

    public Company(String name, ManagingSystem overlord) {
        this.setCompanyName(name);
        this.setCurrentMS(overlord);
        this.getCurrentMS().addCompany(this);
    }

    public ArrayList<String> getBranches() {
        return branches;
    }

    public void addBranch(String branch) {
        this.branches.add(branch);
    }

    public String getCompanyName() {
        return companyName;
    }

    private void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ManagingSystem getCurrentMS() {
        return currentMS;
    }

    public void setCurrentMS(ManagingSystem currentMS) {
        this.currentMS = currentMS;
    }

    public ArrayList<JobPosting> getAllJobPostings() {
        return allJobPostings;
    }

    void addJobPosting(JobPosting posting) {
        this.allJobPostings.add(posting);
    }

    @Override
    public String toString() {
        return this.getCompanyName();
    }
}
