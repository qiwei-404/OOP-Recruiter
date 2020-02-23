package Jobs;

import java.util.ArrayList;

public class CheckRequirements {
    ArrayList<String> appRequirements;
    String checkedStatus="";

    /**
     * Construct a new CheckRequirements object and
     * instantiate appRequirements
     */
    CheckRequirements(ArrayList<String> allRequirements) {
        this.appRequirements = allRequirements;
    }


    public String getCheckedStatus() {
        return this.checkedStatus;
    }

    void setCheckedStatus(String message) {
        checkedStatus = message;
    }

}
