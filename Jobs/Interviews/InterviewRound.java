package Jobs.Interviews;

public class InterviewRound {

    private int roundNumber=0;
    private String interviewType="";

    public InterviewRound() {}

    public int getRoundNumber() {
        return roundNumber;
    }



    public String getInterviewType() {
        return interviewType;
    }



    public void proceedToNextRound(String type) {
        roundNumber += 1;
        interviewType = type;
    }


}
