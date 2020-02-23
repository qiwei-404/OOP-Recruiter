package Jobs;

import java.util.Date;

/**
 * Keeps track of job postings and decides is its open or closed
 */
public class PostingActivation {
    private Date datePosted;
    private Date dateCloses;
    private Date dateFilled;
    private int numOfVacancies;
    private int numOfAccepted = 0;
    private boolean postingFilled = false;
    private boolean postingClosed = false;


    PostingActivation(Date closingDate, Date datePosted, int vacancies){
        this.dateCloses = closingDate;
        this.numOfVacancies = vacancies;
        this.datePosted = datePosted;
    }

    public Date getDatePosted(){
        return datePosted;
    }



    public Date getDateCloses(){
        return dateCloses;
    }



    public int getNumOfVacancies(){
        return numOfVacancies;
    }



    public int getNumOfAccepted(){
        return numOfAccepted;
    }

    public void addAccepted(){
        this.numOfAccepted++;
        if (numOfAccepted == numOfVacancies){
            fillPosting();

        }
    }

    public void fillPosting(){
        this.postingFilled = true;


    }

    public boolean isFilled() {
        return this.postingFilled;
    }

    public boolean isClosed(){
        return postingClosed;

    }

    public void closePosting(){
        this.postingClosed = true;

    }

    public boolean isActive(){
        return !postingClosed && !postingFilled;
    }
}
