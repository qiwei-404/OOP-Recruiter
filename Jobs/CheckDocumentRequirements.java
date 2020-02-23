package Jobs;
import Helpers.CaseInsensitiveMatch;
import Helpers.ListToListOfStrings;

import java.util.ArrayList;

public class CheckDocumentRequirements extends CheckRequirements {

    public CheckDocumentRequirements(ArrayList<String> allRequirements) {
        super(allRequirements);
    }

    public boolean checkDocumentRequirements(ArrayList<Document> documents) {
        if (documents.size() == this.appRequirements.size()) {
            ListToListOfStrings listToListOfStrings = new ListToListOfStrings();
            ArrayList<String> docsToSubmit = listToListOfStrings.convert(documents);
            CaseInsensitiveMatch caseInsensitiveMatch = new CaseInsensitiveMatch();
            ArrayList<String> docsToSubmitUppercase = caseInsensitiveMatch.transformToUppercase(docsToSubmit);
            ArrayList<String> requirementsUppercase = caseInsensitiveMatch.transformToUppercase(this.appRequirements);
            if (requirementsUppercase.containsAll(docsToSubmitUppercase)) {
                return true;
            }
            else {
                setCheckedStatus("The document types you selected are wrong.");
                return false;
            }

        }
        else if (documents.size() > appRequirements.size()) {
            setCheckedStatus("You have selected too many documents to submit.");
        }
        else {
            setCheckedStatus("You have not selected enough documents to submit.");
        }
        return false;
    }


}
