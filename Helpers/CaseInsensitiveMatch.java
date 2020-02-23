package Helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaseInsensitiveMatch {

    public boolean isPatternMatching(String patternLine, String lineToMatch){

        Pattern ptn = Pattern.compile(patternLine, Pattern.CASE_INSENSITIVE);
        Matcher mtch = ptn.matcher(lineToMatch);
        if (!patternLine.isEmpty() && !lineToMatch.isEmpty()) {
            if(mtch.find()){
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> transformToUppercase(ArrayList<String> listToTransform) {
        ArrayList<String> allUppercase = new ArrayList<>();
        for (String element: listToTransform) {
            allUppercase.add(element.toUpperCase());
        }
        return allUppercase;
    }
}
