package Helpers;

import java.util.ArrayList;

public class ListToListOfStrings<T> {

    public ListToListOfStrings() {}

    public ArrayList<String> convert(ArrayList<T> list) {
        ArrayList<String> listOfStrings = new ArrayList<>();
        for (T t: list) {
            listOfStrings.add(t.toString());
        }
        return listOfStrings;
    }
}
