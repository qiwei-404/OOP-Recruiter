package StartUp;

import java.io.File;
import java.util.ArrayList;

/**
 * A System to keep track of, organize, and create Directories.
 */
class DirectorySystem {
    private ArrayList<String> directoryNames;

    DirectorySystem() {
        this.directoryNames = new ArrayList<>();
    }

    ArrayList<String> getDirectoryNames() {
        // No such instance field: credentialFileToCheck
        this.loadDirectoryNames();
        return this.directoryNames;
    }

    private void loadDirectoryNames() {
        // No such instance field: credentialFileToCheck
        File mainDirectory = new File("phase2/files");
        File[] listOfFiles = mainDirectory.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isDirectory()) {
                    this.directoryNames.add(file.getName());
                }
            }
        }
    }

    /**
     * Create a new directory in src/files if and only if a directory titled newDirectoryName does not already exist
     * @param newDirectoryName the name for the new directory
     */
    void createNewDirectory(String newDirectoryName) {
        File newDirectory = new File("phase2/files/" + newDirectoryName);
        if (!newDirectory.exists()) {
            newDirectory.mkdirs();
        }
    }

}