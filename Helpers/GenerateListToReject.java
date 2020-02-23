package Helpers;

import Jobs.Submissions.Submission;

import java.util.ArrayList;

public class GenerateListToReject {


    /**
     * get the Submissions that are not accepted and add them to a separate list.
     * @param wholeList
     * @param listToAccept
     * @return
     */
    public ArrayList<Submission> listToReject(ArrayList<Submission> wholeList, ArrayList<Submission> listToAccept) {
        ArrayList<Submission> toReject = (ArrayList<Submission>) wholeList.clone();
        toReject.removeAll(listToAccept);
        return toReject;
    }
}
