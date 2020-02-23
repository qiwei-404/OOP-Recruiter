package Helpers;

import java.util.ArrayList;

public class SearchListHelper<T> {
    private T objectFound=null;

    public SearchListHelper() {}

    public boolean search(String keyword, ArrayList<T> list) {
        for (T t: list) {
            if (t.toString().equals(keyword)) {
                objectFound = t;
                return true;
            }
        }
        return false;
    }

    /**
     * Usage: only call this method if search() returns true!
     */
    public T getObjectFound() {
        return objectFound;
    }
}
