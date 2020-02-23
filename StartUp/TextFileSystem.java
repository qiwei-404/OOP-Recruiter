package StartUp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A System to keep track of, organize, and create text files.
 */
public class TextFileSystem {
    private ArrayList<String> dataSplitByLine;
    private ArrayList<String[]> dataSplitBySlash;
    private String textFileName;
    private String directoryName;
    private String filePath;

    /**
     * Creates a new text file system
     */
    TextFileSystem() {
        this.setBlankDataVariables();
    }

    private void setBlankDataVariables() {
        this.dataSplitByLine = new ArrayList<>();
        this.dataSplitBySlash = new ArrayList<>();
    }

    /**
     * Returns information about the file
     * @param textFileName A String of the file name
     * @param directoryName A string of the directory name that the file is in
     * @return An ArrayList of information about the file
     */
    ArrayList<String[]> getFileData(String textFileName, String directoryName) {
        this.setBlankDataVariables();
        this.setFileVariables(textFileName, directoryName);
        this.loadDataSplitByLine();
        this.loadDataSplitBySlash();
        return this.dataSplitBySlash;
    }

    private void setFileVariables(String textFileName, String directoryName) {
        this.textFileName = textFileName;
        this.directoryName = directoryName;
        this.filePath = "phase2/files/" + this.directoryName + "/" + this.textFileName + ".txt";
    }

    private void loadDataSplitByLine() {
        try {
            File fileToLoad = new File(this.filePath);
            Scanner s = new Scanner(fileToLoad);
            while (s.hasNext()) {
                // This loads data on top of previous dataSplitByLine
                this.dataSplitByLine.add(s.next());
            }
            s.close();
        }
        catch(FileNotFoundException e) {
            String errorMessage = "File " + this.textFileName +
                    ".txt not found under directory " + this.directoryName;
            System.out.println(errorMessage);
        }
    }

    private void loadDataSplitBySlash() {
        //Split each String in this.dataSplitByLine into
        //individual String arrays with a slash ("/") as the regex
        // TODO: This loads duplicate date from previous dataSplitByLine. Might Affect log in
        // could use foreach?
        for (int i = 0; i < this.dataSplitByLine.size(); i++) {
            String[] individualSplitBySlash = this.dataSplitByLine.get(i).split("/");
            this.dataSplitBySlash.add(individualSplitBySlash);
        }
    }

    void updateFileData(String textFileName, String directoryName, String additionalData) {
        this.setFileVariables(textFileName, directoryName);
        try {
            FileWriter fileFW = new FileWriter(new File(this.filePath), true);
            BufferedWriter fileBW = new BufferedWriter(fileFW);
            PrintWriter fileWriter = new PrintWriter(fileBW);

            fileWriter.println("\n" + additionalData);
            fileWriter.close();
        }
        catch(IOException ioe) {
            System.out.println("Exception occurred when updating file.");
        }
    }

    /**createNewTextFile() method
    //can be used to create new HRCredential.txt files in new company directories**/
    void createNewTextFile(String textFileName, String directoryName) {
        String newTextFilePath = "phase2/files/" + directoryName + "/" + textFileName + ".txt";
        File fileToCheck = new File(newTextFilePath);
        if (!fileToCheck.exists()) {
            try {
                boolean result = fileToCheck.createNewFile();
                if(result) {
                    System.out.println("File creation successful");
                }
                else {
                    System.out.println("File creation NOT successful");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Exception occurred while creating new text file");
            }
        }
    }

    public boolean stringInTextFile(String textFileName, String directoryName,
                                    String stringToCheck) {
        return this.stringInTextFile(textFileName, directoryName, stringToCheck, -1);
    }

    // the next two methods have duplicate code, maybe move that to a helper function?
    private boolean stringInTextFile(String textFileName, String directoryName,
                                        String stringToCheck, int index) {
        String newTextFilePath = "phase2/files/" + directoryName + "/" + textFileName + ".txt";
        File fileToCheck = new File(newTextFilePath);
        try {
            Scanner scanner = new Scanner(fileToCheck);
            while (scanner.hasNextLine()) {
                String lineFromTextFile = scanner.nextLine();
                if (index > -1) {
                    String[] lineSplitBySlash = lineFromTextFile.split("/");
                    if (index <= lineSplitBySlash.length - 1) {
                        if (lineSplitBySlash[index].equals(stringToCheck)) {
                            return true;
                        }
                    }
                    else {
                        System.out.println("Given index " + index + " is out of bounds.");
                    }
                }
                else {
                    if (lineFromTextFile.contains(stringToCheck)) {
                        return true;
                    }
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(textFileName + ".txt was not found in directory " + directoryName);
        }
        return false;
    }

    private boolean stringInTextFile(String directoryName, String stringToCheck, int index) {
        String newTextFilePath = "phase2/files/" + directoryName;
        File fileToCheck = new File(newTextFilePath);
        if (fileToCheck.isFile()) {
            try {
                Scanner scanner = new Scanner(fileToCheck);
                while (scanner.hasNextLine()) {
                    String lineFromTextFile = scanner.nextLine();
                    if (index > -1) {
                        String[] lineSplitBySlash = lineFromTextFile.split("/");
                        if (index <= lineSplitBySlash.length - 1) {
                            if (lineSplitBySlash[index].equals(stringToCheck)) {
                                return true;
                            }
                        } else {
                            System.out.println("Given index " + index + " is out of bounds.");
                        }
                    } else {
                        if (lineFromTextFile.contains(stringToCheck)) {
                            return true;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(textFileName + ".txt was not found in directory " + directoryName);
            }
            return false;
        }
        else {
            File[] listOfFiles = fileToCheck.listFiles();
            for (File subFile: listOfFiles) {
                boolean stringExists = stringInTextFile(directoryName + "/" + subFile.getName(), stringToCheck, index);
                if (stringExists) {
                    return true;
                }
            }
            return false;
        }
    }

    boolean stringInTextFile(String stringToCheck, int index) {
        return stringInTextFile("", stringToCheck, index);
    }
}