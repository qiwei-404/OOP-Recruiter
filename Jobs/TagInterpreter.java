package Jobs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * In the context of Phase 2, the TagInterpreter class is meant to store the tags of:
 * (1) a JobPosting when it is first created by a Coordinator
 * (2) a JobPostingFilter when a user inputs tags while viewing current job postings
 */
public class TagInterpreter {
    private ArrayList<String> tags;
    private String regex;

    TagInterpreter(String regex) {
        this.tags = new ArrayList<>();
        this.regex = regex;
    }

    /**
     * @return ArrayList this.tags
     */
    ArrayList<String> getTags() {
        return this.tags;
    }





    /**
     * Given a string, split it around matches of this.regex
     * and store the resulting array in this.tags
     * @param string The String object to split
     */
    void setTagsFromString(String string) {
        String[] splitString = string.split(this.regex);
        this.tags.addAll(Arrays.asList(splitString));
    }


}