package Jobs;
import Helpers.CaseInsensitiveMatch;
import Jobs.Interviews.Interview;

import java.util.ArrayList;

public class CheckInterviewRequirements extends CheckRequirements {

    public CheckInterviewRequirements(ArrayList<String> allRequirements) {
        super(allRequirements);
    }

    /**
     * Precondition: the InterviewsCompleted has the matching type with CheckInterviewRequirements.
     */
    public boolean checkInterviewRequirements(ArrayList<Interview> interviewsCompleted, String interviewTypeToDo) {
        int indexToCheck = interviewsCompleted.size();
        if (indexToCheck < appRequirements.size()) {
            CaseInsensitiveMatch caseInsensitiveMatch = new CaseInsensitiveMatch();
            if (caseInsensitiveMatch.isPatternMatching(interviewTypeToDo, appRequirements.get(indexToCheck))) {
                return true;
            }
            else {
                setCheckedStatus("The interview type you entered does not match the interview type of this round.");
            }
        }
        else {
            setCheckedStatus("You have reached the maximum number of interviews. " +
                    "We cannot schedule you for more interviews.");
        }
        return false;
    }


}
